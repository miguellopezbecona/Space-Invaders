package si;

/**
 * Esta clase hace referencia a la nave controlable
 */
public class Hero extends Ship{
    private boolean canShoot;
    
    public Hero(int posX, int posY){
        super("Hero.png", posX, posY);
        canShoot = true; // Inicialmente, la nave controlable podrá disparar
    }
    
    public boolean getCanShoot(){
        return canShoot;
    }
    
    public void allowShooting(){
        canShoot = true;
    }
    
    public void forbidShooting(){
        canShoot = false;
    }
}
