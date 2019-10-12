package com.palfish.framework.utils;

import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;

public class CapabilitiesUtil {
    private DesiredCapabilities capabilities;
    private SystemPortUtil[] portUtils;

    public CapabilitiesUtil(String propName, SystemPortUtil... portUtils) {
        this.portUtils = portUtils;
        initCapabilities(propName);
    }

    public DesiredCapabilities getCapabilities() {
        return capabilities;
    }

    private void initCapabilities(String propName) {
        ProUtil proUtil = new ProUtil(propName);
        HashMap<String, String> keyValues = proUtil.getAllKeyValue();
        keyValues.put("serverPort",String.valueOf(portUtils[0].getPortAndFree()));
        keyValues.put("wdaLocalPort",String.valueOf(portUtils[1].getPortAndFree()));
        keyValues.put("bootstrapPort",String.valueOf(portUtils[2].getPortAndFree()));
        keyValues.put("chromeDriverPort",String.valueOf(portUtils[3].getPortAndFree()));
        keyValues.put("systemPort",String.valueOf(portUtils[4].getPortAndFree()));
        DesiredCapabilities caps = new DesiredCapabilities(keyValues);
        this.capabilities = caps;
    }
}
