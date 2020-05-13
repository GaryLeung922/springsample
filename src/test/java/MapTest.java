/**
 * @Author: liangjiaqi
 * @Date: 2020/3/20 8:53 PM
 */

import com.xiaojiaqi.config.MainConfig;
import com.xiaojiaqi.mapper.SimpleSourceDestinationMapper;
import com.xiaojiaqi.pojo.NestableInvocationBO;
import com.xiaojiaqi.pojo.SimpleDestination;
import com.xiaojiaqi.pojo.SimpleSource;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//@Slf4j
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {MainConfig.class})
public class MapTest {

    private SimpleSourceDestinationMapper mapper
            = Mappers.getMapper(SimpleSourceDestinationMapper.class);
//    @Test
//    public void givenSourceToDestination_whenMaps_thenCorrect() {
//        SimpleSource simpleSource = new SimpleSource();
//        simpleSource.setName("SourceName");
//        simpleSource.setDesc("SourceDescription");
//        SimpleDestination destination = mapper.sourceToDestination(simpleSource);
//
//    }


//    @Test
//    public void givenDestinationToSource_whenMaps_thenCorrect() {
//        SimpleDestination destination = new SimpleDestination();
//        destination.setName("DestinationName");
//        destination.setDesc("DestinationDescription");
//        SimpleSource source = mapper.destinationToSource(destination);
//    }

    @Test
    public void aopTest(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(MainConfig.class);

        NestableInvocationBO bean = ac.getBean(NestableInvocationBO.class);

        bean.method2();
        bean.method1();
    }

    public static class Coordinate{
        public int x;
        public int y;

        @Override
        public String toString() {
            return "Coordinate{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    public static class Useage{
        public static void pass(Coordinate coordinate){
            coordinate.x++;
            coordinate.y++;
        }

        public static void passWithNew(Coordinate coordinate){
            coordinate = new Coordinate();
            coordinate.x = 10;
            coordinate.y = 10;
        }

    }

    public static class A{
        public void m(){
            System.out.println("A");
        }
    }
    public static class B extends A{
        public void m(){
            System.out.println("B");
            mm();
        }
        public void mm(){
            System.out.println("BB");
        }
    }
    public static class C extends B{
        public void m(){
            System.out.println("C");
        }
    }
    public static class D extends C{

    }

    @Test
    public void testTest(){
        Coordinate m = new Coordinate();
        m.x = 2;
        m.y = 2;

        Useage.pass(m);
        System.out.println(m);
        Useage.passWithNew(m);
        System.out.println(m);
    }

    @Test
    public void testA(){
        D d = new D();
        C c = d;
        B b = new B();
        A a = b;
        d.m();
        c.m();
        b.m();
        a.m();
       // a.mm();
    }

    @Test
    public void test01(){
        Lock lock = new ReentrantLock();
        lock.lock();
    }



}
