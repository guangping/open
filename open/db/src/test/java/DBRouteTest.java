/*import com.alibaba.fastjson.JSON;
import com.varela.dao.sharding.DataSourceContextHolder;
import com.varela.entity.Level;
import com.varela.entity.Order;
import com.varela.service.api.bus.LevelService;
import com.varela.service.api.bus.OrderService;
import org.springframework.beans.factory.annotation.Autowired;*/
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by lance on 11/4/2015.
 */
@ContextConfiguration("classpath:applicationContext-route.xml")
public class DBRouteTest extends AbstractTestNGSpringContextTests {

   /* @Autowired
    private OrderService orderService;

    @Autowired
    private LevelService levelService;


    *//**
     * 库1
     *//*
    @Test
    public void queryVersion() {
        //切库
        DataSourceContextHolder.switchDataSource2();
        Level level = this.levelService.queryById("117");
        System.out.printf("level:" + level);
    }


    *//**
     * 库2
     *//*
    @Test
    public void queryOrder() {
        Order order = new Order();
        order.setId("81150608111147527408");
        List<Order> list = this.orderService.query(order);
        System.out.println("list:" + JSON.toJSONString(list));
    }
*/

}
