package com.ccy.tank;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Main {
    public static void main(String[] args) throws Exception {
        TankFrame tf=new TankFrame();
        while (true){
            Thread.sleep(50);//主线程睡眠50毫秒
            tf.repaint();
        }
    }
}
