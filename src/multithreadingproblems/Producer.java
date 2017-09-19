package multithreadingproblems;

import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Producer implements Runnable {

	private final Queue<String> q;
	private final int MAXSIZE;
	private final Lock lock;
	private final Condition bufferFull, bufferEmpty;
	private final String name;


	public Producer(Queue<String> q, Lock lock, Condition bufferFull,
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
		for(int i = 0;i<100;i++){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			put(i);
		}
	}

	void put(int i){
		lock.lock();
		while(q.size() == MAXSIZE){
			try {
				bufferFull.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		q.add(""+i);
		System.out.println("Producer - "+name+" Value produced = "+i);
		bufferEmpty.signalAll();
		lock.unlock();
	}

}
