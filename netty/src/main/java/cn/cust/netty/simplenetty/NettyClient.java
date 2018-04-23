//package cn.cust.simplenetty;
//
//
//import Request;
//import User;
//import com.alibaba.fastjson.JSONObject;
//import io.netty.bootstrap.Bootstrap;
//import io.netty.buffer.PooledByteBufAllocator;
//import io.netty.channel.*;
//import io.netty.channel.nio.NioEventLoopGroup;
//import io.netty.channel.socket.nio.NioSocketChannel;
//import io.netty.handler.codec.DelimiterBasedFrameDecoder;
//import io.netty.handler.codec.Delimiters;
//import io.netty.handler.codec.string.StringDecoder;
//import io.netty.handler.codec.string.StringEncoder;
//import io.netty.util.AttributeKey;
//import netscape.javascript.JSObject;
//
//import java.io.IOException;
//import java.util.UUID;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
//public class NettyClient {
//	public static EventLoopGroup group=null;
//	public static Bootstrap boostrap=null;
//	static{
//		 group = new NioEventLoopGroup();
//		 boostrap = new Bootstrap();
//		boostrap.channel(NioSocketChannel.class);
//		boostrap.group(group);
//		boostrap.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
//		boostrap.option(ChannelOption.SO_KEEPALIVE, true)
//		             .handler(new ChannelInitializer<NioSocketChannel>() {
//						@Override
//						protected void initChannel(NioSocketChannel ch)
//								throws Exception {
//							ch.pipeline().addLast(new DelimiterBasedFrameDecoder
//									(Integer.MAX_VALUE,Delimiters.lineDelimiter()[0]));
//							ch.pipeline().addLast(new StringDecoder());
//							ch.pipeline().addLast(new ClientHandler());
//							ch.pipeline().addLast(new StringEncoder());
//
//
//						}
//					});
//	}
//	public static void main(String[] args) throws IOException {
//
//		try{
//			ChannelFuture future = boostrap.connect("localhost", 8080).sync();
//
////			ExecutorService executorService = Executors.newFixedThreadPool(1);
////			executorService.execute(new ClientMutiThread(future.channel()));
//
////			Thread client = new Thread(new ClientMutiThread(future.channel()));
////			client.start();
//
//			User user = new User();
//			user.setId(1);
//			user.setAge("25");
//			user.setName("zhangbing");
//
//			Request request = new Request();
//			request.setCommand("saveUser");
//			request.setContent(user);
//
//			future.channel().writeAndFlush(JSONObject.toJSONString(request));
//			future.channel().writeAndFlush("\r\n");
//
//			future.channel().closeFuture().sync();
//
//			Object result = future.channel().attr(AttributeKey.valueOf("ChannelKey")).get();
//			System.out.println(result);
//		}catch(Exception e){
//			e.printStackTrace();
//		}finally{
//			group.shutdownGracefully();
//		}
//	}
//
//}
