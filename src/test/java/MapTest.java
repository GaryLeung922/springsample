/**
 * @Author: liangjiaqi
 * @Date: 2020/3/20 8:53 PM
 */

import com.xiaojiaqi.config.MainConfig;
import com.xiaojiaqi.config.RedisConfiguration;
import com.xiaojiaqi.distributedLock.RedisLock;
import com.xiaojiaqi.external.A;
import com.xiaojiaqi.mapper.SimpleSourceDestinationMapper;
import com.xiaojiaqi.pojo.NestableInvocationBO;
import com.xiaojiaqi.pojo.SimpleDestination;
import com.xiaojiaqi.pojo.SimpleSource;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MainConfig.class, RedisConfiguration.class})
public class MapTest {

    @Autowired
    private ApplicationContext applicationContext;

    private int count = 0;

    private int NUM = 99999;


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

        //com.xiaojiaqi.external.A bean = applicationContext.getBean(com.xiaojiaqi.external.A.class);
        //bean.a();

        User user = new User();
        user.setAge(11);
        user.setName("sadf");
        AtomicStampedReference a = new AtomicStampedReference(user, 0);
        a.getReference();
        Exchanger<String> ex = new Exchanger<>();

        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                String A = "流水A";
                try {
                    String s = ex.exchange(A);
                    System.out.println("A:"+s);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                String A = "流水B";
                try {
                    String s = ex.exchange(A);
                    System.out.println("B:"+s);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


//        NestableInvocationBO bean = ac.getBean(NestableInvocationBO.class);
//
//        bean.method2();
//        bean.method1();
    }

    @Test
    public void testRedisLock() throws InterruptedException {
        RedisLock lock = applicationContext.getBean("redisLock", RedisLock.class);
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(NUM,NUM,10, TimeUnit.SECONDS, new LinkedBlockingQueue<>());

        CountDownLatch countDownLatch = new CountDownLatch(NUM);
        for(int i=0;i<NUM;i++){
            poolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    count++;
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        System.out.println(count);
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

    @Data
    static class User {
        private String name;
        private Integer age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }



}
