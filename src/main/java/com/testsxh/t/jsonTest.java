package com.testsxh.t;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.io.file.FileWriter;
import cn.hutool.core.util.HashUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.json.JSONObject;

import java.io.IOException;

public class jsonTest {
    public static void main(String[] args) throws InterruptedException, IOException {

        var arr1 = new JSONObject();
        //生成demo
        for (int i = 0; i < 10; ++i) {
            //            var salt = "sxh705"+System.currentTimeMillis();
            var salt = "sxh705";
            var k = DigestUtil.sha1Hex(salt + i).substring(1, 2);
            var v0 = DigestUtil.sha1Hex(salt + 5 * i).substring(1, 2);
            var v1 = Math.abs(HashUtil.mixHash(v0)) % 1000;
            arr1.set(k, v1);
        }
        System.out.println("arr1 " + arr1);

        //反序列化demo
        var arr2 = new JSONObject(arr1.toString());
        System.out.println("arr2 " + arr2);

        //存取demo

        //        FileUtil.writeUtf8String(""+arr1,"Save/2003");
        //        var arr3 = new JSONObject(
        //                FileUtil.readUtf8String("Save/2003")
        //        );
        //        System.out.println("arr3 "+arr3);


        //        Files.createFile(Paths.get("Save/2004"));

        FileUtil.touch("Save/2004");

        FileUtil.getAbsolutePath("Save/2004");

        var R = new FileReader("Save/2004");

        var W = new FileWriter("Save/2004");

        W.write("" + arr1);
        System.out.println(new JSONObject(R.readString()));


    }
}
