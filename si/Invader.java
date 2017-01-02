package si;

import static si.Constants.TAM;
import static si.Constants.W_HEIGHT;
import static si.Constants.W_WIDTH;

/**
 * This class represents enemy ships
 */
public class Invader extends Ship {
    private boolean movesToRight;

    public Invader(String filename, int posX, int posY){
        super(filename, posX, posY);
        movesToRight = true;
    }

    public void move(){
        if(!movesToRight)
            goLeft();
        else
            goRight();
    }

    public void changeMovDirection(){
        movesToRight = !movesToRight;
    }
    
    public boolean reachsSideLimit(){
        return posX<TAM || posX>W_WIDTH-4*TAM;
    }
    
    public boolean reachsBottom(){
        return posY==W_HEIGHT-3*TAM;
    }
}
