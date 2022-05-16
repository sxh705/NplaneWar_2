package com.sxh.排行榜;

import javax.swing.*;

public class 排行榜 extends JFrame{
    private JPanel Main;
    private JScrollPane JS;
    排行榜(){
        super();
        this.setTitle("排行榜");
        this.add(Main);
        this.pack();
        this.setSize(600,600);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
//
//    public void add(String a){
//        JS.add()
//    }

    public static void main(String[] args) {
        var F = new 排行榜();
    }
}
