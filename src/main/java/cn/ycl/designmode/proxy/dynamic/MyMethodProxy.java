package cn.ycl.designmode.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyMethodProxy implements InvocationHandler {
    //被代理的对象
    private Object target;

    public MyMethodProxy(Object target){
        this.target = target;
    }

    /**
     * 返回代理对象
     * @return
     */
    public Object getProxy() {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //执行结果
        Object result = null;

        //判断目标对象中是否存在需要代理的方法（代理的方法定义在一个接口中，这里是IMethod）
        if(method.getName().equals("sayHello")){
            //如果确定要代理，加上代理的逻辑，中间执行目标对象的方法
            before();
            //result = method.invoke(proxy,args);这样调用会进入死循环
            result = method.invoke(target,args);
            after();
        }else {
            //这样调用会报错，不知道为什么，不理解
            result = method.invoke(proxy,args);
        }
        return result;
    }

    private void before(){
        System.out.println("在执行之前，我执行了");
    }

    private void after(){
        System.out.println("在执行之后，我执行了");
    }
}
