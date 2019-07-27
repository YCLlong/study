package cn.ycl.designmode.adapter;

/**
 * usb接口
 */
public interface Usb {
    void dataIn(byte[] bytes);

    byte[] dataOut();
}
