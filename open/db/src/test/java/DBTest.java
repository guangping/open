import com.varela.utils.ResourceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

/**
 * Created by lance on 10/14/2015.
 */
@ContextConfiguration("classpath:applicationContext.xml")
public class DBTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private ResourceUtils resourceUtils;

    @Test
    public void run() {
        String key = "jdbc.url";
        String val = this.resourceUtils.getStringValue(key);
        System.out.println(val);
        key="ddd.ff";
        val = this.resourceUtils.getStringValue(key);
        System.out.println(val);
    }


}
