package multithreadingproblems;

import java.util.concurrent.Semaphore;

// Semaphore generally is used to constrain on how many threads can access a resource at a time. How many are permitted at a time.
public class SemaphoreExample {

	public static void main(String[] args) {
		Semaphore semaphore = new Semaphore(3, true);

		ATMThread t1 = new ATMThread(semaphore, "t1");
		t1.start();

		ATMThread t2 = new ATMThread(semaphore, "t2");
		t2.start();

		ATMThread t3 = new ATMThread(semaphore, "t3");
		t3.start();

		ATMThread t4 = new ATMThread(semaphore, "t4");
		t4.start();

		ATMThread t5 = new ATMThread(semaphore, "t5");
		t5.start();
	}

	static class ATMThread extends Thread {
		Semaphore semaphore;
		String name;

		ATMThread(Semaphore semaphore, String name){
			this.semaphore = semaphore;
			this.name = name;			
		}

		@Override
		public void run(){
			try {
				semaphore.acquire();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("After acquire of "+name);
			for(int i = 0;i<5;i++){
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("ATM: "+this.name + " available permits = "+semaphore.availablePermits());
			}

			semaphore.release();
			System.out.println("After release by "+name);
			for(int i = 0;i<5;i++){
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("ATM: "+this.name + " available permits = "+semaphore.availablePermits());
			}
		}
	}
}
