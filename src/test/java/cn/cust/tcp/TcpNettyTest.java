package cn.cust.tcp;

import cn.cust.netty.client.TcpCliect;
import cn.cust.netty.param.Request;
import cn.cust.netty.user.model.User;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.UUID;

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
    }
}
