import com.xiaojiaqi.config.MainConfig;
import com.xiaojiaqi.config.RedisConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author: liangjiaqi
 * @Date: 2020/6/6 10:19 AM
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MainConfig.class, RedisConfiguration.class})
public class DistributedLockTest {

    //@ShareLock
    public void lockMethod(){

    }
}
