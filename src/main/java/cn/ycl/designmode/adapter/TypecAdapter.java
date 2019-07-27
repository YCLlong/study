package cn.ycl.designmode.adapter;

/**
 * typec接口的适配器
 * 我需要将USB接口的存储器适配到TypeC接口上
 *
 *
 */
public class TypecAdapter implements TypeC{
    private Usb usbDevice;
    public TypecAdapter(Usb usbDevice){
        this.usbDevice = usbDevice;
    }


    @Override
    public void dataIn(int[] datas) {
        for(int var:datas){
            //int形值占用4个字节,32个bit位
            usbDevice.dataIn(toByte(var));
        }
    }

    @Override
    public int[] dataOut() {
        return new int[0];
    }

    /**
     * 将一个整形转化成4个字节
     * @param var
     * @return
     */
    private byte[] toByte(int var){
        byte[] result = new  byte[4];
        String binaryString = Integer.toBinaryString(var);
        int n;
        if(binaryString.length() % 8 == 0) {
            n = binaryString.length() / 8;
        }else {
            n = binaryString.length() / 8 + 1;
        }

        //左边用0补齐
        int length = binaryString.length();
        for(int i=0;i<32 -length;i++){
            binaryString = '0' + binaryString;
        }
        StringBuilder sb = new StringBuilder(binaryString);
        //切割成4份
        for(int i=0;i<Integer.SIZE/Byte.SIZE;i++){
            result[i] = toByte(sb.substring(i*8,i*8 + 8));
        }
        return result;

    }

    private byte toByte(String binaryString){
        char[] chars = binaryString.toCharArray();
        int size = Byte.SIZE;
        int sum = 0;
        for(int i=chars.length-1;i >=0;i--,size--){
            byte value = Byte.valueOf(String.valueOf(chars[i]));
            sum += value * 2 << (8 - size);
        }
        return (byte) sum;
    }
}
