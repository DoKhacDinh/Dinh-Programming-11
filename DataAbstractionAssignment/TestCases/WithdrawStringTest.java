import org.junit.Test;
import java.util.Calendar;
import java.util.Date;
import static org.junit.Assert.assertEquals;

public class WithdrawStringTest {
    @Test
    public void testNormalCondition() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2022, Calendar.AUGUST, 16, 10, 52, 17);
        Date date = calendar.getTime();

        Withdraw withdraw = new Withdraw(100.0, date, "Checking", 100.00);
        assertEquals("Withdraw of: $100.00 Date: " + date + " from account: Checking" + " Current Balance in Checking is: $100.00", withdraw.toString());
    }

    @Test
    public void testNegative() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2022, Calendar.AUGUST, 16, 10, 52, 17);
        Date date = calendar.getTime();

        Withdraw withdraw = new Withdraw(-1.0, date, "Checking",100.00);
        assertEquals("Invalid amount", withdraw.toString());
    }

    @Test
    public void testZero() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2022, Calendar.AUGUST, 16, 10, 52, 17);
        Date date = calendar.getTime();

        Withdraw withdraw = new Withdraw(0.0, date, "Checking",100.00);
        assertEquals("Invalid amount", withdraw.toString());
    }

    @Test
    public void testSmallValidAmount() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2022, Calendar.AUGUST, 16, 10, 52, 17);
        Date date = calendar.getTime();

        Withdraw withdraw = new Withdraw(0.01, date, "Saving",100.00);
        assertEquals("Withdraw of: $0.01 Date: " + date + " from account: Saving" + " Current Balance in Saving is: $100.00", withdraw.toString());
    }

    @Test
    public void testNullDate() {
        Withdraw withdraw = new Withdraw(100.0, null, "Checking", 100.00);
        assertEquals("Invalid date", withdraw.toString());
    }

    @Test
    public void testInvalidAccount() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2022, Calendar.AUGUST, 16, 10, 52, 17);
        Date date = calendar.getTime();

        Withdraw withdraw = new Withdraw(100.0, date, "Credit",100.00);
        assertEquals("Invalid account", withdraw.toString());
    }
}