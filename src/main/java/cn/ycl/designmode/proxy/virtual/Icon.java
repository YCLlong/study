package cn.ycl.designmode.proxy.virtual;

import java.io.IOException;

/**
 * 不管是什么代理，肯定有一个接口，这个接口中有着需要被代理的方法
 */
public interface Icon {
    int getWidth();
    int getHeight();
    void paint() throws IOException;
}
