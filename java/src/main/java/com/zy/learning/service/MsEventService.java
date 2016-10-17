package com.zy.learning.service;

import com.zy.learning.beans.NettyCxtMsEvent;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;

public class MsEventService {
/**
 * Created by zhougb on 2016/9/18.
 */
    private static final Logger logger = LoggerFactory.getLogger(MsEventService.class);

    public void doMsEvent(NettyCxtMsEvent nettyCxtMsEvent){

        PooledByteBufAllocator pooledByteBufAllocator = PooledByteBufAllocator.DEFAULT;
        ByteBuf byteBuf = pooledByteBufAllocator.buffer(1024);
        logger.info("doMsEvent start1 {}", byteBuf);
        byteBuf.writeCharSequence("abcdefasdfasdfasdfasdfasdfdasfssssasssss", Charset.defaultCharset());
        nettyCxtMsEvent.getCtx().writeAndFlush(byteBuf);
        logger.info("doMsEvent end1 {}"+byteBuf);


        //pooledByteBufAllocator.

        byteBuf = pooledByteBufAllocator.buffer(1024);
        logger.info("doMsEvent start2 {}", byteBuf);
        byteBuf.writeCharSequence("abcdefasdfasdfasdfasdfasdfdasfssssasssss", Charset.defaultCharset());
        nettyCxtMsEvent.getCtx().writeAndFlush(byteBuf);

    }
}
