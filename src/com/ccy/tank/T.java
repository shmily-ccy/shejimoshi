package com.ccy.tank;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class T {
    public static void main(String[] args) {
        Frame f=new Frame();
        f.setSize(800,600);
        f.setResizable(false);
        f.setVisible(true);
        //添加window的监听器
        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}
