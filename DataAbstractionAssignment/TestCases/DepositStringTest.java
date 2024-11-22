import org.junit.Test;
import java.util.Calendar;
import java.util.Date;
import static org.junit.Assert.assertEquals;

public class DepositStringTest {

    @Test
    public void testNormalCondition() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2022, Calendar.AUGUST, 16, 10, 52, 17);
        Date date = calendar.getTime();

        Deposit deposit = new Deposit(100.0, date, "Checking", 100.00);
        assertEquals("Deposit of: $100.00 Date: " + date + " into account: Checking" + " Current Balance in Checking is: $100.00", deposit.toString());
    }

    @Test
    public void testNegative() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2022, Calendar.AUGUST, 16, 10, 52, 17);
        Date date = calendar.getTime();

        Deposit deposit = new Deposit(-1.0, date, "Checking", 100.00);
        assertEquals("Invalid amount", deposit.toString());
    }

    @Test
    public void testZero() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2022, Calendar.AUGUST, 16, 10, 52, 17);
        Date date = calendar.getTime();

        Deposit deposit = new Deposit(0.0, date, "Checking",100.00);
        assertEquals("Invalid amount", deposit.toString());
    }
    @Test
    public void testSmallValidAmount() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2022, Calendar.AUGUST, 16, 10, 52, 17);
        Date date = calendar.getTime();

        Deposit deposit = new Deposit(0.01, date, "Saving", 100.00);
        assertEquals("Deposit of: $0.01 Date: " + date + " into account: Saving" + " Current Balance in Saving is: $100.00", deposit.toString());
    }

    @Test
    public void testInvalidAccount() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2022, Calendar.AUGUST, 16, 10, 52, 17);
        Date date = calendar.getTime();

                Deposit deposit = new Deposit(100.0, date, "Credit", 100.00);
        assertEquals("Invalid account", deposit.toString());
    }

    @Test
    public void testNullDate() {
        Deposit deposit = new Deposit(100.0, null, "Checking", 100.00);
        assertEquals("Invalid date", deposit.toString());
    }
}
