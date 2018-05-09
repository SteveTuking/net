package cn.cust.netty.proxy;


import org.springframework.cglib.proxy.CallbackFilter;

import java.lang.reflect.Method;

/**
 * Created by zhangbing on 2018/3/29.
 */
public class SimpleCallBackfilter implements CallbackFilter {

    @Override
    public int accept(Method method) {
        System.out.println("test ........");
        return 0;
    }
}
