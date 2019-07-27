package cn.ycl.designmode.state;

public class HappyState implements State {
    @Override
    public void describe() {
        System.out.println("哈哈哈哈，我真的非常的开心");
    }

    @Override
    public void speek() {
        System.out.println("我现在很乐意和你交流哦");
    }

    @Override
    public void helpMe() {
        System.out.println("助人为乐嘛，有什么我可以帮你的尽管讲");

    }
}
