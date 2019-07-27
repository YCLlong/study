package cn.ycl.thread;


/**
 *线程中断。
 * 当一个线程执行完毕，就会处于完结状态，那么线程就正常退出了
 * 但是在线程执行的过程中，我们可能想让它停止执行,jdk提供了stop(),但是这个方法太过于暴力，被标记为删除状态，不建议使用
 * 因为它可能会导致线程没完成任务就直接结束，导致数据出现问题。
 *
 * Thread类中，给我们提供了方法，可以设置，获得，清除线程的中断状态，我们可以拿这些状态来做逻辑上的控制，对于我们自己编写的程序，并不是调用了中断的方法就能中断我们的线程。
 * 但是，JDK提供的API很多都响应中断，会抛出 InterruptedException
 * 比如Thread.sleep() 方法，假设我让线程睡了5秒，在第2秒的时候，调用这个线程的中断方法，那么暂停5秒这个动作就被中断了，就会排出中断异常。
 * Thread提供的中断方法：
 * void interrupt() 设置中断标记，中断线程
 * boolean isInterrupted() 判断是否中断，true标是是,false标识否
 * boolean interrupted() 判断是否中断，true标是是,false标识否,但是会重置线程的中断状态，将中断状态改成false.
 */
public class InterruptedMain {

    private class Task implements Runnable{
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "开始运行，睡眠5s");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "正常结束");
        }
    }


    public static void main(String[] args) {
        System.out.println("main开始");
        Thread thread = new Thread(new InterruptedMain().new Task());
        System.out.println(thread.getState() + " 1 " + thread.isInterrupted());
        thread.start();
        //thread.interrupt();
        thread.stop();
        System.out.println(thread.getState() + " 2 " + thread.interrupted());
        System.out.println(thread.getState() + " 3 " + thread.isInterrupted());
        System.out.println("main结束");
    }
}
