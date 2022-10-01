package com.sxh;

import java.io.*;

public class Warehouse {

    public void write(int i) throws IOException {
        File file = new File("Warehouse.txt");
        String number = "" + i + "\n";
        if (!file.exists())
            file.createNewFile();
        FileOutputStream out = new FileOutputStream(file, false);
        out.write(number.getBytes("gb2312"));
        out.close();
    }

    public int read() throws IOException {
        File file = new File("Warehouse.txt");
        if (!file.exists() || file.isDirectory())
            throw new FileNotFoundException();
        BufferedReader br = new BufferedReader(new FileReader(file));
        String number = br.readLine();
        int a = Integer.parseInt(number);

        return a;
    }

}
