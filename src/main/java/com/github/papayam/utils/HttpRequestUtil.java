package com.github.papayam.utils;

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
}