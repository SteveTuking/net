package cn.cust.netty.medium;

import cn.cust.netty.annotation.Remote;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;

@Component
public class InitialMedium implements BeanPostProcessor{

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        if(bean.getClass().isAnnotationPresent(Remote.class)){
            Method[] methods = bean.getClass().getDeclaredMethods();
            for(Method m: methods){
                //.getInterfaces()[0]  获取第一个接口
                String key = bean.getClass().getInterfaces()[0].getName()+"."+m.getName();
                Map<String,BeanMethod> beanMap = Media.beanMap;
                BeanMethod beanMethod = new BeanMethod();
                beanMethod.setBean(bean);
                beanMethod.setMethod(m);
                beanMap.put(key, beanMethod );
            }
        }

        return bean;
    }



}
