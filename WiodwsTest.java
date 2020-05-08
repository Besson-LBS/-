package com.lbs.java;

/**
 * @author Besson Email:1466981275@qq.com
 * @Description
 * @date 2020年5月3日上午10:04:00
 */

class  Wiodws implements  Runnable{

    private int tikd=100;

    @Override
    public void run() {
         while (true){
             if(tikd>0){
                 try {
                     Thread.sleep(150);
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
                 System.out.println(Thread.currentThread().getName()+"售票："+tikd);
             tikd--;
             }else {
                 break;
             }
         }
    }
}
public class WiodwsTest {
    public static void main(String[] args) {
        Wiodws w = new Wiodws();

        Thread t = new Thread(w);
        Thread t1 = new Thread(w);
        Thread t2 = new Thread(w);

        t.setName("窗口1");
        t1.setName("窗口2");
        t2.setName("窗口3");

        t.start();
        t1.start();
        t2.start();
    }
}
