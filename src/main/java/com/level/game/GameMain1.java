package com.level.game;

import Rank.Record;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameMain1 extends GameMain {
    //无尽模式

    public GameMain1() {
        this(null);
    }


    public GameMain1(GameMainWin father) {
        this.剩余生命 = 2;
        this.游戏间隔_难度 = 12;
        this.father = father;
        if (father != null && father.father != null) {
            var f = father.father;
            清屏武器 = f.varList.getOrDefault("清屏", 0);
            火力武器 = f.varList.getOrDefault("火力", 0);
            剩余生命 = f.varList.getOrDefault("生命", 0);
        }

        //添加开始鼠标事件
        this.addMouseListener(
                new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        super.mouseReleased(e);
                        //左中右 1 2 3
                        if (e.getButton() == 1) {
                            switch (游戏状态) {
                                case GameMain.未开始:
                                    游戏状态 = GameMain.运行中;
                                    游戏开始时间 = System.currentTimeMillis();
                                    objectList.add(planeObj);
                                    break;
                                case GameMain.运行中:
                                    System.out.println("运行");
                                    游戏状态 = GameMain.暂停;
                                    break;
                                case GameMain.暂停:
                                    System.out.println("暂停");
                                    游戏状态 = GameMain.运行中;
                                    break;
                                case GameMain.成功:
                                case GameMain.失败:
                                    //                                    System.out.println("Stopr");
                                    ////                                    Sound.BGMstop();
                                    Record.pscore = 击杀数量;
                                    Record.update();
                                    var f = father.father;
                                    if (f != null) {
                                        int x = f.varList.getOrDefault("金币", 0);
                                        x += 击杀数量;
                                        f.varList.put("金币", x);
                                        f.varList.put("清屏", 清屏武器);
                                        f.varList.put("火力", 火力武器);
                                        f.varList.put("生命", 剩余生命);
                                        System.out.println(f.varList);
                                        f.setVisible(true);
                                        f.saveVar();
                                        f.readVar();
                                    }
                                    father.dispose();
                                    break;
                                case GameMain.系统暂停:
                                    鼠标点击();
                                default:
                            }
                        }
                    }
                }
        );
    }


    @Override
    void 绘制分数(Graphics g) {
        g.setColor(Color.RED);
        g.setFont(new Font("等线", Font.BOLD, 15));
        g.drawString("击杀分数: " + 击杀数量 + "  剩余生命: " + 剩余生命, 300, 50);
        g.drawString("关卡目标: 得到尽可能高的分数", 300, 70);
        g.drawString("游戏难度" + 游戏间隔_难度, 300, 90);
        g.drawString("程序运行时间" + getSec() / 1000 + "秒", 300, 110);
        g.drawString("清屏武器" + 清屏武器 + "  火力武器" + 火力武器, 300, 130);
        if (火力超时 > System.currentTimeMillis())
            g.drawString("火力等级" + 火力等级(), 300, 150);
    }


    @Override
    void 运行检测() {
        if (this.击杀数量 != 0 && this.击杀数量 % 10 == 0 && 游戏间隔_难度 > 2) {
            this.击杀数量++;
            this.游戏间隔_难度--;
        }
    }
}
