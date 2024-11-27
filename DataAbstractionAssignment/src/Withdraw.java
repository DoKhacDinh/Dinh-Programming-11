import java.util.Date;

public class Withdraw {
    private double amount;
    private Date date;
    private String account;
    private double currentBalance;

    Withdraw(double amount, Date date, String account, double currentBalance) {
            this.amount = amount;
            this.date = date;
            this.account = account;
            this.currentBalance = currentBalance;
        }


    // Requires clause: amount > 0, date is not null, account is either "CHECKING" or "SAVING", and currentBalance is defined
    // Modifies clause: nothing
    // Effects clause: if amount <= 0, returns "Invalid amount";
    //          if date is null, returns "Invalid date";
    //          if account is "CHECKING" or "SAVING", returns a string in the format:
    //          "Withdraw of: $amount Date: date from account: account Current Balance in account is: $currentBalance";
    //          otherwise, returns "Invalid account".
    public String toString() {

        if (amount <= 0) {
            return "Invalid amount";
        }
        if (date == null) {
            return "Invalid date";
        }
        if (account.equals(Customer.CHECKING) || account.equals(Customer.SAVING)) {
            return "Withdraw of: $" + String.format("%.2f", amount) + " Date: " + date + " from account: " + account + " Current Balance in " + account + " is: $" + String.format("%.2f", currentBalance);
        } else {
            return "Invalid account";
        }
    }
}

