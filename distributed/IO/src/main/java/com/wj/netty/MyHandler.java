package com.wj.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;

public class MyHandler extends ChannelInitializer {


    @Override
    protected void initChannel(Channel ch) throws Exception {

    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("1");
    }
}
