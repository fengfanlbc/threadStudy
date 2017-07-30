import java.security.Provider;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by F-victor on 2017/7/30.
 */
public class InterruptTest {
    //测试中断
    public static void main(String[] args){
        MyService s = new MyService();
        Thread t1 = new MyThread(s);
        Thread t2 = new MyThread(s);
        t1.start();
        try {
            //延时
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t2.start();
        //中断
        t2.interrupt();
    }
}

class MyService{
    ReentrantLock lock = new ReentrantLock();
    public void service(){
        try {
            System.out.println(Thread.currentThread().getName() + " is trying to get lock");
            lock.lockInterruptibly();
            //lock.lock();
            System.out.println(Thread.currentThread().getName() + " is enjoying its service");
            while(true);
        } catch (Exception e) {
            System.out.println(Thread.currentThread().getName() + " receive interrupt");
        }
        System.out.println("hello");


    }
}
class MyThread extends Thread{
    MyService s;
    public MyThread(MyService s){
        this.s = s;
    }
    @Override
    public void run() {
        s.service();
    }
}
