package com.ccy.tank;

import org.junit.Test;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.*;

public class TankTest {

    @Test
    public void getDir() {
        //里面不是空值,测试就会通过
        assertNotNull(new Object());
        //把一张图片从硬盘上拿到内存里
        try {
            BufferedImage image=ImageIO.read(new File("/Users/ccy/Desktop/qq.png"));
            assertNotNull(image);//测试通过,表示文件已经放到内存上了,断言就是来判断条件成不成立,条件成立就通过,不成立就不通过
            //在硬盘上的class文件,全都被classLoader 加载到内存中,每一个class在内存中就是Class类的对象,这种写法的好处是只要把需要的图片打包到项目中,扔到每一个机器中都可以使用
            //每一个类都有一个classLoader,很多类都是同一个classLoader
            Class<TankTest> tankTestClass = TankTest.class;
            //通过找到class文件的对象,然后getClassLoader,找到是哪个机器把class文件加载到内存中的,然后让他去找文件
            BufferedImage image2=ImageIO.read(TankTest.class.getClassLoader().getResourceAsStream("images/bulletD.gif"));
            //另一种写法:BufferedImage image2=ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("images/bulletD.gif"));
            assertNotNull(image2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}