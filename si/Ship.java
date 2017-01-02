package si;

import static si.Constants.TAM;

/**
 * This class represents both enemies and the controllable character
 */
public abstract class Ship extends Figure {
    public Ship(String f, int posX, int posY){
        super(f, posX+TAM, posY, 3*TAM, 2*TAM);
    }
}