package com.sxh;

import Rank.Rank;
import com.formdev.flatlaf.FlatLightLaf;
import com.level.game.GameMainWin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.HashMap;


public class 主界面 extends JFrame {
    public String name;
    public HashMap<String, Integer> varList = new HashMap<>();
    private JPanel Main;
    private JButton 选关模式Button;
    private JButton 进入商城Button;
    private JButton 无尽模式Button;
    private JButton 关于作者Button;
    private JButton 切换账号Button;
    private JButton 离开游戏Button;
    private JButton 多人模式Button;
    private JButton 排行榜Button;
    private JLabel L1;
    private JLabel 金币;
    private JLabel 武器;

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
                saveVar();
                setVisible(false); //you can't see me!
                dispose(); //Destroy the JFrame object
                System.exit(0);
            }
        });

        无尽模式Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false); //you can't see me!
                GameMainWin.main1(getThis());
                //                dispose(); //Destroy the JFrame object
            }
        });

        排行榜Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Rank.setpage();
            }
        });


        进入商城Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String[] options = {"清屏武器 价格10", "火力武器 价格10", "医疗包 价格10"};
                var res = (String) JOptionPane.showInputDialog(
                        getThis(),
                        "请选择你要购买的武器",
                        "商城",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        options,
                        options[0]
                );
                System.out.println(res);
                String snum;
                int num;
                if (res != null) {
                    while (true) {
                        snum = JOptionPane.showInputDialog("输入购买数量", 1);
                        try {
                            num = Integer.parseInt(snum);
                            break;
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(getThis(), "输入不是数字", "错误", JOptionPane.PLAIN_MESSAGE);
                        }
                    }
                    int s = varList.getOrDefault("金币", 0);
                    System.out.println("金币" + s);
                    if (num * 10 > s) {
                        JOptionPane.showMessageDialog(getThis(), "金币数量不足", "错误", JOptionPane.PLAIN_MESSAGE);
                    } else {
                        if (res.equals("清屏武器 价格10")) varList.put("清屏", varList.get("清屏") + num);
                        if (res.equals("火力武器 价格10")) varList.put("火力", varList.get("火力") + num);
                        if (res.equals("医疗包 价格10")) varList.put("生命", varList.get("生命") + num);

                        varList.put("金币", s - num * 10);
                    }
                    saveVar();
                    readVar();
                }
                //                //https://docs.oracle.com/javase/7/docs/api/java/awt/Dialog.ModalityType.html
                //                JOptionPane.showMessageDialog(getThis(), "没有图标对话框的消息~",
                //                            "Swing对话框", JOptionPane.PLAIN_MESSAGE);
                //                int a = JOptionPane.showConfirmDialog(getThis(),"您确定要退出？", "Swing是/否确认对话框",
                //                        JOptionPane.YES_NO_OPTION,
                //                        JOptionPane.QUESTION_MESSAGE);
                //                int res = JOptionPane.showConfirmDialog(getThis(),"确定要进入商城?", "Swing对话框",
                //                        JOptionPane.YES_NO_CANCEL_OPTION,
                //                        JOptionPane.QUESTION_MESSAGE);
                //原文出自【易百教程】，商业转载请联系作者获得授权，非商业请保留原文链接：https://www.yiibai.com/swingexamples/show_confirm_dialog_with_yesnocancel.html
            }
        });
        选关模式Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] options = {"第一关 打boos", "第二关 标题2", "第三关 测试3"};
                String res = (String) JOptionPane.showInputDialog(
                        getThis(),
                        "请选择你要选择的关卡",
                        "选关",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        options,
                        options[0]
                );
                System.out.println(res);
                if (res != null && res.equals("第一关 打boos")) {
                    setVisible(false); //you can't see me!
                    GameMainWin.main2(getThis());
                    setVisible(false);
                    //                    getThis().setVisible(false);
                    //                    dispose(); //Destroy the JFrame object
                }
            }
        });
    }

    public static void init(String name) {
        //        FlatLightLaf.setup();
        FlatLightLaf.setup();
        var F = new 主界面();
        F.name = name;

        var file = new File("Save/" + name);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
            F.saveVar();
        } else {
            F.readVar();
            System.out.println(F.varList);
        }

        F.setSize(400, 500);
        F.setLocationRelativeTo(null);
        F.setVisible(true);

        var l = F.varList;

        F.readVar();
    }

    public static void main(String[] args) throws InterruptedException {
        init("null 未知");
    }

    public void readVar() {
        var path = "Save/" + name;
        try {
            var ois = new ObjectInputStream(new FileInputStream(path));
            varList = (HashMap<String, Integer>) ois.readObject();
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
            关于.show("文件被占用, 请重试");
        }
        金币.setText("金币剩余" + varList.getOrDefault("金币", 0) + "    医疗包: " + varList.getOrDefault("生命", 0));
        武器.setText("清屏武器: " + varList.getOrDefault("清屏", 0) + "   火力武器: " + varList.getOrDefault("火力", 0));
        L1.setText("欢迎回来, " + name + "! ");
    }

    public void saveVar() {
        var path = "Save/" + name;
        try {
            var oos = new ObjectOutputStream(new FileOutputStream(path));
            oos.writeObject(varList);
            oos.flush();
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
            关于.show("文件被占用, 请重试");
        }
    }

    主界面 getThis() {
        return this;
    }
}
