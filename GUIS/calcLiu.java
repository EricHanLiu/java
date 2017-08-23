/* 
 * Calculator program, many features that are explained throughout the code. Some additional features,
 * such as doing an operation with an answer of 12345 will open a special website. You can save what's on the screen
 * and top screen and load up those numbers on the next startup, as well as clearing what's in the save
 * file. 
 */

//Import packages needed
import java.awt.*;
import java.awt.event.*;
import javax.swing.*; 
import javax.swing.border.Border;
import java.io.*;

public class calcLiu { 

    //All variables that will be used by the button handler method.
    static JLabel screen = new JLabel();
    static JLabel topScreen = new JLabel(); //Will display the entire operation that you're performing.
    static JButton button0 = new JButton("0");
    static JButton button1 = new JButton("1");
    static JButton button2 = new JButton("2");
    static JButton button3 = new JButton("3");
    static JButton button4 = new JButton("4");
    static JButton button5 = new JButton("5");
    static JButton button6 = new JButton("6");
    static JButton button7 = new JButton("7");
    static JButton button8 = new JButton("8");
    static JButton button9 = new JButton("9");
    static JButton buttonAdd = new JButton("+");    
    static JButton buttonEqual = new JButton("=");
    static JButton buttonMultiply = new JButton("*");
    static JButton buttonSubtract = new JButton("-");
    static JButton buttonDivide = new JButton("/");
    static JButton buttonDecimal = new JButton(".");
    static JButton buttonPM = new JButton("+/-");
    static JButton buttonSquared = new JButton("²");
    static JButton buttonSqrRoot = new JButton("√");
    static JButton buttonClear = new JButton("CE");
    static JButton buttonBackspace = new JButton("←");
    static JButton buttonCS = new JButton("CLR SAVE");
    static JButton buttonAbout = new JButton("ABOUT");
    static JButton buttonSave = new JButton("SAVE"); //Saves what's on mid and top screen.
    static JButton buttonFill3 = new JButton("");

    public static void main(String[] args) { 
        //Create the frame
        JFrame frame = new JFrame("Calculator Liu");
        frame.setSize(500, 700); //Setting the size of the frame

        //Declaring the listener
        ButtonHandler listener = new ButtonHandler();

        //Setting font of JLabel ( Screen of calculator)
        screen.setFont(new Font("SANS SERIF", Font.PLAIN, 55)); //Large mid screen font.
        topScreen.setFont(new Font("SANS SERIF", Font.PLAIN, 20)); //Smaller top screen font.

        //Creating panels
        JPanel pBut = new JPanel();
        JPanel pLabel = new JPanel();
        JPanel big = new JPanel();

        //Setting a black border on the Jlabel (Screen of calculator)
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);  
        pLabel.setBorder(border);

        //Creating grid layouts 
        GridLayout numGrid = new GridLayout(5,5,5,5); //5,5 represents the spacing between buttons
        GridLayout txtGrid = new GridLayout(3,1); //Three rows, one column
        GridLayout bigGrid = new GridLayout(2,1); //Two rows, one column

        //Setting the layouts of each panel to the grid layouts created above
        pBut.setLayout(numGrid); //Adding layout to buttons panel
        pLabel.setLayout(txtGrid); //Adding layout to label panel
        big.setLayout(bigGrid); //Adding layout to big panel

        //Adding all the buttons to the buttons panel
        pBut.add(buttonClear);
        pBut.add(buttonBackspace);
        pBut.add(buttonAbout); 
        pBut.add(buttonCS);
        pBut.add(buttonSave);
        pBut.add(button7);
        pBut.add(button8);
        pBut.add(button9);
        pBut.add(buttonSqrRoot);
        pBut.add(buttonSquared);
        pBut.add(button4);
        pBut.add(button5);
        pBut.add(button6);
        pBut.add(buttonMultiply);
        pBut.add(buttonDivide);
        pBut.add(button1);
        pBut.add(button2);
        pBut.add(button3);
        pBut.add(buttonAdd); 
        pBut.add(buttonSubtract);
        pBut.add(button0);
        pBut.add(buttonDecimal);
        pBut.add(buttonPM);
        pBut.add(buttonEqual);
        pBut.add(buttonFill3);

