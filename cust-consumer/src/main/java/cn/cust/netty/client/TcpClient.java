package cn.cust.netty.client;


import cn.cust.netty.core.DefaultFuture;
import cn.cust.netty.handler.SimpleClientHandler;
import cn.cust.netty.param.Response;
import cn.cust.netty.zk.ZookeeperFactory;
import com.alibaba.fastjson.JSONObject;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.CuratorWatcher;

import java.util.List;

public class TcpClient {

    static final Bootstrap b=new Bootstrap();
    static ChannelFuture f=null;
    static{
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        b.group(workerGroup); // (2)
        b.channel(NioSocketChannel.class); // (3)
        b.option(ChannelOption.SO_KEEPALIVE, true); // (4)
        b.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            public void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(new DelimiterBasedFrameDecoder(Integer.MAX_VALUE, Delimiters.lineDelimiter()[0]));
                ch.pipeline().addLast(new StringDecoder());
                ch.pipeline().addLast(new SimpleClientHandler());
                ch.pipeline().addLast(new StringEncoder());
            }
        });
        String host = "localhost";
        int port = 8080;
        CuratorFramework client = ZookeeperFactory.create();
        try {
            CuratorWatcher watcher = new ServerWatcher();
            //加上zk监听服务器的变化
            client.getChildren().usingWatcher(watcher ).forPath("/netty");

            List<String> serverPaths = client.getChildren().forPath("/netty");
            for(String serverPath:serverPaths){
                String[] str = serverPath.split("#");
                int weight = Integer.valueOf(str[2]);
                if(weight>0){
                    for(int w=0;w<=weight;w++){
                        ChannelManager.realServerPath.add(str[0]+"#"+str[1]);
                        ChannelFuture  channelFuture = TcpClient.b.connect(str[0], Integer.valueOf(str[1]));
                        ChannelManager.add(channelFuture);
                    }
                }
            }
            if(ChannelManager.realServerPath.size()>0){
                String[] hostAndPort = ChannelManager.realServerPath.toArray()[0].toString().split("#");
                host=hostAndPort[0];
                port=Integer.valueOf(hostAndPort[1]);
            }

        } catch (Exception e1) {
            e1.printStackTrace();
        }

    }

    //注意：1点每一个请求都是同一个连接，并发问题
    //发送数据
    public static Response send(ClientRequest request){
        f=ChannelManager.get(ChannelManager.position);
        f.channel().writeAndFlush(JSONObject.toJSONString(request));
        f.channel().writeAndFlush("\r\n");
        DefaultFuture df = new DefaultFuture(request);
        return df.get();

    }



}

