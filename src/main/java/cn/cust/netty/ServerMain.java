package cn.cust.netty;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by zhangbing on 2018/3/4.
 */
public class ServerMain {


    private static volatile boolean running = true;

    public static void main(String args[]) {
        final ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("classpath:applicationContext.xml");

        try {
            applicationContext.start();

            Runtime.getRuntime().addShutdownHook(new Thread(){
                @Override
                public void run() {
                    System.out.println("the jvm is shutting");
                    applicationContext.stop();
                    running = false;
                    ServerMain.class.notify();
                }
            });

            synchronized (ServerMain.class){
                while (running){
                    ServerMain.class.wait();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }

    }

}
