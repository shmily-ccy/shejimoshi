package com.ccy.tank;

import java.awt.*;

/**
 * 子弹(炮弹)
 */
public class Bullet {
    private static final int SPEED=4;//速度
    private int x,y;//坐标位置
    public static int WIDTH=ResourceMgr.bulletD.getWidth();
    public static int HEIGHT=ResourceMgr.bulletD.getHeight();//子弹的大小,宽度和高度
    private Dir dir;//子弹方向
    private boolean live=true;//活或者死
    private TankFrame tf;

    public Bullet(int x, int y, Dir dir,TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf=tf;
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
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
        if(!live){
            tf.bulletList.remove(this);//删除已经死了的子弹,每次都会重新计算size,所以不会出现越界的问题
        }
       /* Color c=g.getColor();
        g.setColor(Color.RED);//画笔的颜色
        //子弹设置成圆的
//        g.fillOval(x,y,WIDTH,HEIGHT);
        g.setColor(c);
//        move();*/

        switch (dir){
            case LEFT:
                g.drawImage(ResourceMgr.bulletL,x,y,Color.RED,null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR,x,y,Color.RED,null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD,x,y,Color.RED,null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU,x,y,Color.RED,null);
                break;
        }
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
        if(x<0 || y<0 || x>TankFrame.GAME_HEIGHT || y>TankFrame.GAME_WIDTH){
            live=false;
        }

    }
}
