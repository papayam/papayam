package com.palfish.framework.utils;

import com.alibaba.fastjson.JSONObject;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class HttpRequestUtil {

    public Response post(String apiUrl, String param, String cookie) {
        Response response = given()
                .relaxedHTTPSValidation()
                .header("Cookie",cookie)
                .contentType("application/json")
                .log().all()
                .request()
                .body(param)
                .when()
                .post(apiUrl);
        response.print();
        return response;
    }

    public Response get(String apiUrl, Map param, String cookie) {
        Response response = given()
                .relaxedHTTPSValidation()
                .header("Cookie",cookie)
                .log().all()
                .request()
                .params(param)
                .when()
                .get(apiUrl);
        response.print();
        return response;
    }

    public Response request(RequestBean requestBean, String param) {
        Response response = null;
        String apiUrl = requestBean.getUrl();
        String cookie = requestBean.getCookie();
        if(cookie == null || "".equals(cookie)) {
            ProUtil proUtil = new ProUtil("api");
            cookie = proUtil.getKey("cookie");
        }
        String method = requestBean.getMethod();
        switch (method) {
            case "post":
                response = post(apiUrl,param,cookie);
                break;
            case "get":
                response = get(apiUrl,JSONObject.parseObject(param),cookie);
                break;
        }
        return response;
    }

    public static void main(String[] args) {
        RequestBean requestBean = new RequestBean();
        requestBean.setUrl("https://test.ipalfish.com:30000/opapi/alert/sms?phone=86-72166668581");
        requestBean.setMethod("get");
        requestBean.setCookie("utype=op; user=zhanghao1; id=MjAwODE=; name=5byg5rWp; email=; phone=ODYtMTM2ODMyMzg1ODE=; groups=WyI4NDMzOTQiLCJkZWZhdWx0IiwicmQiLCJkdXdvIiwiY2xpZW50Il0=; token=90c6cf039d2002941155816dd187b0a1; logintype=0");
        String param = "{\"phone\":\"86-72166668581\"}";
        HttpRequestUtil httpRequestUtil = new HttpRequestUtil();
        httpRequestUtil.request(requestBean,param);
    }
}