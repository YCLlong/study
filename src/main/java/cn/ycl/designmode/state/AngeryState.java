package cn.ycl.designmode.state;

public class AngeryState implements State {
    @Override
    public void describe() {
        System.out.println("老子现在真的太生气了");
    }

    @Override
    public void speek() {
        System.out.println("给老子滚！");
    }

    @Override
    public void helpMe() {
        System.out.println("求你帮帮我吧！！");
    }
}
