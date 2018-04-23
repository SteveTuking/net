package cn.cust.netty.proxy;


import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by zhangbing on 2018/3/29.
 */
public class SimpleMethodInterceptor implements MethodInterceptor {

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("proxy control!");
        //注意，如果使用invoke方法，并且参数也为代理类，则会循环进入此拦截方法从而导致爆栈
        //invokeSuper即为调用原生的方法
        return proxy.invokeSuper(obj, args);
    }

    public Object createProxy(Class<?> clazz, Class<?>[] interfaces) {
        Enhancer enhancer = new Enhancer();
        //设置需要代理的类
        enhancer.setSuperclass(clazz);
        //设置需要代理的接口
        enhancer.setInterfaces(interfaces);
        //设置处理方法的回调函数
        enhancer.setCallback(this);
        //设置回掉函数的过滤方法
        enhancer.setCallbackFilter(new SimpleCallBackfilter());
        return enhancer.create();
    }
}
