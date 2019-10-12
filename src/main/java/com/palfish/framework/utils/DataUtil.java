package com.palfish.framework.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataUtil {
    private final String parameterExpression = "(?<=\\{)[^}]*(?=\\})";
    private Log logger = Log.getLogger(DataUtil.class);

    private String parseParameter(String arg) {
        switch (arg) {
            case "generateUsername":
                arg = new NumberUtil().generateUsername();
                break;
            case "generatePassword":
                arg = new NumberUtil().generatePassword();
                break;
            case "generateLoginName":
                arg = new NumberUtil().generateLoginName();
                break;
            case "generateTime":
                arg = new NumberUtil().generateTime();
                break;
            case "generatePhoneNumber":
                arg = new NumberUtil().generatePhoneNumber();
                break;
            case "getUsername":
                arg = new NumberUtil().getUsername();
                break;
            case "getSms":
                arg = new NumberUtil().getSms();
                break;
            default:
                arg = getParameterValue(arg);
        }
        return arg;
    }

    public String filterData(String parameter){
        Pattern p = Pattern.compile(parameterExpression);
        Matcher m = p.matcher(parameter);
        while (m.find()){
            String methodName = m.group();
            String value = parseParameter(methodName);
            if (null != value)
                parameter = parameter.replace("{"+methodName+"}",value) ;
        }
        return parameter;
    }

    private String getParameterValue(String arg) {
        String value = null;
        if(arg != null) {
            String[] str = arg.split("#");
            if(str != null && str.length == 2) {
                try {
                    Class actionClass = Class.forName(str[0]);
                    Method method = actionClass.getMethod(str[1],null);
                    value = (String)method.invoke(null,null);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            } else {
                logger.debug("类名方法名格式不正确");
            }
        } else {
            logger.debug("类名方法名不能为空");
        }
        return value;
    }
}
