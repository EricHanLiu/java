import java.awt.*;
import java.awt.event.*;
import javax.swing.*; 
import javax.swing.border.Border;
import java.io.*;

public class battle { 

    static JLabel screen = new JLabel();
    static JLabel yourHealth = new JLabel();
    static JLabel theirHealth = new JLabel();
    static JButton attack = new JButton("Attack");
    static JButton block = new JButton("Block");
    static JButton heal = new JButton("Heal");    
    static JButton run = new JButton("Run");
    static int yHV = 100;
    static int tHV = 100;
    static int yourTurn = 0;

    public static void main(String[] args) { 

        //Create the frame
        JFrame frame = new JFrame("Battle");
        frame.setSize(400, 600); //Setting the size of the frame

        //Declaring the listener
        ButtonHandler listener = new ButtonHandler();

        screen.setFont(new Font("Serif", Font.PLAIN, 30)); 
        theirHealth.setFont(new Font("Serif", Font.PLAIN, 15));
        yourHealth.setFont(new Font("Serif", Font.PLAIN, 15));

        //Creating panels
        JPanel pBut = new JPanel();
        JPanel pLabel = new JPanel();
        JPanel big = new JPanel();

        Border border = BorderFactory.createLineBorder(Color.BLACK, 2); 
        pLabel.setBorder(border);

        //Creating grid layouts 
        GridLayout buttonGrid = new GridLayout(2,2,3,3); //5,5 represents the spacing between buttons
        GridLayout screenGrid = new GridLayout(3,1); //One row, one column
        GridLayout bigGrid = new GridLayout(2,1);//Two rows, one column

        //Setting the layouts of each panel to the grid layouts created above
        pBut.setLayout(buttonGrid); //Adding layout to buttons panel
        pLabel.setLayout(screenGrid); //Adding layout to label panel
        big.setLayout(bigGrid); //Adding layout to big panel

        //Adding all the buttons to the buttons panel
        pBut.add(attack);
        pBut.add(block);
        pBut.add(heal);
        pBut.add(run);

        //Attaching the listener to all the buttons
        attack.addActionListener(listener);
        block.addActionListener(listener);
        heal.addActionListener(listener);
        run.addActionListener(listener);

        pLabel.add(theirHealth);
        pLabel.add(screen); 
        pLabel.add(yourHealth);
        big.add(pLabel); 
        big.add(pBut); 

        try {
            BufferedReader read = new BufferedReader(new FileReader("battle.txt"));
            tHV = Integer.parseInt(read.readLine());
            yHV = Integer.parseInt(read.readLine());
            read.close();
        } catch (Exception a) {

        }

        theirHealth.setText("                                           Enemy Health: " + tHV);
        yourHealth.setText("    Your Health: " + yHV);
        frame.getContentPane().add(big); //panel to frame 
        frame.setVisible(true); // Shows frame on screen
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    } 

    //Class represents what do when a button is pressed
    private static class ButtonHandler implements ActionListener { 
        public void actionPerformed(ActionEvent e) {
            final int ranDmg = (int) (1 + Math.random() * 10);
            final int blockChance = (int) (Math.random() * 10); //Random number from 0-9.
            final JButton choice = (JButton) e.getSource();

            Timer timer = new Timer(1000, new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            if (choice == attack) {
                                screen.setText("    You did " + ranDmg + " damage!");
                                tHV -= ranDmg;
                                setHealth(tHV, yHV);
                            } else if (choice == block) {
                                if (blockChance < 5) {
                                    screen.setText("    Successful block!");
                                } else if (blockChance >= 5) {
                                    screen.setText("    They did " + ranDmg + " damage!");
                                    yHV -= ranDmg;
                                    setHealth(tHV, yHV);
                                }
                                yourTurn--; //Allows you to go again.
                            } else if (choice == heal) {
                                screen.setText("    Healed for " + ranDmg + " health!");
                                yHV += ranDmg;
                                setHealth(tHV, yHV);
                            } else if (choice == run) {
                                save(tHV, yHV);
                                System.exit(0);
                            }
                            yourTurn++;
                            Timer timer = new Timer(1000, new TimerHandler());
                            timer.setRepeats(false);
                            timer.start();
                        }
                    });
            timer.setRepeats(false);
            timer.start();

        }
    } 
    private static class TimerHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            //TURN CHANGE
            //Resets the variable.
            int ranDmg = (int) (1 + Math.random() * 10);
            int blockChance = (int) (Math.random() * 10);
            int doWhat = (int) (Math.random() * 3); //Number from 0-2.

            while (e.getSource() != attack && doWhat == 1) {
                doWhat = (int) (Math.random() * 3); //Doesn't allow ai to block if you don't attack.
            }

            if (yourTurn == 1) { //CPU turn.
                if (doWhat == 0) {
                    screen.setText("    They did " + ranDmg + " damage!");
                    yHV -= ranDmg;
                    setHealth(tHV, yHV);
                } else if (doWhat == 1) {
                    if (blockChance < 5) {
                        screen.setText("    Attack failed!");
                    } else if (blockChance >= 5) {
                        screen.setText("    You did " + ranDmg + " damage!");
                        tHV -= ranDmg;
                        setHealth(tHV, yHV);
                    }
                } else if (doWhat == 2) {
                    screen.setText("    They healed for " + ranDmg + "!");
                    tHV += ranDmg;
                    setHealth(tHV, yHV);
                }
                yourTurn--;
            }
        }
    }

    public static void setHealth(int tHV, int yHV) {
        if (tHV <= 0) {
            theirHealth.setHorizontalAlignment(SwingConstants.CENTER);
            theirHealth.setText("ENEMY HAS FAINTED! YOU WIN!");
            save(100, 100); //Restarts the game.
            attack.setEnabled(false);
            block.setEnabled(false);
            heal.setEnabled(false);
            run.setEnabled(false);
        } else if (yHV <= 0) {
            yourHealth.setHorizontalAlignment(SwingConstants.CENTER);
            yourHealth.setText("YOU HAVE FAINTED! YOU LOSE!");
            save(100, 100); //Restarts the game.
            attack.setEnabled(false);
            block.setEnabled(false);
            heal.setEnabled(false);
            run.setEnabled(false);
        } else {
            theirHealth.setText("                                           Enemy Health: " + tHV);
            yourHealth.setText("    Your Health: " + yHV);
        }
    }

    public static void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (Exception efawf) {

        }
    }

    public static void save(int tHV, int yHV) {
        try {
            BufferedWriter write = new BufferedWriter(new FileWriter("battle.txt"));
            write.write(String.valueOf(tHV));
            write.newLine();
            write.write(String.valueOf(yHV));
            write.close();
        } catch (Exception a) {
            System.out.println("Fail");
        }
    }
}
