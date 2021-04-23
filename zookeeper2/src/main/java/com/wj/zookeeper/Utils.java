package com.wj.zookeeper;

import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;


public class Utils {

    public static void printPathChildrenCacheEvent(PathChildrenCacheEvent pathChildrenCacheEvent) {
        if (null == pathChildrenCacheEvent) return;
        System.out.printf("Type:%s",pathChildrenCacheEvent.getType());
        if (null != pathChildrenCacheEvent.getData().getPath()) {
            System.out.printf(",path:%s",pathChildrenCacheEvent.getData().getPath());
        }
        if (null != pathChildrenCacheEvent.getData().getData()) {
            System.out.printf(",data:%s",new String(pathChildrenCacheEvent.getData().getData()));
        }
        System.out.println();
    }

    public static void main(String[] args) {
        PathChildrenCacheEvent pathChildrenCacheEvent = new PathChildrenCacheEvent(PathChildrenCacheEvent.Type.CHILD_ADDED, new ChildData("/watch",null,"hello".getBytes()));
        System.out.println(pathChildrenCacheEvent);
        System.out.printf("Type:%s,path:%s,data:%s\n",pathChildrenCacheEvent.getType(),pathChildrenCacheEvent.getData().getPath(),new String(pathChildrenCacheEvent.getData().getData()));
    }
}

enum T{
    test,test2,test3
}
