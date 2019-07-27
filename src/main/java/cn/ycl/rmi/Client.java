package cn.ycl.rmi;

import java.io.UnsupportedEncodingException;
        import java.net.MalformedURLException;
        import java.rmi.Naming;
        import java.rmi.NotBoundException;
        import java.rmi.RemoteException;

public class Client {
    public static void main(String[] args) {
        try {
            // 填写服务器ip
            IMethod rhello = (IMethod) Naming.lookup("rmi://127.0.0.1:8080/test");
            //这个会在客户端本地打印'aaaa'，服务端不会打印，就很神奇
            rhello.showMsg("aaaa");
            MyData<byte[]> data = new MyData<byte[]>();
            data.setData("ycl is cool".getBytes("utf-8"));
            MyData<byte[]> result = rhello.dealData(data);
            System.out.println("结束");
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
