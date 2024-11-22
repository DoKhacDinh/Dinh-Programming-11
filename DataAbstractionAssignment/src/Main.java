import java.util.Date;
import java.util.Calendar;

public class Main {
    public static void main(String[] args) {

        // First date
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(2022, Calendar.AUGUST, 16, 10, 52, 17);
        Date date1 = calendar1.getTime();

        // Second date
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(2022, Calendar.NOVEMBER, 4, 0, 0, 0);
        Date date2 = calendar2.getTime();

        // Create a customer
        Customer customer1 = new Customer("John", 1, 200.00, 1250.00);

        customer1.deposit(400, date2, Customer.CHECKING); // Deposit money into Checking
        customer1.deposit(500.00, date1, Customer.SAVING); // Deposit money into Saving

        customer1.withdraw(400.00, date2, Customer.CHECKING); // Withdraw money from Checking
        customer1.withdraw(500.00, date1, Customer.SAVING); // Withdraw money from Saving

        // Print out 2 lists
        System.out.println("Deposit list: ");
        customer1.displayDeposits();
        System.out.println("");
        System.out.println("Withdraw list: ");
        customer1.displayWithdraws();


    }

}
