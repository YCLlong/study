package cn.ycl.designmode.observer;

import java.util.Observable;
import java.util.Observer;

public class MyObserver2 implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        MyData data = (MyData) arg;
        System.out.println(o.getClass().getName() + "推送来了,我是Observer2：" + data.toString());
    }
}
