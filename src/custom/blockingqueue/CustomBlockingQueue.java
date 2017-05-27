package custom.blockingqueue;

import java.util.LinkedList;
import java.util.Queue;

public class CustomBlockingQueue<T> {
	private final Queue<T> q = new LinkedList<T>();
	private final int limit;

	public CustomBlockingQueue(final int limit) {
		this.limit = limit;
	}

	synchronized void offer(T data){
		while(q.size()==limit){
			try {
				/**
				 * IF I would have removed this wait,
				 * and had a reader come in first, 
				 * the application would have gone into infinite wait.
				 * (Pay attention to the synchronized keyword)
				 * This takes a lock on this object and hence the queue.
				 * 
				 * If not possible to go forward, it should wait, and relinquish the control.
				 * 
				 * On notifcation, it wakes up, with the state it was in,
				 * preloaded into stack and memory (Thread loaded)
				 */
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		q.add(data);
		notifyAll();
	}

	synchronized T take(){
		while(q.size()==0){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
		}
		T polledItem = q.poll();
		notifyAll();
		return polledItem;
	}

	synchronized boolean isEmpty(){
		return q.size() > 0;
	}
}
