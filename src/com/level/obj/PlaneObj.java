package com.level.obj;

import com.level.game.GameMain;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PlaneObj extends GameObject{
    static int revise = 10;
    public PlaneObj(String img, double x, double y, double speedy, GameMain frame) {
        super(img, x, y, speedy, frame);
        width -= revise;
        height -= revise;
    }

    @Override
    public Rectangle getRec() {
        return new Rectangle((int)x+revise,(int)y+revise,width,height);
    }

    //主角
    @Override
    public void paintSelf(Graphics g) {
        g.drawImage(img,(int)x,(int)y,null);
        //添加鼠标事件 跟随
        this.frame.addMouseMotionListener(
                new MouseAdapter() {
                    @Override
                    public void mouseMoved(MouseEvent e) {
                        super.mouseMoved(e);
                        x = e.getX() - width/2;
                        y = e.getY() - height/3;
                    }
                }
        );

        for(var a:frame.enemyShellObjList){
            if(this.getRec().intersects(a.getRec())){
                frame.life--;
                a.x = -100;
                a.y = -100;
//                frame.removeList.add(a);
            }
            if(frame.life <= 0){
                frame.state = 3;
            }
        }
        //我方飞机碰撞敌方boos
        if(frame.bossObj!=null)
            if(this.getRec().intersects(frame.bossObj.getRec())){
                frame.state = 3;
            }
    }
}
