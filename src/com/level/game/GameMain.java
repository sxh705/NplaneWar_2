package com.level.game;

import com.level.obj.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;


public class GameMain extends JFrame implements Runnable {
    public GameMain(){
        this("飞机大战");
    }

    public GameMain(String title) throws HeadlessException {
        super(title);
        System.out.println("测试");
//        var t = new File("images/me1.png");
//        try{
//            System.out.println(t.getCanonicalPath());//获取标准的路径
//            System.out.println(t.getAbsolutePath());//获取绝对路径
//        }catch(Exception x){}

        this.setResizable(false);
        this.life = 2;

//        setVisible(true);
        setSize(600, 600);
        setLocationRelativeTo(null);//位置
        //添加开始鼠标事件 ??
        this.addMouseListener(
                new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        //左中右 1 2 3
                        if (e.getButton() == 1) {
                            state = 1;
                            objectList.add(planeObj);
                            objectList.add(bossObj);
                            repaint();
                        }
                        //System.out.println(e.getButton());
                    }
                }
        );

    }

    public void run(){
        while (checkend()){
            this.loop();//进入循环
            try {
                Thread.sleep(12);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public boolean checkend(){
        return true;
    }

    public static void main(String[] args) {
        var gameWin = new GameMain("飞机大战");
        gameWin.setVisible(true);
        gameWin.start();

    }

    public BgObj bgObj = new BgObj("images/backgroundN.png",0,0,600,1750,1,this);//背景
    public PlaneObj planeObj = new PlaneObj("images/life.png",290,520,0,this);//主角

    public BossObj bossObj = new BossObj("images/boos_sm.png",250,30,5,this);

    public List<GameObject> objectList = new ArrayList<>();    //定义大集合
    public List<ShellObj> shellObjList = new ArrayList<>();    //我方子弹集合
    public List<EnemyObj> enemyObjList = new ArrayList<>();    //敌方战斗机集合
    public List<GameObject> removeList = new ArrayList<>();    //被删除物体集合
    public List <EnemyShellObj> enemyShellObjList = new ArrayList<>();
    public int count = 1;    //游戏重绘次数

    public void loop(){
        count++;
        if(state == 1){
            createObj();
        }
        repaint();
    }

    public int score = 0;
    public int life = 2;
    public Image offScreenImage = null;//双缓存
    public int state = 0;//游戏状态 0未开始 1运行中 2暂停 3失败 4成功

    @Override
    public void paint(Graphics g) {
        if(offScreenImage == null){
            offScreenImage = this.createImage(600,600);
        }
        Graphics tg = offScreenImage.getGraphics();
        //tg.fillRect(0,0,600,600);
        bgObj.paintSelf(tg);
        tg.setColor(Color.BLACK);
        tg.setFont(new Font("等线", Font.BOLD, 50));
        switch (state){
            case 0:
                tg.drawString("点击开始游戏", 130, 300);
                break;
            case 1:
                objectList.removeAll(removeList);
                shellObjList.removeAll(removeList);
                enemyShellObjList.removeAll(removeList);
                enemyShellObjList.removeAll(removeList);
                removeList.clear();

                for(GameObject object : objectList){
                    object.paintSelf(tg);
                }
                paintScore(tg);
                break;
            case 2:
                tg.drawString("游戏暂停",170,300);
            case 3:
                tg.drawString("游戏结束", 170, 300);
                break;
            case 4:
                tg.drawString("挑战成功", 170, 300);
                break;
        }
        g.drawImage(offScreenImage,0,0,null);
    }

    public void paintScore(Graphics g){
        g.setColor(Color.BLACK);
        g.setFont(new Font("等线", Font.BOLD, 15));
        g.drawString("击杀数量: "+ score + "  剩余生命: " + life, 350, 50);
        g.drawString("关卡目标: 击败最终boss / 击杀数量达到95",300,70);
        if(score >= 95){
            state = 4;
        }
    }

    public void createObj(){
        if(count % 10 == 0) generateShell();//生成子弹
        if(count % 30 == 0) generateEnemy();//生成敌人
        if(count % 30 == 0 && bossObj != null) generateEShell();
    }

    private void generateEShell() {
        enemyShellObjList.add(
                new EnemyShellObj(
                        "images/bullet1.png",
                        bossObj.x + bossObj.width/2,
                        bossObj.y + bossObj.height,
                        4,
                        this
                )
        );
        objectList.add(
                enemyShellObjList.get(enemyShellObjList.size()-1)
        );
    }

    void generateShell() {
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

    void generateEnemy(){
            enemyObjList.add(new EnemyObj(
                    (int)xstl.xrand(0,1000),
                    xstl.xrand(0,600 - 50),
                    xstl.xrand(-50,-100),
                    this
            ));
            objectList.add(enemyObjList.get(enemyObjList.size()-1));
        }

    Thread t;
    public void start() {
        System.out.println("Starting ");
        t = new Thread(this);
        t.start();
//        if (t == null) {
//            t = new Thread (this);
//            t.start ();
//        }
    }

}

