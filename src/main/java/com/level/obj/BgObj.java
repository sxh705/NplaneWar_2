package com.level.obj;

import com.level.game.GameMain;

import java.awt.*;

/*
 * 背景的实体类
 * */
public class BgObj extends GameObject {
    public BgObj(String img, double x, double y, int width, int height, double speedy, GameMain frame) {
        super(img, x, y, width, height, 0, speedy, frame);
    }

    @Override
    public Image getImg() {
        return super.getImg();
    }

    @Override
    public void paintSelf(Graphics g) {
        g.drawImage(img, (int) x, (int) y, null);
        //背景移动
        y -= speedy;
        y %= (height / 2);
    }

}
