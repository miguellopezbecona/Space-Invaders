package si;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Random;
import javax.swing.Timer;

import static si.Constants.COLS;
import static si.Constants.INIT_VEL;
import static si.Constants.MISSIL_VEL;
import static si.Constants.ROWS;
import static si.Constants.SEPX;
import static si.Constants.SEPY;
import static si.Constants.TAM;
import static si.Constants.VAR_VEL;
import static si.Constants.W_HEIGHT;
import static si.Constants.W_WIDTH;

/**
 *
 * @author Miguel
 */
public class Controller {
    
    private static List<Figure> paintables;
    private static List<Invader> enemies;
    private static List<Missile> missiles;
    private static Hero hero;
    private static Timer t;
    
    public Controller(){
        paintables = new ArrayList(); 
        enemies = new ArrayList();
        missiles = new ArrayList();
                
        // Creates initial elements
        createEnemies(TAM, 0);
        createHero(W_WIDTH/2, W_HEIGHT-2*TAM);
        
        activateTimers();
    }
    
    public static List<Missile> getMissiles() {
        return missiles;
    }

    public static List<Figure> getPaintables() {
        return paintables;
    }

    public static Hero getHero() {
        return hero;
    }
    
    /**
     * Generates an invader matrix
     * @param x, matrix's X initial position
     * @param y, matrix's Y initial position
     */
    private void createEnemies(int x, int y){
        for(int j=0;j<ROWS;j++){
            for(int i=0;i<COLS;i++){
                Invader m = new Invader("Inv" + j + ".png", x+SEPX*i, y+SEPY*j);
                paintables.add(m);
                enemies.add(m);
            }
        }
    }

    /**
     * @param x, hero's X position
     * @param y, hero's Y position
     */
    private void createHero(int x, int y){
        hero = new Hero(x, y);
        paintables.add(hero);
    }
    
    /**
     * This method is called when an enemy reaches a side limit
     * It makes every enemy go down and switch their movement.
     */
    private void enemiesGoDeeper(){
        for(Invader m : enemies){
            m.goDown();
            m.goDown();
            m.changeMovDirection();
        }
    }
    
    private void activateTimers(){
        // Timer regarding enemies collection
        t = new Timer(INIT_VEL, new ActionListener() {
            Random r = new Random(System.currentTimeMillis());
            
            public synchronized void actionPerformed(ActionEvent e) {
                // Victory condition
                if(enemies.isEmpty())
                    Screen.victory();
                
                // First we check if any enemy ship reached a limit
                // It could be melted in next loop, but it gave complications
                // and it's better this way
                boolean reachedLimit = false;
                for(Invader inv : enemies){
                    reachedLimit = inv.reachsSideLimit();
                    if(reachedLimit)
                        break;
                }
                
                // If so, they will go down and toggle movement
                if(reachedLimit)
                    enemiesGoDeeper();
                
                // Then, they move and check collisions
                for(Invader inv : enemies){
                    inv.move();

                    // Two loss conditions: enemy reaching bottom or enemy touching hero
                    if(inv.do_collide(hero) || inv.reachsBottom()){
                       paintables.remove(hero);
                       Screen.loss();
                    }

                    // 2% of probability of shooting a misile
                    if(r.nextInt(50)==1){
                        Missile mi = new Missile("MissileI.png",inv.getPosX()+TAM, inv.getPosY()+2*TAM, false);
                        paintables.add(mi);
                        missiles.add(mi);
                    }

                }
            }
        });

        t.start();
        
        // Timer regarding missile collection
        new Timer(MISSIL_VEL, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{
                    for(Missile mi : missiles){
                        // Missiles that get out of the screen will be removed
                        if(mi.reachesLimits()){
                            if(mi.isFromHero()) // If it is from the hero...
                                hero.allowShooting(); // it will allow shooting again
                            paintables.remove(mi);
                            missiles.remove(mi);
                            continue;
                        }

                        // Movement
                        mi.move();

                        // Loss condition: hero getting hit by missile
                        // Hero will never be hit by its own missiles
                        if(mi.do_collide(hero)){
                            paintables.remove(hero);
                            paintables.remove(mi);
                            Screen.loss();
                            continue;
                        }

                        // If it is from hero, did it touch any enemy?
                        if(mi.isFromHero())
                            checkEnemyImpact(mi);
                    }
                // I guess this should be done in other way to avoid this...
                } catch (ConcurrentModificationException cme){
                }
            }
        }).start();
    }

    /**
     * Kills an enemy if it was hit by "mi"
     * @param mi Hero's missile
     */
    private void checkEnemyImpact(Missile mi){
        for(Invader ma: enemies) {
            // If the condition is true, both enemy and missile disappear
            if(mi.do_collide(ma)){
                paintables.remove(ma);
                paintables.remove(mi);
                enemies.remove(ma);
                missiles.remove(mi);
                
                // Increases enemy's speed movement and allows to shoot again
                int killed_enemies = ROWS*COLS - enemies.size();
                t.setDelay(INIT_VEL - killed_enemies*VAR_VEL);
                hero.allowShooting();
                
                return;
            }
        }
    }
}
