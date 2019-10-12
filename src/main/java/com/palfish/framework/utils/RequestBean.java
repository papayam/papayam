package com.palfish.framework.utils;

import java.util.List;
import java.util.Map;

public class RequestBean {
    private String url;
    private String method;
    private String cookie = "";
    private List<String> params;
    private Map<String, Object> validate;

    public Map<String, Object> getValidate() { return validate; }

    public void setValidate(Map<String, Object> validate) {
        this.validate = validate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getParams() {
        return params;
    }

    public void setParams(List<String> params) {
        this.params = params;
    }

    public String getCookie() { return cookie; }

    public void setCookie(String cookie) { this.cookie = cookie; }

    public String getMethod() { return method; }

    public void setMethod(String method) { this.method = method; }
}
