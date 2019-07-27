package cn.ycl.designmode.proxy.dynamic;

public class MyMethod implements IMethod {
    @Override
    public void sayHello(String msg) {
        System.out.println("我的方法执行了," + msg);
    }
}
