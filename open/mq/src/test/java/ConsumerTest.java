import com.varela.rabbitmq.listener.MQTestConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

/**
 * Created by lance on 10/14/2015.
 */
@ContextConfiguration("classpath:applicationContext.xml")
public class ConsumerTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private MQTestConsumer consumer;

    @Test
    public void consumer() {
        while (true){
            System.out.println("dd");
        }
    }
}
