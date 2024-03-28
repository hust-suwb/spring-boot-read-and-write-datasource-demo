package org.example.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.example.db.helper.MasterSlaveDataSourceMarker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MasterSlaveDataSourceAop {

    private static final Logger log = LoggerFactory.getLogger(MasterSlaveDataSourceAop.class);

    @Pointcut("!@annotation(org.example.annotation.Master) " +
            "&& (execution(* org.example.service..*.select*(..)) " +
            "|| execution(* org.example.service..*.get*(..)))")
    public void readPointcut() {

    }

    @Pointcut("@annotation(org.example.annotation.Master) " +
            "|| execution(* org.example.service..*.insert*(..)) " +
            "|| execution(* org.example.service..*.add*(..)) " +
            "|| execution(* org.example.service..*.update*(..)) " +
            "|| execution(* org.example.service..*.edit*(..)) " +
            "|| execution(* org.example.service..*.delete*(..)) " +
            "|| execution(* org.example.service..*.remove*(..))")
    public void writePointcut() {

    }

    @Before("readPointcut()")
    public void read() {
        log.info("标记为从库");
        MasterSlaveDataSourceMarker.slave();// 只读，从库
    }

    @Before("writePointcut()")
    public void write() {
        log.info("标记为主库");
        MasterSlaveDataSourceMarker.master();// 可写，主库
    }
}