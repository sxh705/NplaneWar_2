package com.level.obj;

import com.level.game.GameMain;

public class ShellObj extends GameObject {
    //主角子弹

    public ShellObj() {
    }

    public ShellObj(String img, double x, double y, double speedy, GameMain frame) {
        super(img, x, y, speedy, frame);
    }

    public ShellObj(String img, double x, double y, int width, int height, double speedx, double speedy, GameMain frame) {
        super(img, x, y, width, height, speedx, speedy, frame);
    }

    @Override
    public void delete() {
        //        frame.shellObjList.remove(this);
    }
}
