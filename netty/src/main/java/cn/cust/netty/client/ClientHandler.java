package cn.cust.netty.client;

import cn.cust.core.DefaultFuture;
import cn.cust.netty.param.Response;
import com.alibaba.fastjson.JSONObject;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ClientHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		if("ping".equals(msg.toString())){
			ctx.channel().writeAndFlush("ping\r\n");
			return ;
		}

		Response res = JSONObject.parseObject(msg.toString(), Response.class);
		DefaultFuture.recive(res);
		System.out.println("客户端返回数据==="+msg.toString());
	}
	
	

}
