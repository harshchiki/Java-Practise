package multithreadingproblems;

import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Consumer implements Runnable{
	private final Queue<String> q;
	private final int MAXSIZE;
	private final Lock lock;
	private final Condition bufferFull, bufferEmpty;
	private final String name;
	boolean is99Received = false;
	
	public Consumer(Queue<String> q, Lock lock, Condition bufferFull,
			Condition bufferEmpty, int maxSize, String name) {
		this.q = q;
		this.lock = lock;
		this.bufferFull = bufferFull;
		this.bufferEmpty = bufferEmpty;
		this.MAXSIZE = maxSize;
		this.name = name;
	}


	@Override
	public void run() {
		while(!is99Received){
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Consumer - "+this.name+" Value consumed = "+ get());
		}
	}
	
	String get(){
		lock.lock();
		while(q.isEmpty()){
			try {
				this.bufferEmpty.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		String element = q.remove();
		if(element.equals("99")){
			is99Received = true;
		}
		this.bufferFull.signalAll();
		lock.unlock();
		return element;
	}
}
