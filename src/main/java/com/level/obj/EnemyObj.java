package com.level.obj;

import com.level.game.GameMain;
import com.testsxh.Sound;

import java.awt.*;

//敌方
public class EnemyObj extends GameObject {
    int blood;
    int fullBlood;
    int type;

    public EnemyObj(int type, double x, double y, GameMain frame) {
        this.frame = frame;
        this.x = x;
        this.y = y;
        this.type = type;
        if (type <= 700) {
            this.speedy = 1.5 + Math.random() * 0.5;
            blood = 1;
            this.setImg("images/enemy/1/" + blood + ".png");
        } else if (type <= 750) {
            this.speedy = 0.2 + Math.random() * 0.8;
            blood = 10;
            this.setImg("images/enemy/3/" + blood + ".png");
        } else {
            this.speedy = this.speedy = 1 + Math.random() * 0.5;
            blood = 3;
            this.setImg("images/enemy/2/" + blood + ".png");
        }
        fullBlood = blood;
    }

    double xrand(int l, int r) {
        return (int) (l + Math.random() * (r - l + 1));
    }

    @Override
    public void paintSelf(Graphics g) {
        garbageRecycle();
        //敌我相撞 游戏结束
        if (this.getRec().intersects(this.frame.planeObj.getRec())) {
            this.x = -150;
            this.y = 150;
            //            this.frame.removeList.add(this);
            this.frame.剩余生命--;
            if (this.frame.剩余生命 <= 0) {
                this.frame.游戏状态 = 3;
            }
        }
        int xx = frame.shellObjList.size();
        var yy = frame.shellObjList;
        for (int i = 0; i < xx; ++i) {
            var shellObj = yy.get(i);
            if (this.getRec().intersects(shellObj.getRec())) {
                //                this.frame.removeList.add(shellObj);
                shellObj.x = -100;
                shellObj.y = 100;
                blood--;
                if (type <= 700) {
                    this.setImg("images/enemy/1/" + blood + ".png");
                } else if (type <= 750) {
                    this.setImg("images/enemy/3/" + blood + ".png");
                } else {
                    this.setImg("images/enemy/2/" + blood + ".png");
                }
                if (blood <= 0) {
                    if (y >= 0) {
                        this.frame.击杀数量++;
                        Sound.enemy_down();
                    }
                    this.x = -150;
                    this.y = 150;
                    //                    this.frame.removeList.add(this);
                }
            }

        }


        //        var sx = frame.shellObjList.listIterator();
        //        while(sx.hasNext()){
        //            var shellObj = sx.next();
        //            if(this.getRec().intersects(shellObj.getRec())){
        ////                this.frame.removeList.add(shellObj);
        //                shellObj.x = -100;
        //                shellObj.y = 100;
        //                blood--;
        //                if(type <= 700) {
        //                    this.setImg((String)"images/enemy/1/" + blood + ".png");
        //                }else if(type <= 750){
        //                    this.setImg((String)"images/enemy/3/" + blood + ".png");
        //                }else{
        //                    this.setImg((String)"images/enemy/2/" + blood + ".png");
        //                }
        //                if(blood <= 0){
        //                    if(y >= 0) {
        //                        this.frame.击杀数量++;
        //                        Sound.enemy_down();
        //                    }
        //                    this.x = -150;
        //                    this.y = 150;
        ////                    this.frame.removeList.add(this);
        //                }
        //            }
        //        }

        g.drawImage(img, (int) x, (int) y, null);

        drawBlood(20, g);
        y += speedy;
    }

    void drawBlood(int width, Graphics g) {
        g.setColor(Color.black);
        double ratio = (double) blood / (double) fullBlood;
        g.setColor(Color.black);
        g.drawRect((int) this.x - width / 2 + this.width / 2, (int) (this.y + this.height), width, 4);

        if (ratio < 0.4) g.setColor(Color.red);
        else if (ratio < 0.6) g.setColor(Color.yellow);
        else g.setColor(Color.green);

        g.fillRect((int) this.x - width / 2 + this.width / 2, (int) (this.y + this.height), (int) (width * ratio), 4);
    }

    @Override
    public void delete() {
        //        frame.enemyObjList.remove(this);
    }

    @Override
    public void garbageRecycle() {
        if (!(x > -10 && x < 610) || !(y > -10000 && y < 610)) {
            frame.removeList.add(this);
            delete();
        }
    }
}
