package com.zy.learning.beans;

import io.netty.channel.ChannelHandlerContext;

/**
 * Created by zhougb on 2016/9/18.
 */
public class NettyCxtMsEvent {
    private ChannelHandlerContext ctx;

    private  MsEvent msEvent;

    public NettyCxtMsEvent(ChannelHandlerContext ctx, MsEvent msEvent) {
        this.ctx = ctx;
        this.msEvent = msEvent;
    }

    public ChannelHandlerContext getCtx() {
        return ctx;
    }

    public void setCtx(ChannelHandlerContext ctx) {
        this.ctx = ctx;
    }

    public MsEvent getMsEvent() {
        return msEvent;
    }

    public void setMsEvent(MsEvent msEvent) {
        this.msEvent = msEvent;
    }
}
