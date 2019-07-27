package cn.ycl.designmode.proxy.virtual;

import java.io.IOException;

public class Client {
    public static void main(String[] args) throws IOException, InterruptedException {
        ImageProxy image = new ImageProxy(new RealImage("小龙最帅"));

        while (true){
            System.out.println("图片高度:" + image.getHeight());
            System.out.println("图片宽度：" + image.getWidth());
            System.out.println("开始打印图片：");
            image.paint();
            Thread.sleep(4000);
        }
    }
}
