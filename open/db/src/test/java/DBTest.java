/*import com.varela.entity.Level;
import com.varela.service.api.bus.LevelService;
import org.springframework.beans.factory.annotation.Autowired;*/
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

/**
 * Created by lance on 10/14/2015.
 */
@ContextConfiguration("classpath:applicationContext-db.xml")
public class DBTest extends AbstractTestNGSpringContextTests {

   /* @Autowired
    private LevelService levelService;

    @Test
    public void queryVersion() {
        Level level = this.levelService.queryById("117");
        System.out.printf("level:" + level);
    }
*/
}
