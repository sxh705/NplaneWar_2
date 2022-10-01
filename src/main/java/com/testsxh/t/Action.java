package com.testsxh.t;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Action extends Frame implements KeyListener {

    /**
     * @param args
     */
    Button b1;

    public Action() {
        Frame frame = new Frame("事件监听");
        //b1=new Button("按下");
        addKeyListener(this);//**绑定监听 没有这行 不会有任何监听**
        this.setSize(500, 500);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);

            }
        });


        this.setVisible(true);


    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Action action = new Action();

    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("T" + e.getKeyChar());
        // TODO Auto-generated method stub

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        System.out.println("p" + e.getKeyChar());
        if (e.getKeyCode() == KeyEvent.VK_E) {
            System.out.println("按下E键");

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        System.out.println("R" + e.getKeyChar());
    }

}

