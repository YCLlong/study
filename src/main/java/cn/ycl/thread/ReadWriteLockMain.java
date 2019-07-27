package cn.ycl.thread;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写分离锁
 * 当操作临界资源时（读写数据）不管是内部锁sychronized还是重入锁ReentrantLock,都会阻塞
 * 读-读，读写，写写 都阻塞
 *
 * 但是我们可以发现，读-读完全没有必要阻塞，因为并不会改变临界资源的数据。
 * 所以，读写锁的出现就是为了优化这个问题，读写所锁的读-读操作不会受到阻塞
 */
public class ReadWriteLockMain {
    /**
     * 每个线程的耗时时间
     */
    private long[] time = new long[20];
    private int resource;
    final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    final ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
    final ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();

    final Lock lock = new  ReentrantLock();

    /**
     * 读任务
     */
    class ReadTask implements Runnable{
        int index;
        Lock lock;
        public ReadTask(Lock lock,int index){
            this.lock = lock;
            this.index = index;
        }

        @Override
        public void run() {
            Long start = System.currentTimeMillis();
            lock.lock();
            System.out.println(Thread.currentThread().getName() + ":读取到内容:r=" + resource);
            lock.unlock();
            Long end = System.currentTimeMillis();
            time[index] = end - start;
            System.out.println(Thread.currentThread().getName() + ":总耗时" + time[index]);
        }
    }

    class WriteTask implements Runnable{
        int index;
        Lock lock;
        int radom;
        public WriteTask(Lock lock,int radom,int index){
            this.lock = lock;
            this.radom = radom;
            this.index = index;
        }

        @Override
        public void run() {
            Long start = System.currentTimeMillis();
            lock.lock();
            resource = radom;
            System.out.println(Thread.currentThread().getName() + ":写内容:r=" + resource);
            lock.unlock();
            Long end = System.currentTimeMillis();
            time[index] = end - start;
            System.out.println(Thread.currentThread().getName() + ":总耗时" + time[index]);
        }
    }

    public static void testReadWriteLock(int threadNum) throws InterruptedException {
        Random random = new Random();
        ReadWriteLockMain main = new ReadWriteLockMain();
        System.out.println(threadNum + "个读线程测和写线程，测试读写分离锁的耗时。");
        for(int i = 0;i<threadNum;i++){
            new Thread(main.new ReadTask(main.readLock,i),"读写分离锁，读线程-" + i).start();
            new Thread(main.new WriteTask(main.writeLock,random.nextInt(),i+10),"读写分离锁，写线程-" + i).start();
        }
        Thread.sleep(3000);
        maopao(main.time);
        System.out.println("读写锁总耗时：" + main.time[0]);
    }



    public static void testLock(int threadNum) throws InterruptedException {
        Random random = new Random();
        ReadWriteLockMain main = new ReadWriteLockMain();
        System.out.println(threadNum + "个读线程测和写线程，测试普通锁的耗时。");
        for(int i = 0;i<threadNum;i++){
            new Thread(main.new ReadTask(main.lock,i),"普通锁，读线程-" + i).start();
            new Thread(main.new WriteTask(main.lock,random.nextInt(),i+10),"普通锁，写线程-" + i).start();
        }
        Thread.sleep(3000);
        maopao(main.time);
        System.out.println("普通锁总耗时：" + main.time[0]);
    }
    /**
     * 冒泡排序
     * @param data
     */
    public static void maopao(long[] data){
        for(int i=1;i<data.length;i++){
            for(int j=0;j<i;j++){
                if(data[i] > data[j]){
                    long temp = data[i];
                    data[i] = data[j];
                    data[j] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        try {
            testLock(10);
           // testReadWriteLock(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
