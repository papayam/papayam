package com.palfish.framework.core;

import java.util.HashMap;

public class ShareDataManager {
    private static ShareDataManager instance = new ShareDataManager();
    private HashMap<String,Object> ShareDataMap = new HashMap<>();

    private ShareDataManager(){}

    public static ShareDataManager getInstance() {
        return instance;
    }

    public ShareDataManager addShareData(String key, Object value) {
        ShareDataMap.put(Thread.currentThread().getId()+key,value);
        return this;
    }

    public Object getShareData(String key) {
        return ShareDataMap.get(Thread.currentThread().getId()+key);
    }
}
