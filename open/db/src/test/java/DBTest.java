/*import com.varela.entity.Level;
import com.varela.service.api.bus.LevelService;
import org.springframework.beans.factory.annotation.Autowired;*/

import com.varela.dao.UserDao;
import com.varela.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

/**
 * Created by lance on 10/14/2015.
 */
@ContextConfiguration("classpath:applicationContext-db.xml")
public class DBTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private UserDao userDao;

    @Test
    public void run(){
        System.out.println(userDao);
    }

    @Test
    public void queryById(){
        long id=1;
        User user=this.userDao.selectByPrimaryKey(id);
        System.out.println(user);
    }
}
