package cn.ycl.thread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 信号量，相当于是锁的一种扩展
 * 锁允许同一时刻只有一个线程进入同步区执行，但是型号量在同一时刻允许多个线程执行
 *
 */
public class SemaphoreMan {
    /**
     * 信号量的构造函数有两个
     * 1，指定最多可以被获取的信号量权限数，这个数量并不是线程数量，比如我设置成5，一个线程acquire（3）一个线程 acquire(2),那么权限数就被用完了，别的线程就不能获取到，就无法进同步块执行。
     * 2,指定数量的同时指定锁是否公平
     */
    private  final  Semaphore semaphore = new Semaphore(5);

    private int resource;

    private class Task implements Runnable{

        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + "：开始执行");
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName() + "：获得信号量权限数：1个。剩余可获取信号量的数量:" + semaphore.availablePermits());
                resource++;
                semaphore.release();
                System.out.println(Thread.currentThread().getName() + "：任务执行完毕，释放信号量权限数：1个。剩余可获取信号量的数量:" + semaphore.availablePermits());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    private void testMethod(){
        //可以获取的型号量数量
        int n = semaphore.availablePermits();

        //获取并返回所有立即可用的许可证
        semaphore.drainPermits();

        //获取许可证，数量为1，会相应中断，获取不到会阻塞
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //获取指定许可数量，获取不到会阻塞
        try {
            semaphore.acquire(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //获取许可数量，但是不相应中断，获取不到会阻塞
        semaphore.acquireUninterruptibly();

        //获取指定的许可数量，但是不相应中断，获取不到会阻塞
        semaphore.acquireUninterruptibly(2);

        //释放许可，数量为1
        semaphore.release();

        //释放指定数量的许可
        semaphore.release(2);

        //尝试获得许可，获取到了返回true，获取不到立刻返回false，不会阻塞
        boolean canGet = semaphore.tryAcquire();

        //尝试获得指定许可，获取到了返回true，获取不到立刻返回false，不会阻塞
        canGet = semaphore.tryAcquire(2);

        //尝试在指定时间内获得1个许可，获取到了立刻返回true，指定时间内获取不到返回false，不会阻塞，会响应中断
        try {
            canGet = semaphore.tryAcquire(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //尝试在指定时间内获得指定数量的许可，获取到了立刻返回true，指定时间内获取不到返回false，不会阻塞，会响应中断
        try {
            canGet = semaphore.tryAcquire(3,2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SemaphoreMan main = new SemaphoreMan();
        Thread[] ts = new Thread[1000];
        for(int i=0;i<1000;i++){
            ts[i] = new Thread(main.new Task(),"线程-" + i);
        }

        for(int i=0;i<1000;i++){
            ts[i].start();
        }
    }
}
