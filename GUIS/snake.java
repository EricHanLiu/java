import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import java.awt.*;
import javax.swing.*; 
import javax.swing.border.Border;
import java.io.*; 
import javax.swing.text.*;

public class snake {
    static ImageIcon link = new ImageIcon("link.jpg");
    static ImageIcon linkR = new ImageIcon("link.jpg");
    static ImageIcon linkL = new ImageIcon("link.jpg");
    static ImageIcon linkU = new ImageIcon("link.jpg");
    static ImageIcon chuchu = new ImageIcon("link.jpg");

    static JFrame frame = new JFrame("Game");
    static JPanel pBig = new JPanel();
    static JLabel hero = new JLabel(link);
    //static ImageIcon segmentsIconArray[] = {link,link,link,link,link,link,link,link,link,link,link,link,link,link,link,link,link,link,link,link};
    static JLabel segmentsArray[] = new JLabel[20];
    static int segmentX[] = new int[20];
    static int segmentY[] = new int[20];
    static int segmentVisi[] = new int[20];

    static JLabel mob = new JLabel(chuchu);

    static int counterOne = 0;
    static int counterTwo = -1;

    static int i = 0;

    static int heroXArray[] = new int [20];
    static int heroYArray[] = new int [20];

    static int heroXTemp = 0;
    static int heroYTemp = 0;

    static int heroX = 0;
    static int heroY = 0;
    static int segments = 1;
    static int lives = 3;
    //Mobs will spawn one on each side of map.
    static int mobX =  25 * (int) (Math.random() * 23);
    static int mobY =  25 * (int) (Math.random() * 23);
    //Booleans for movement.
    static boolean left = false;
    static boolean right = false;
    static boolean up = false;
    static boolean down = false;

    public static void main(String[] args) {

        //segmentsArray[0].setIcon(link);
        frame.setSize(600,600);
        pBig.setLayout(null);
        for(int c = 0; c < 20;c++) {
            segmentsArray[c] = new JLabel(link);
        }
        KeyListenerTester listener = new KeyListenerTester();

        //Placing.
        for(int q = 0;q < segmentsArray.length;q++) {
            segmentsArray[q].setBounds(segmentX[q],segmentY[q],segmentVisi[q],segmentVisi[q]);
        }
        //segmentsArray[0].setBounds(segmentX,segmentY,segmentVisi,segmentVisi);
        hero.setBounds(heroX,heroY,25,25);
        //Randomize placement of mobs.
        mob.setBounds(mobX,mobY,25,25);

        //Adding.

        for(int q = 0;q < segmentsArray.length;q++) {
            pBig.add(segmentsArray[q]);
        }

        pBig.add(hero);
        pBig.add(mob);

        hero.addKeyListener(listener);
        frame.addKeyListener(listener);
        pBig.addKeyListener(listener);

        hero.setFocusable(true);

        frame.getContentPane().add(pBig); //panel to frame 
        frame.setVisible(true); // Shows frame on screen
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JOptionPane.showMessageDialog (null, lives + " lives left.");
        while(i == 0) {
            //do all updates

            try {
                Thread.sleep(100);
            } catch (Exception e) {
                System.out.println("FAIL");
            }

            /*if(segments > 1)
            {
            if(segments < segmentVisi.length){segmentVisi[segments - 1] = 25;}
            }//else{for(int w = 0;w < segmentsArray.length;w++){segmentVisi[w] = 0;}}*/
            if (left == true) {
                heroX -= 25;
            } else if (right == true) {
                heroX += 25;
            } else if (up == true) {
                heroY -= 25;
            } else if (down == true) {
                heroY += 25;
            }

            heroXTemp = heroX;
            heroYTemp = heroY;

            //displays all segments in the correct position
            for(int q = 0;q < segmentsArray.length;q++) {
                segmentsArray[q].setBounds(heroXArray[q],heroYArray[q],segmentVisi[q],segmentVisi[q]);
            }

            //shifts all positions over by one
            for(int q = heroXArray.length - 2;q >= 0;q--) {
                heroXArray[0] = heroXTemp;
                heroYArray[0] = heroYTemp;
                heroXArray[q + 1] = heroXArray[q];
                heroYArray[q + 1] = heroYArray[q];
            }

            //If user dies.
            if (heroX < 0 || heroX > 600 || heroY < 0 || heroY > 600) {
                lives -= 1;
                JOptionPane.showMessageDialog (null, lives + " lives left. \n" + segments + " segments.");
                segments = 1;
                for (int w = 0;w < segmentsArray.length;w++) {
                    segmentX[w] = 0;
                }
                for (int w = 0;w < segmentsArray.length;w++) {
                    segmentY[w] = 0;
                }
                heroX = 0;
                heroY = 0;
                //resets all tails
                for (int w = 0;w < segmentsArray.length;w++) {
                    segmentVisi[w] = 0;
                }
                left = false;
                right = false;
                up = false;
                down = false;
                hero.setIcon(link);
            } 

            if (heroX == mobX && heroY == mobY) {
                //Relocates mob.
                mobX =  25 * (int) (Math.random() * 11);
                mobY =  25 * (int) (Math.random() * 23);
                mob.setBounds(mobX,mobY,25,25);
                //gives a segment

                segments++;
                //if(segments < segmentVisi.length){segmentVisi[segments - 1] = 25;}
                segmentVisi[segments - 1] = 25;
            }

            hero.setBounds(heroX,heroY,25,25);
            if (lives == 0) {
                JOptionPane.showMessageDialog(null, "OUT OF LIVES! GAME OVER");
                System.exit(0);
            }
            counterOne++;
            counterTwo++;
        }

    }
    private static class KeyListenerTester implements KeyListener {
        public void keyTyped(KeyEvent e) {
            int key = e.getKeyChar();

            if (key == 97 && right == false) { //A
                left = true;
                right = false;
                up = false;
                down = false;
                hero.setIcon(linkL);
            }

            if (key == 100 && left == false) { //D
                left = false;
                right = true;
                up = false;
                down = false;
                hero.setIcon(linkR);
            }

            if (key == 119 && down == false) { //W
                left = false;
                right = false;
                up = true;
                down = false;
                hero.setIcon(linkU);
            }

            if (key == 115 && up == false) { //S
                left = false;
                right = false;
                up = false;
                down = true;
                hero.setIcon(link);
            }

            if (key == 27) { //ESC
                System.exit(0);
            }

            //Mob interactions.

            hero.setBounds(heroX,heroY,25,25);
        }

        @Override
        public void keyPressed(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {

        }

    }
}


