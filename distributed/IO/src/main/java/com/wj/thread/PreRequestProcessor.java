package com.wj.thread;

import java.util.concurrent.LinkedBlockingQueue;

public class PreRequestProcessor implements IRequestProcessor{

    LinkedBlockingQueue<Request> blockingQueue = new LinkedBlockingQueue<>();
    private volatile boolean isFinish = false;

    void setFinish(boolean isFinish) {
        this.isFinish = true;
    }

    private IRequestProcessor nextRequestProcessor;

    public PreRequestProcessor(IRequestProcessor nextRequestProcessor) {
        this.nextRequestProcessor = nextRequestProcessor;
    }

    public PreRequestProcessor() {

    }

    @Override
    public void process(Request request) {
        System.out.println("pre-add");
//        nextRequestProcessor.process();
        blockingQueue.add(request);
    }

    @Override
    public void run() {
        while (!isFinish) {
            try {
                Request request = blockingQueue.take();
                //处理
                if (null != request) {
                    System.out.println("pre");
                    if (null != nextRequestProcessor)
                        nextRequestProcessor.process(request);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
