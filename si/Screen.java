package si;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

/**
 * This class does the "view" functions such as painting objects.
 * It also defines keyboard interactions and victory/loss messages
 */
public class Screen extends JFrame implements Constants{
    private final MyPanel panel;

    private final List<Figure> paintables;
    private final List<Missile> missiles;
    private final Hero hero;
    
    private final Figure background;

    /**
     * Constructor
     * Initializes fields and activates timers
     * @param title - Frame title
     */
    public Screen(String title){
        super(title);
        paintables = Controller.getPaintables();
        hero = Controller.getHero();
        missiles = Controller.getMissiles();
        background = new Background();
        panel = new MyPanel();
        
        setResizable(false);

        // Panel configuration
        panel.setPreferredSize(new Dimension(W_WIDTH, W_HEIGHT));
        panel.setBackground(Color.WHITE);
        panel.setFocusable(true); // Permite capturar los clics

        // Activates timers for key handling and periodic refresh
        activateKeyHandling();
        refreshPeriodically();

        add(panel); // Adds panel to frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Allows "good" exiting

        // Puts window in the center of the screen.
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation( ( (int) screenSize.getWidth() - W_WIDTH )/2, ( (int) screenSize.getHeight() - W_HEIGHT )/2);

        pack(); // Adjusts panel to frame
        setVisible(true);
    }

    /**
     * Inner class which extends JPanel
     * Paints all objects in paintables
     * It is called in each repaint()
     */
    private class MyPanel extends JPanel {
        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            
            background.paint(g); // Paints background

            // Paints paintables (xd)
            for(Figure d: paintables)
                d.paint(g);
        }
    }

    private void activateKeyHandling(){
        // What to do depending on pressed keys
        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent event) {
                int keyCode = event.getKeyCode(); // Gets pressed key code                   

                // Moves the hero to left or right
                if (keyCode == KeyEvent.VK_LEFT) {                             
                    hero.goLeft();
                    panel.repaint();
                }

                if(keyCode == KeyEvent.VK_RIGHT) {
                    hero.goRight();
                    panel.repaint();
                }            

                // Testing purposes. It should not be allowed!
                /*
                if(keyCode == KeyEvent.VK_UP) {
                    hero.goUp();
                    panel.repaint();
                }

                if(keyCode == KeyEvent.VK_DOWN) {
                    hero.goDown();
                    panel.repaint();
                }
                */

                // Pressing space creates a hero missile if it is allowed
                if (keyCode == KeyEvent.VK_SPACE && hero.getCanShoot()) {
                    Missile mi = new Missile("MissileH.png", hero.getPosX()+TAM, hero.getPosY()-4*TAM, true);
                    paintables.add(mi);
                    missiles.add(mi);
                    hero.forbidShooting(); // No infinite shoots
                    panel.repaint();
                }
                
                // Pressing escape means exit
                if (keyCode == KeyEvent.VK_ESCAPE)
                    System.exit(0);
            }
        });
    }

    /**
     * It uses a timer internally to do this
     */
    private void refreshPeriodically(){
        new Timer(50, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panel.repaint();
            }
        }).start();
    }
    
    /**
     * Just a loss message and exit
     */
    public static void loss(){
        JOptionPane.showMessageDialog(null, "Oh, no! You have lost! The game will end now.", "Game over", JOptionPane.ERROR_MESSAGE);
        System.exit(0);
    }

    /**
     * Just a victory message and exit
     */
    public static void victory(){
        JOptionPane.showMessageDialog(null, "Congratulations! You have saved Earth! :) The game will end now.");
        System.exit(0);
    }
}
