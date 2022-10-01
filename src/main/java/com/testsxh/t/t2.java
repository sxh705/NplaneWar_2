package com.testsxh.t;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class t2 {
    public static void main(String[] args) {
        createWindow();
    }

    private static void createWindow() {
        JFrame frame = new JFrame("Swing创建模态对话框");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createUI(frame);
        frame.setSize(560, 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void createUI(final JFrame frame) {
        JPanel panel = new JPanel();
        LayoutManager layout = new FlowLayout();
        panel.setLayout(layout);

        JButton button = new JButton("点击这里开始~");
        final JDialog modelDialog = createDialog(frame);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modelDialog.setVisible(true);
            }
        });

        panel.add(button);
        frame.getContentPane().add(panel, BorderLayout.CENTER);
    }

    private static JDialog createDialog(final JFrame frame) {
        //        final JDialog modelDialog = new JDialog(frame, "Swing创建模态对话框", Dialog.ModalityType.DOCUMENT_MODAL);
        final JDialog modelDialog = new JDialog(frame, "Swing创建模态对话框", Dialog.ModalityType.MODELESS);
        modelDialog.setBounds(132, 132, 300, 200);
        Container dialogContainer = modelDialog.getContentPane();
        dialogContainer.setLayout(new BorderLayout());
        dialogContainer.add(new JLabel("欢迎您学习Java/Swing!")
                , BorderLayout.CENTER);
        JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout());
        JButton okButton = new JButton("确定");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modelDialog.setVisible(false);
            }
        });

        panel1.add(okButton);
        dialogContainer.add(panel1, BorderLayout.SOUTH);

        return modelDialog;
    }
}//原文出自【易百教程】，商业转载请联系作者获得授权，非商业请保留原文链接：https://www.yiibai.com/swingexamples/show_modal_dialog.html

