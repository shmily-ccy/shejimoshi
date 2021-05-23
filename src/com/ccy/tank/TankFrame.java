package com.ccy.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame extends Frame {

    int x=200,y=200;

    public TankFrame(){
        setSize(800,600);
        setResizable(false);
        setTitle("tank war");
        setVisible(true);
        addKeyListener(new MyKeyListener());

        //windows关闭窗口的时候调用
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
   }

    /**
     * 将原来的背景全部清除一遍,然后再出现方块
     * @param g 画笔,只有系统本身可以获取到
     */
    @Override
    public void paint(Graphics g) {
        //每次最小化窗口,就会进行移动,所以需要不断的调用这个方法(刷新)
        System.out.println("paint");
        g.fillRect(x,y,50,50);
        x+=10;
//        y+=10;

    }

    /**
     * 键盘的监听处理对象
     */
    class MyKeyListener extends KeyAdapter{

        @Override
        public void keyPressed(KeyEvent e) {
            //键按下的时候自动调用
//            System.out.println("key press");
            //x+=200;

        }

        @Override
        public void keyReleased(KeyEvent e) {
            //抬起的时候调用
            System.out.println("key released");
        }
    }
}


