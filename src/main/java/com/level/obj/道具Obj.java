package com.level.obj;

import com.level.game.GameMain;

public class 道具Obj extends GameObject {
    public static final String 清屏武器 = "images/bomb_supply.png";
    public static final String 子弹加速 = "images/bullet_supply.png";
    public static final String 生命 = "images/life_supply.png";

    public 道具Obj() {
    }

    public 道具Obj(String img, GameMain frame) {
        super(img, frame);
    }

    public 道具Obj(String img, double x, double y, double speedy, GameMain frame) {
        super(img, x, y, speedy, frame);
    }

}
