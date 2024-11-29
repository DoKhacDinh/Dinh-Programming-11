import org.junit.Before;
import org.junit.Test;
import java.util.Calendar;
import java.util.Date;
import static org.junit.Assert.assertEquals;

public class CustomerDepositTest {
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
    public void testNormalCondition() {
        customer.deposit(100.0, date, "Checking");
        assertEquals(200.0, customer.getCheckBalance(), 0.0001);
    }
    @Test
    public void testSmallAmountDeposit(){
        customer.deposit(0.01, date, "Checking");
        assertEquals(100.01, customer.getCheckBalance(), 0.001);
    }
    @Test
    public void testNegativeAmountDeposit() {
        customer.deposit(-50.0, date, "Checking");
        assertEquals(100.0, customer.getCheckBalance(), 0.001);
    }

    @Test
    public void testZeroAmountDeposit() {
        customer.deposit(0.0, date, "Checking");
        assertEquals(100.0, customer.getCheckBalance(), 0.001);
    }

    @Test
    public void testInvalidAccountDeposit() {
        customer.deposit(100.0, date, "Nothing");
        assertEquals(100.0, customer.getCheckBalance(), 0.001);
    }

    @Test
    public void testNullDateDeposit () {
        customer.deposit(100.0, null, Customer.CHECKING);
        assertEquals(100.0, customer.getCheckBalance(), 0.001);
        }
    }

