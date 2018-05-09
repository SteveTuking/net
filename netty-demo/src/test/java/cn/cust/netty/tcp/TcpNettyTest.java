package cn.cust.netty.tcp;

import cn.cust.netty.netty.client.TcpCliect;
import cn.cust.netty.netty.param.Request;
import cn.cust.netty.netty.user.model.User;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

/**
 * Created by zhangbing on 2018/3/11.
 */
public class TcpNettyTest {
    @Test
    public void test(){
        User user = new User();
        user.setId(1);
        user.setName("zhangbing");
        user.setAge("25");

        Request request = new Request();
        request.setContent(user);
        Object send = TcpCliect.send(request);
        System.out.println(JSONObject.toJSONString(send));

//        for (int i = 0; i < 100; i++){
//            new Thread(new RequestThread(request)).start();
//        }
    }

    public static class RequestThread implements Runnable{

        private Request request;

        public RequestThread(Request request) {
            this.request = request;
        }

        @Override
        public void run() {
            Object send = TcpCliect.send(request);
            System.out.println(JSONObject.toJSONString(send));
        }
    }
}
