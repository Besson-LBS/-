package com.lbs.exer;

/**
 * @author Besson Email:1466981275@qq.com
 * @Description
 * @date 2020年5月3日上午10:04:00
 */
class Clerk {//店员
    private int produ = 0;

    //生产产品
    public synchronized void produceProduct() {
        if (produ < 20) {
            produ++;
            System.out.println(Thread.currentThread().getName() + "开始生产第：" + produ + "个产品");

            notify();
        } else {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    //消费产品
    public synchronized void consumeProduct() {

        if (produ > 0) {
            System.out.println(Thread.currentThread().getName() + "消费者开始消费第：" + produ + "个产品");
            produ--;

            notify();
        } else {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Producer extends Thread {//生产者
    private Clerk clerk;

    public Producer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        System.out.println(getName() + ":开始生产产品......");
        for (; ; ) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.produceProduct();
        }
    }
}

class Consumer extends Thread {//消费者
    private Clerk clerk;

    public Consumer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {

        System.out.println(getName() + ":消费者开始消费产品....");

        for (; ; ) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.consumeProduct();
        }
    }
}

public class ProductTest {
    public static void main(String[] args) {
        Clerk clerk = new Clerk();

        Producer p1 = new Producer(clerk);
        p1.setName("生产者甲");

        Consumer c1 = new Consumer(clerk);
        c1.setName("消费者乙");
        Consumer c2 = new Consumer(clerk);
        c2.setName("消费者丙");

        p1.start();
        c1.start();
        c2.start();
    }

}
