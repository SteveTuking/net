package cn.cust.netty.proxy;

/**
 * Created by zhangbing on 2018/3/29.
 */
public class App {
    public static void main(String args[]){
        SimpleMethodInterceptor cgProxy = new SimpleMethodInterceptor();

        UserDao userDao = (UserDao) cgProxy.createProxy(UserDaoImpl.class, null);

        userDao.queryName();
    }
}
