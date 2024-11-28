import org.junit.Before;
import org.junit.Test;
import java.util.Calendar;
import java.util.Date;
import static org.junit.Assert.assertEquals;

public class CustomerWithdrawTest {
    Customer customer;
    Calendar calendar = Calendar.getInstance();
    Date date;

    @Before
    public void setup() {
        customer = new Customer("John", 1, 100.0, 100.0);

        calendar.set(2022, Calendar.AUGUST, 16, 10, 52, 17);
        date = calendar.getTime();
    }

    @Test
    public void NormalCondition(){
        customer.withdraw(100.0, date, "Checking");
        assertEquals(0.0, customer.getCheckBalance(),0.001);
    }

    @Test
    public void testSmallAmountWithdraw() {
        customer.withdraw(0.01, date, "Checking");
        assertEquals(99.99, customer.getCheckBalance(), 0.001);
    }

    @Test
    public void testNegativeAmountWithdraw(){
        customer.withdraw(-1.0, date, "Checking");
        assertEquals(100.0,customer.getCheckBalance(),0.001);
    }

    @Test
    public void testZeroAmountWithdraw(){
        customer.withdraw(0.0, date, "Checking");
        assertEquals(100.0,customer.getCheckBalance(),0.001);
    }

    @Test
    public void testInvalidAccountWithdraw() {
        customer.withdraw(100.0, date, "Nothing");
        assertEquals(100.0, customer.getCheckBalance(), 0.001);
    }

    @Test
    public void testNullDateWithdraw () {
        customer.withdraw(100.0, null, "Checking");
        assertEquals(100.0, customer.getCheckBalance(), 0.001);
    }

    @Test
    public void testOverdraws(){
        customer.withdraw(200, date, "Checking");
        assertEquals(-100, customer.getCheckBalance(), 0.001);
    }

    @Test
    public void testOverOverDraft(){
        customer.withdraw(300, date, "Checking");
        assertEquals(100, customer.getCheckBalance(), 0.001);
    }
}