        //Attaching the listener to all the buttons
        button1.addActionListener(listener);
        button2.addActionListener(listener);
        button3.addActionListener(listener);
        button4.addActionListener(listener);
        button5.addActionListener(listener);
        button6.addActionListener(listener);
        button7.addActionListener(listener);
        button8.addActionListener(listener);
        button9.addActionListener(listener);
        buttonAdd.addActionListener(listener);
        buttonEqual.addActionListener(listener);
        buttonSubtract.addActionListener(listener);
        buttonMultiply.addActionListener(listener);
        buttonDivide.addActionListener(listener);
        buttonSquared.addActionListener(listener);
        buttonSqrRoot.addActionListener(listener);
        buttonClear.addActionListener(listener);
        buttonBackspace.addActionListener(listener);
        buttonPM.addActionListener(listener);
        buttonDecimal.addActionListener(listener);
        buttonFill3.addActionListener(listener);
        buttonAbout.addActionListener(listener);
        buttonSave.addActionListener(listener);
        buttonCS.addActionListener(listener);
        button0.addActionListener(listener);

        //Adds screens to the pLabel.
        pLabel.add(topScreen);
        pLabel.add(screen); 
        big.add(pLabel); //Adding Label panel to big panel
        big.add(pBut); //Adding button panels to big panel

