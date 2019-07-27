package cn.ycl.thread;

import java.util.concurrent.*;

/**
 * 线程池的使用
 */
public class ThreadPoolMain {

    public static void main(String[] args) {
        ExecutorService service = Executors.newScheduledThreadPool(10);

    }

    /**
     * 测试主要的线程池的构造方法
     * ThreadPoolExecutor
     * 通过ThreadPoolExecutor的构造函数可以创建我们想要的线程池.,它的构造方法按照顺序的7个构造方法
     * 1，线程池核心数量，线程池会保证有指定数量的活跃线程
     * 2，线程池允许的最大线程数量
     * 3，除了核心数量之外的空闲线程存活时间，长整型，超过这个时间，线程就会被杀死
     * 4，存活时间的单位，TimeUnit
     * 5,工作队列，实现了BlockingQueue<Runnable>接口,线程任务在未执行之前是保存在这个任务队列中
     * 6，线程工厂对象，创建线程的工厂对象，ThreadFactory 接口，里面只有 Thread newThread(Runnable r)方法。一般使用默认的就行，Executors.defaultThreadFactory()实
     * 7，拒绝策略，RejectedExecutionHandler 接口对象，只有方法  void rejectedExecution(Runnable r, ThreadPoolExecutor executor)
     *
     */
    public void testMainThreadPool(){
        int activeNum = 10;
        int maxNum = 100;
        Long keepTime = 60L;
        TimeUnit unit = TimeUnit.SECONDS;
        ThreadPoolExecutor executor = new ThreadPoolExecutor(activeNum, maxNum, keepTime, unit, new LinkedBlockingQueue<Runnable>(10), Executors.defaultThreadFactory(), new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {

            }
        });
    }


    public void threadPoolFactory(){
        ExecutorService pool1 = Executors.newCachedThreadPool();
        ExecutorService pool2 = Executors.newFixedThreadPool(10);
        ExecutorService pool3 = Executors.newSingleThreadExecutor();
        ScheduledExecutorService pool4 = Executors.newScheduledThreadPool(10);
        ScheduledExecutorService pool5 = Executors.newSingleThreadScheduledExecutor();
    }
}
