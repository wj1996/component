package com.wj.thread;

import java.util.concurrent.LinkedBlockingQueue;

public class PrintRequestProcessor implements IRequestProcessor {

    LinkedBlockingQueue<Request> blockingQueue = new LinkedBlockingQueue<>();
    private volatile boolean isFinish = false;

    void setFinish(boolean isFinish) {
        this.isFinish = true;
    }

    private IRequestProcessor nextRequestProcessor;

    public PrintRequestProcessor(IRequestProcessor nextRequestProcessor) {
        this.nextRequestProcessor = nextRequestProcessor;
    }

    @Override
    public void process(Request request) {
        System.out.println("print");
        blockingQueue.add(request);
    }

    @Override
    public void run() {
        while (!isFinish) {
            try {
                Request request = blockingQueue.take();
                if (null != request) {
                    System.out.println("print-data");
                    if (null != nextRequestProcessor)
                        nextRequestProcessor.process(request);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
