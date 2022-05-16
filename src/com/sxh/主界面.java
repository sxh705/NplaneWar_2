package com.sxh;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.HashMap;
import com.MainFrame.*;
import com.level.game.GameMain;


public class 主界面 extends JFrame{
    private JPanel Main;
    private JButton 战役模式关卡11Button;
    private JButton 进入商城Button;
    private JButton 无尽模式Button;
    private JButton 关于作者Button;
    private JButton 切换账号Button;
    private JButton 离开游戏Button;
    private JButton 多人模式Button;
    private JButton 排行榜Button;
    private JLabel L1;
    public String name;
    public GameMain s;
    public HashMap<String, Integer> varList = new HashMap<>();

    public static void readVar(String path,HashMap<String, Integer> varList){

        try {
            var ois = new ObjectInputStream(new FileInputStream(path));
            varList = (HashMap<String, Integer>) ois.readObject();
            ois.close();
        }catch (Exception e){
            e.printStackTrace();
            关于.show("文件被占用, 请重试");
        }
    }

    public static void saveVar(String path,HashMap<String, Integer> varList){
        try{
            var oos = new ObjectOutputStream(new FileOutputStream(path));
            oos.writeObject(varList);
            oos.flush();
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
            关于.show("文件被占用, 请重试");
        }
    }

    public 主界面() {
        super();
        this.setTitle("主菜单");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(Main);
        this.setResizable(false);
        this.pack();
        关于作者Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                关于.main(null);
            }
        });
        切换账号Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false); //you can't see me!

                登录界面.main(null);
                dispose(); //Destroy the JFrame object

            }
        });
        离开游戏Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveVar("Save"+name,varList);
                setVisible(false); //you can't see me!
                dispose(); //Destroy the JFrame object
            }
        });
        无尽模式Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false); //you can't see me!

                s.setVisible(true);
                s.start();
//                for(int i = 0;i < 99999999;++i){
//                    System.out.println(i);
//
//                    try {
//                        Thread.sleep(200);
//                    } catch (InterruptedException ex) {
//                        ex.printStackTrace();
//                    }
//
//                }


                dispose(); //Destroy the JFrame object
            }
        });


    }



    public static void init(String name) {
        FlatLightLaf.setup();
        var F = new 主界面();
        F.name = name;

        var file = new File("Save/"+name);
        if(!file.exists()){
            try{
                file.createNewFile();
            }catch (Exception e){
                e.printStackTrace();
            }
            saveVar("Save/"+name,F.varList);
        }else{
            readVar("Save/"+name,F.varList);
        }

        F.setSize(400,500);
        F.setLocationRelativeTo(null);
        F.setVisible(true);
        F.L1.setText("欢迎回来, "+name+"! ");

        F.s = new GameMain("无尽模式");
        F.s.setVisible(false);
    }


    public static void main(String[] args) throws InterruptedException {

        FlatLightLaf.setup();
        var F = new 主界面();
//        F.setBounds(400,30,400,500);
        F.setSize(400,500);
        F.setLocationRelativeTo(null);

        F.setVisible(true);

        F.s = new GameMain("无尽模式");
        F.s.setVisible(false);
    }
}
