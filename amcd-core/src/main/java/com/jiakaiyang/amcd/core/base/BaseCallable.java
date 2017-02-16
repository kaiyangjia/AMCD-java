package com.jiakaiyang.amcd.core.base;

import java.util.concurrent.Callable;

/**
 * Created by jia on 2017/2/16.
 */
public interface BaseCallable<V> extends Callable {
    public boolean needThread();
}
