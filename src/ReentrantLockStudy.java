import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockStudy {
	public static void main(String[] args) {
		Object o = new Object();
		Lock lock = new ReentrantLock(true);
		Thread t1 = new Thread(new Test("first", lock));
		Thread t2 = new Thread(new Test("second", lock));
		long currentTimeMillis = System.currentTimeMillis();
		
		
		
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Total millis = "+(System.currentTimeMillis()-currentTimeMillis));
		
	}
}


class Test implements Runnable{
	String str;
//	ReentrantLock lock = new ReentrantLock();
	Lock lock;
	
	Test(String a, Lock lock){
		this.str = a;
		this.lock = lock;
	}
	
	public void run(){
		System.out.println(Thread.currentThread().getName());
		lock.lock();
		try{
			System.out.println("in = "+Thread.currentThread().getName());
			Thread.sleep(2000);
			System.out.println("out = "+Thread.currentThread().getName());
			System.out.println(str);
		}catch(Exception e){
			System.out.println("Exception");
		}
		lock.unlock();
	}
}