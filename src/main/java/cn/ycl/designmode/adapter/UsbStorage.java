package cn.ycl.designmode.adapter;

import java.util.List;
import java.util.Map;

/**
 * usb接口类型的存储器
 */
public class UsbStorage implements Usb {
    byte[] nowData;

    @Override
    public void dataIn(byte[] bytes) {
        for(byte b:bytes){
            System.out.print(b + " ");
        }
        nowData = bytes;
    }

    @Override
    public byte[] dataOut() {
        return nowData;
    }
}
