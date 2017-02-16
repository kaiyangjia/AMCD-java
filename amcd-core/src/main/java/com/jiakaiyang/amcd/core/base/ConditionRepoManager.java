package com.jiakaiyang.amcd.core.repo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jia on 2017/2/16.
 *
 */
public class ConditionRepoManager{
    private Map<String, ConditionRepo> repoMap;
    private static ConditionRepoManager instance;

    private ConditionRepoManager(){
        repoMap = new HashMap<String, ConditionRepo>();
    }

    public static ConditionRepoManager getInstance(){
        if (instance == null) {
            synchronized (ConditionRepoManager.class){
                if (instance == null) {
                    instance = new ConditionRepoManager();
                }
            }
        }

        return instance;
    }

    /**
     创建一个实例
     */
    public ConditionRepo create (String repoId){
        ConditionRepo repo = ConditionRepoFactory.getInstance().create();
        repoMap.put(repoId, repo);
        return repo;
    }

    /**
     * 获取指定id的repo，如果已经存在的话直接返回存在的，否则新建一个并返回
     * @param repoId
     * @return
     */
    public ConditionRepo obtain (String repoId){
        if (repoMap.containsKey(repoId)) {
            return repoMap.get(repoId);
        }

        return create(repoId);
    }

    /**
     获取指定的仓库
     */
    public ConditionRepo getRepo(String repoId){
        if (repoMap.containsKey(repoId)) {
            return repoMap.get(repoId);
        }
        return null;
    }

    /**
     删除一个仓库，成功返回true，否则返回false
     */
    public boolean removeRepo(String repoId){
        if (repoMap.containsKey(repoId)
                && repoMap.remove(repoId) != null) {
            return true;
        }
        return false;
    }
}
