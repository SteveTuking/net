package cn.cust.simplenetty;


import io.netty.channel.Channel;
import io.netty.util.AttributeKey;

/**
 * Created by zhangbing on 2017/12/28.
 */
public class ClientMutiThread implements Runnable {


    private Channel channel;

    public ClientMutiThread(Channel channel){
        this.channel = channel;
    }

    public void run() {
        channel.writeAndFlush(Thread.currentThread().getName()+"send message to nettyserver\r\n");
        Object msg = channel.attr(AttributeKey.valueOf("ChannelKey")).get();
        if(msg==null){
            System.out.println("netty server no response");
            return;
        }
        System.out.println(msg.toString());
    }
}
