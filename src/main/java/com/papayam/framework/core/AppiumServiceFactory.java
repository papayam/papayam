package com.papayam.framework.core;

import com.papayam.framework.utils.Log;
import com.papayam.framework.utils.NumberUtil;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import io.appium.java_client.service.local.flags.ServerArgument;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

public class AppiumServiceFactory {

    private Log logger = Log.getLogger(AppiumServiceFactory.class);
    private String serverPort;
    private String bootstrapPort;
    private String chromeDriverPort;
    private String udid;
    private DesiredCapabilities capabilities;
    private AppiumDriverLocalService service;

    public AppiumServiceFactory(DesiredCapabilities capabilities) {
        this.capabilities = capabilities;
        this.serverPort = String.valueOf(capabilities.getCapability("serverPort"));;
        this.bootstrapPort = String.valueOf(capabilities.getCapability("bootstrapPort"));
        this.chromeDriverPort = String.valueOf(capabilities.getCapability("chromeDriverPort"));
        this.udid = String.valueOf(capabilities.getCapability("udid"));
        initService();
    }

    public AppiumDriverLocalService getService() {
        return service;
    }

    private void initService() {
        AppiumServiceBuilder ab = new AppiumServiceBuilder();
        ab.withIPAddress("127.0.0.1");
        ab.usingPort(Integer.valueOf(serverPort));
        ab.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
        ab.withArgument(new ServerArgument() {
            public String getArgument() {
                // TODO Auto-generated method stub
                return "-bp";
            }
        }, bootstrapPort);
        ab.withArgument(new ServerArgument() {
            public String getArgument() {
                // TODO Auto-generated method stub
                return "--udid";
            }
        }, udid);
        ab.withArgument(new ServerArgument() {
            public String getArgument() {
                // TODO Auto-generated method stub
                return "--chromedriver-port";
            }
        }, chromeDriverPort);
        ab.withLogFile(new File(System.getProperty("user.dir")+"/logs/appium_"+new NumberUtil().generateTime()+ ".log"));
        service = ab.build();
    }
}
