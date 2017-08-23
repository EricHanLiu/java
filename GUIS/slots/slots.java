/*
 * Slot machine assignment, standard slot machine with 6 images. Press spin to start, three slots will
 * start spinning one after the other (short delay). Slots will stop 2 seconds apart from each other,
 * starting with the click of the button. If user gets a combo will add money and certain stats, as
 * well as display the appropriate messages. If user runs out of coins, will display a message saying
 * cannot continue playing, then will shutdown on next click attempt. This program also follows a profile
 * system, where you can load up the previous profile with the coins and cash inside that profile and
 * continue playing from there. Cash is what is used to carry on from profiles, and is essentially a
 * bank for the coins. Cashing in and cashing out changes the coins you already have or don't have.
 * You can also buy items from the shop which will be displayed on the slot machine. There are 4 tabs
 * that separate the different sections.
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*; 
import javax.swing.border.Border;
import java.io.*; 
import javax.swing.text.*;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

public class slots { 
    //6 images, a gif.
    static ImageIcon seven = new ImageIcon("seven.jpg");
    static ImageIcon cherry = new ImageIcon("cherry.jpg");
    static ImageIcon bar = new ImageIcon("bar.jpg");
    static ImageIcon diamond = new ImageIcon("diamond.jpg");
    static ImageIcon ruby = new ImageIcon("ruby.jpg");
    static ImageIcon strawberry = new ImageIcon("strawberry.jpg");
    static ImageIcon gif = new ImageIcon("gif.gif");
    static ImageIcon forest = new ImageIcon("forest.jpg");

    //All the global variables.
    static JFrame frame = new JFrame("Slots");
    static int coins = 100;
    static int spins = 0;
    static int ran;
    static int ranCount = 0;
    static int combosHit = 0;
    static int cash = 0;
    static int ranCoin;
    static JLabel background = new JLabel(forest);
    static JLabel statistics = new JLabel("Coins: " + coins + " | Spins: " + spins + " | Combos: " + combosHit);
    static JLabel one = new JLabel();
    static JLabel two = new JLabel();
    static JLabel three = new JLabel();    
    static JLabel screen = new JLabel();
    static JButton button = new JButton("SPIN"); 
    static JTextArea combos = new JTextArea("                                    COMBOS \n" + "- 3 Of A Kind (1/216 Chance) \n" + "- Cherry, X, X (1/6 Chance) \n" + "- Cherry, Cherry, X (1/36 Chance) \n" + "- Diamond, Diamond, X (1/ 36 Chance) \n" + "- All Gems! (1/27 Chance)");
    static JPanel pBig = new JPanel();
    static JLabel display = new JLabel("Liu Casino Slot Machine™");
    static JTextArea rules = new JTextArea("                                    RULES \n" + "- Click spin to start the machine. \n" + "- Each spin costs 5 coins (100 starting). \n" + "- Click the combos tab to see the possibilities. \n" + "- There is a cash shop where you can buy bling.");
    static JPanel pShop = new JPanel();
    //Your 'real' currency used in the casino slot machine.
    static JButton cashOut = new JButton("Cash Out");
    static JButton cashIn = new JButton("Buy Coins");
    static JLabel cashDisplay = new JLabel(" Your Cash: " + cash + "$");
    //Prizes you can win and will be displayed.
    static ImageIcon chain = new ImageIcon("chain.jpg");
    static ImageIcon car = new ImageIcon("car.jpg");
    static ImageIcon gun = new ImageIcon("gun.jpg");
    static JLabel item1 = new JLabel();
    static JLabel item2 = new JLabel();
    static JLabel item3 = new JLabel();
    static int[] boughtItems = new int[3];

    //Shop buttons and stuff.
    static JButton buyChain = new JButton(chain);
    static JButton buyCar = new JButton(car);
    static JButton buyGun = new JButton(gun);
    static JLabel chainPrice = new JLabel("COST: 500$");
    static JLabel carPrice = new JLabel("COST: 1000$");
    static JLabel gunPrice = new JLabel("COST: 150$");
    static JButton buyBox = new JButton("MYSTERY BOX"); //Box that contains random $$.
    static JLabel boxPrice = new JLabel("COST: 200$");
    static JLabel yourCash = new JLabel("Your Cash: " + cash + "$");
    
    //Array to keep track of pattern wins.
    static int[] isWin = {0,0,0,0,0,0};

    public static void main(String[] args) throws Exception { 
        //Create the frame and tabbed panes.
        frame.setSize(1060, 400); //Setting the size of the frame
        JTabbedPane tabs = new JTabbedPane(JTabbedPane.LEFT);
        tabs.setUI(new BasicTabbedPaneUI() { //Changes the height of the tabs (visual appeal).
                @Override
                protected int calculateTabHeight(
                int tabPlacement, int tabIndex, int fontHeight) {
                    return 88;
                }
            });
        //4 tabs.
        tabs.addTab("Slots", pBig);
        tabs.addTab("Rules", null, rules, "Rules");
        tabs.addTab("Combos", null, combos, "Combos");
        tabs.addTab("Shop", null, pShop, "Shop");

        //Fonts and text positioning.
        statistics.setHorizontalAlignment(SwingConstants.CENTER);
        cashDisplay.setHorizontalAlignment(SwingConstants.CENTER);
        screen.setHorizontalAlignment(SwingConstants.CENTER);
        chainPrice.setHorizontalAlignment(SwingConstants.CENTER);
        carPrice.setHorizontalAlignment(SwingConstants.CENTER);
        gunPrice.setHorizontalAlignment(SwingConstants.CENTER);
        boxPrice.setHorizontalAlignment(SwingConstants.CENTER);
        statistics.setFont(new Font("SANS-SERIF", Font.PLAIN, 20)); 
        button.setFont(new Font("SANS-SERIF", Font.BOLD, 25)); 
        screen.setFont(new Font("SANS-SERIF", Font.PLAIN, 20)); 
        combos.setFont(new Font("SANS-SERIF", Font.BOLD, 15)); 
        display.setFont(new Font("SANS-SERIF", Font.BOLD, 34)); 
        cashOut.setFont(new Font("SANS-SERIF", Font.BOLD, 20)); 
        cashIn.setFont(new Font("SANS-SERIF", Font.BOLD, 20)); 
        cashDisplay.setFont(new Font("SANS-SERIF", Font.BOLD, 15)); 
        yourCash.setFont(new Font("SANS-SERIF", Font.BOLD, 30)); 
        rules.setFont(new Font("SANS-SERIF", Font.BOLD, 35));
        combos.setFont(new Font("SANS-SERIF", Font.BOLD, 35));
        chainPrice.setFont(new Font("SANS-SERIF", Font.BOLD, 30));
        carPrice.setFont(new Font("SANS-SERIF", Font.BOLD, 30));
        gunPrice.setFont(new Font("SANS-SERIF", Font.BOLD, 30));
        boxPrice.setFont(new Font("SANS-SERIF", Font.BOLD, 30));
        cashDisplay.setForeground(Color.white);
        statistics.setForeground(Color.white);
        screen.setForeground(Color.white);
        display.setForeground(Color.white);

        //BORDERS
        Border bigBorder = BorderFactory.createLineBorder(Color.BLACK, 5); 
        Border smallBorder = BorderFactory.createLineBorder(Color.RED, 2); 
        Border cashBorder = BorderFactory.createLineBorder(Color.GREEN, 1);
        pBig.setBorder(bigBorder);
        one.setBorder(smallBorder);
        two.setBorder(smallBorder);
        three.setBorder(smallBorder);
        screen.setBorder(smallBorder);  
        statistics.setBorder(smallBorder);
        cashIn.setBorder(cashBorder);
        cashOut.setBorder(cashBorder);
        cashDisplay.setBorder(cashBorder);
        rules.setBorder(bigBorder);
        combos.setBorder(bigBorder);
        button.setBorder(smallBorder);
        pShop.setBorder(bigBorder);

        //SHOP TAB PANEL
        pShop.setLayout(null);
        //Adding to shop.
        buyChain.setBounds(30,120,212,220);
        buyCar.setBounds(262,120,212,220);
        buyGun.setBounds(494,120,212,220);
        buyBox.setBounds(726,120,212,220);
        chainPrice.setBounds(20,50,212,100);
        carPrice.setBounds(262,50,212,100);
        gunPrice.setBounds(494,50,212,100);
        boxPrice.setBounds(726,50,212,100);
        yourCash.setBounds(35,20,1000,50);
        //Adding things to shop.
        pShop.add(buyChain);
        pShop.add(buyCar);
        pShop.add(buyGun);
        pShop.add(chainPrice);
        pShop.add(carPrice);
        pShop.add(gunPrice);
        pShop.add(boxPrice);
        pShop.add(buyBox);
        pShop.add(yourCash);
        
        //SLOT TAB PANEL
        pBig.setLayout(null); 
        //Placing everything.
        one.setBounds(20,20,150,200);
        two.setBounds(190,20,150,200); 
        three.setBounds(360,20,150,200); 
        button.setBounds(600,230,350,110); //ends at 950
        screen.setBounds(600,20,350,100);
        statistics.setBounds(600,120,350,100);
        display.setBounds(20,280,510,90);
        cashDisplay.setBounds(20,230,150,65);
        cashIn.setBounds(190,230,150,65);
        cashOut.setBounds(360,230,150,65);
        item1.setBounds(520,20,70,100);
        item2.setBounds(520,130,70,100);
        item3.setBounds(520,240,70,100);
        background.setBounds(0,0,1060,400);
        //Adding everything to pBig.     
        pBig.add(one);
        pBig.add(two);
        pBig.add(three);
        pBig.add(button);
        pBig.add(screen);
        pBig.add(statistics);
        pBig.add(display);
        pBig.add(cashDisplay);
        pBig.add(cashIn);
        pBig.add(cashOut);
        pBig.add(item1);
        pBig.add(item2);
        pBig.add(item3);
        pBig.add(background);

        //Listeners
        ButtonHandler listener = new ButtonHandler();
        button.addActionListener(listener);
        cashOut.addActionListener(listener);
        cashIn.addActionListener(listener);
        buyChain.addActionListener(listener);
        buyCar.addActionListener(listener);
        buyGun.addActionListener(listener);
        buyBox.addActionListener(listener);

        //Displays item info when hovering over with mouse.
        buyChain.setToolTipText("Will display a fresh diamond chain on the slot machine.");  
        buyCar.setToolTipText("Will display a pimpin' car on the slot machine.");  
        buyGun.setToolTipText("Will display a gun on the slot machine.");  
        buyBox.setToolTipText("Will give a random amount of coins (50-400).");

        //Loading existing profile, reads text file with info in it.
        int newGame = JOptionPane.showConfirmDialog(
                frame,
                "Would you like to load an existing profile?",
                "Load Existing Profile",
                JOptionPane.YES_NO_OPTION);
        if (newGame == JOptionPane.YES_OPTION) {
            try {
                BufferedReader read = new BufferedReader(new FileReader("slots.txt"));
                coins = Integer.parseInt(read.readLine());
                cash = Integer.parseInt(read.readLine());
                spins = Integer.parseInt(read.readLine());
                combosHit = Integer.parseInt(read.readLine());
                boughtItems[0] = Integer.parseInt(read.readLine());
                boughtItems[1] = Integer.parseInt(read.readLine());
                boughtItems[2] = Integer.parseInt(read.readLine());
                read.close();
                statistics.setText("Coins: " + coins + " | Spins: " + spins + " | Combos: " + combosHit);
                cashDisplay.setText(" Your Cash: " + cash + "$");
                yourCash.setText("Your Cash: " + cash + "$");

                if (boughtItems[0] == 1) {
                    item1.setIcon(chain);
                } 
                if (boughtItems[1] == 1) {
                    item2.setIcon(car);
                } 
                if (boughtItems[2] == 1) {
                    item3.setIcon(gun);
                } 
                if (coins == 0) {
                    cashOut.setEnabled(false);
                } 
            } catch (Exception a) {

            }
        } 
        if (cash == 0) { //Cannot buy coins with 0 cash.
            cashIn.setEnabled(false);
        }
        
        frame.getContentPane().add(tabs); //panel to frame 
        frame.setVisible(true); // Shows frame on screen
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    } 

    private static class ButtonHandler implements ActionListener { 
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == button) {
                //Default price is 5, can be changed anytime.
                coins -= 5;
                //If you lose or run out of coins.
                if (coins < 0) {
                    JOptionPane.showMessageDialog(null, "You're out of coins! Restart the program in this profile to buy more.");
                    System.exit(0);
                }

                //Resets array on button press (resets every position to 0).
                for (int i = 0; i < 6; i++) {
                    isWin[i] = 0;
                    ranCount = 0;
                }

                //Starts gif with small delay between each. (Simulates unevenness of spins)
                one.setIcon(gif);
                Timer timer = new Timer(200, new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                two.setIcon(gif);
                                Timer timer = new Timer(200, new ActionListener() {
                                            public void actionPerformed(ActionEvent e) {
                                                three.setIcon(gif);
                                            }
                                        });
                                timer.setRepeats(false);
                                timer.start();
                            }
                        });
                timer.setRepeats(false);
                timer.start();

                //Disables buttons during spin.
                button.setEnabled(false);
                cashIn.setEnabled(false);
                cashOut.setEnabled(false);

                screen.setText("Game Start! -5 Coins, +1 Spins");
                spins++;
                statistics.setText("Coins: " + coins + " | Spins: " + spins + " | Combos: " + combosHit);
                //Delay between stops of the slot columns.
                timer = new Timer(1500, new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            one.setIcon(img());
                            Timer timer = new Timer(1500, new ActionListener() {
                                        public void actionPerformed(ActionEvent e) {
                                            two.setIcon(img());
                                            Timer timer = new Timer(1500, new ActionListener() {
                                                        public void actionPerformed(ActionEvent e) {
                                                            three.setIcon(img());
                                                            button.setEnabled(true);
                                                            cashIn.setEnabled(true);
                                                            cashOut.setEnabled(true);
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

                //Saves stats on every button press.
                save();
            } else if (e.getSource() == cashOut) {
                Object[] options = {"Half My Coins!", "All My Coins!"};
                int halfOrAll = JOptionPane.showOptionDialog(null,
                        "Would you like to cash out half or all your coins?",
                        "CASH OUT",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,     
                        options,  
                        options[0]);
                //Operation based on choice.
                if (halfOrAll == JOptionPane.YES_OPTION) { //Half
                    cash += coins/2;
                    coins /= 2;
                } else if (halfOrAll == JOptionPane.NO_OPTION) { //All
                    cash += coins;
                    coins = 0;
                    JOptionPane.showMessageDialog(null, "You've cashed out all your coins! \n" + "Thanks for playing at the Liu Casino.");
                    cashOut.setEnabled(false);
                }
                cashIn.setEnabled(true);
                cashDisplay.setText(" Your Cash: " + cash + "$");
                statistics.setText("Coins: " + coins + " | Spins: " + spins + " | Combos: " + combosHit);  
                save();
            } else if (e.getSource() == cashIn) {
                Object[] options = {"Half My Cash!", "All My Cash!"};
                int halfOrAll = JOptionPane.showOptionDialog(null,
                        "Would you like to use half your cash or all your cash?",
                        "CASH IN",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,     
                        options,  
                        options[0]);
                if (halfOrAll == JOptionPane.YES_OPTION) { //Half
                    coins += cash/2;
                    cash /= 2;
                    cashOut.setEnabled(true);
                } else if (halfOrAll == JOptionPane.NO_OPTION) { //All
                    coins += cash;
                    cash = 0;
                    cashOut.setEnabled(true);
                }
                cashDisplay.setText(" Your Cash: " + cash + "$");
                statistics.setText("Coins: " + coins + " | Spins: " + spins + " | Combos: " + combosHit);  
                save();

            } else if (e.getSource() == buyChain) { //BUYING ITEMS
                int chainConfirm = JOptionPane.showConfirmDialog(
                        frame,
                        "Are you sure you'd like to buy this item for 500$?",
                        "Shop",
                        JOptionPane.YES_NO_OPTION);
                if (chainConfirm == JOptionPane.YES_OPTION) {
                    if (cash < 500) {
                        JOptionPane.showMessageDialog(null, "Not enough money!");
                    } else {
                        cash -= 500;
                        item1.setIcon(chain);
                        boughtItems[0] = 1;
                        JOptionPane.showMessageDialog(null, "The item is now displayed on the slot machine!");
                    }
                }
            } else if (e.getSource() == buyCar) {
                int carConfirm = JOptionPane.showConfirmDialog(
                        frame,
                        "Are you sure you'd like to buy this item for 1000$?",
                        "Shop",
                        JOptionPane.YES_NO_OPTION);
                if (carConfirm == JOptionPane.YES_OPTION) {
                    if (cash < 1000) {
                        JOptionPane.showMessageDialog(null, "Not enough money!");
                    } else {
                        cash -= 1000;
                        item2.setIcon(car);
                        boughtItems[1] = 1;
                        JOptionPane.showMessageDialog(null, "The item is now displayed on the slot machine!");
                    }
                }
            } else if (e.getSource() == buyGun) {
                int gunConfirm = JOptionPane.showConfirmDialog(
                        frame,
                        "Are you sure you'd like to buy this item for 150$?",
                        "Shop",
                        JOptionPane.YES_NO_OPTION);
                if (gunConfirm == JOptionPane.YES_OPTION) {
                    if (cash < 150) {
                        JOptionPane.showMessageDialog(null, "Not enough money!");
                    } else {
                        cash -= 150;
                        item3.setIcon(gun);
                        boughtItems[2] = 1;
                        JOptionPane.showMessageDialog(null, "The item is now displayed on the slot machine!");
                    }
                }
            } else if (e.getSource() == buyBox) { //Mystery box of 50-400 coin output.
                ranCoin = 50 * (int) (1 + Math.random() * 8);
                int boxConfirm = JOptionPane.showConfirmDialog(
                        frame,
                        "Are you sure you'd like to buy this item for 200$?",
                        "Shop",
                        JOptionPane.YES_NO_OPTION);
                if (boxConfirm == JOptionPane.YES_OPTION) {
                    if (cash < 200) {
                        JOptionPane.showMessageDialog(null, "Not enough money!");
                    } else {
                        cash -= 200;
                        coins += ranCoin;
                        JOptionPane.showMessageDialog(null, "You've won " + ranCoin + " coins! Congrats!");
                        cashOut.setEnabled(true);
                    }
                }
            }
            statistics.setText("Coins: " + coins + " | Spins: " + spins + " | Combos: " + combosHit);
            cashDisplay.setText(" Your Cash: " + cash + "$");
            yourCash.setText("Your Cash: " + cash + "$");
            save();
        }
    }

    public static ImageIcon img() {
        //Method to choose a random image and check for combos and payouts.
        ran = (int) (Math.random() * 6);
        isWin[ran]++;
        ranCount++;
        if (isWin[0] == 3) { //7s
            coins += 1000; //Jackpot!
            screen.setText("JACKPOT! +1000 Coins");
            combosHit++;
        } else if (isWin[1] == 3) { //Cherries
            coins += 100;
            screen.setText("Win! +100 Coins");
            combosHit++;
        } else if (isWin[2] == 3) { //Bars
            coins += 200;
            screen.setText("Win! +200 Coins");
            combosHit++;
        } else if (isWin[3] == 3) { //Diamonds
            coins += 400;
            screen.setText("Win! +400 Coins");
        } else if (isWin[3] == 2 && ranCount == 2) { //2 Diamonds out of 3
            coins += 200;
            screen.setText("Win! +200 Coins");
            combosHit++;
        } else if (isWin[4] == 3) { //Rubies
            coins += 300;
            screen.setText("Win! +300 Coins");
            combosHit++;
        } else if (isWin[5] == 3) { //Strawberries
            coins += 150;
            screen.setText("Win! +150 Coins");
            combosHit++;
        } else if (isWin[1] == 2 && ranCount == 2) { //2 Cherries
            coins += 40; //40 + 10 = 50
            screen.setText("Win! +50 Coins");
        } else if (isWin[1] == 1 && ranCount == 1) { //1 Cherry
            coins += 10;
            screen.setText("Win! +10 Coins");
            combosHit++;
        } else if (isWin[4] + isWin[3] == 3) { //All Gems
            coins += 80;
            screen.setText("Win! +80 Coins");
            combosHit++;
        }
        statistics.setText("Coins: " + coins + " | Spins: " + spins + " | Combos: " + combosHit);
        save();
        if (ran == 0) {
            return seven;
        } else if (ran == 1) {
            return cherry;
        } else if (ran == 2) {
            return bar;
        } else if (ran == 3) {
            return diamond;
        } else if (ran == 4) {
            return ruby;
        } else if (ran == 5) {
            return strawberry;
        }

        return null;      
    }

    public static void save() {
        //Saves game.
        try {
            BufferedWriter write = new BufferedWriter(new FileWriter("slots.txt"));
            write.write(String.valueOf(coins));
            write.newLine();
            write.write(String.valueOf(cash));
            write.newLine();
            write.write(String.valueOf(spins));
            write.newLine();
            write.write(String.valueOf(combosHit));
            write.newLine();
            write.write(String.valueOf(boughtItems[0]));
            write.newLine();
            write.write(String.valueOf(boughtItems[1]));
            write.newLine();
            write.write(String.valueOf(boughtItems[2]));
            write.close();
        } catch (Exception a) {
            System.out.println("Fail");
        }
    }
}