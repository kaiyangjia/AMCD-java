package com.jiakaiyang.amcd.core.base;

/**
 * Created by jia on 2017/2/16.
 * 包含是否在新线程执行的Runnable
 */
public interface BaseRunnable extends Runnable {
    public boolean needThread();
}
