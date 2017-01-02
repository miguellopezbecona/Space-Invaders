package si;

/**
 * The classic Space Invaders game
 * 
 * @author Miguel López Becoña
 * @version 1.0
 */
public class SpaceInvaders{   
    /**
     * Initializes controller and view classes
     */
    public SpaceInvaders() {
        new Controller();  
        new Screen("Space Invaders");
    }

    public static void main(String[] args) {
        new SpaceInvaders();
    } 
}