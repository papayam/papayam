package com.palfish.framework.step;

import com.palfish.framework.command.*;
import com.palfish.framework.core.DriverManagerFactory;
import com.palfish.framework.core.ShareDataManager;
import com.palfish.framework.utils.*;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.openqa.selenium.By;
import org.testng.Assert;


public class BaseStep {
    private Log logger = Log.getLogger(BaseStep.class);

    @Given("^如果控件存在\"([^\"]*)\"$")
    public void if_exists(String arg0) {
        MacroCommand command = new MacroCommand();
        command.add(new AssertArgCommand(arg0,2,3));
        command.add(new AssertExistCommand(arg0));
        command.execute();
    }

    @Given("^打开设备\"([^\"]*)\"$")
    public void open_device(String arg0) {
        OpenDeviceCommand command = new OpenDeviceCommand(arg0);
        command.execute();
    }

    @Given("关闭设备")
    public void quit_device() {
        QuitDeviceCommand command = new QuitDeviceCommand();
        command.execute();
    }

    @Given("^关闭设备\"([^\"]*)\"$")
    public void quit_device(String arg0) {
        QuitDeviceCommand command = new QuitDeviceCommand(arg0);
        command.execute();
    }

    @Given("^切换设备\"([^\"]*)\"$")
    public void change_device(String arg0) {
        ChangeDeviceCommand command = new ChangeDeviceCommand(arg0);
        command.execute();
    }

    @Given("切换到webview")
    public void change_webview() {
        ChangeContextCommand command = new ChangeContextCommand("WEBVIEW");
        command.execute();
    }

    @Given("切换到native")
    public void change_native() {
        ChangeContextCommand command = new ChangeContextCommand("NATIVE");
        command.execute();
    }

    @Given("^发送网络请求\"([^\"]*)\"$")
    public void request(String arg0) {
        MacroCommand command = new MacroCommand();
        command.add(new AssertArgCommand(arg0,1));
        command.add(new RequestCommand(arg0));
        command.execute();
    }

    @When("^点击控件\"([^\"]*)\"$")
    public void clickElement(String arg0) {
        MacroCommand command = new MacroCommand();
        command.add(new AssertArgCommand(arg0,2,3,4,5));
        command.add(new ClickElementCommand(arg0));
        command.execute();
    }

    @When("^点击坐标\"([^\"]*)\"$")
    public void clickXY(String arg0) {
        MacroCommand command = new MacroCommand();
        command.add(new AssertArgCommand(arg0,2));
        command.add(new ClickXYCommand(arg0));
        command.execute();
    }

    @When("^长按控件\"([^\"]*)\"$")
    public void longPress(String arg0) {
        MacroCommand command = new MacroCommand();
        command.add(new AssertArgCommand(arg0,3));
        command.add(new LongPressCommand(arg0));
        command.execute();
    }

    @When("^滑动屏幕\"([^\"]*)\"$")
    public void swipe(String arg0) {
        MacroCommand command = new MacroCommand();
        command.add(new AssertArgCommand(arg0,2,5));
        command.add(new SwipeCommand(arg0));
        command.execute();
    }

    @Given("^课堂划线X坐标\"([^\"]*)\"$")
    public void drawLine_x(String arg0) {
        String[] str = arg0.split(",");
        ElementUtil elementUtil = new ElementUtil();
        By by = elementUtil.getByLocator(str[0], str[1]);
        MobileElement mobileElement = elementUtil.findElement(DriverManagerFactory.getInstance().getDriverManager().getRunningDriver(),by,5);
        if(mobileElement != null) {
            SwipeUtil swipeUtil = new SwipeUtil(DriverManagerFactory.getInstance().getDriverManager().getRunningDriver());
            swipeUtil.swipe(mobileElement,"x",200);
        }
    }

    @Given("^课堂划线Y坐标\"([^\"]*)\"$")
    public void drawLine_y(String arg0) {
        String[] str = arg0.split(",");
        ElementUtil elementUtil = new ElementUtil();
        By by = elementUtil.getByLocator(str[0], str[1]);
        MobileElement mobileElement = elementUtil.findElement(DriverManagerFactory.getInstance().getDriverManager().getRunningDriver(),by,5);
        if(mobileElement != null) {
            SwipeUtil swipeUtil = new SwipeUtil(DriverManagerFactory.getInstance().getDriverManager().getRunningDriver());
            swipeUtil.swipe(mobileElement,"y",500);
        }
    }

