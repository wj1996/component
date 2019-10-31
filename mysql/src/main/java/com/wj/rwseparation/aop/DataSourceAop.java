package com.wj.rwseparation.aop;

import com.wj.rwseparation.dbutils.DBContextHolder;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 类说明：定义了aop，在进行数据库操作选择主库还是从库
 */
@Aspect
@Component
public class DataSourceAop {
    /*从库的切点,没有标注Master注解，并且方法名为select和get开头的方法，走从库*/
    @Pointcut("!@annotation(com.wj.rwseparation.annotation.Master) " +
            "&& (execution(* com.wj.rwseparation.service..*.select*(..)) " +
            "|| execution(* com.wj.rwseparation.service..*.get*(..))" +
            "|| execution(* com.wj.rwseparation.service..*.find*(..))" +
            "|| execution(* com.wj.rwseparation.service..*.query*(..)))")
    public void slavePointcut() {

    }

    /*主库的切点,或者标注了Master注解或者方法名为insert、update等开头的方法，走主库*/
    @Pointcut("@annotation(com.wj.rwseparation.annotation.Master) " +
            "|| execution(* com.wj.rwseparation.service..*.insert*(..)) " +
            "|| execution(* com.wj.rwseparation.service..*.add*(..)) " +
            "|| execution(* com.wj.rwseparation.service..*.update*(..)) " +
            "|| execution(* com.wj.rwseparation.service..*.edit*(..)) " +
            "|| execution(* com.wj.rwseparation.service..*.delete*(..)) " +
            "|| execution(* com.wj.rwseparation.service..*.remove*(..))")
    public void masterPointcut() {
    }

    @Before("slavePointcut()")
    public void slave() {
        DBContextHolder.slave();
    }

    @Before("masterPointcut()")
    public void master() {
        DBContextHolder.master();
    }
}
