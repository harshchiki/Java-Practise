package custom.blockingqueue;

public class CustomBlockingQueueMain {
	public static void main(String[] args) {
		CustomBlockingQueue<String> bq = new CustomBlockingQueue<String>(3);
		CustomWriter W1 = new CustomWriter(bq, "W1");
		CustomWriter W2 = new CustomWriter(bq, "W2");
		CustomWriter W3 = new CustomWriter(bq, "W3");
		CustomWriter W4 = new CustomWriter(bq, "W4");
		CustomWriter W5 = new CustomWriter(bq, "W5");
		CustomWriter W6 = new CustomWriter(bq, "W6");
		CustomWriter W7 = new CustomWriter(bq, "W7");
		
		CustomReader R1 = new CustomReader(bq, "R1");
		CustomReader R2 = new CustomReader(bq, "R2");
		
		
		Thread tw1 = new Thread(W1);
		tw1.start();
		Thread tw2 = new Thread(W2);
		tw2.start();
		Thread tw3 = new Thread(W3);
		tw3.start();
		Thread tw4 = new Thread(W4);
		tw4.start();
		Thread tw5 = new Thread(W5);
		tw5.start();
		Thread tw6 = new Thread(W6);
		tw6.start();
		Thread tw7 = new Thread(W7);
		tw7.start();
		
		Thread tr1 = new Thread(R1);
		tr1.start();
		Thread tr2 = new Thread(R2);
		tr2.start();
		
		
	}
}
