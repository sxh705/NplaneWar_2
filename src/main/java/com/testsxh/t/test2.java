package com.testsxh.t;

import java.util.Locale;
import java.util.Scanner;

public class test2 {
    public static void main(String[] args) {
        var sc = new Scanner(System.in);
        int max = Integer.MIN_VALUE;
        System.out.println("请输入数据. ");
        while (true) {
            if (sc.hasNextInt()) {
                max = Math.max(sc.nextInt(), max);
            } else {
                var str = sc.next().toLowerCase(Locale.ROOT);
                if (str.equals("end")) {
                    break;
                } else {
                    System.out.println("输入不合法, 重新输入");
                }
            }
        }
        System.out.println("最大值为: " + max);
    }
}
