package com.data.datawaytest.threads;

import java.util.concurrent.*;

/** @author Baijl
 * @date 2020-04-24
 * @time 09:24
 * @description
 */
public class Pool {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {

            executorService.execute(new Runnable() {
                int i=0;
                @Override
                public void run() {

                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+" "+i++);
                }
            });
        }
    }
}
