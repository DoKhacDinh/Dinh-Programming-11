import java.util.Date;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Customer customer = new Customer();

        boolean running = true;
        int x;

        System.out.println("Welcome to the Bank!");

        while (running) {
            System.out.println("1. Deposit money into a Checking account");
            System.out.println("2. Deposit money into a Saving account");
            System.out.println("3. Withdraw money from a Checking account");
            System.out.println("4. Withdraw money from a Saving account");
            System.out.println("5. Display list of deposit");
            System.out.println("6. Display list of withdrawal");
            System.out.println("7. Exit the Bank");
            System.out.print("\nPlease select an action:");

            String userSelection = scanner.next();
            if (isNum(userSelection)) {
                x = Integer.parseInt(userSelection);
            } else {
                System.out.println("Invalid command! Please enter a valid number.");
                System.out.println("");
                continue;
            }


            switch (x) {
                case 1:
                    System.out.print("Deposit of: $");
                    double amtDepositCheck = scanner.nextDouble();
                    customer.deposit(amtDepositCheck, new Date(), Customer.CHECKING);
                    System.out.println("Checking account balance: $" + customer.getCheckBalance());
                    System.out.println("");
                    break;

                case 2:
                    System.out.print("Deposit of: $");
                    double amtDepositSav = scanner.nextDouble();
                    customer.deposit(amtDepositSav, new Date(), Customer.SAVING);
                    System.out.println("Saving account balance: $" + customer.getSavingBalance());
                    System.out.println("");
                    break;

                case 3:
                    System.out.print("Withdrawal of: $");
                    double amtWithdrawCheck = scanner.nextDouble();
                    customer.withdraw(amtWithdrawCheck, new Date(), Customer.CHECKING);
                    System.out.println("Checking account balance: $" + customer.getCheckBalance());
                    System.out.println("");
                    break;

                case 4:
                    System.out.print("Withdrawal of: $");
                    double amtWithdrawSav = scanner.nextDouble();
                    customer.withdraw(amtWithdrawSav, new Date(), Customer.SAVING);
                    System.out.println("Saving account balance: $" + customer.getSavingBalance());
                    System.out.println("");
                    break;

                case 5:
                    System.out.println("History transactions:");
                    customer.displayDeposits();
                    System.out.println("");
                    break;

                case 6:
                    System.out.println("History transactions:");
                    customer.displayWithdraws();
                    System.out.println("");
                    break;

                case 7:
                    System.out.println("Thank for using our bank!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid action!");
            }
        }
    }
    static boolean isNum(String str) {
        return str.matches("-?\\d+"); // Check if the string is a valid integer
    }
    }