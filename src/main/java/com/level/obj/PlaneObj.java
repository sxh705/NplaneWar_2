package com.level.obj;

import com.level.game.GameMain;
import com.testsxh.Sound;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PlaneObj extends GameObject {
    static int revise = 10;

    public PlaneObj(String img, double xx, double yy, double speedy, GameMain frame) {
        super(img, xx, yy, speedy, frame);
        width -= revise;
        height -= revise;

        this.frame.addMouseMotionListener(
                new MouseAdapter() {
                    @Override
                    public void mouseMoved(MouseEvent e) {
                        super.mouseMoved(e);
                        x = e.getX() - width / 2;
                        y = e.getY() - height / 3;
                    }
                }
        );
    }

    @Override
    public Rectangle getRec() {
        return new Rectangle((int) x + revise, (int) y + revise, width, height);
    }

    //主角
    @Override
    public void paintSelf(Graphics g) {
        g.drawImage(img, (int) x, (int) y, null);
        //添加鼠标事件 跟随
        //        this.frame.addMouseMotionListener(
        //                new MouseAdapter() {
        //                    @Override
        //                    public void mouseMoved(MouseEvent e) {
        //                        super.mouseMoved(e);
        //                        x = e.getX() - width/2;
        //                        y = e.getY() - height/3;
        //                    }
        //                }
        //        );

        for (var a : frame.enemyShellObjList) {
            if (this.getRec().intersects(a.getRec())) {
                frame.剩余生命--;
                if (frame.剩余生命 <= 0) {
                    frame.游戏状态 = 3;
                    Sound.me_down();
                }
                a.x = -100;
                a.y = -100;
            }
        }

        for (var a : frame.道具ObjList) {
            if (this.getRec().intersects(a.getRec())) {
                a.x = -100;
                a.y = -100;
                if (a.speedy == 1.4) {
                    //清屏武器
                    frame.火力武器++;
                    Sound.play("sound/get_bullet.wav", false);
                } else if (a.speedy == 1.3) {
                    frame.清屏武器++;
                    Sound.play("sound/get_bomb.wav", false);
                } else if (a.speedy == 1.2) {
                    Sound.play("sound/get_bullet.wav", false);
                    frame.剩余生命++;
                }
            }
        }

        //我方飞机碰撞敌方boos
        if (frame.boosObj != null)
            if (this.getRec().intersects(frame.boosObj.getRec())) {
                frame.游戏状态 = 3;
                Sound.me_down();
            }
    }
}
