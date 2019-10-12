package com.palfish.framework.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageUtil {

    private Log logger=Log.getLogger(ImageUtil.class);
    private String screen_shot_root = System.getProperty("user.dir") + File.separator + "screenshot" + File.separator;

    private int[] getData(String name) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(screen_shot_root + name + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedImage slt = new BufferedImage(100,100,BufferedImage.TYPE_INT_RGB);
        slt.getGraphics().drawImage(img,0,0,100,100,null);
        int[] data = new int[256];
        for(int x = 0;x<slt.getWidth();x++){
            for(int y = 0;y<slt.getHeight();y++){
                int rgb = slt.getRGB(x,y);
                Color myColor = new Color(rgb);
                int r = myColor.getRed();
                int g = myColor.getGreen();
                int b = myColor.getBlue();
                data[(r+g+b)/3]++;
            }
        }
        return data;
    }
    private float compare(int[] s,int[] t){
        float result = 0F;
        for(int i = 0;i<256;i++){
            int abs = Math.abs(s[i]-t[i]);
            int max = Math.max(s[i],t[i]);

            result += (1-((float)abs/(max==0?1:max)));

        }
        return (result/256)*100;
    }
    public boolean compareImg(String srcName,String desName,float f) {
        float result = compare(getData(srcName), getData(desName));
        logger.debug("图片相似度："+result);
        if(result>=f){
            return true;
        }else{
            return false;
        }

    }
}
