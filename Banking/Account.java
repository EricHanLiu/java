public class Account {
    private int acctNum;
    private double initialDeposit;
    private String firstName;
    private String lastName;
    private double savBalance;
    private double chqBalance;

    public Account(String firstName, String lastName, int newAcctNum, double initialDeposit, int accountType) {
        firstName = firstName;
        lastName = lastName;
        acctNum = newAcctNum;
        if (accountType == 1) {
            chqBalance = initialDeposit;
        } else if (accountType == 2) {
            savBalance = initialDeposit;
        }
    }

    public void deposit (double amount, int accountType) {
        //Deposits specified amount into the account.
        if (accountType == 1) {
            chqBalance += amount;
        } else if (accountType == 2) {
            savBalance += amount;
        }
    }

    public void withdraw(double amount, double fee, int accountType) {
        if (accountType == 1) {
            chqBalance -= amount + fee;
        } else if (accountType == 2) {
            savBalance -= amount + fee;
        }
    }

    public int getAccountNumber() {
        return acctNum;
    }

    public double getBalance(int accountType) {
        if (accountType == 1) {
            return chqBalance;
        } else if (accountType == 2) {
            return savBalance;
        }
        return 1;
    }
    
    public double getLoan(double amount, double income) {
        System.out.println("You have applied for a loan of (" + amount + "$)!");
        System.out.println("Your yearly income is (" + income + "$)!");
        if (income > (amount * 3)) {
            System.out.println("Loan has been accepted! Loan of (" + amount + "$) granted!");
            return amount;
        } else {
            System.out.println("Your yearly income is not high enough to grant you a loan of this amount.");
            System.out.println("Payment refused.");
            return 0;
        }
    }
}
