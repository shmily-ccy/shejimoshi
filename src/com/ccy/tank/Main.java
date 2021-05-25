package com.ccy.tank;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Main {
    public static void main(String[] args) throws Exception {
        TankFrame tf=new TankFrame();
        //初始化敌方坦克
        for (int i = 0; i <5 ; i++) {
            tf.tanks.add(new Tank(50+i*80,200,Dir.DOWN,tf));
        }
        while (true){
            Thread.sleep(50);//主线程睡眠50毫秒
            tf.repaint();
        }
    }
}
