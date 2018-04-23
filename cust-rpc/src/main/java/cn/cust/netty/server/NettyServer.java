package cn.cust.netty.server;

import cn.cust.netty.handler.ServerHandler;
import cn.cust.netty.constant.Constants;
import cn.cust.netty.factory.ZookeeperFactory;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhangbing on 2018/4/10.
 */
@Configuration
@ComponentScan("cn.cust")
public class NettyServer {
    public static void main(String[] args) {
//        EventLoopGroup parentGroup = new NioEventLoopGroup();
//        EventLoopGroup childGroup = new NioEventLoopGroup();
//        try {
//            /**
//             * unix 的网络的异步编程，
//             * 1、一个group是reactor的事件监听、端口的链接connect、read事件
//             * 2、一个group是处理通道，线程数一般是cpu的两倍
//             */
//            ServerBootstrap bootstrap = new ServerBootstrap();
//            bootstrap.group(parentGroup, childGroup);
//
//            bootstrap
//                    /**
//                     *设置阻塞队列的大小具、体看压力测试
//                     */
//                    .option(ChannelOption.SO_BACKLOG, 128)
//                    /**
//                     * 需要靠linux的自己的握手机制
//                     */
//                    .childOption(ChannelOption.SO_KEEPALIVE, false)
//                    /**
//                     * 选择netty的通道的类型
//                     */
//                    .channel(NioServerSocketChannel.class)
//                    .childHandler(new ChannelInitializer<SocketChannel>() { // (4)
//                        @Override
//                        public void initChannel(SocketChannel ch) throws Exception {
//                            /**
//                             * 将接收的二进制流进行分割，（"/r/n"）作为分隔符
//                             */
//                            ch.pipeline().addLast(new DelimiterBasedFrameDecoder(65535, Delimiters.lineDelimiter()[0]));
//                            /**
//                             * 添加解码器
//                             */
//                            ch.pipeline().addLast(new StringDecoder());
//                            /**
//                             * 网络的配置
//                             */
//                            ch.pipeline().addLast(new IdleStateHandler(60, 45, 20, TimeUnit.SECONDS));
//                            /**
//                             * 自己的业务处理类
//                             */
//                            ch.pipeline().addLast(new ServerHandler());
//                            /**
//                             * 发送数据的时候的编码器
//                             */
//                            ch.pipeline().addLast(new StringEncoder());
//                        }
//                    });
//            /**
//             * 绑定端口：并阻塞sync()
//             */
//            ChannelFuture f = bootstrap.bind(8080).sync();
//            CuratorFramework client = ZookeeperFactory.create();
//            InetAddress netAddress = InetAddress.getLocalHost();
//            client.create().withMode(CreateMode.EPHEMERAL).forPath(Constants.SERVER_PATH+netAddress.getHostAddress());
//            f.channel().closeFuture().sync();
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            parentGroup.shutdownGracefully();
//            childGroup.shutdownGracefully();
//        }

        ApplicationContext context =
                new AnnotationConfigApplicationContext(NettyServer.class);

    }
}
