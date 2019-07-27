package cn.ycl.designmode.command;

public class Light implements Opration {
    private String location;
    public Light(String location){
        this.location = location;
    }

    @Override
    public void on() {
        System.out.println(location + "的灯开启");
    }

    @Override
    public void off() {
        System.out.println(location + "的灯关闭");
    }
}
