package com.ccy.tank;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 帮我们把图片的内容先加载进来,坦克有四个方向不同的图片,朝哪个方向画哪个图片
 */
public class ResourceMgr {
    //如果用了final修饰,就要赋值
    public static BufferedImage goodTankL,goodTankU,goodTankR,goodTankD;
    public static BufferedImage badTankL,badTankU,badTankR,badTankD;
    public static BufferedImage bulletL,bulletU,bulletR,bulletD;
    public static BufferedImage[] explodes=new BufferedImage[16];
    //resourceMgr.class文件load内存的时候,静态代码块自动执行,bufferImage全都初始化,坦克直接使用就行
   static {
       try {
           goodTankU=ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/GoodTank1.png"));
           goodTankL=ImageUtil.rotateImage(goodTankU,-90);
           goodTankR=ImageUtil.rotateImage(goodTankU,90);
           goodTankD=ImageUtil.rotateImage(goodTankU,180);

           badTankU=ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/BadTank1.png"));
           badTankL=ImageUtil.rotateImage(badTankU,-90);
           badTankR=ImageUtil.rotateImage(badTankU,90);
           badTankD=ImageUtil.rotateImage(badTankU,180);

           bulletU=ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletL.gif"));
           bulletD=ImageUtil.rotateImage(bulletU,180);
           bulletR=ImageUtil.rotateImage(bulletU,90);
           bulletL=ImageUtil.rotateImage(bulletU,-90);
           //爆炸的图片放到内存中
           for (int i = 0; i < 16; i++) {
               explodes[i]=ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/e"+(i+1)+".gif"));
           }

       } catch (IOException e) {
           e.printStackTrace();
       }
   }

}
