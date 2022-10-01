package com.sxh.排行榜;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class 排行榜 extends JFrame {
    private JPanel Main;
    private JScrollPane JS;

    排行榜() {
        super();
        this.setTitle("排行榜");
        this.add(Main);
        this.pack();
        this.setSize(600, 600);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        this.setFocusable(true);
        this.requestFocus();


        JS.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                System.out.println(e.getKeyChar());
            }
        });
    }
    //
    //    public void add(String a){
    //        JS.add()
    //    }

    public static void main(String[] args) {
        FlatLightLaf.setup();
        var F = new 排行榜();
    }
}
