package com.github.papayam.command;

import org.testng.Assert;

public class AssertArgCommand implements Command {
    private String arg;
    private int[] count;

    public AssertArgCommand(String arg, int... count) {
        this.arg = arg;
        this.count = count;
    }

    public void execute() {
        if(arg == null) {
            Assert.fail("参数不能为空");
        }
        if(count == null || count.length == 0) {
            Assert.fail("校验个数不能为空");
        }
        boolean result = false;
        String[] str = arg.split(",");
        for(int i = 0; i < count.length; i++) {
            if(str.length == count[i]) {
                result = true;
                break;
            }
        }
        Assert.assertEquals(result, true, "参数个数校验失败");
    }
}
