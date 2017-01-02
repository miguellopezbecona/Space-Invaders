package si;

/**
 *
 * @author Miguel
 */
public interface Constants {
    final int COLS = 7; // Enemy matrix's columns 
    final int ROWS = 4; // Enemy matrix's rows
    final int TAM = 15; // Pixel size of each "square" confirming the figures
    final int SEPX = 90; // Pixel separation of enemy (horizontal) adjacent ships
    final int SEPY = 45; // Pixel separation of enemy (vertical) adjacent ships
    final int W_WIDTH = 800; // Window width
    final int W_HEIGHT = 600; // Window height
    final int INIT_VEL = 750; // Initial enemy speed movement
    final int VAR_VEL = 22; // How much does enemy speed movement increase with each enemy death
    final int MISSIL_VEL = 30; // Missile speed
    final int STEP = 10; // How much does any ship move
}
