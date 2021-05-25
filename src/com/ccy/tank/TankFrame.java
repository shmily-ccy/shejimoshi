package com.ccy.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Iterator;


/*
弹出一个窗框
 */
public class TankFrame extends Frame {

    static final int GAME_WIDTH=800,GAME_HEIGHT=600;

//    int x=200,y=200;
//    Dir dir=Dir.DOWN;
//    final int SPEED=10;
    //主站坦克
    Tank myTank=new Tank(200,200,Dir.DOWN,this);//坦克的起始位置,以及方向
    //Bullet bullet=new Bullet(300,300,Dir.DOWN);//将子弹显示到窗口中
    java.util.List<Bullet> bulletList=new ArrayList<>();

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

    /**
     * 将原来的背景全部清除一遍,然后再出现方块
     * @param g 画笔,只有系统本身可以获取到
     */
    @Override
    public void paint(Graphics g) {
        //画笔交给tank,由坦克自己去画自己
        g.drawString("子弹的数量"+bulletList.size(),10,60);
        myTank.paint(g);
        //第一次用foreach循环的时候,我们在bullet中进行了删除操作,会报错,是因为迭代器的原因
        for (int i = 0; i < bulletList.size(); i++) {
            bulletList.get(i).paint(g);
        }
      /*  Iterator<Bullet> iterator = bulletList.iterator();
        while (iterator.hasNext()){
            Bullet next = iterator.next();
            if(!next.isLive()) iterator.remove();
        }*/
        //每次最小化窗口,就会进行移动,所以需要不断的调用这个方法(刷新)
//        g.fillRect(x,y,50,50);
//        switch (dir){
//            case LEFT:
//                x-=SPEED;
//                break;
//            case RIGHT:
//                x+=SPEED;
//                break;
//            case UP:
//                y-=SPEED;
//                break;
//            case DOWN:
//                y+=SPEED;
//                break;
//            default:
//                break;
//
//        }
    }

    /**
     * 键盘的监听处理对象
     */
    class MyKeyListener extends KeyAdapter{
        boolean bL=false;
        boolean bU=false;
        boolean bR=false;
        boolean bD=false;

        @Override
        public void keyPressed(KeyEvent e) {
            //键按下的时候自动调用
//            System.out.println("key press");
            //x+=200;
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

        @Override
        public void keyReleased(KeyEvent e) {
            //抬起的时候调用
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


