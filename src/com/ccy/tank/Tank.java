package com.ccy.tank;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.awt.*;
import java.util.Random;

/**
 * 坦克自身的属性,动作进行封装
 */
public class Tank {
    private int x,y;//坦克的坐标,移动的话,就是坐标的加减
    private Dir dir;//坦克的方向
    private static final int SPEED=3;//坦克移动速度
    private boolean moving=true;//静止/移动,当为true的时候,对坦克进行位置上的改变,当我们按下某个键的时候为true;
    private TankFrame tf;//拥有了该窗口的引用,我们在创做坦克的时候,就可以将坦克随身携带的子弹也在画面中可以画出来(持有另外一个对象的引用)
    public static int WIDTH=ResourceMgr.goodTankU.getWidth();
    public static int HEIGHT=ResourceMgr.goodTankU.getHeight();
    boolean living=true;
    private Random random=new Random();
    private Group group=Group.BAD;
    Rectangle rectangle=new Rectangle();
    public Tank(int x, int y, Dir dir,Group group,TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf=tf;
        this.group=group;
        rectangle.x=this.x;
        rectangle.y=this.y;
        rectangle.height=this.HEIGHT;
        rectangle.width=this.WIDTH;
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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void paint(Graphics g) {
        //改成画图片,而不是方块
        //ImageObserver是一个监听者,画进来的时候有事件发生
        if(!living) tf.tanks.remove(this);//死了需要移除,不然坦克还会占内存,造成内存溢出
        switch (dir){
            case LEFT:
                g.drawImage(this.group==Group.BAD?ResourceMgr.badTankL:ResourceMgr.goodTankL,x,y,null);
                break;
            case RIGHT:
                g.drawImage(this.group==Group.BAD?ResourceMgr.badTankR:ResourceMgr.goodTankR,x,y,null);
                break;
            case DOWN:
                g.drawImage(this.group==Group.BAD?ResourceMgr.badTankD:ResourceMgr.goodTankD,x,y,null);
                break;
            case UP:
                g.drawImage(this.group==Group.BAD?ResourceMgr.badTankU:ResourceMgr.goodTankU,x,y,null);
                break;
        }
        //坦克的移动
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
        //生成10以内的随机数,随机发射子弹,坏蛋随机发射
        int i = random.nextInt(100);
        if(this.group==Group.BAD && i>95){
          this.fire();
      }
        if(this.group==Group.BAD && random.nextInt(100)>95)
        randomDir();//方向有四个值,所以就是取四以内的随机数

        //边界检测,碰上之后不让开出去
        boundsCheck();

        rectangle.x=this.x;
        rectangle.y=this.y;
    }

    private void boundsCheck() {
        if(this.x<0) x=0;
        if(this.y<30) y=30;
        if(this.x>TankFrame.GAME_WIDTH-Tank.WIDTH) x=TankFrame.GAME_WIDTH-Tank.WIDTH;
        if(this.y>TankFrame.GAME_HEIGHT-Tank.HEIGHT) y=TankFrame.GAME_HEIGHT-Tank.HEIGHT;
    }

    private void randomDir() {
         this.dir=Dir.values()[random.nextInt(4)];
    }

    /**
     * 发射子弹
     */
    public void fire() {
        //计算子弹出来的位置
        int ex=this.x +Tank.WIDTH/2-Bullet.WIDTH/2;
        int ey=this.y+Tank.WIDTH/2-Bullet.HEIGHT/2;
        tf.bulletList.add (new Bullet(ex,ey, this.dir,this.getGroup(),tf));
    }

    public void die() {
        this.living=false;
    }
}
