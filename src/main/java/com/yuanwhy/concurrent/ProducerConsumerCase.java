package com.yuanwhy.concurrent;

/**
 * Created by hongyuan.wang on 17/09/2017.
 */
public class ProducerConsumerCase {


    private int product = 0;

    private final int MAX_NUM = 100;

    public synchronized void produce() {

        if (product >= MAX_NUM) {

            try {
                this.wait();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return;
        }
        product++;
        System.out.println(Thread.currentThread() + "生产一个，剩余：" + product);

    }


    public synchronized void consume() {
        if (product <= 0) {

            this.notify();

            return;

        }
        product--;
        System.out.println(Thread.currentThread() + "消费一个，剩余：" + product);
    }


    public static void main(String[] args) {

        final ProducerConsumerCase producerConsumerCase = new ProducerConsumerCase();


        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    producerConsumerCase.produce();
                }
            }
        }).start();


        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    producerConsumerCase.consume();
                }
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    producerConsumerCase.consume();
                }
            }
        }).start();


        while (true) {
        }

    }

}



