package cn.ycl.thread;

/**
 * 总结：
 * volatile关键字只保证原子性，可见性和有序性。
 * 原子性：
 * 比如32位的操作系统中写多线程给Long型变量赋值(jvm能保证某些操作的原子性，比如i = 1,这个赋值操作是具有原子性的，不会被打断)会造成数据被写坏
 * 因为long型变量占64位，如果加上volatile就不会被写坏了。
 * 但是并不能保证复合操作的原子性，比如i++,相当于 i = i+1.并不能完全的替代锁
 *
 * 可见性：
 * volatile关键字声明的变量一旦被修改，那么程序中所有的线程都会“看见”
 * volatile声明的关键字读写的对象都是主存，不会经过缓存，这样保证了可见性
 *
 * 多线程可以充分的利用系统资源，但是如果涉及到多线程操作临界资源，那么必须要小心处理，不然数据被写坏，多线程将失去意义！
 * 当操作临界资源时，就会涉及到锁，比如内部所synchronize关键字，或者jdk并发包中的别的锁，那么获取锁资源本身就需要消耗资源，反而没有单线程的效率高。
 * 如果我们要处理一个任务，可以将这个任务划分成很多小任务，这些小任务之间并不会涉及到什么临界资源，可以考虑使用多线程，如果每个小任务的工作量依然十分巨大
 * 甚至可以考虑使用分布式来解决（分治思想）相对与任务分发和数据传输消耗的时间，多线程或者分布式处理也更加高效。
 * 但是如果多个任务之间需要频繁使用临界资源，那就要慎重考虑，要尽量将获取锁的频率和占有锁的时间最小化。
 *
 */

public class VolatileMain {
    /**
     * 共享资源
     */
    private  int resource;
    //private  volatile int resource;

    /**
     * 每个线程的耗时时间
     */
    private long[] time = new long[10];

    /**
     * 共享资源的操作线程的任务
     * 给i赋值，+1操作,重复循环100万次
     */
    private class WriteTask implements Runnable{
        int index;
        public WriteTask(int index){
            this.index = index;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "   start");
            Long start = System.currentTimeMillis();
            synchronized (VolatileMain.class){
            for(int j=0;j<1000000;j++){
                    resource++;
            }
            }
            Long end = System.currentTimeMillis();
            time[index] = end - start;
            System.out.println(Thread.currentThread().getName() + "   end。耗时：" + (end - start) + "毫秒" );
        }
    }


    public static void main(String[] args) throws InterruptedException {
        VolatileMain main = new VolatileMain();
        /**
         * 创建10个线程，每个线程写100万次，如果volatile能保证i++这样的操作的原子性，那么i = 1000万
         */
        for(int n=0;n<10;n++){
            Thread t = new Thread(main.new WriteTask(n),"写线程" + n);
            //t.join
            t.start();
        }
        Thread.sleep(3000);
        long timeSum = 0L;
        for(int i =0;i<10;i++){
            timeSum+=main.time[i];
        }
        maopao(main.time);

        /**
         * 执行结果
         * volatile 关键字并不能保证 i++ 这样复合操作的原子性
         * sychronized可以保证 i++ 这样操作的原子性，但是消耗时间最多，volatile 次之，不加sychronized 和 volatile效率最高
         * 耗时相差200倍之多
         *
         * 在主线程中循环创建并开启线程，如果在循环中，开启线程之后再调用join()，也能保证结果正确。
         * 因为开启线程的操作是主线程完成，开启一个线程就join，主线程就会等待这个线程执行完毕，才会开启下一个线程
         * 所以操作临界资源每次只有一个线程再操作，并不会把数据写坏。
         */
        System.out.println(Thread.currentThread().getName() + " end, the resource = " + main.resource + "，耗时：" + main.time[0] + "毫秒");
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
}
