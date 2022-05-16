package com.sxh;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class 账号密码_注册 extends JFrame {
    private JPanel Main;
    private JButton 注册账号Button;
    private JButton 返回Button;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;

    public 登录界面 Father;

    public 账号密码_注册() {
        super();
        this.setTitle("用户注册");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(Main);
        this.setResizable(false);
        this.pack();
        返回Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false); //you can't see me!
                登录界面.main(null);
                dispose(); //Destroy the JFrame object
            }
        });

        注册账号Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(checkPassWord() && checkAccount()){
                    var name = textField1.getText();
                    var pass = new String(passwordField2.getPassword());
                    Father.nameList.put(name,pass);
                    Father.savePsw();
                    Father.readPsw();
                    setVisible(false); //you can't see me!
                    Father.setVisible(true);
                    关于.show("注册成功");
                    dispose(); //Destroy the JFrame object
                }
                return;
            }
        });
    }

    boolean checkPassWord(){
        var a = passwordField1.getPassword();
        var b= passwordField2.getPassword();
        var c = textField1.getText();
        if(a.length < 4 || a.length > 15){
            关于.show("密码长度4-15位之间!");
            return false;
        }
        if(c.length() < 4 || c.length() > 15){
            关于.show("用户名长度4-15位之间!");
            return false;
        }
        if(Father.nameList.containsKey(c)){
            关于.show("用户名不得重复!");
            return false;
        }
        if(!Arrays.equals(a,b)){
            关于.show("两次密码不一致");
            return false;
        }
        return true;
    }

    boolean checkAccount(){
        var s = textField1.getText();
        return true;
    }

    public static void 子注册(登录界面 Father) {
        FlatLightLaf.setup();
        var F = new 账号密码_注册();
        F.Father = Father;
        F.setSize(300,300);
        F.setLocationRelativeTo(null);
        F.setVisible(true);
    }

//    public static void main(String[] args) {
//        FlatLightLaf.setup();
//        JFrame F = new 账号密码_注册();
////        F.setBounds(400,30,300,300);
//        F.setSize(300,300);
//        F.setLocationRelativeTo(null);
//        F.setVisible(true);
//    }

}
