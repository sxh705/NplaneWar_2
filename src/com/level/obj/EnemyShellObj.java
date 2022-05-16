package com.level.obj;

import com.level.game.GameMain;

import java.awt.*;

public class EnemyShellObj extends GameObject{
    //敌方子弹

    public EnemyShellObj() {
        super();
    }

    public EnemyShellObj(String img, double x, double y, double speedy, GameMain frame) {
        super(img, x, y, speedy, frame);
    }

    @Override
    public void paintSelf(Graphics g) {
        super.paintSelf(g);
    }

    @Override
    public void delete() {
//        frame.enemyShellObjList.remove(this);
    }
}
