package com.papayam.framework.step;

import com.papayam.framework.command.*;
import com.papayam.framework.core.DriverManagerFactory;
import com.papayam.framework.utils.ElementUtil;
import com.papayam.framework.utils.SwipeUtil;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

public class BaseStep {

    @Given("^if_exists\"([^\"]*)\"$")
    public void if_exists(String arg0) {
        MacroCommand command = new MacroCommand();
        command.add(new AssertArgCommand(arg0,2,3));
        command.add(new ElementExistCommand(arg0));
        command.execute();
    }

    @Given("^open_device\"([^\"]*)\"$")
    public void open_device(String arg0) {
        OpenDeviceCommand command = new OpenDeviceCommand(arg0);
        command.execute();
    }

    @Given("quit_device")
    public void quit_device() {
        QuitDeviceCommand command = new QuitDeviceCommand();
        command.execute();
    }

    @Given("^quit_device\"([^\"]*)\"$")
    public void quit_device(String arg0) {
        QuitDeviceCommand command = new QuitDeviceCommand(arg0);
        command.execute();
    }

    @Given("^change_device\"([^\"]*)\"$")
    public void change_device(String arg0) {
        ChangeDeviceCommand command = new ChangeDeviceCommand(arg0);
        command.execute();
    }

    @When("^click\"([^\"]*)\"$")
    public void clickElement(String arg0) {
        MacroCommand command = new MacroCommand();
        command.add(new AssertArgCommand(arg0,2,3));
        command.add(new ClickElementCommand(arg0));
        command.execute();
    }

    @When("^clickXY\"([^\"]*)\"$")
    public void clickXY(String arg0) {
        MacroCommand command = new MacroCommand();
        command.add(new AssertArgCommand(arg0,2));
        command.add(new ClickXYCommand(arg0));
        command.execute();
    }

    @When("^longPress\"([^\"]*)\"$")
    public void longPress(String arg0) {
        MacroCommand command = new MacroCommand();
        command.add(new AssertArgCommand(arg0,3));
        command.add(new LongPressCommand(arg0));
        command.execute();
    }

    @When("^swipe\"([^\"]*)\"$")
    public void swipe(String arg0) {
        MacroCommand command = new MacroCommand();
        command.add(new AssertArgCommand(arg0,2,5));
        command.add(new SwipeCommand(arg0));
        command.execute();
    }

    @Given("^drawLine_x\"([^\"]*)\"$")
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

    @Given("^drawLine_y\"([^\"]*)\"$")
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

    @When("^sleep\"([^\"]*)\"$")
    public void sleep(String arg0) {
        SleepCommand command = new SleepCommand(arg0);
        command.execute();
    }

    @When("^takeScreen\"([^\"]*)\"$")
    public void takeScreen(String arg0) {
        TakeScreenCommand command = new TakeScreenCommand(arg0);
        command.execute();
    }

    @When("^CompareImage\"([^\"]*)\"$")
    public void CompareImage(String arg0) {
        CompareImageCommand command = new CompareImageCommand(arg0);
        command.execute();
    }

    @When("^clear\"([^\"]*)\"$")
    public void clearText(String arg0) {
        MacroCommand command = new MacroCommand();
        command.add(new AssertArgCommand(arg0,2,3));
        command.add(new ClearElementCommand(arg0));
        command.execute();
    }

    @When("^input\"([^\"]*)\"$")
    public void sendText(String arg0) {
        MacroCommand command = new MacroCommand();
        command.add(new AssertArgCommand(arg0,3,4));
        command.add(new InputElementCommand(arg0));
        command.execute();
    }

    @Then("^assert_exists\"([^\"]*)\"$")
    public void assertExitElement(String arg0) {
        MacroCommand command = new MacroCommand();
        command.add(new AssertArgCommand(arg0,2,3));
        command.add(new ElementExistCommand(arg0));
        command.execute();
    }

    @Then("^assert_notExists\"([^\"]*)\"$")
    public void assertNotExitElement(String arg0) {
        MacroCommand command = new MacroCommand();
        command.add(new AssertArgCommand(arg0,2,3));
        command.add(new ElementExistCommand(arg0,false));
        command.execute();
    }

    @Then("^hideKeyboard_ios\"([^\"]*)\"$")
    public void HideKeyboard_ios(String arg0) {
        MacroCommand command = new MacroCommand();
        command.add(new AssertArgCommand(arg0,1));
        command.add(new HideKeyboardIOSCommand(arg0));
        command.execute();
    }
}