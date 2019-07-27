package cn.ycl.designmode.decorate.jdk;

import java.io.File;
import java.io.FileInputStream;

public class Main {
    public static void main(String[] args) {
        try {
            ToUpperInputStream inputStream = new ToUpperInputStream(new FileInputStream(new File("C:\\Users\\ycl\\Desktop\\test.txt")));
            System.out.println(Character.toChars(inputStream.read()));
            byte[] b = new byte[1024];
            int l = inputStream.read(b);
            System.out.println(new String(b));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
