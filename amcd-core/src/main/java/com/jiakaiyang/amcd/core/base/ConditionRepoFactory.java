package com.jiakaiyang.amcd.core.base;

/**
 * Created by jia on 2017/2/16.
 */
public class ConditionRepoFactory {
    private static ConditionRepoFactory instance;

    public static ConditionRepoFactory getInstance(){
        if (instance == null) {
            synchronized (ConditionRepoFactory.class){
                if (instance == null) {
                    instance = new ConditionRepoFactory();
                }
            }
        }

        return instance;
    }

    public ConditionRepo create(){
        ConditionRepo repo = new ConditionRepoImpl();

        return repo;
    }
}
