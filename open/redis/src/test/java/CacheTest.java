import com.alibaba.fastjson.JSON;
import com.varela.cache.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by lance on 10/14/2015.
 */
@ContextConfiguration("classpath:applicationContext-test.xml")
public class CacheTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void keys() {
        List<String> list = this.redisCache.keys();
        System.out.println(JSON.toJSONString(list));

        list=this.redisCache.keys("insurance:cargo:type");
        System.out.println(JSON.toJSONString(list));
    }

    @Test
    public void setList(){

    }
}
