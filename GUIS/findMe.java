import java.awt.*;
import java.awt.event.*;
import javax.swing.*; 

public class findMe { 
    static ImageIcon park = new ImageIcon("park.jpg");
    static JLabel image = new JLabel(park);
    static JPanel big = new JPanel();
    static JLabel text = new JLabel("Clicks: 0");
    static int i = 0;

    public static void main(String[] args) { 
        //Create the frame
        JFrame frame = new JFrame("Find Me");
        frame.setSize(1300, 802); //Setting the size of the frame

        //Declaring the Mouse listener
        MouseHandler listener = new MouseHandler();

        big.add(image);
        big.add(text);
        
        image.addMouseListener(listener);

        JOptionPane.showMessageDialog (null, "Hint: The name of this image is tranquility.");

        frame.getContentPane().add(big); //panel to frame 
        frame.setVisible(true); // Shows frame on screen
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    } 

    private static class MouseHandler implements MouseListener {

        public void mousePressed(MouseEvent e) {
            if (e.getX() >= 502 && e.getX() <= 517 && e.getY() >= 572 && e.getY() <= 592) {
                JOptionPane.showMessageDialog (null, "You've found it!");
            } else {
                i++;
                text.setText("Clicks: " + i);
            }
        }
        
        public void mouseReleased(MouseEvent e) {
        }
        
        public void mouseEntered(MouseEvent e) {
        }
        
        public void mouseExited(MouseEvent e) {
        }
        
        public void mouseClicked(MouseEvent e) {
            System.out.println(e.getX() + " " + e.getY());
        }
    }    

    
    
    
    
}

