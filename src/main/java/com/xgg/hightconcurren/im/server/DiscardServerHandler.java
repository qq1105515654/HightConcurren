package com.xgg.hightconcurren.im.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

import java.lang.ref.Reference;

/**
 * 丢弃服务，不处理任何消息
 * 处理服务端 channel
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 丢弃收到的服务
//        ((ByteBuf)msg).release();
        ByteBuf in= (ByteBuf) msg;
        try{
            while(in.isReadable()){
                System.out.println((char)in.readByte());
                System.out.flush();
            }

        }finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //出现异常就关闭连接
        cause.printStackTrace();
        ctx.close();
    }
}
