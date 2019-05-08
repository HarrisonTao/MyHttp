package com.net.util;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程管理工程
 */
public class ThreadFactory {

  private  static ExecutorService fixedThreadPool;

    public   static  Executor getThreadServer(){
        if(fixedThreadPool==null){
             fixedThreadPool= Executors.newFixedThreadPool(10);
        }
        return  fixedThreadPool;
    }


}
