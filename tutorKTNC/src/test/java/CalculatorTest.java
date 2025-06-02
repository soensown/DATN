import com.example.tutorktnc.Calculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalculatorTest {
    Calculator calculator = new Calculator();
    @Test
    public void testAdd() {
        int exp= 8;
        int act= calculator.add(3, 5);
        Assertions.assertEquals(exp, act);

    }
    @Test
    public void testDiv() {
        int exp= 2;
        int act= calculator.div(6, 3);
        Assertions.assertEquals(exp, act);
    }
    @Test
    public void testDivByZero() {
        Assertions.assertThrows(
                ArithmeticException.class, () -> calculator.div(0,0)
        );
    }
}
