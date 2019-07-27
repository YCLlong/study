package cn.ycl.rmi;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;

public class Method implements IMethod, Serializable {
    @Override
    public Integer add(Integer v1, Integer v2) throws RemoteException {
        if(v1 == null || v2 == null){
            throw new IllegalAccessError("参数为null");
        }
        return v1+v2;
    }

    @Override
    public void showMsg(String msg) throws RemoteException {
        if(msg != null && !"".equals(msg)){
            System.out.println(msg);
        }
    }

    @Override
    public MyData<byte[]> dealData(MyData<byte[]> data) throws RemoteException {
        //业务逻辑，如果data的数据域是 "燕成龙好帅，那么就返回0 success,你客气了"、
        if(data == null || data.getData() == null){
            throw new IllegalAccessError("data为null");
        }
        byte[] d = data.getData();
        try {
            String result = new String(d,"utf-8");
            if("燕成龙好帅".equals(result)){
                return new MyData<byte[]>(0,"success","你客气了".getBytes("utf-8"));
            }else {
                return new MyData<byte[]>(-1,"error");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
