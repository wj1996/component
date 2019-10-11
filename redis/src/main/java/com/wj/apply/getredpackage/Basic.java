package com.wj.apply.getredpackage;

public class Basic {

    public static int hongBaoCount = 1000;

    public static int threadCount = 20;
    public static String hongBaoPoolKey = "hongBaoPoolKey";
    public static String hongBaoDetailListKey = "hongBaoDetailListKey";
    public static String userIdRecordKey = "userIdRecordKey";


    public static String getHongBaoScript =
            "if redis.call('hexists',KEYS[3],KEYS[4]) ~= 0 then\n" +
                "return nil\n" +
            "else\n" +
                //从红包池中取出一个小红包
                "local hongBao = redis.call('rpop',KEYS[1]);\n" +
                "if hongBao then\n" +   //判断红包池的红包是否不为空
                    "local x = cjson.decode(hongBao);\n" +
                    "x['userId'] = KEYS[4];\n" +  //将红包信息与用户ID信息绑定，表示该用户已经抢到红包
                    "local re = cjson.encode(x);\n" +
                    "redis.call('hset',KEYS[3],KEYS[4],'1');\n" +  //记录用户已经抢过 userIdRecordKey
                    "redis.call('lpush',KEYS[2],re);\n" +    //将抢红包的结果详情存入hongBaoDetailListKey
                    "return re;\n" +
                    "end\n" +
            "end\n" +
            "return nil";

}
