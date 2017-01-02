package si;

import java.awt.*;

import static si.Constants.STEP;
import static si.Constants.TAM;
import static si.Constants.W_HEIGHT;
import static si.Constants.W_WIDTH;

/**
 * This class represents the basic unit of each graphic element
 */
public abstract class Figure {
    protected int posX;
    protected int posY;
    private final Image image;

    /**
     * Figure constructor
     * 
     * @param filename  Figure's image URL
     * @param posX      Initial X position
     * @param posY      Initial Y position
     * @param width     Figure's width
     * @param height    Figure's width
     */
    public Figure(String filename, int posX, int posY, int width, int height){
        this.posX = posX;
        this.posY = posY;
        
        image = new Image(posX, posY, width, height, filename);
    }

    /**
     * @return Devuelve el campo posX
     */
    public int getPosX() {
        return posX;
    }

    /**
     * @return Devuelve el campo posY
     */
    public int getPosY() {
        return posY;
    }

    public void goLeft() {
        // Checks bounds
        if(posX > 5){
            posX -= STEP;

            image.setPos(posX, posY);
        }
    }

    public void goRight() {
        // Checks bounds
        if(posX < W_WIDTH-3*TAM){
            posX += STEP;

            image.setPos(posX, posY);
        }
    }

    public void goDown() {
        // Checks bounds
        if(posY < W_HEIGHT){
            posY += STEP;

            image.setPos(posX, posY);
        }
    }

    public void goUp() {
        // Checks bounds
        if(posY > -3*TAM){
            posY -= STEP;

            image.setPos(posX, posY);
        }
    }

    /**
     * Paints the object
     * 
     * @param g Graphic object to be painted
     */
    public void paint(Graphics g) {
        image.paint(g);
    }

    /**
     * Delegates collision detection for image class
     * @param other - The figure to calculate detection with
     * @return - True if this object collides with "other", false otherise
     */
    public boolean do_collide(Figure other){
        return image.do_collide(other.image);
    }
}

