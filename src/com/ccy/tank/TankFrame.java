package com.ccy.tank;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame extends Frame {

    int x=200,y=200;

    public TankFrame(){
        setSize(800,600);
        setResizable(false);
        setTitle("tank war");
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
   }

    /**
     * 将原来的背景全部清除一遍,然后再出现方块
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        //每次最小化窗口,就会进行移动,所以需要不断的调用这个方法
        System.out.println("paint");
        g.fillRect(x,y,50,50);
        x+=10;
        y+=10;
    }

}