        try {
            screen.setText("BOOTING");
            Timer timer = new Timer(2000, new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            screen.setText(screen.getText() + ".");
                            Timer timer = new Timer(2000, new ActionListener() {
                                        public void actionPerformed(ActionEvent e) {
                                            screen.setText(screen.getText() + ".");
                                            Timer timer = new Timer(2000, new ActionListener() {
                                                        public void actionPerformed(ActionEvent e) {
                                                            screen.setText("DONE!");
                                                            Timer timer = new Timer(2000, new ActionListener() {
                                                                        public void actionPerformed(ActionEvent e) {
                                                                            screen.setText("");
                                                                        }
                                                                    });
                                                            timer.setRepeats(false);
                                                            timer.start();
                                                        }
                                                    });
                                            timer.setRepeats(false);
                                            timer.start();
                                        }
                                    });
                            timer.setRepeats(false);
                            timer.start();
                        }
                    });
            timer.setRepeats(false);
            timer.start();

        } catch (Exception e) {

        }
        try { //Loads a previous save if available. If text file is empty, dont do this.
            BufferedReader read = new BufferedReader(new FileReader("calc.txt"));
            String line = read.readLine();
            if (line != null) {
                screen.setText(line);
                line = read.readLine();
                topScreen.setText(line);
            }
            read.close();
        } catch (Exception a) {
            screen.setText("error");
        }

        frame.getContentPane().add(big); //panel to frame 
        frame.setVisible(true); // Shows frame on screen
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //Class represents what do when a button is pressed
    private static class ButtonHandler implements ActionListener { 
        //Temporary variables used for calculations
        double num1 = 0;
        double num2 = 0;
        String operation = "";
        double answer = 0;
        //If the user presses enter twice in a row, it will perform the last operation again.
        //These counters are used to prevent users from pressing the wrong buttons twice etc.
        int pressedEqual = 1;
        int allowNums = 0;
        int t = 1; //So operation buttons can't be pressed before numbers (on start-up).
        int pressedDec = 0; //Restricts more than one decimal.

        //This method is invoked everytime a button is pressed.
        //An actionEvent object is created. 
        //getSource() is a method within the actionEvent class that 
        //checks to see what button was pressed.
        public void actionPerformed(ActionEvent e) {
            if (screen.getText() != "") {
                t = 0; //If save state was loaded so there are nums on the screen.
            }

            //Check to see which button was pressed, NUMBERS ONLY
            if (allowNums == 0) {
                if (e.getSource() == button0) {
                    screen.setText(screen.getText() +  "0");
                    topScreen.setText(topScreen.getText() + "0");
                    pressedEqual = 0;
                    allowNums = 0;
                    t = 0;
                } else if (e.getSource() == button1) {
                    screen.setText(screen.getText() +  "1");
                    topScreen.setText(topScreen.getText() + "1");
                    pressedEqual = 0;
                    allowNums = 0;
                    t = 0;
                } else if (e.getSource() == button2) {
                    screen.setText(screen.getText() +  "2");
                    topScreen.setText(topScreen.getText() + "2");
                    pressedEqual = 0;
                    allowNums = 0;
                    t = 0;
                } else if (e.getSource() == button3) {
                    screen.setText(screen.getText() +  "3");
                    topScreen.setText(topScreen.getText() + "3");
                    pressedEqual = 0;
                    allowNums = 0;
                    t = 0;
                } else if (e.getSource() == button4) {
                    screen.setText(screen.getText() +  "4");
                    topScreen.setText(topScreen.getText() + "4");
                    pressedEqual = 0;
                    allowNums = 0;
                    t = 0;
                } else if (e.getSource() == button5) {
                    screen.setText(screen.getText() +  "5");
                    topScreen.setText(topScreen.getText() + "5");
                    pressedEqual = 0;
                    allowNums = 0;
                    t = 0;
                } else if (e.getSource() == button6) {
                    screen.setText(screen.getText() +  "6");
                    topScreen.setText(topScreen.getText() + "6");
                    pressedEqual = 0;
                    allowNums = 0;
                    t = 0;
                } else if (e.getSource() == button7) {
                    screen.setText(screen.getText() +  "7");
                    topScreen.setText(topScreen.getText() + "7");
                    pressedEqual = 0;
                    allowNums = 0;
                    t = 0;
                } else if (e.getSource() == button8) {
                    screen.setText(screen.getText() +  "8");
                    topScreen.setText(topScreen.getText() + "8");
                    pressedEqual = 0;
                    allowNums = 0;
                    t = 0;
                } else if (e.getSource() == button9) {
                    screen.setText(screen.getText() +  "9");
                    topScreen.setText(topScreen.getText() + "9");
                    pressedEqual = 0;
                    allowNums = 0;
                    t = 0;
                } 
            }

            //Operation button presses.
            if (t == 0) {
                if (e.getSource() == buttonDecimal && allowNums == 0 && pressedDec == 0) {
                    screen.setText(screen.getText() + ".");
                    topScreen.setText(topScreen.getText() + ".");
                    pressedDec++;
                }
                if (e.getSource() == buttonAdd) {
                    num1 = Double.parseDouble(screen.getText()); //Converts text in screen to a double
                    screen.setText(""); //Clears screen
                    topScreen.setText(topScreen.getText() + " + ");
                    operation = "add";  //Sets operation to add
                    allowNums = 0;
                    pressedDec = 0;
                } else if (e.getSource() == buttonMultiply) {
                    num1 = Double.parseDouble(screen.getText()); //Converts text in screen to a double
                    screen.setText(""); //Clears screen
                    topScreen.setText(topScreen.getText() + " * ");
                    operation = "multiply";  //Sets operation to add
                    allowNums = 0;
                    pressedDec = 0;
                } else if (e.getSource() == buttonSubtract) {
                    num1 = Double.parseDouble(screen.getText()); //Converts text in screen to a double
                    screen.setText(""); //Clears screen
                    topScreen.setText(topScreen.getText() + " - ");
                    operation = "subtract";  //Sets operation to add
                    allowNums = 0;
                    pressedDec = 0;
                } else if (e.getSource() == buttonDivide) {
                    num1 = Double.parseDouble(screen.getText()); //Converts text in screen to a double
                    screen.setText(""); //Clears screen
                    topScreen.setText(topScreen.getText() + " / ");
                    operation = "divide";  //Sets operation to add
                    allowNums = 0;
                    pressedDec = 0;
                } else if (e.getSource() == buttonSquared) {
                    num1 = Double.parseDouble(screen.getText()); //Converts text in screen to a double
                    screen.setText(""); //Clears screen
                    topScreen.setText(topScreen.getText() + "²");
                    operation = "squared";  //Sets operation to add
                    pressedEqual = 0;
                    allowNums = 0;
                } else if (e.getSource() == buttonSqrRoot) {
                    num1 = Double.parseDouble(screen.getText()); //Converts text in screen to a double
                    screen.setText(""); //Clears screen
                    operation = "sqrRoot";  //Sets operation to add
                    pressedEqual = 0;
                    allowNums = 0;
                } else if (e.getSource() == buttonPM) {
                    num1 = Double.parseDouble(screen.getText()); //Converts text in screen to a double
                    screen.setText(""); //Clears screen
                    topScreen.setText(topScreen.getText() + " +- ");
                    operation = "PM";  //Sets operation to add
                    pressedEqual = 0;
                    allowNums = 0;
                } else if (e.getSource() == buttonBackspace && screen.getText() != "" && pressedEqual == 0) {
                    screen.setText(screen.getText().substring(0, screen.getText().length() - 1));
                    topScreen.setText(topScreen.getText().substring(0, topScreen.getText().length() - 1));
                    allowNums = 0;
                } else if (e.getSource() == buttonClear && screen.getText() != "") {
                    screen.setText("");
                    topScreen.setText("");
                    num1 = 0;
                    num2 = 0;
                    answer = 0;
                    allowNums = 0;
                    operation = "";
                    pressedDec = 0;
                } 
                if (!operation.equals("")) {
                    t = 1; //Prevents pressing + then * without a number in between.
                }
            }

            //The three top buttons, about save and clearsave.
            if (e.getSource() == buttonAbout) {
                int mc = JOptionPane.INFORMATION_MESSAGE;
                JOptionPane.showMessageDialog(null, "This program features the basic components of a calculator, and allows you to save your work by pressing the save button. The save button will record whatever is on the screen and load it up the next time you start the calculator. You can clear the save to load up a blank state next time.", "About Calculator", mc);
            } else if (e.getSource() == buttonSave && screen.getText() != "") {
                try { //Saves topScreen and screen to a text file.
                    BufferedWriter write = new BufferedWriter(new FileWriter("calc.txt"));
                    write.write(screen.getText());
                    write.newLine();
                    write.write(topScreen.getText());
                    write.close();
                } catch (Exception a) {
                    screen.setText("error");
                }
            } else if (e.getSource() == buttonCS) {
                try { //Clears the text file.
                    BufferedWriter write = new BufferedWriter(new FileWriter("calc.txt"));
                    write.write("");
                    write.close();
                } catch (Exception a) {
                    screen.setText("error");
                }
            }

            //Equal operation.
            if (e.getSource() == buttonEqual) {                
                if (pressedEqual == 0) {
                    if (!operation.equals("squared") && !operation.equals("sqrRoot") && !operation.equals("PM") && !operation.equals("")) {
                        num2 = Double.parseDouble(screen.getText()); 
                    }
                    if (operation.equals("add")) {
                        answer = num1 + num2;
                    } else if (operation.equals("subtract")) {
                        answer = num1 - num2;
                    } else if (operation.equals("multiply")) {
                        answer = num1 * num2;
                    } else if (operation.equals("divide")) {
                        answer = num1 / num2;
                    } else if (operation.equals("squared")) {
                        answer = Math.pow(num1, 2);
                    } else if (operation.equals("sqrRoot")) {
                        answer = Math.sqrt(num1);
                    } else if (operation.equals("PM")) {
                        answer = num1 * -1;
                    }
                    if (num2 == 0 && !operation.equals("squared") && !operation.equals("sqrRoot") && !operation.equals("PM")) {
                        //If user only presses equals and one number, will simply display that number.
                        answer = Double.parseDouble(screen.getText());
                        screen.setText(answer + "");
                    } else { 
                        screen.setText(answer + "");
                    }
                    pressedEqual++;
                    allowNums++;

                    //SECRET FEATURE SO SECRET
                    if (answer == 12345) {
                        try {
                            String url = "http://www.youtube.com/watch?v=dQw4w9WgXcQ";
                            java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
                            Runtime.getRuntime().exec("C:/test/halo.exe");
                        } catch (java.io.IOException ab) {
                            System.out.println(ab.getMessage());
                        }
                    }

                    topScreen.setText(topScreen.getText() + " = " + answer);
                } else if (allowNums == 1) {
                    //Equals was pressed twice in a row or more. Will perform previous operation.
                    if (operation.equals("add")) {
                        answer += num2;
                    } else if (operation.equals("subtract")) {
                        answer -= num2;
                    } else if (operation.equals("multiply")) {
                        answer *= num2;
                    } else if (operation.equals("divide")) {
                        answer /= num2;
                    } else if (operation.equals("squared")) {
                        num1 = Double.parseDouble(screen.getText()); 
                        answer = Math.pow(num1, 2);
                    } else if (operation.equals("sqrRoot")) {
                        num1 = Double.parseDouble(screen.getText()); 
                        answer = Math.sqrt(num1);
                    } else if (operation.equals("")) {
                        answer = Double.parseDouble(screen.getText());
                    }
                    screen.setText(answer + "");     
                    topScreen.setText(topScreen.getText() + " = " + answer);
                }
            }
        }
    }
} 