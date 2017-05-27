package concurrency;

import java.util.Collections;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class NonBlockingQueue<T> implements Iterable<T>{
	private final BlockingQueue<T> queue = new LinkedBlockingQueue<T>();
	private Set<QueueListener> registeredListeners = Collections.EMPTY_SET;
	private final long configuredMillisToWait = 10L;
	private AtomicInteger counter;
	
	@Override
	public Iterator<T> iterator() {
		return this.queue.iterator();
	}
	
	
	
	public synchronized T take(){
		/* for a configured while, wait on take,
		 * if still does not get - register listener and move out 
		*/
		return null;
	}
	
	public synchronized void offer(T element){
		/*
		 * notify registered listeners, and remove them all.
		 */
	}
}

interface QueueListener{
	void listen();
}

class QueueListenerImpl implements QueueListener{
	private final NonBlockingQueue<String> queue = null;
	
	public void listen(){
		String element = queue.take();
		/*
		 * if element is null - packet not received.
		 */
		if(!Objects.isNull(element)){
			processPacket(element);
		}
	}
	
	public void processPacket(String element){
		
	}
}
