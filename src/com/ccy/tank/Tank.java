package com.ccy.tank;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.awt.*;

/**
 * 坦克自身的属性,动作进行封装
 */
public class Tank {
    private int x,y;//坦克的坐标,移动的话,就是坐标的加减
    private Dir dir;//坦克的方向
    private static final int SPEED=10;//坦克移动速度
    private boolean moving=false;//静止/移动,当为true的时候,对坦克进行位置上的改变,当我们按下某个键的时候为true;

    public Tank(int x, int y, Dir dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
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

    public static int getSPEED() {
        return SPEED;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void paint(Graphics g) {
        g.fillRect(x,y,50,50);
        move();

    }

    private void move() {
        if(!moving) return;
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
