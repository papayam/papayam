package com.github.papayam.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONReader;
import com.alibaba.fastjson.TypeReference;

import java.io.InputStreamReader;

public class JsonUtil {

    private String jsonReader(String path) {
        JSONReader reader = new JSONReader(new InputStreamReader(JsonUtil.class.getClassLoader().getResourceAsStream("api/"+path+".json")));
        return reader.readString();
    }

    public RequestBean jsonToBean(String path) {
        String json = jsonReader(path);
        if(json == null || "".equals(json)) {
            return null;
        } else {
            RequestBean teacher = JSON.parseObject(json, new TypeReference<RequestBean>() {});
            return teacher;
        }
    }
}
