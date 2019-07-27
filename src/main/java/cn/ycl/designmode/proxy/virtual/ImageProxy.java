package cn.ycl.designmode.proxy.virtual;

import java.io.IOException;

/**
 * 图片代理类
 */
public class ImageProxy implements Icon{
    RealImage image;
    public ImageProxy(RealImage image){
        this.image = image;
        //模拟加载图片
        image.loadImage();
    }


    @Override
    public int getWidth() {
        if(image.getImage() != null){
            return image.getWidth();
        }
        return 1000;
    }

    @Override
    public int getHeight() {
        if(image.getImage() != null){
            return image.getHeight();
        }
        return 400;
    }

    @Override
    public void paint() throws IOException {
        if(image.getImage() != null){
            image.paint();
            System.out.println("打印成功");
            return;
        }
        System.out.println("图片正在加载中，请您稍后");
    }
}
