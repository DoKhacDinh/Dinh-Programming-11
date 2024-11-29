//import javax.security.sasl.SaslClient;
import java.util.ArrayList;
import java.util.Date;

public class Customer {
    private int accountNumber;
    private ArrayList<Deposit> deposits;
    private ArrayList<Withdraw> withdraws;
    private double checkBalance;
    private double savingBalance;
    private String name;
    public static final String CHECKING = "Checking";
    public static final String SAVING = "Saving";


    Customer(){
        this.deposits = new ArrayList<>();
        this.withdraws = new ArrayList<>();
    }
    Customer(String name, int accountNumber, double checkDeposit, double savingDeposit){
        this.deposits = new ArrayList<>();
        this.withdraws = new ArrayList<>();
        this.name = name;
        this.accountNumber = accountNumber;
        this.checkBalance = checkDeposit;
        this.savingBalance = savingDeposit;
    }




    // Requires clause: amt > 0, date is not null, account is either "CHECKING" or "SAVING"
    // Modifies clause: checkBalance, savingBalance, deposits
    // Effects clause: if amt <= 0, prints "Invalid amount" and returns -1;
    //                 if date is null, prints "Invalid date!" and returns -1;
    //                 if account is "CHECKING", increases checkBalance by amt and adds a deposit entry to deposits, then returns 0
    //                 if account is "SAVING", increases savingBalance by amt and adds a deposit entry to deposits, then returns 0
    //                 otherwise, prints "Invalid account!" and returns -1.
    public double deposit(double amt, Date date, String account){
        if(amt<=0) {
            System.out.println("Invalid amount");
            return -1;
        }
        if(date == null){
            System.out.println("Invalid date!");
            return -1;
        }
        if(account.equals(CHECKING)){
            checkBalance += amt;
            deposits.add(new Deposit(amt, date, account, checkBalance));
            return 0;
        } else if (account.equals(SAVING)) {
            savingBalance += amt;
            deposits.add(new Deposit(amt, date, account, savingBalance));
            return 0;
        }else{
            System.out.println("Invalid account!");
            return -1;
        }
    }





    // Requires clause: amt > 0, date is not null, account is either "CHECKING" or "SAVING"
    // Modifies clause: checkBalance, savingBalance, withdraws
    // Effects clause:
    //  If amt <= 0, prints "Invalid amount" and returns -1.
    //  If checkOverdraft(amt, account) is true, prints "You are not allowed to overdraw your account!" and "Transaction has been declined!" and returns -1.
    //  If date is null, prints "Invalid date!" and returns -1.
    //  If account is "CHECKING":
    //    + Decreases checkBalance by amt, adds a withdrawal entry to withdraws, and returns 0.
    //  If account is "SAVING":
    //    + Decreases savingBalance by amt, adds a withdrawal entry to withdraws, and returns 0.
    //  If account is invalid, prints "Invalid account!" and returns -1.
    public double withdraw(double amt, Date date, String account){
        if(amt<=0) {
            System.out.println("Invalid amount");
            return -1;
        }
        if(checkOverdraft(amt, account)){ // Overdrawing account, transaction gets declined
            System.out.println("You are not allowed to overdraw your account!");
            System.out.println("Transaction has been declined!");
            return -1;
        }
        if(date == null){
            System.out.println("Invalid date!");
            return -1;
        }

        if(account.equals(CHECKING)){
            // Normal withdrawal condition
                checkBalance -= amt;
                withdraws.add(new Withdraw(amt, date, account, getCheckBalance()));
                return 0;
        } else if(account.equals(SAVING)){
            // Normal withdrawal condition
                savingBalance -= amt;
                withdraws.add(new Withdraw(amt, date, account, getSavingBalance()));
                return 0;
        } else {
            System.out.println("Invalid account!");
        } return -1;
    }




    // Requires clause: amt > 0, account is either "CHECKING" or "SAVING"
    // Modifies clause: nothing
    // Effects clause:
    //  If account is "CHECKING", returns true if (checkBalance - amt) < 0, otherwise, returns false
    //  If account is "SAVING", returns true if (savingBalance - amt) < 0, otherwise, returns false
    //  If account is invalid, returns false.
    private boolean checkOverdraft(double amt, String account) {
        if (account.equals(CHECKING)) {
            return (checkBalance - amt) < 0; // not allowed to withdraw more money than it presents in the account, return true
        } else if (account.equals(SAVING)) {
            return (savingBalance - amt) < 0; // not allowed to withdraw more money than it presents in the account, return true
        }
        return false;
    }

    //do not modify
    public void displayDeposits(){
        for(Deposit d : deposits){
            System.out.println(d);
        }
    }
    //do not modify
    public void displayWithdraws(){
        for(Withdraw w : withdraws){
            System.out.println(w);
        }
    }
    public double getCheckBalance() { // Get the balance in Checking account
        return checkBalance;
    }
    public double getSavingBalance() { // Get the balance in Saving account
        return savingBalance;
    }

    public static void main(String[] args) {
        Deposit deposit = new Deposit(100, new Date(),"Checking",100);
        System.out.println(deposit);
    }
}
