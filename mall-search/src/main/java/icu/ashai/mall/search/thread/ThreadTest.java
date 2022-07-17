package icu.ashai.mall.search.thread;

/**
 * @author ashai
 * @date 2022/7/16 18:05
 * @email ashai@gmail.com
 * @description:
 */
public class ThreadTest {
    public static void main(String[] args) {

        Thread01 thread01 = new Thread01();
        thread01.start();
    }

    public static class Thread01 extends Thread{
        @Override
        public void run() {

            System.out.println("当前线程："+Thread.currentThread().getId());
        }
    }
}
