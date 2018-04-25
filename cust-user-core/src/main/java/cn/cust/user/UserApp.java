package cn.cust.user;

import cn.cust.netty.server.NettyServer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by zhangbing on 2018/4/25.
 */
@Configuration
@ComponentScan("cn.cust")
public class UserApp {
    public static void main(String args[]){
        ApplicationContext context =
                new AnnotationConfigApplicationContext(NettyServer.class);
    }
}
