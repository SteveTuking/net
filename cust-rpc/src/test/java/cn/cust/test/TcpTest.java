package cn.cust.test;

import cn.cust.netty.client.ClientRequest;
import cn.cust.netty.client.TcpClient;
import cn.cust.netty.param.Response;
import cn.cust.user.model.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangbing on 2018/4/23.
 */
public class TcpTest {

    @Test
    public void testGetResponse(){
        ClientRequest request = new ClientRequest();
        request.setContent("测试tcp长连接请求");
        Response resp = TcpClient.send(request);
        System.out.println(resp.getResult());
    }

    @Test
    public void testSaveUser(){
        ClientRequest request = new ClientRequest();
        User u = new User();
        u.setId(1);
        u.setName("张三");
        request.setCommand("cn.cust.user.controller.UserController.saveUser");
        request.setContent(u);
        Response resp = TcpClient.send(request );
        System.out.println(resp.getResult());
    }


    @Test
    public void testSaveUsers(){
        ClientRequest request = new ClientRequest();
        List<User> users = new ArrayList<User>();
        User u = new User();
        u.setId(1);
        u.setName("张三");
        users.add(u);
        request.setCommand("cn.cust.user.controller.UserController.saveUsers");
        request.setContent(users);
        Response resp = TcpClient.send(request );
        System.out.println(resp.getResult());
    }
}
