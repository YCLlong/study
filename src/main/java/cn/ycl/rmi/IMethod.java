package cn.ycl.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 1，我们定义的接口需要继承 java.rmi.Remote,这个Remote接口类似于序列化的接口，里面里面没有任何要序列化的方法
 * 2，在接口中定义好我们自己方法，需要显示的抛出RemoteException
 */
public interface IMethod extends Remote {
    Integer add(Integer v1,Integer v2) throws RemoteException;
    void showMsg(String msg) throws RemoteException;
    MyData<byte[]>  dealData(MyData<byte[]> data) throws RemoteException;
}
