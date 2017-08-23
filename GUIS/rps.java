import java.awt.*;
import java.awt.event.*;
import javax.swing.*; 
import javax.swing.border.Border;
import java.io.*;

public class rps { 
    //ROCK PAPER SCISSORS
    static JLabel middle = new JLabel();
    static JLabel them = new JLabel();
    static JLabel yourWins = new JLabel();
    static JLabel theirWins = new JLabel();
    static JPanel yourPanel = new JPanel();
    static JPanel middlePanel = new JPanel();
    static JLabel blank1 = new JLabel();
    static JLabel blank2 = new JLabel();
    static JButton rock = new JButton("Rock");
    static JButton paper = new JButton("Paper");
    static JButton scissors = new JButton("Scissors");
    static int yw = 0;
    static int tw = 0;
    static ButtonHandler listener = new ButtonHandler();

    public static void main(String[] args) { 

        //Create the frame
        JFrame frame = new JFrame("Rock Paper Scissors");
        frame.setSize(500, 500); //Setting the size of the frame

        middle.setFont(new Font("Serif", Font.PLAIN, 30)); 
        middle.setHorizontalAlignment(SwingConstants.CENTER);
        them.setFont(new Font("Serif", Font.PLAIN, 15));
        them.setHorizontalAlignment(SwingConstants.CENTER);
        yourWins.setHorizontalAlignment(SwingConstants.CENTER);
        theirWins.setHorizontalAlignment(SwingConstants.CENTER);

        //Creating panels
        JPanel bigPanel = new JPanel();

        Border border = BorderFactory.createLineBorder(Color.BLACK, 1); 
        Border wlb = BorderFactory.createLineBorder(Color.RED, 1); 
        them.setBorder(border);
        yourPanel.setBorder(border);
        bigPanel.setBorder(border);
        yourWins.setBorder(wlb);
        theirWins.setBorder(wlb);
        middlePanel.setBorder(border);

        //Creating grid layouts 
        GridLayout yourGrid = new GridLayout(1,3,10,10); //5,5 represents the spacing between buttons
        GridLayout theirGrid = new GridLayout(1,1); //One row, one column
        GridLayout middleGrid = new GridLayout(5,1);
        GridLayout bigGrid = new GridLayout(3,1);//Two rows, one column

        //Setting the layouts of each panel to the grid layouts created above
        yourPanel.setLayout(yourGrid); //Adding layout to buttons panel
        them.setLayout(theirGrid); //Adding layout to label panel
        middlePanel.setLayout(middleGrid); 
        bigPanel.setLayout(bigGrid);

        //Adding r/p/s to your grid.
        yourPanel.add(rock);
        yourPanel.add(paper);
        yourPanel.add(scissors);

        //Adding w/l rations to middlegrid.
        middlePanel.add(theirWins);
        middlePanel.add(blank1);
        middlePanel.add(middle);
        middlePanel.add(blank2);
        middlePanel.add(yourWins);

        //Attaching the listener to all the buttons
        rock.addActionListener(listener);
        paper.addActionListener(listener);
        scissors.addActionListener(listener);

        bigPanel.add(them);
        bigPanel.add(middlePanel);
        bigPanel.add(yourPanel); 

        //Shows the score at 0-0.
        yourWins.setText("Your wins: " + yw);
        theirWins.setText("Their wins: " + tw);

        frame.getContentPane().add(bigPanel); //panel to frame 
        frame.setVisible(true); // Shows frame on screen
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //Class represents what do when a button is pressed
    private static class ButtonHandler implements ActionListener { 
        public void actionPerformed (ActionEvent e) {
            middle.setText("");
            them.setText("");
            final JButton button = (JButton)e.getSource();
            //Disables buttons until sleep is done.
            rock.setEnabled(false);
            paper.setEnabled(false);
            scissors.setEnabled(false);

            //Sets a 1s delay between messages.
            Timer timer = new Timer(1000, new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            final String tc = random();
                            them.setText("They chose: " + tc + "!");
                            Timer timer = new Timer(1000, new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            if (button == rock) {
                                                whoWins("rock", tc);
                                            } else if (button == paper) {
                                                whoWins("paper", tc);
                                            } else if (button == scissors) {
                                                whoWins("scissors", tc);
                                            }
                                            yourWins.setText("Your wins: " + yw);
                                            theirWins.setText("Their wins: " + tw);
                                            rock.setEnabled(true);
                                            paper.setEnabled(true);
                                            scissors.setEnabled(true);
                                        }
                                    });
                            timer.setRepeats(false);
                            timer.start();

                        }
                    });
            timer.setRepeats(false);     
            timer.start();
        }              
    }

    public static String random() {
        int random = (int) (Math.random() * 3);
        if (random == 0) {
            return "Rock";
        } else if (random == 1) {
            return "Paper";
        } else if (random == 2) {
            return "Scissors";
        }
        return "";
    }

    public static void whoWins(String yc, String tc) {
        if (yc.equals("rock")) {
            if (tc.equals("Rock")) {
                middle.setText("It's a tie!");            
            } else if (tc.equals("Paper")) {
                middle.setText("You lose!");
                tw++;
            } else if (tc.equals("Scissors")) {
                middle.setText("You win!");
                yw++;
            }
        } else if (yc.equals("paper")) {
            if (tc.equals("Rock")) {
                middle.setText("You win!");
                yw++;
            } else if (tc.equals("Paper")) {
                middle.setText("It's a tie!");
            } else if (tc.equals("Scissors")) {
                middle.setText("You lose!");
                tw++;
            }
        } else if (yc.equals("scissors")) {
            if (tc.equals("Rock")) {
                middle.setText("You lose!");
                tw++;
            } else if (tc.equals("Paper")) {
                middle.setText("You win!");
                yw++;
            } else if (tc.equals("Scissors")) {
                middle.setText("It's a tie!");
            }
        }
    }
}