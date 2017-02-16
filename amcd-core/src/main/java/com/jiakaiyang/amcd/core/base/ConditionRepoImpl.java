package com.jiakaiyang.amcd.core.base;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * Created by jia on 2017/2/16.
 */
public class ConditionRepoImpl<A> implements ConditionRepo<A> {
    private BaseRunnable targetRunnable;
    private BaseCallable<A> targetCallable;
    private A result;
    private Map<String, Boolean> conditionMap;
    private ExecCallback callback;

    public ConditionRepoImpl(){
        conditionMap = new HashMap<String, Boolean>();
    }

    public void setTarget(Runnable target) {
        setTarget(target, false);
    }

    public void setTarget(final Runnable target, final boolean needThread) {
        this.targetRunnable = new BaseRunnable() {
            public boolean needThread() {
                return needThread;
            }

            public void run() {
                target.run();
                if (callback != null) {
                    callback.onComplete();
                }
            }
        };
    }

    public void setTarget(final Callable<A> target) {
        setTarget(target, false);
    }

    public void setTarget(final Callable<A> target, final boolean needThread) {
        this.targetCallable = new BaseCallable<A>() {
            public boolean needThread() {
                return needThread;
            }

            public A call() throws Exception {
                A result = target.call();
                if (callback != null) {
                    callback.onComplete();
                    callback.onResult(result);
                }
                return result;
            }
        };
    }

    public void add(String... conditionNames) {
        for (String condition : conditionNames) {
            if (condition != null) {
                conditionMap.put(condition, false);
            }
        }
    }

    public void remove(String... conditionNames) {
        for (String condition : conditionNames) {
            if (conditionMap.containsKey(condition)) {
                conditionMap.remove(condition);
            }
        }
    }

    public boolean complete(String conditionName) {
        //如果该条件没有被添加过的话直接返回false
        if (conditionMap.containsKey(conditionName)) {
            conditionMap.put(conditionName, true);
            return judge();
        } else {
            return false;
        }
    }

    public boolean judge() {
        for (Boolean value : conditionMap.values()) {
            if (!value) {
                //有不成立的就返回false
                return false;
            }
        }
        return true;
    }

    public void execTarget(ExecCallback<A> callback) {
        if (targetRunnable != null) {
            if (targetRunnable.needThread()) {
                new Thread(targetRunnable){}.start();
            } else {
                targetRunnable.run();
                callback.onComplete();
            }
        } else if (targetCallable != null) {
            //TODO Callable的执行
        }
    }
}
