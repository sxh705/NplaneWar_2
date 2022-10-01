package com.testsxh;

import java.io.FileNotFoundException;

public class Sound {
    static MusicPlay player;


    //BGM
    static public void BGM() {
        play("sound/game_music.wav", true);// 循环播放背景音乐
    }

    //敌机死亡
    static public void enemy_down() {
        play("sound/enemy1_down.wav", false);// 循环播放背景音乐
    }

    //自己阵亡
    static public void me_down() {
        play("sound/me_down.wav", false);// 循环播放背景音乐
    }

    //自己发射子弹
    static public void bullet() {
        play("sound/bullet.wav", false);// 循环播放背景音乐
    }

    //循环
    public static void play(String file, boolean circulate) {
        try {
            player = new MusicPlay(file, circulate);
            player.play();// 播放器开始播放
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void BGMstop() {
        player.stop();
    }

}

