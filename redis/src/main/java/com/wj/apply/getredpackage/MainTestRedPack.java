package com.wj.apply.getredpackage;

public class MainTestRedPack {

    public static void main(String[] args) throws InterruptedException {
        GenRedPack.genHongBao(); //初始化红包

        GetRedPack.getHongBao(); //从红包池中抢红包
    }
}
