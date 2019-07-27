package cn.ycl.thread;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 重入锁学习，可以完全替代synchronized关键字
 * 根据名字可知，重入，标识可以多次获得锁，获得几次就记得解锁几次，不然锁资源不会释放
 * 如果解锁的次数大于获取锁的次数，就会报出{@link IllegalMonitorStateException}异常
 *
 */
public class ReentrantLockMain {
    private int resource;
    /**
     * 是否创建公平的锁。
     * 当线程执行到同步区，就会尝试获取这个同步代码所在的锁的资源，如果锁资源被别的对象占用着，那么这个线程就处于阻塞状态。
     * 这个阻塞的线程会在锁对象的线程等待区中
     * 非公平锁当占用锁资源的线程执行完任务，释放锁资源时，会随机唤醒一个等待区的线程，让那个线程获取锁。
     *  公平锁：锁对象内部会维护一个有序的线程队列，当请求锁但是没有请求到时，就会进入这个有序队列中，当持有锁的线程执行完同步代码块之后
     *  就会从有序队列中唤醒最先进入队列的线程，让它执行，所以公平锁不会出现饥饿现象，但是相对于非公平锁而言，它需要维护一个有序队列
     *  效率也比非公平锁低下
     */
    //final ReentrantLock lock = new ReentrantLock(true);
    final ReentrantLock lock = new ReentrantLock();

    /**
     * Condition对象是重入锁的好搭档
     * 内部锁synchronized（锁对象A）的同步代码块中才能够调用  锁对象A.wait() 和 锁对象A.notify()
     * 很明显，线程只能阻塞到一个对象的等待线程组中，比如消费者线程和生产者线程如果都保存到一个等待组中
     * 而唤醒是随机唤醒，逻辑就很混乱。
     * 但是Lock接口中的newCondition()方法，可以创建监视器对象。
     * 一个锁可以创建多个监视器对象向，这样就可以把消费者线程放到监视器对象A中，生产者线程放到监视器对象B中
     * 这样唤醒哪个线程就会很明确。
     */
    final Condition readCondition = lock.newCondition();
    final Condition writeCondition = lock.newCondition();



    private class Task implements Runnable{
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " start");
            try{
                for(int i=0;i<100;i++){
                    /**
                     * 重入锁 ReentrantLock 实现了Lock接口，它和内部锁synchronized不同之处在于它的上锁和释放锁是显示操作的，这将非常的灵活
                     */
                    lock.lockInterruptibly();
                    resource++;
                    lock.unlock();
                    Thread.sleep(1000);
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                /**
                 * 被中断的线程没有获取到锁
                 */
                if(lock.isHeldByCurrentThread()){
                    System.out.println("-------------------");
                    lock.unlock();
                }
            }
            System.out.println(Thread.currentThread().getName() + " end");
        }
    }

    public void testLock(ReentrantLockMain main){
        for(int i=0;i<10;i++){
            Thread t = new Thread(main.new Task(),"task-" + i);
            t.start();
            /**
             * 如果线程任务中，上锁的方式是lockInterruptibly() 或者有其他会抛出中断异常的方法
             * 那么中断这个线程时，线程就会抛出中断异常。如果上锁的方式是lock()，调用这个线程的interrupt（）方法
             * 只会修改其中断状态，并不会让它停止运行
             */
            t.interrupt();
        }
    }

    //
    public void testReentrantLockMethod(){
        /**
         * 获得锁，不会响应中断
         * 如果获取不到线程就会被阻塞
         *
         */
        lock.lock();

        /**
         * 获得锁，优先响应中断
         * 如果获取不到线程就会被阻塞，在阻塞的过程中如果线程被中断了，就会抛出中断异常
         * 如果获取锁了，在执行任务的过程中，被中断了，会抛出中断异常么？答案是不会！！！
         */
        try {
            lock.lockInterruptibly();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        boolean get = false;
        while (!get){
            /**
             * 不断的尝试获得锁，获取到了返回true，获取不到就立马返回false
             */
            get = lock.tryLock();
        }

        /**
         * 在指定的时间内尝试获得锁（这里是2秒），会响应中断，。
         * 在2秒内的任意一个时刻获取到锁了就会立马返回true。
         * 2秒之后如果还没有获取到锁，就会返回false.
         */
        try {
            boolean t1= lock.tryLock(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock.unlock();
    }

    public void testConditionMethod(){
        //和内部锁的wait方法效果一致，阻塞当前线程的执行，然后释放锁资源.会相应中断
        try {
            readCondition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        /**
         * 立刻释放锁资源，但是2秒钟之后会自动唤醒，唤醒后会尝试获取锁资源，获取到了就会接着执行。
         * 和await方法的区别就在于会自动唤醒
         */
        try {
            readCondition.await(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //不会释放锁资源
        readCondition.awaitUninterruptibly();

        /**
         * 立刻释放锁资源，到指定的时间之后会自动唤醒，唤醒后会尝试获取锁资源，获取到了就会接着执行。
         * 和await方法的区别就在于会自动唤醒
         */
        try {
            readCondition.awaitUntil(new Date(System.currentTimeMillis() + 3000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //相当于notify()
        readCondition.signal();

        //相当于notifyAll()
        readCondition.signalAll();
    }


    public class LockInterruptedTest implements Runnable{
        @Override
        public void run() {
            try {
                //lockInterruptibly获得锁时优先响应中断，只在获得锁阻塞的过程中被中断才会响应到。获得到锁之后就不会被中断了
                lock.lockInterruptibly();
                Thread.sleep(3000);
                System.out.println(Thread.currentThread().getName() + "：开始阻塞");
                /**
                 * 调用了这个方法之后就会释放资源，但是2秒钟之后会自动唤醒这个线程，这个线程唤醒之后会先获得锁，
                 * 获取到了就会执行
                 */
                readCondition.await(2,TimeUnit.SECONDS);
                System.out.println(Thread.currentThread().getName() + "：2秒之后开始执行");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LockSupport.parkNanos(3000000000L);
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("main start");
        ReentrantLockMain main = new ReentrantLockMain();
        for(int i=0;i<10;i++){
            Thread t = new Thread(main.new LockInterruptedTest(),"thread-" + i);
            t.start();
        }
        Thread.sleep(1000);
       // t.interrupt();
       /* main.testLock(main);
        Thread.sleep(3000);*/
        System.out.println("main end,the resource = " + main.resource);
    }
}
