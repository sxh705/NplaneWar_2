

package com.testsxh.t;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

class propertyTest {
    public static void main(String[] args) throws IOException {
        var url = "Save/pTest.txt";
        var F = new File(url);
        if (!F.exists()) Files.createFile(Paths.get(url));

        Properties props = new Properties();
        //        props.load(new FileInputStream(url));

        props.load(new FileReader(url, StandardCharsets.UTF_8));

        //        props.setProperty("sxh","1");
        //        props.setProperty("lcr","2");
        //        props.setProperty("测试","测试2");

        System.out.println(props.getProperty("测试"));
        System.out.println(props.getProperty("sxh") + props.getProperty("lcr"));


        props.store(new FileWriter(url, StandardCharsets.UTF_8), "注释");
        //        props.store(new FileOutputStream(url),"注释");


    }

}

