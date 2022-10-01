package Rank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Rank {
    public static void setpage() {

        Icon ic = new ImageIcon("images/background.png");
        JFrame frm = new JFrame("Rank");
        CardLayout c = new CardLayout();
        frm.setLayout(c);

        JLabel img = new JLabel(ic);
        img.setSize(480, 600);
        JPanel p1 = new JPanel();
        p1.setSize(480, 600);
        p1.setLayout(null);
        JButton b1 = new JButton("World Rank");
        b1.setFont(new Font("宋体", Font.PLAIN, 20));
        b1.setContentAreaFilled(false);
        b1.setBorderPainted(false);
        JButton b2 = new JButton("My Record");
        b2.setFont(new Font("宋体", Font.PLAIN, 20));
        b2.setContentAreaFilled(false);
        b2.setBorderPainted(false);
        b1.setBounds(140, 160, 200, 50);
        b2.setBounds(140, 370, 200, 50);
        p1.add(b1);
        p1.add(b2);
        p1.add(img);

        JPanel p2 = new JPanel();
        Record.showrank();
        String[] worldrank = Record.wrank;
        JList list1 = new JList(worldrank);
        DefaultListCellRenderer renderer = (DefaultListCellRenderer) list1.getCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        list1.setFont(new Font("宋体", Font.PLAIN, 20));
        JScrollPane sp1 = new JScrollPane();
        sp1.setViewportView(list1);
        p2.setLayout(new BorderLayout());
        p2.add(sp1, BorderLayout.CENTER);
        JButton b3 = new JButton("返回");
        p2.add(b3, BorderLayout.SOUTH);
        JLabel l1 = new JLabel("World Rank");
        l1.setFont(new Font("仿宋", Font.BOLD, 40));
        l1.setHorizontalAlignment(SwingConstants.CENTER);
        p2.add(l1, BorderLayout.NORTH);
        p2.setSize(480, 600);

        JPanel p3 = new JPanel();
        Record.showrec();
        String[] rec = Record.record;
        JList list2 = new JList(rec);
        DefaultListCellRenderer renderer1 = (DefaultListCellRenderer) list2.getCellRenderer();
        renderer1.setHorizontalAlignment(SwingConstants.CENTER);
        list2.setFont(new Font("宋体", Font.PLAIN, 20));
        JScrollPane sp2 = new JScrollPane();
        sp2.setViewportView(list2);
        p3.setLayout(new BorderLayout());
        p3.add(sp2, BorderLayout.CENTER);
        JButton b4 = new JButton("返回");
        p3.add(b4, BorderLayout.SOUTH);
        JLabel l2 = new JLabel("My Record");
        l2.setFont(new Font("仿宋", Font.BOLD, 40));
        l2.setHorizontalAlignment(SwingConstants.CENTER);
        p3.add(l2, BorderLayout.NORTH);
        p3.setSize(480, 600);


        frm.add(p1);
        frm.add(p2);
        frm.add(p3);

        ActionListener lis = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cmd = e.getActionCommand();
                switch (cmd) {
                    case "World Rank":
                        c.next(frm.getContentPane());
                        break;
                    case "My Record":
                        c.last(frm.getContentPane());
                        break;
                    case "返回":
                        c.first(frm.getContentPane());
                        break;
                }
            }
        };

        b1.addActionListener(lis);
        b2.addActionListener(lis);
        b3.addActionListener(lis);
        b4.addActionListener(lis);

        frm.setSize(480, 600);
        frm.setLocationRelativeTo(null);
        frm.setVisible(true);
    }
}