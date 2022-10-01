package com.level.obj;

import com.level.game.GameMain;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public abstract class GameObject {
    //游戏元素坐标
    public double x;
    public double y;
    //游戏元素的宽高
    public int width;
    public int height;
    Image img;
    //速度
    double speedx;
    double speedy;

    //引入主界面
    GameMain frame;

    public GameObject() {
    }

    public GameObject(String img, GameMain frame) {
        this.setImg(img);
        this.frame = frame;
    }

    public GameObject(String img, double x, double y, double speedy, GameMain frame) {
        this.setImg(img);
        this.x = x;
        this.y = y;
        this.speedy = speedy;
        this.frame = frame;
    }

    public GameObject(String img, double x, double y, int width, int height, double speedx, double speedy, GameMain frame) {
        this.setImg(img);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speedx = speedx;
        this.speedy = speedy;
        this.frame = frame;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = Toolkit.getDefaultToolkit().getImage(img);
        try {
            BufferedImage bimg = ImageIO.read(new File(img));
            this.width = bimg.getWidth();
            this.height = bimg.getHeight();
        } catch (Exception e) {
            this.width = -1;
            this.height = -1;
        }
    }

    //继承元素绘制自己的方法
    public void paintSelf(Graphics g) {
        garbageRecycle();
        g.drawImage(img, (int) x, (int) y, null);
        y += speedy;
        x += speedx;
    }

    public void garbageRecycle() {
        if (!(x > -100 && x < 700) || !(y > -100 && y < 7000)) {
            frame.removeList.add(this);
            delete();
        }
    }

    public void delete() {
    }

    //获取当前游戏元素的矩形, 碰撞检测
    public Rectangle getRec() {
        return new Rectangle((int) x, (int) y, width, height);
    }


}
