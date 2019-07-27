package cn.ycl.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * 到计时器
 * 比较好用的工具，可以设置一个倒计时标记，当标记数量为0时，就可以唤醒在这个倒计时器上等待的线程。
 * 计数器减一的方法 countDownLatch.countDown()
 * 阻塞在倒计时器上的方法：
 * countDownLatch.await()
 * boolean await(long timeout, TimeUnit unit)
 */
public class CountDownLatchMain {
    final CountDownLatch countDownLatch = new CountDownLatch(5);

    class Task implements Runnable{
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + ":开始执行");
            LockSupport.parkNanos(2000000000);
            countDownLatch.countDown();
            System.out.println(Thread.currentThread().getName() + ":执行完毕" + countDownLatch.getCount());
        }
    }
    public static void main(String[] args) {
        CountDownLatchMain main = new CountDownLatchMain();
        for(int i=0;i<10;i++){
            new Thread(main.new Task()).start();
        }
        try {
            boolean t = main.countDownLatch.await(1,TimeUnit.SECONDS);
            System.out.println("t=" + t);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("主线程结束");
    }

    private void testMethod(){
        //倒计时器数量减一
        countDownLatch.countDown();
        try {
            //让某个线程阻塞到这个倒计时器上，等倒计时器倒计时为0之后就会唤醒阻塞在它上面的线程
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            /**
             *  让某个线程阻塞到这个倒计时器上,设置了等待时间，在指定的时间内，如果倒计时器还没倒计时到0.
             *  那么这个方法直接返回，返回false
             *
             *  如果提前倒计时器计时为0，那么返回true，会响应中断
             */
            countDownLatch.await(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //获得当前倒计时器倒计时的值
        countDownLatch.getCount();
    }
}
