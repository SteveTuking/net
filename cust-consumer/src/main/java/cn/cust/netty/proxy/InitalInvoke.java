package cn.cust.netty.proxy;

import cn.cust.netty.annotation.RemoteInvoke;
import cn.cust.netty.client.ClientRequest;
import cn.cust.netty.client.TcpClient;
import cn.cust.netty.param.Response;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Component
public class InitalInvoke implements BeanPostProcessor{

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object 	postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        System.out.println(bean.getClass().getName());
        Field[] fields = bean.getClass().getDeclaredFields();
        for(Field f: fields){
            if(f.isAnnotationPresent(RemoteInvoke.class)){
                RemoteInvoke remoteInvoke = f.getAnnotation(RemoteInvoke.class);
                f.setAccessible(true);
                Enhancer enhancer = new Enhancer();
                enhancer.setInterfaces(new Class[]{f.getType()});
                final Map<Method,Class> methodMap=new HashMap<Method,Class>();
                putMethod(methodMap, f);
                enhancer.setCallback(new MethodInterceptor() {
                    @Override
                    public Object intercept(Object instance, Method method, Object[] args, MethodProxy poxy) throws Throwable {

                        //调用netty客户端去处理
                        ClientRequest request = new ClientRequest();
                        request.setCommand(methodMap.get(method).getName()+"."+method.getName());
                        request.setContent(args[0]);
                        Response resp = TcpClient.send(request );
//						Class<?>returnType = method.getReturnType();
                        return resp;
                    }
                });

                try {
                    f.set(bean, enhancer.create());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return bean;
    }


    public static void putMethod(Map<Method,Class> methodMap,Field f){
        for(Method m: f.getType().getDeclaredMethods()){
            methodMap.put(m, f.getType());
        }
    }
}
