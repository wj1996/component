package com.wj.disruptor;

import com.lmax.disruptor.EventHandler;

public class LongEventHandler implements EventHandler<LongEvent> {


    public static long count = 0;

    /**
     *
     * @param longEvent
     * @param l
     * @param b  是否为最后一个参数
     * @throws Exception
     */
    public void onEvent(LongEvent longEvent, long l, boolean b) throws Exception {
        count++;
        System.out.println(Thread.currentThread().getName() + "," + longEvent.toString()
        +",sequence:" + l);
    }
}
