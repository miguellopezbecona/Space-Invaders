package si;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.Graphics;

public class Image {
    private BufferedImage image;
    private int posX;
    private int posY;
    private int tamX;
    private int tamY;
    
     /**
     * Image constructor
     *  
     * @param posX     Initial X position
     * @param posY     Initial Y position
     * @param tamX     Image's width
     * @param tamY     Image's width
     * @param filename Image URL
     */
    public Image(int posX, int posY, int tamX, int tamY, String filename){
        // Initializes fields
        this.posX = posX;
        this.posY = posY;
        this.tamX = tamX;
        this.tamY = tamY;        
        
        try {
            image = ImageIO.read(getClass().getResource("/img/" + filename));
        } catch(IOException exc) {
            System.out.println("Error reading image file: " + filename);
            System.exit(1);
        }
        
    }
    
    // Setters e getters
    public void setPos(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }
    
    public void setTam(int tamX, int tamY) {
        this.tamX = tamX;
        this.tamY = tamY;        
    }
    
    public int getPosX() {
        return posX;
    }
    
    public int getPosY() {
        return posY;
    }
    
    public int getTamX() {
        return tamX;
    }
    
    public int getTamY() {
        return tamY;
    }
    

    /**
     * @param other - The image to calculate detection with
     * @return - True if this object collides with "other", false otherise
     */
    public boolean do_collide(Image other){
        // Collision detection for rectangular bounding-boxes: (AxMin < BxMax and AxMax > BxMin) AND (AyMin < ByMax and AyMax > ByMin)
        return (posX < (other.posX + other.tamX) && (posX + tamX) > other.posX) && (posY < (other.posY + other.tamY) && (posY + tamY) > other.posY);
    }
    
    /**
     * Paints the object
     * 
     * @param g Graphic object to be painted
     */   
    public void paint(Graphics g) {
        g.drawImage(image, posX, posY, tamX, tamY, null);
    }

}
