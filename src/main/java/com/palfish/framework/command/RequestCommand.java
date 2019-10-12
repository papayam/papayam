package com.palfish.framework.command;

import com.palfish.framework.utils.HttpRequestUtil;
import com.palfish.framework.utils.JsonUtil;
import com.palfish.framework.utils.RequestBean;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.Iterator;
import java.util.Map;

public class RequestCommand implements Command {
    private String path;
    private RequestBean requestBean;

    public RequestCommand(String path) {
        this.path = path;
        this.requestBean = new JsonUtil().jsonToBean(path);
    }

    public RequestCommand(RequestBean requestBean) {
        this.requestBean = requestBean;
    }

    public void execute() {
        Map<String, Object> requestBeanMap = requestBean.getValidate();
        Iterator<String> validateIterator = requestBeanMap.keySet().iterator();
        Iterator<String> requestBeanIterator = requestBean.getParams().iterator();
        while(requestBeanIterator.hasNext()) {
            HttpRequestUtil httpRequestUtil = new HttpRequestUtil();
            Response response = httpRequestUtil.request(requestBean, requestBeanIterator.next());
            Assert.assertNotNull(response);
            Assert.assertEquals(200, response.getStatusCode());
            JsonPath jsonPath = new JsonPath(response.asString());
            while(validateIterator.hasNext()) {
                String validateKey = validateIterator.next();
                Object validateValue = requestBeanMap.get(validateKey);
                Object actual = jsonPath.get(validateKey);
                Assert.assertEquals(validateValue.getClass(),actual.getClass());
                Assert.assertEquals(validateValue,actual);
            }
        }
    }
}
