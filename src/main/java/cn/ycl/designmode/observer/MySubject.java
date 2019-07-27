package cn.ycl.designmode.observer;

import java.util.Observable;

public class MySubject extends Observable {

    public void pushData(String pushName){
        //必须要调用这个hasChanged 方法，不然数据不会推过去
        setChanged();
        notifyObservers(new MyData(pushName,80,90));
    }
}
