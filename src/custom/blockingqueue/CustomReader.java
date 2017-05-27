package custom.blockingqueue;

public class CustomReader implements Runnable {
	private final CustomBlockingQueue<String> bq;
	private final String id;

	public CustomReader(final CustomBlockingQueue<String> bq, final String id) {
		this.bq = bq;
		this.id = id;
	}

	@Override
	public void run() {
		while(true){
			try {
				Thread.sleep((long) Math.random());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(this.id+" "+bq.take());


		}
	}


}
