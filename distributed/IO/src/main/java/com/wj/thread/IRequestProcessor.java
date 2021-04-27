package com.wj.thread;

public interface IRequestProcessor extends Runnable {

    void process(Request request);
}
