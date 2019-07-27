package cn.ycl.thread;


public class SynchronizedMain {
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    private class Task implements Runnable{

        @Override
        public void run() {
            synchronized (lock1){
                try {
                    /**
                     * 这样调用就会出错java.lang.IllegalMonitorStateException，
                     * 因为 synchronized的锁对象是lock1 只能调用 lock1的notify和wait方法
                     */
                  //  lock2.notify();


                    /**
                     * 一旦调用了wait方法就会释放锁资源，线程就会阻塞到lock1的等待线程组中
                     * 等待调用lock1.notify() 方法就可能会被随机选中，然后继续执行
                     */
                    lock1.wait();
                    System.out.println("继续执行");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        SynchronizedMain main = new SynchronizedMain();
        Thread t = new Thread(main.new Task());
        t.start();
    }
}
