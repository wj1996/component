package com.wj.rwseparation.dbutils;


import com.wj.rwseparation.vo.DBTypeEnum;

/**
 * 类说明：数据源的持有
 */
public class DBContextHolder {
    /*保存系统中存在的数据源的标识符，然后通过该标识符定位到实际的数据源实体*/
    private static final ThreadLocal<DBTypeEnum> contextHolder
            = new ThreadLocal<DBTypeEnum>();

    public static void set(DBTypeEnum dbTypeEnum){
        contextHolder.set(dbTypeEnum);
    }

    public static DBTypeEnum get(){
        return contextHolder.get();
    }

    public static void master(){
        set(DBTypeEnum.MASTER);
        System.out.println("切换到主库-----------------------");
    }

    public static void slave(){
        set(DBTypeEnum.SLAVE);//轮询
        System.out.println("切换到从库-----------------------");
    }

}
