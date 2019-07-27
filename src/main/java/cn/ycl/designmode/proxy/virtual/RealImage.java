package cn.ycl.designmode.proxy.virtual;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Data
@RequiredArgsConstructor
public class RealImage implements Icon{
    @NonNull
    private String content;
    private volatile BufferedImage image = null;

    @Override
    public int getWidth() {
        return image.getWidth();
    }

    @Override
    public int getHeight() {
        return image.getHeight();
    }

    @Override
    public void paint() throws IOException {
        File outputfile  = new File("C:\\Users\\ycl\\Desktop\\images",System.currentTimeMillis() + "save.png");
        ImageIO.write(image,"png",outputfile);
    }


    private BufferedImage createImage(){
        //创建一个宽100像素，高60像素的RGB类型的图片的数据缓冲区
        BufferedImage data = new BufferedImage(100,60,BufferedImage.TYPE_INT_RGB);
        //得到它的绘制环境，也就是画笔
        Graphics2D g2=(Graphics2D)data.getGraphics();
        g2.setColor(Color.YELLOW);//设置颜色
        g2.fillRect(0, 0, 100, 60);//填充整张图片,设置背景色
        g2.setColor(Color.RED);//设置颜色
        g2.drawRect(0,0,99,59);//画一个外框
        g2.setFont(new Font("黑体",Font.BOLD,15));//设置字体
        g2.drawString(content, 3, 40);
        return data;
    }

    public void loadImage(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //模拟10s后开始加载好图片
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                image = createImage();
            }
        }).start();
    }
}
