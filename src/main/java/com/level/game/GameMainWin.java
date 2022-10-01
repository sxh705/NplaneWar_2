package com.level.game;

import com.formdev.flatlaf.FlatLightLaf;
import com.sxh.主界面;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameMainWin extends JFrame {
    public GameMain gameMain;
    主界面 father;

    public GameMainWin() {
        this("飞机大战");
    }

    public GameMainWin(String title) {
        super(title);
        FlatLightLaf.setup();
        setResizable(false);
        setSize(600, 600);
        setLocationRelativeTo(null);//位置
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.addKeyListener(
                new KeyAdapter() {
                    @Override
                    public void keyReleased(KeyEvent e) {
                        if (gameMain == null) return;
                        if (gameMain.游戏状态 == GameMain.运行中) {
                            char now = Character.toLowerCase(e.getKeyChar());
                            if (now == 'w') {
                                if (gameMain.清屏武器 > 0) {
                                    gameMain.清屏();
                                    gameMain.清屏武器--;
                                } else {
                                    JOptionPane.showMessageDialog(getThis(), "道具数量不足~",
                                            "警告", JOptionPane.PLAIN_MESSAGE);
                                }
                            } else if (now == 'q') {
                                if (gameMain.火力武器 > 0) {
                                    gameMain.火力();
                                    gameMain.火力武器--;
                                } else {
                                    JOptionPane.showMessageDialog(getThis(), "道具数量不足~",
                                            "警告", JOptionPane.PLAIN_MESSAGE);
                                }
                            } else if (now == '\u001B') {
                                gameMain.游戏状态 = GameMain.失败;
                            }
                        }
                    }
                }
        );
    }

    public static void main(String[] args) {
        var gameWin = new GameMainWin();
        gameWin.setVisible(true);
    }

    public static void main2(主界面 x) {
        var F = new GameMainWin("第一关 打boos");
        F.father = x;
        var S = new GameMain(F);

        F.setVisible(true);

        F.add(S);
        F.gameMain = S;

        S.start();
    }

    public static void main1(主界面 x) {
        var F = new GameMainWin("无尽模式");
        F.father = x;
        var S = new GameMain1(F);
        F.add(S);
        F.gameMain = S;
        S.start();
        F.setVisible(true);
    }

    GameMainWin getThis() {
        return this;
    }

    public void start() {
        gameMain.start();
    }

}


