package cn.ycl.designmode.observer;

public class Main {
    public static void main(String[] args) {
        MyObserver1 observer1 = new MyObserver1();
        MyObserver2 observer2 = new MyObserver2();
        MySubject subject = new MySubject();
        subject.addObserver(observer1);
        subject.addObserver(observer2);
        subject.pushData("第1次推送");
        //移除通知者
        subject.deleteObserver(observer1);
        //尝试重复添加订阅者看看执行效果
        subject.addObserver(observer2);
        subject.pushData("第2次推送");
    }
}
