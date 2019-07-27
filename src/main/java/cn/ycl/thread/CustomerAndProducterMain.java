package cn.ycl.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class CustomerAndProducterMain {
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition productCondition = lock.newCondition();
    private final Condition customerCondition = lock.newCondition();
    private int resource;


    /**
     * 生产者线程任务
     */
    private class ProductTask implements Runnable{
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "开始执行");
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "获得锁");
            /**
             * 这里为什么不用if而用while呢？
             */
            while (resource == 100){
                try {
                    /**
                     * 如果资源创造的有100个了，就让生产者线程暂停运行
                     */
                    System.out.println(Thread.currentThread().getName() + "资源数量为100，不能再生产了，调用wait（）方法");
                    productCondition.await();
                    System.out.println(Thread.currentThread().getName() + "调用wait() 方法之后");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            resource ++;
            System.out.println(Thread.currentThread().getName() +"生产资源+1.resource = " + resource);
            //生产成功一个之后唤醒一个消费者线程
            customerCondition.signal();
            lock.unlock();
        }
    }

    /**
     * 消费者线程
     */
    private class CustomerTask implements Runnable{
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "开始执行");
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "获得锁");
            while (resource == 0){
                System.out.println(Thread.currentThread().getName() + ":没有资源可以消费了，调用wait()方法等待生产线程生产");
                try {
                    customerCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ":调用wait()方法之后");
            }
            resource--;
            //消费之后调用生产线程生产
            productCondition.signal();
            System.out.println(Thread.currentThread().getName() + ":消费成功，resource=" + resource);
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        CustomerAndProducterMain main = new CustomerAndProducterMain();
        for(int i =0;i<10000;i++){
            new Thread(main.new ProductTask(),"生产者线程-" + i).start();
        }
        for(int i =0;i<10000;i++){
            new Thread(main.new CustomerTask(),"消费者线程-" + i).start();
        }
    }
}
