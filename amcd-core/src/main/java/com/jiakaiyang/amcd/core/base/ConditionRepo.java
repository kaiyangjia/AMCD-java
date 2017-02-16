package com.jiakaiyang.amcd.core.base;

import java.util.concurrent.Callable;

/**
 * Created by jia on 2017/2/16.
 */
public interface ConditionRepo<A> {
    /**
     * 设置目标代码，目标代码是以一个Runnable的形式装载的
     * @param target
     */
    void setTarget(Runnable target);

    /**
     * 设置目标代码，目标代码是以一个Runnable的形式装载的
     * @param target
     * @param needThread
     */
    void setTarget(Runnable target, boolean needThread);


    /**
     设置目标代码，目标代码是以一个Callable的形式装载的
     * @param target
     */
    void setTarget(Callable<A> target);


    /**
     * 设置目标代码，目标代码是以一个Callable的形式装载的
     * @param target
     * @param needThread 为true时表示需要在新线程执行
     */
    void setTarget(Callable<A> target, boolean needThread);


    /**
     添加多个条件，并设置其值为未完成false
     */
    void add(String ... conditionNames);

    /**
     移除多个条件
     */
    void remove(String ... conditionNames);

    /**
     完成某个条件，默认情况下，每完成一个条件之后都会自动判断是否已经完成了，如果已经全部完成了则返回true，否则返回false
     */
    boolean complete(String conditionName);

    /**
     判断是否所有的条件都得到了满足，是的话返回true，否则返回false
     */
    boolean judge();

    /**
     * 执行target，Runnable 和 Callable只有一个会被执行，Runnable会被优先执行
     * @param callback
     */
    void execTarget(ExecCallback<A> callback);
}
