/*
 * This program will essentially open a bank account for the user, ask them for their full name and to
 * create a PIN number. A random account number will be assigned. The user must enter the pin
 * correctly within 3 tries to access the account, or risk the program being shut down. From there,
 * the user can choose to do four things: deposit, withdraw, take out a loan, 
 * or check the current balance. After the user decides to quit the program, the final balance 
 * among other things will be printed on the screen (full name, both balances, and a parting message). 
 * User can choose from a chequings and savings account. User can also apply for a loan,
 * which will be granted based on the yearly income of the user. If the user`s yearly income is at
 * least three times that of the requested loan, the loan will be accepted. Otherwise, the chance
 * that the loan will be paid back is too low to grant it, and the loan will be declined. The user
 * can only apply for a loan on the first loop of the program, to avoid lying about the yearly
 * salary. As well, the name the user inputs will always be outputted with a capital first letter
 * and lowercase following, for example if the user inputs a first name of joHN, it would output
 * John. Finally, if the user chooses chequing first, he will be prompted to make an initial deposit.
 * If he says he wants to continue banking, and decides to choose savings next, he will also be prompted
 * to make an initial deposit. Vice-Versa as well. Program will also save all the account information
 * for future use, even after quitting the program. This information can be accessed by writing
 * '1234' when asked for a last name. It will then display all the accounts ever created using this
 * program.
 */
