package com.level.game;

import com.level.obj.*;
import com.testsxh.Sound;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class GameMain extends JPanel implements Runnable {
    //第一关
    static final int 未开始 = 0;//游戏状态 0未开始 1运行中 2暂停 3失败 4成功
    static final int 运行中 = 1;
    static final int 暂停 = 2;
    static final int 失败 = 3;
    static final int 成功 = 4;
    static final int 系统暂停 = 5;

    public GameMainWin father;

    public int 游戏间隔_难度 = 12;
    public int 重绘次数 = 1;    //游戏重绘次数
    public int 击杀数量 = 0;
    public int 剩余生命 = 2;
    public Image offScreenImage = null;//双缓存
    public int 游戏状态 = 0;//游戏状态 0未开始 1运行中 2暂停 3失败 4成功
    public BgObj bgObj = new BgObj("images/backgroundN.png", 0, 0, 600, 1750, 1, this);//背景
    public PlaneObj planeObj = new PlaneObj("images/life.png", 290, 520, 0, this);//主角
    public BoosObj boosObj = null;
    public List<GameObject> objectList = new ArrayList<>();    //定义大集合
    public List<ShellObj> shellObjList = new ArrayList<>();    //我方子弹集合
    public List<EnemyObj> enemyObjList = new ArrayList<>();    //敌方战斗机集合
    public List<GameObject> removeList = new ArrayList<>();    //被删除物体集合
    public List<EnemyShellObj> enemyShellObjList = new ArrayList<>();
    public List<道具Obj> 道具ObjList = new ArrayList<>(); //道具
    public int 清屏武器 = 0;
    public int 火力武器 = 0;
    public int 目标2 = 9999;
    int 游戏暂停次数 = 0;
    long 游戏开始时间 = 0;
    long 火力超时 = 0;
    boolean boos生成flag = false;
    Random rd = new Random(System.currentTimeMillis());


    public GameMain() {

    }

    public GameMain(GameMainWin father) {
        this.游戏间隔_难度 = 12;
        this.father = father;
        if (father != null && father.father != null) {
            var f = father.father;
            清屏武器 = f.varList.getOrDefault("清屏", 0);
            火力武器 = f.varList.getOrDefault("火力", 0);
            剩余生命 = f.varList.getOrDefault("生命", 2);
        }
        if (剩余生命 < 2) 剩余生命 = 2;

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
                                    System.out.println("zaGm");
                                    游戏状态 = GameMain.暂停;
                                    break;
                                case GameMain.暂停:
                                    游戏状态 = GameMain.运行中;
                                    break;
                                case GameMain.成功:
                                case GameMain.失败:
                                    //                                    System.out.println("Stopr");
                                    //                                    Sound.BGMstop();
                                    //                                    Record.pscore = 击杀数量;
                                    //                                    Record.update();
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

    public void 清屏() {
        Sound.play("sound/use_bomb.wav", false);
        for (var a : enemyObjList) {
            removeList.add(a);
            if (a.y < 500 && a.y > 100 && a.x < 500 && a.x > 100) 击杀数量++;
        }
    }

    public void 火力() {
        火力超时 = System.currentTimeMillis() + 12 * 1000;
    }

    void 鼠标点击() {
        游戏状态 = GameMain.运行中;
    }

    void 进入系统暂停() {
        游戏暂停次数++;
    }

    void 系统暂停绘制(Graphics tg) {
        tg.drawString("Boos 苏醒了...", 130, 300);
    }

    void 运行检测() {

        if (this.击杀数量 > 10 && !boos生成flag) {
            boos生成flag = true;
            boosObj = new BoosObj("images/boos_sm.png", 250, 30, 5, this);
            objectList.add(boosObj);
            进入系统暂停();
            this.游戏状态 = GameMain.系统暂停;
        }

        if (击杀数量 >= 目标2) {
            游戏状态 = GameMain.成功;
        }

    }

    void 绘制分数(Graphics g) {
        g.setColor(Color.RED);
        g.setFont(new Font("等线", Font.BOLD, 15));
        g.drawString("击杀数量: " + 击杀数量 + "  剩余生命: " + 剩余生命, 300, 50);
        if (boosObj == null) {
            if (目标2 == 9999) {
                g.drawString("关卡目标1: 击杀50个敌人", 300, 70);
            } else {
                g.drawString("关卡目标3: 击杀数达到" + 目标2, 300, 70);
            }
        } else {
            g.drawString("关卡目标2: 击败最终boss", 300, 70);
        }
        g.drawString("程序运行时间" + getSec() / 1000 + "秒", 300, 110);
        g.drawString("清屏武器" + 清屏武器 + "  火力武器" + 火力武器, 300, 130);
        if (火力超时 > System.currentTimeMillis())
            if (火力超时 > System.currentTimeMillis())
                g.drawString("火力等级" + 火力等级(), 300, 150);
    }

    int 火力等级() {
        int x = (int) ((火力超时 - System.currentTimeMillis()) / 1000);
        return x > 0 ? 12 - x : 12;
    }

    public void run() {
        while (true) {
            {
                重绘次数++;
                if (游戏状态 == GameMain.运行中) {
                    createObj();
                }
                repaint();
            }
            try {
                Thread.sleep(游戏间隔_难度);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void start() {
        //        Sound.BGM();
        System.out.println("Starting ");
        Thread t = new Thread(this);
        t.start();
    }

    public void paintComponent(Graphics g) {
        if (offScreenImage == null)
            offScreenImage = this.createImage(600, 600);
        Graphics tg = offScreenImage.getGraphics();
        bgObj.paintSelf(tg);
        tg.setColor(Color.BLACK);
        tg.setFont(new Font("等线", Font.BOLD, 50));
        switch (游戏状态) {
            case GameMain.未开始:
                tg.drawString("点击开始游戏", 130, 300);
                tg.setFont(new Font("等线", Font.BOLD, 25));
                tg.drawString("按W使用清屏武器, 按Q使用子弹加速", 130, 400);
                tg.drawString("ESC退出", 150, 450);
                break;
            case GameMain.运行中:
                垃圾释放();
                游戏运行绘制(tg);
                运行检测();
                break;
            case GameMain.暂停:
                tg.drawString("暂停", 210, 300);
                break;
            case GameMain.失败:
                tg.drawString("结束 点击结算", 150, 300);
                break;
            case GameMain.成功:
                tg.drawString("成功 点击结算", 150, 300);
                break;
            case GameMain.系统暂停:
                系统暂停绘制(tg);
                break;
        }
        g.drawImage(offScreenImage, 0, 0, null);
    }

    void 垃圾释放() {
        objectList.removeAll(removeList);
        shellObjList.removeAll(removeList);
        enemyShellObjList.removeAll(removeList);
        enemyObjList.removeAll(removeList);
        道具ObjList.removeAll(removeList);
        removeList.clear();
        //        System.out.println(""+objectList.size()+"\n"
        //                +shellObjList.size()+"\n"
        //                +enemyObjList.size()+"\n"
        //                +enemyShellObjList.size()
        //                +"\n"+道具ObjList.size()+“\n\n"
        //        );
    }

    void 游戏运行绘制(Graphics tg) {
        int x = objectList.size();
        for (int i = 0; i < x; ++i) {
            objectList.get(i).paintSelf(tg);
        }
        //        var x = objectList.listIterator();
        //        while (x.hasNext()){
        //            x.next().paintSelf(tg);
        //        }

        //        for(GameObject object : objectList){
        //            object.paintSelf(tg);
        //        }
        绘制分数(tg);
    }

    public long getSec() {
        return System.currentTimeMillis() - 游戏开始时间;
    }

    public void createObj() {
        if (重绘次数 % 30 == 0) generateEnemy();//生成敌人
        if (重绘次数 % 30 == 0 && boosObj != null) generateEShell();
        if (重绘次数 % 300 == 0) generate道具();
        if (火力超时 > System.currentTimeMillis()) {
            if (重绘次数 % (火力等级() + 2) == 0) generateShell();
        } else if (重绘次数 % 10 == 0)
            generateShell();//生成子弹

        //        if(火力超时 > System.currentTimeMillis() && 重绘次数 % 5 == 0) generateShell();
        //        else if(重绘次数 % 10 == 0) generateShell();//生成子弹
    }

    void generate道具() {
        int i = rd.nextInt(0, 100);
        道具Obj x;
        if (i < 45) {
            x = new 道具Obj(道具Obj.清屏武器, rd.nextInt(100, 500), -10, 1.3, this);
        } else if (i < 90) {
            x = new 道具Obj(道具Obj.子弹加速, rd.nextInt(100, 500), -10, 1.4, this);
        } else {
            x = new 道具Obj(道具Obj.生命, rd.nextInt(100, 500), -10, 1.2, this);
        }
        道具ObjList.add(x);
        objectList.add(x);
    }

    private void generateEShell() {
        enemyShellObjList.add(
                new EnemyShellObj(
                        "images/bullet1.png",
                        boosObj.x + boosObj.width / 2,
                        boosObj.y + boosObj.height,
                        4,
                        this
                )
        );
        objectList.add(
                enemyShellObjList.get(enemyShellObjList.size() - 1)
        );
    }

    void generateShell() {
        Sound.bullet();
        shellObjList.add(
                new ShellObj(
                        "images/bullet2.png",
                        planeObj.x + planeObj.width / 2 - 2,
                        planeObj.y - planeObj.height / 5,
                        -10, this
                )
        );
        objectList.add(
                shellObjList.get(shellObjList.size() - 1)
        );
    }

    void generateEnemy() {
        enemyObjList.add(new EnemyObj(rd.nextInt(1000), rd.nextInt(550), rd.nextInt(-100, -50), this));
        objectList.add(enemyObjList.get(enemyObjList.size() - 1));
    }

}

