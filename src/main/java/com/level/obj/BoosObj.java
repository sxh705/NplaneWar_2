package com.level.obj;

import com.level.game.GameMain;

import java.awt.*;

//生成boos
public class BoosObj extends GameObject {
    int blood;
    int fullBlood;

    public BoosObj(String img, double x, double y, double speedy, GameMain frame) {
        super(img, x, y, speedy, frame);
        this.speedx = 2;
        this.blood = 1000;
        this.fullBlood = blood;
    }

    @Override
    public void paintSelf(Graphics g) {
        //        super.paintSelf(g);
        g.drawImage(img, (int) x, (int) y, null);
        x += speedx;
        if (x > (500 - width + 140)) {
            speedx = -Math.abs(speedx);
        }
        if (x <= -40) {
            speedx = Math.abs(speedx);
        }

        for (var a : frame.shellObjList) {
            if (this.getRec().intersects(a.getRec())) {
                this.blood--;
                a.x = -100;
                a.y = -100;
            }

            //            g.setColor(Color.red);
            //            g.setFont(new Font("等线", Font.BOLD, 20));
            //            g.drawString("血量剩余: "+blood,(int)this.x,(int)(this.y+this.height));
            drawBlood(580, g);

            if (blood <= 0) {
                frame.removeList.add(this);
                frame.击杀数量 += 20;
                frame.目标2 = frame.击杀数量 + 80;
                frame.boosObj = null;
            }
        }
    }

    void drawBlood(int width, Graphics g) {
        g.setColor(Color.black);
        double ratio = (double) blood / (double) fullBlood;
        g.setColor(Color.black);
        g.drawRect(10, 35, width, 4);

        if (ratio < 0.4) g.setColor(Color.red);
        else if (ratio < 0.6) g.setColor(Color.yellow);
        else g.setColor(Color.green);

        g.fillRect(10, 35, (int) (width * ratio), 4);
    }


    @Override
    public Rectangle getRec() {
        return super.getRec();
    }
}