import java.io.*;
import java.util.*;
public class Banking {
    public static void main(String [ ] args) {
        //VARIABLES
        Scanner stdin = new Scanner(System.in);
        String firstName;
        String lastName;
        double initialDeposit;
        double amount;
        double fee = 0;
        String line = null;
        int answer;
        String keepBanking = "";
        int newAcctNum = (int) (100000 + Math.random() * 900000);
        int password;
        int incorrectAttempts = 0;
        int guessedPin;
        double roundedBalance; //Rounds the balance to two decimal places.
        int accountType;
        double income = 0;
        boolean firstLoan = true; //Boolean to determine whether a loan can be requested.
        boolean firstChq = true; //Determines whether an initial balance will be made.
        boolean firstSav = true; //Same but for the saving account.

        //Welcome messages.
        System.out.println("Welcome to LiuBank! Thanks for opening an account with us!");
        //Initializes variables.
        System.out.print("First name: ");
        firstName = stdin.next();
        firstName = firstName.substring(0,1).toUpperCase() + firstName.substring(1).toLowerCase();
        System.out.print("Last name: ");
        lastName = stdin.next();
        lastName = lastName.substring(0,1).toUpperCase() + lastName.substring(1).toLowerCase();
        sleep(500);
        //Only prints this if 1234 wasn't entered to access account info.
        if (!lastName.equals("1234")) {
            System.out.println("We have opened a chequing and savings account under your name.");
            sleep(500);
            System.out.println("Account number: " + newAcctNum);
            System.out.println();
        }
        //Allows secret access to viewing all the account information.
        if (lastName.equals("1234")) {
            try {
                BufferedReader read = new BufferedReader(new FileReader("accounts.txt"));
                while ((line = read.readLine()) != null) {
                    System.out.println(line);
                }
                String exit = stdin.next();
                if (exit != " ") {
                    System.exit(0);
                }
            } catch (Exception ex) {
                System.exit(0);
            }
        }

        //PIN creation.
        do {
            sleep(500);
            System.out.println("Make a PIN number (4 digits)!");
            password = stdin.nextInt();
            if (password >= 10000 || password < 1000) {
                System.out.println("PIN does not meet requirements!");
            }
        } while (password >= 10000 || password < 1000);

        //Determines what account type the user accesses. Used in account class.
        do {
            sleep(500);
            System.out.println();
            System.out.println("Which account would you like to access?");
            System.out.println("(1: Chequing) (2: Savings) ");
            accountType = stdin.nextInt();
            accountType(accountType);
        } while (accountType > 2 || accountType < 1);

        if (accountType == 1) {
            firstChq = false;
        } else if (accountType == 2) {
            firstSav = false;
        }
        System.out.print("Initial deposit: ");  
        initialDeposit = stdin.nextDouble();
        System.out.println("Successfully deposited " + initialDeposit + "$");
        //Initializes all the variables given into the Account class.
        Account bankAccount = new Account(firstName, lastName, newAcctNum, initialDeposit, accountType);  

        do {
            //On second loop and after, will reset the accountType if needed.
            if (keepBanking.equalsIgnoreCase("yes")) {
                do {
                    sleep(500);
                    System.out.println("Which account would you like to access?");
                    System.out.println("(1: Chequing) (2: Savings) ");
                    accountType = stdin.nextInt();
                    accountType(accountType);
                    //Ensures you make an initial deposit in each account (if both are accessed).
                    if (accountType == 1 && firstChq == true) {
                        System.out.print("Initial deposit: ");  
                        initialDeposit = stdin.nextDouble();
                        System.out.println("Successfully deposited " + initialDeposit + "$");
                        //Initializes all the variables given into the Account class.
                        bankAccount = new Account(firstName, lastName, newAcctNum, initialDeposit, accountType);
                        firstChq = false;
                    } else if (accountType == 2 && firstSav == true) {
                        System.out.print("Initial deposit: ");  
                        initialDeposit = stdin.nextDouble();
                        System.out.println("Successfully deposited " + initialDeposit + "$");
                        //Initializes all the variables given into the Account class.
                        bankAccount = new Account(firstName, lastName, newAcctNum, initialDeposit, accountType);
                        firstSav = false;
                    }
                } while (accountType > 2 || accountType < 1);
            }

            //Asks for PIN.
            do {
                System.out.println();
                System.out.print("Please enter your PIN number: ");
                guessedPin = stdin.nextInt();
                if (guessedPin == password) {
                    sleep(500);
                    System.out.println("Hello, " + firstName + "!");
                    break;
                } else {
                    sleep(500);
                    System.out.println("INCORRECT ATTEMPT!");
                    incorrectAttempts++;
                }
            } while (incorrectAttempts != 3);
            if (incorrectAttempts == 3) { //Shutdown the program.
                System.out.println("TOO MANY INCORRECT ATTEMPTS! SHUTTING DOWN SYSTEM!!!!!");
                System.exit(0);
            }

            //OPTIONS
            System.out.println("What would you like to do?");
            System.out.println("(1) Deposit into the account.");
            System.out.println("(2) Withdraw from the account.");
            System.out.println("(3) Display current balance.");
            System.out.println("(4) Apply for a loan.");
            System.out.println("(5) Get your account number for your usage.");
            answer = stdin.nextInt();
            System.out.println();

            do {
                if (answer == 1) {
                    System.out.println("How much would you like to deposit?");
                    amount = stdin.nextDouble();
                    sleep(1000);
                    bankAccount.deposit(amount, accountType);
                    System.out.println(amount + "$" + " successfully deposited!");
                } else if (answer == 2) {
                    do { //Makes sure user has enough money in the account.
                        System.out.println("How much would you like to withdraw?");
                        amount = stdin.nextDouble();
                        fee = 2; //Fee is 2$ tax on amount.
                        if (fee + amount > bankAccount.getBalance(accountType)) {
                            System.out.println("Not enough currency in account!");
                            System.out.println();
                        } 
                        if (amount == 0) {
                            break;
                        }
                    } while (fee + amount > bankAccount.getBalance(accountType));
                    if (amount != 0) {
                        sleep(1000);
                        bankAccount.withdraw(amount, fee, accountType);
                        System.out.println(amount + "$" + " successfully withdrawn.");
                        System.out.println("Service fee of 2.0" + "$ was charged to the account.");
                    }
                } else if (answer == 3) {
                    roundedBalance = bankAccount.getBalance(accountType) * 100;
                    roundedBalance = Math.round(roundedBalance);
                    roundedBalance /= 100;
                    System.out.println("Current balance: " + roundedBalance + "$");
                } else if (answer == 4 && firstLoan == true) {
                    System.out.println("How much would you like to apply for?");
                    amount = stdin.nextDouble();
                    System.out.println("What is your yearly income?");
                    income = stdin.nextDouble();
                    System.out.println("Payment: " + bankAccount.getLoan(amount, income));
                    firstLoan = false;
                } else if (firstLoan == false && answer == 4) {
                    System.out.println("You cannot apply for a loan again!");
                    continue; //Ensures another loop.
                } else if (answer == 5) {
                    System.out.println("Your account number: " + bankAccount.getAccountNumber());
                } else {
                    System.out.println("Please try again :).");
                }
            } while (answer > 5 || answer < 1);

            //Continue?
            System.out.println("Keep banking? (yes/no) ");
            keepBanking = stdin.next();
            System.out.println();
        } while (keepBanking.equalsIgnoreCase("yes"));
        //After user quits.
        System.out.println("Thanks for banking with LiuBank, " + firstName + " " + lastName + "!");
        System.out.println("Account Number: " + bankAccount.getAccountNumber());
        //Chequing balance (rounded).
        roundedBalance = bankAccount.getBalance(1) * 100;
        roundedBalance = Math.round(roundedBalance);
        roundedBalance /= 100;
        System.out.println("Balance (CHEQUING): " + roundedBalance + "$");
        //Savings balance (rounded).
        roundedBalance = bankAccount.getBalance(2) * 100;
        roundedBalance = Math.round(roundedBalance);
        roundedBalance /= 100;
        System.out.println("Balance (SAVINGS): " + roundedBalance + "$");
        saveAccount(firstName, lastName, bankAccount, password);
    }

    public static void accountType(int accountType) {
        if (accountType == 1) {
            System.out.println("Accessing chequings account...");
            sleep(1000);
        } else if (accountType == 2) {
            System.out.println("Accessing savings account...");
            sleep(1000);
        } else {
            System.out.println("That account doesn't exist! ");
        }
    }

    public static void sleep(int length) {
        //Method to avoid having to use a try catch at every location I want a sleep.
        try {
            Thread.sleep(length);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    public static void saveAccount(String firstName, String lastName, Account bankAccount, int password) {
        try {
            BufferedWriter write = new BufferedWriter(new FileWriter("accounts.txt", true));
            write.write(firstName + " " + lastName);
            write.newLine();
            write.write("Chequing: " + bankAccount.getBalance(1));
            write.newLine();
            write.write("Savings: " + bankAccount.getBalance(2));
            write.newLine();
            write.write("PIN: " + password);
            write.newLine();
            write.write("Account number: " + bankAccount.getAccountNumber());
            write.newLine();
            write.newLine();
            write.close();
        } catch (Exception ex) {
            System.exit(0);
        }
    }

}