package cn.ycl.designmode.state;

public class SadState implements State {
    @Override
    public void describe() {
        System.out.println("我现在很难过");
    }

    @Override
    public void speek() {
        System.out.println("我不想说话，我想静静");

    }

    @Override
    public void helpMe() {
        System.out.println("有什么我能帮你的呢，只要我做的到吧");
    }
}
