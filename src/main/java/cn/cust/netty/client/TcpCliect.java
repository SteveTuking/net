package cn.cust.netty.client;

import cn.cust.core.DefaultFuture;
import cn.cust.netty.param.Request;
import cn.cust.netty.param.Response;
import com.alibaba.fastjson.JSONObject;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * Created by zhangbing on 2018/3/11.
 */
public class TcpCliect {

    public static EventLoopGroup group = null;
    public static Bootstrap boostrap = null;
    public static ChannelFuture future;

    static {
        group = new NioEventLoopGroup();
        boostrap = new Bootstrap();
        boostrap.channel(NioSocketChannel.class);
        boostrap.group(group);
        boostrap.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
        boostrap.option(ChannelOption.SO_KEEPALIVE, true)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch)
                            throws Exception {
                        ch.pipeline().addLast(new DelimiterBasedFrameDecoder
                                (Integer.MAX_VALUE, Delimiters.lineDelimiter()[0]));
                        ch.pipeline().addLast(new StringDecoder());
                        ch.pipeline().addLast(new ClientHandler());
                        ch.pipeline().addLast(new StringEncoder());


                    }
                });

        future = boostrap.connect("127.0.0.1", 8888);
    }


    public static Object send(Request request) {

        try {
            future.channel().writeAndFlush(JSONObject.toJSONString(request));
            future.channel().writeAndFlush("\r\n");
            DefaultFuture defaultFuture = new DefaultFuture(request);
            Response response = defaultFuture.get(10);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;


    }
}
