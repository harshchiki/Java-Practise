package custom.blockingqueue;

public class CustomWriter implements Runnable {
	private final CustomBlockingQueue<String> bq;
	private final String id;
	
	CustomWriter(final CustomBlockingQueue bq, final String id){
		this.bq = bq;
		this.id = id;
	}

	@Override
	public void run() {
		for(int i = 0;i<4;i++){
			try {
				Thread.sleep((long) Math.random());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			bq.offer(id+" "+ String.valueOf(i));
		}
	}

}
