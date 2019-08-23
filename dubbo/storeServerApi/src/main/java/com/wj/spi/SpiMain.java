package com.wj.spi;

import com.wj.spi.service.SpiService;

import java.io.IOException;
import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * JDK中的SPI
 */
public class SpiMain {
    public static void main(String[] args) throws IOException {
        //加载接口 把所有的实现找出来
        ServiceLoader<SpiService> spiLoader = ServiceLoader.load(SpiService.class);
        Iterator<SpiService> iteratorSpi = spiLoader.iterator();
        while (iteratorSpi.hasNext()) {
            SpiService spiService = iteratorSpi.next();
            spiService.sayHello();
        }
    }


}
