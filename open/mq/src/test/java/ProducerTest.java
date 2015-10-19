import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lance on 10/14/2015.
 */
@ContextConfiguration("classpath:applicationContext.xml")
public class ProducerTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private AmqpTemplate amqpTemplate;

    private String queueName = "lance";


    @Test
    public void send() {
        Map<String, String> map =null;
        int i=0;
        while (true){
            map=new HashMap<String, String>();
            map.put("age", "10");
            this.amqpTemplate.convertAndSend(queueName, map);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }




}
