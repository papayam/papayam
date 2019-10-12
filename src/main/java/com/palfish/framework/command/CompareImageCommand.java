package com.palfish.framework.command;

import com.palfish.framework.utils.ImageUtil;
import org.testng.Assert;

public class CompareImageCommand implements Command {
    private String arg;

    public CompareImageCommand(String arg) {
        this.arg = arg;
    }

    public void execute() {
        String[] str = arg.split(",");
        if(str.length == 4) {
            boolean result = new ImageUtil().compareImg(str[0],str[1],Float.valueOf(str[2]));
            Assert.assertEquals(String.valueOf(result),str[3]);
        }
    }
}
