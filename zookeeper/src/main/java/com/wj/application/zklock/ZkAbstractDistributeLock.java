package com.wj.application.zklock;

import org.I0Itec.zkclient.ZkClient;


public abstract class ZkAbstractDistributeLock extends AbstractLock{


    protected static final String CONNECTSTRING = "10.0.0.141:2181";
    protected ZkClient zkClient = new ZkClient(CONNECTSTRING);
    protected static final String PATH = "/lock";
    protected static final String PATH2 = "/lock2";


}
