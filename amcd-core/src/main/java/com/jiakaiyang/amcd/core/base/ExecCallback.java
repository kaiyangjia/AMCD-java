package com.jiakaiyang.amcd.core.repo;

/**
 * Created by jia on 2017/2/16.
 */
public interface ExecCallback<A> {

    /**
     * 执行完成
     */
    public void onComplete();

    /**
     * 执行错误
     */
    public void onError();

    /**
     * 执行结果
     * @param result
     */
    public void onResult(A result);
}
