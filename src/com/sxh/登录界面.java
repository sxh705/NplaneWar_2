package com.sxh;

import com.formdev.flatlaf.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.HashMap;

public class 登录界面 extends JFrame{

    private JPanel mainPainel;
    private JLabel Title;
    private JButton 登录账号Button;
    private JButton 注册账号Button;
    private JPasswordField passwordField1;
    private JCheckBox 记住账号CheckBox;
    private JCheckBox 记住密码CheckBox;
    public JComboBox comboBox1;
    private JButton 注销账号Button;
    HashMap<String,String> nameList = new HashMap<>();
    public 登录界面(){
        super();
        this.setTitle("飞机大战 登录");
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE );
        this.setContentPane(mainPainel);
        this.setResizable(false);
        this.pack();
        this.init();
        注册账号Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false); //you can't see me!
                账号密码_注册.子注册(登录界面.this);
//                dispose(); //Destroy the JFrame object
            }
        });
        登录账号Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(checkPw()){
                    setVisible(false); //you can't see me!
                    主界面.init( comboBox1.getSelectedItem().toString() );
                    dispose(); //Destroy the JFrame object
                }else{
                    关于.show("密码错误! ");
                }
            }
        });
        注销账号Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(checkPw()){
                    nameList.remove(comboBox1.getSelectedItem());
                    savePsw();
                    readPsw();
                    关于.show("账号已注销! ");
                }else{
                    关于.show("密码错误! ");
                }
            }
        });
    }

    public boolean checkPw(){
        var i = comboBox1.getSelectedItem();
        var j = new String(passwordField1.getPassword());
        i = nameList.get(i);
        return i.equals(j);
    }

    public void init(){
        fileInit();
    }

    void fileInit(){
        var file = new File("Save/name.info");
        if(!file.exists()){
            try{
                file.createNewFile();
            }catch (Exception e){
                e.printStackTrace();
            }
            savePsw();
        }else{
            readPsw();
        }
    }

    public void readPsw(){
        try {
            var ois = new ObjectInputStream(new FileInputStream("Save/name.info"));
            nameList = (HashMap<String, String>) ois.readObject();
            ois.close();
        }catch (Exception e){
            e.printStackTrace();
            关于.show("文件被占用, 请重试");
        }

        System.out.println("Key/Value 映射: " + nameList.entrySet());
        comboBox1.removeAllItems();
        for(String i:nameList.keySet()){
            comboBox1.addItem(i);
        }
    }

    public void savePsw(){
        try{
            var oos = new ObjectOutputStream(new FileOutputStream("Save/name.info"));
            oos.writeObject(nameList);
            oos.flush();
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
            关于.show("文件被占用, 请重试");
        }
    }


    public static void main(String[] args) {
        FlatLightLaf.setup();
        var F = new 登录界面();
        F.setSize(400,400);
        F.setLocationRelativeTo(null);
        F.setVisible(true);
    }

}
