package com.ccy.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;


/*
弹出一个窗框
 */
public class TankFrame extends Frame {

    static final int GAME_WIDTH=1080,GAME_HEIGHT=860;

    //主战坦克
    Tank myTank=new Tank(200,400,Dir.DOWN,Group.GOOD,this);//坦克的起始位置,以及方向
    java.util.List<Bullet> bulletList=new ArrayList<>();
    List<Tank> tanks=new ArrayList<>();
    Explode explode=new Explode(100,100,this);
    List<Explode> explodes=new ArrayList<>();
    /**
     * 窗口属性
     */
    public TankFrame(){
        setSize(GAME_WIDTH,GAME_HEIGHT);
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

    Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    /**
     * 将原来的背景全部清除一遍,然后再出现方块
     * @param g 画笔,只有系统本身可以获取到
     */
    @Override
    public void paint(Graphics g) {
        //画笔交给tank,由坦克自己去画自己
        //数量都为0的时候,说明不存在内存溢出
        g.drawString("子弹的数量"+bulletList.size(),10,60);
        g.drawString("敌人的数量"+tanks.size(),10,80);
        g.drawString("爆炸的数量"+explodes.size(),10,100);
        myTank.paint(g);
        //第一次用foreach循环的时候,我们在bullet中进行了删除操作,会报错,是因为迭代器的原因
        for (int i = 0; i < bulletList.size(); i++) {
            bulletList.get(i).paint(g);
        }
        //画敌人坦克
        for (int i = 0; i < tanks.size(); i++) {
            tanks.get(i).paint(g);
        }
        //已经画出来的,所以需要爆炸的时候,往里面画就行了
        for (int i = 0; i < explodes.size(); i++) {
            explodes.get(i).paint(g);
        }

        for (int i = 0; i < bulletList.size(); i++) {
            for (int j = 0; j < tanks.size(); j++) {
                //每一个子弹都要和坦克碰撞
                bulletList.get(i).collideWith(tanks.get(j));
            }
        }

    }

    /**
     * 键盘的监听处理对象
     */
    class MyKeyListener extends KeyAdapter{
        boolean bL=false;
        boolean bU=false;
        boolean bR=false;
        boolean bD=false;

        /**
         * 键盘按下的时候系统自动调用
         * @param e
         */
        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch(keyCode){
                case KeyEvent.VK_LEFT:
                    bL=true;
                    break;
                case KeyEvent.VK_UP:
                    bU=true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD=true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR=true;
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }

        /**
         * 根据按键改变坦克设定方向
         */
        private void setMainTankDir() {
            if(!bR && !bL && !bU && !bD) myTank.setMoving(false);
            else {
                myTank.setMoving(true);
                if (bL) myTank.setDir(Dir.LEFT);
                if (bU) myTank.setDir(Dir.UP);
                if (bR) myTank.setDir(Dir.RIGHT);
                if (bD) myTank.setDir(Dir.DOWN);
            }
        }

        /**
         * 键盘抬起的时候系统自动调用
         * @param e
         */
        @Override
        public void keyReleased(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch(keyCode){
                case KeyEvent.VK_LEFT:
                    bL=false;
                    break;
                case KeyEvent.VK_UP:
                    bU=false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD=false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR=false;
                    break;
                case KeyEvent.VK_CONTROL://抬起control键打出一颗子弹
                    myTank.fire();
                    break;
                default:
                    break;

            }
            setMainTankDir();
        }
    }
}


