package cn.cust.netty.handler;

import cn.cust.netty.core.DefaultFuture;
import cn.cust.netty.param.Response;
import com.alibaba.fastjson.JSONObject;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.AttributeKey;

public class SimpleClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if("ping".equals(msg.toString())){
            ctx.channel().writeAndFlush("ping\r\n");
            return ;
        }

		ctx.channel().attr(AttributeKey.valueOf("sssss")).set(msg);
        Response response = JSONObject.parseObject(msg.toString(), Response.class);
        DefaultFuture.recive(response );
//		ctx.channel().close();

    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
    }






}
