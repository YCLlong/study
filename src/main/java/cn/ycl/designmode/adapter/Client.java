package cn.ycl.designmode.adapter;

public class Client {
    public static void main(String[] args) {
        TypeC device = new TypecAdapter(new UsbStorage());
        device.dataIn(new int[]{4,180,2557897});
    }
}
