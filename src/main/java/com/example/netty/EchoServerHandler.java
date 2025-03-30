package com.example.netty;


import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf in = (ByteBuf) msg;

        System.out.println("서버 수신: " + in.toString(io.netty.util.CharsetUtil.UTF_8));

        ctx.write(in); // 받은 메시지를 그대로 씀
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush(); // write한 내용을 실제로 클라이언트에 전송
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
