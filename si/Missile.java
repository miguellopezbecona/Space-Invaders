package si;

import static si.Constants.TAM;
import static si.Constants.W_HEIGHT;

/**
 * This class represents ships' shoots
 */
public class Missile extends Figure {
    // It varies missil's movement and possible targets
    private final boolean fromHero;

    public Missile(String filename, int posX, int posY, boolean fromHero){
        super(filename, posX, posY, TAM, 3*TAM);

        this.fromHero = fromHero;
    }

    public void move(){
        if(fromHero)
            goUp();
        else
            goDown();

    }

    /**
     * @return fromHero value
     */
    public boolean isFromHero(){
        return fromHero;
    }

    public boolean reachesLimits() {
        return posY < TAM || posY >= W_HEIGHT;
    }
}