import com.xiaojiaqi.entity.Spitter;
import com.xiaojiaqi.pojo.Car;
import com.xiaojiaqi.service.SpitterService;
import com.xiaojiaqi.tx.TxConfig;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.util.stream.Stream;

/**
 * @Author: liangjiaqi
 * @Date: 2020/3/29 1:33 PM
 */
@Slf4j
public class TxTest {

    @Test
    public void test01() throws Throwable {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TxConfig.class);
        SpitterService spitterService = ac.getBean(SpitterService.class);

        spitterService.insert();

        ac.close();
    }

    @Test
    public void test02() throws IntrospectionException {
        BeanInfo beanInfo = Introspector.getBeanInfo(Car.class);

        Stream.of(beanInfo.getPropertyDescriptors()).forEach(propertyDescriptor -> {
            // log.info("{}",propertyDescriptor);
            System.out.println(propertyDescriptor);
        });

    }

    @Test
    public void test03(){
       BeanDefinitionBuilder beanDefinitionBuilder1 = BeanDefinitionBuilder.genericBeanDefinition(Spitter.class);
        beanDefinitionBuilder1.addPropertyValue("username","小假期");
        beanDefinitionBuilder1.addPropertyValue("id",19);

        BeanDefinition beanDefinition = beanDefinitionBuilder1.getBeanDefinition();

        System.out.println(beanDefinition);
    }
}
