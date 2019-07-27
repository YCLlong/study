package cn.ycl.designmode.proxy.dynamic;

public class Client {
    public static void main(String[] args) {
        Client client = (Client) new MyMethodProxy(new Client()).getProxy();
       // method.sayHello("小龙最帅");
        client.test();
    }

    public void test(){
        System.out.println("test");
    }
}
