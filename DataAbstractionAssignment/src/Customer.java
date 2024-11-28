//import javax.security.sasl.SaslClient;
import java.util.ArrayList;
import java.util.Date;

public class Customer {
    private int accountNumber;
    private ArrayList<Deposit> deposits;
    private ArrayList<Withdraw> withdraws;
    private double checkBalance;
    private double savingBalance;
    private double savingRate;
    private String name;
    public static final String CHECKING = "Checking";
    public static final String SAVING = "Saving";
    private final int OVERDRAFT = -100;


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
    // - If amt <= 0, prints "Invalid amount" and returns -1.
    // - If checkOverdraft(amt, account) is true, prints "You have exceeded your overdraft!" and
    //   "Transaction has been declined!" and returns -1.
    // - If date is null, prints "Invalid date!" and returns -1.
    // - If account is "CHECKING":
    //     - If (checkBalance - amt) < 0 but >= -100, prints "Your balance is negative! Overdraft in effect!", decreases
    //       checkBalance by amt, adds a withdrawal entry to withdraws, and returns 0.
    //     - Otherwise, decreases checkBalance by amt, adds a withdrawal entry to withdraws, and returns 0.
    // - If account is "SAVING":
    //     - If (savingBalance - amt) < 0 but >= -100, prints "Your balance is negative! Overdraft in effect!", decreases
    //       savingBalance by amt, adds a withdrawal entry to withdraws, and returns 0.
    //     - Otherwise, decreases savingBalance by amt, adds a withdrawal entry to withdraws, and returns 0.
    // - If account is invalid, prints "Invalid account!" and returns -1.
    public double withdraw(double amt, Date date, String account){
        if(amt<=0) {
            System.out.println("Invalid amount");
            return -1;
        }
        if(checkOverdraft(amt, account)){ // Exceeded overdraft limit condition, transaction gets declined
            System.out.println("You have exceeded your overdraft!");
            System.out.println("Transaction has been declined!");
            return -1;
        }
        if(date == null){
            System.out.println("Invalid date!");
            return -1;
        }
        if(account.equals(CHECKING)){
            if((checkBalance - amt)<0 && (checkBalance - amt)>=-100){ // In range of overdraft, allowing transaction to be happening
                System.out.println("Your balance is negative! Overdraft in effect!");
                checkBalance -= amt;
                withdraws.add(new Withdraw(amt, date, account, getCheckBalance()));
                return 0;
            }else { // Normal withdrawal condition
                checkBalance -= amt;
                withdraws.add(new Withdraw(amt, date, account, getCheckBalance()));
                return 0;
            }
        } else if(account.equals(SAVING)){
            if((savingBalance-amt)<0 && (savingBalance-amt)>=-100){ // In range of overdraft, allowing transaction to be happening
                System.out.println("Your balance is negative! Overdraft in effect!");
                savingBalance -= amt;
                withdraws.add(new Withdraw(amt, date, account, getSavingBalance()));
                return 0;
            }else { // Normal withdrawal condition
                savingBalance -= amt;
                withdraws.add(new Withdraw(amt, date, account, getSavingBalance()));
                return 0;
            }
        } else {
            System.out.println("Invalid account!");
        } return -1;
    }




    // Requires clause: amt > 0, account is either "CHECKING" or "SAVING"
    // Modifies clause: nothing
    // Effects clause: if account is "CHECKING",  subtract checkBalance by amt and compare with OVERDRAFT
    //                 if the result is smaller than OVERDRAFT, return true, if not, return false
    //                 if account is "Saving",  subtract savingBalance by amt and compare with OVERDRAFT
    //                 if the result is smaller than OVERDRAFT, return true, if not, return false
    private boolean checkOverdraft(double amt, String account){
        if (account.equals(CHECKING)) {
            return (checkBalance - amt) < OVERDRAFT; // return true
        } else if (account.equals(SAVING)) {
            return savingBalance - amt < OVERDRAFT; // return true
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
