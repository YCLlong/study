package cn.ycl.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(8080);
            IMethod method = new Method();
            //  Naming.bind("rmi://127.0.0.1:8080/test", method);
            //如果端口被占用，直接释放资源，重新绑定
            Naming.rebind("rmi://127.0.0.1:8080/test", method);
            System.out.println(">>>>>INFO:远程IMethod对象绑定成功！");
        } catch (RemoteException e) {
            System.out.println("创建远程对象发生异常！");
            e.printStackTrace();
        } catch (MalformedURLException e) {
            System.out.println("发生URL畸形异常！");
            e.printStackTrace();
        }

        //直接就结束了，应该是将服务开关的权限给用户控制，在这儿可以让主线程阻塞
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
