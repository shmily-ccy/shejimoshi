package com.ccy.tank;

import java.awt.*;

/**
 * 子弹(炮弹)
 */
public class Bullet {
    private static final int SPEED=1;//速度
    private int x,y;//坐标位置
    private static int WIDTH=30,HEIGHT=30;//子弹的大小,宽度和高度
    private Dir dir;//子弹方向

    public Bullet(int x, int y, Dir dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public static int getSPEED() {
        return SPEED;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }
    /**
     * 画出子弹自己
     */
    public void paint(Graphics g){
        Color c=g.getColor();
        g.setColor(Color.RED);//画笔的颜色
        //子弹设置成圆的
        g.fillOval(x,y,WIDTH,HEIGHT);
        g.setColor(c);
        move();

    }

    private void move() {
      //  if(!moving) return;子弹是没有停止状态的
        switch (dir){
            case LEFT:
                x-=SPEED;
                break;
            case RIGHT:
                x+=SPEED;
                break;
            case UP:
                y-=SPEED;
                break;
            case DOWN:
                y+=SPEED;
                break;
            default:
                break;

        }
    }
}