    @When("^休眠\"([^\"]*)\"$")
    public void sleep(String arg0) {
        SleepCommand command = new SleepCommand(arg0);
        command.execute();
    }

    @When("^截屏\"([^\"]*)\"$")
    public void takeScreen(String arg0) {
        TakeScreenCommand command = new TakeScreenCommand(arg0);
        command.execute();
    }

    @When("^图像对比\"([^\"]*)\"$")
    public void CompareImage(String arg0) {
        CompareImageCommand command = new CompareImageCommand(arg0);
        command.execute();
    }

    @When("^清除文本\"([^\"]*)\"$")
    public void clearText(String arg0) {
        MacroCommand command = new MacroCommand();
        command.add(new AssertArgCommand(arg0,2,3));
        command.add(new ClearElementCommand(arg0));
        command.execute();
    }

    @When("^发送短信\"([^\"]*)\"$")
    public void getSms(String arg0) {
        RequestBean requestBean = new RequestBean();
        requestBean.setUrl("https://test.ipalfish.com:30000/opapi/alert/sms");
        requestBean.setMethod("get");
        if(arg0 == null || "".equals(arg0)) {
            arg0 = (String) ShareDataManager.getInstance().getShareData("phoneNumber");
        }
        String param = "{\"phone\":\"86-"+arg0+"\"}";
        HttpRequestUtil httpRequestUtil = new HttpRequestUtil();
        Response response = httpRequestUtil.request(requestBean,param);
        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getStatusCode());
        JsonPath jsonPath = new JsonPath(response.asString());
        String text = jsonPath.getString("data.history[0].text");
        if(text != null && text.contains("(")) {
            String smsCode = text.split("\\(")[0];
            ShareDataManager.getInstance().addShareData("smsCode",smsCode);
        }
    }

    @When("获取页面层级结构")
    public void getPageSource() {
        AppiumDriver driver = DriverManagerFactory.getInstance().getDriverManager().getRunningDriver();
        String source = driver.getPageSource();
        logger.debug("------begin getPageSource-------");
        logger.debug(source);
        logger.debug("------end getPageSource-------");
    }

    @When("^输入文本\"([^\"]*)\"$")
    public void sendText(String arg0) {
        MacroCommand command = new MacroCommand();
        command.add(new AssertArgCommand(arg0,3,4));
        command.add(new InputElementCommand(arg0));
        command.execute();
    }

    @When("^隐藏IOS键盘\"([^\"]*)\"$")
    public void HideKeyboard_ios(String arg0) {
        MacroCommand command = new MacroCommand();
        command.add(new AssertArgCommand(arg0,1));
        command.add(new HideKeyboardIOSCommand(arg0));
        command.execute();
    }

    @Then("^断言控件存在\"([^\"]*)\"$")
    public void assertExitElement(String arg0) {
        MacroCommand command = new MacroCommand();
        command.add(new AssertArgCommand(arg0,2,3));
        command.add(new AssertExistCommand(arg0));
        command.execute();
    }

    @Then("^断言控件不存在\"([^\"]*)\"$")
    public void assertNotExitElement(String arg0) {
        MacroCommand command = new MacroCommand();
        command.add(new AssertArgCommand(arg0,2,3));
        command.add(new AssertExistCommand(arg0,false));
        command.execute();
    }

    @Then("^断言文本\"([^\"]*)\"$")
    public void assertText(String arg0) {
        MacroCommand command = new MacroCommand();
        command.add(new AssertArgCommand(arg0,3,4));
        command.add(new AssertTextCommand(arg0));
        command.execute();
    }

    @Then("^断言数组长度\"([^\"]*)\"$")
    public void assertSize(String arg0) {
        MacroCommand command = new MacroCommand();
        command.add(new AssertArgCommand(arg0,3));
        command.add(new AssertSizeCommand(arg0));
        command.execute();
    }
}