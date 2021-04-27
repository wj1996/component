package com.wj.thread;

public class App {

    public static void main(String[] args) {
        IRequestProcessor saveRequestProcessor = new SaveRequestProcessor();
        new Thread(saveRequestProcessor).start();
        IRequestProcessor printRequestProcessor = new PrintRequestProcessor(saveRequestProcessor);
        new Thread(printRequestProcessor).start();
        IRequestProcessor requestProcessor = new PreRequestProcessor(printRequestProcessor);
        new Thread(requestProcessor).start();

        Request request = new Request();

        requestProcessor.process(request);


    }
}
