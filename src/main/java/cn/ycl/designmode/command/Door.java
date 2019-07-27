package cn.ycl.designmode.command;

public class Door implements Opration {
    private String location;
    public Door(String location){
        this.location = location;
    }

    @Override
    public void on() {
        System.out.println(location + "的门开启");
    }

    @Override
    public void off() {
        System.out.println(location + "的门关闭");
    }
}
