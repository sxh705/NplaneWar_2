package com.sxh;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class 关于 extends JFrame {
    public JLabel L1;
    private JPanel Main;
    private JButton 确定Button;

    public 关于() throws HeadlessException {
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(Main);
        this.pack();

        确定Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false); //you can't see me!
                dispose(); //Destroy the JFrame object
            }
        });
    }

    public static void main(String[] args) {
        FlatLightLaf.setup();
        JFrame F = new 关于();
        F.setTitle("关于");
        F.setBounds(400, 80, 200, 300);
        F.setVisible(true);
    }

    public static void show(String a) {
        FlatLightLaf.setup();
        var F = new 关于();
        F.setTitle("通知");
        F.L1.setText(a);
        //        F.setBounds(400,80,200,100);
        F.setSize(200, 100);
        F.setLocationRelativeTo(null);
        F.setVisible(true);
    }
}
