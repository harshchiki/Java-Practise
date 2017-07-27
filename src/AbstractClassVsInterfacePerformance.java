
/*
 * Apparently, interfaces seem to work faster than abstract classes
 * (Java 1.8 0_131)
 */
public class AbstractClassVsInterfacePerformance {
	public static void main(String[] args) {
		A abstracto = new AC();
		B interfaceo = new BC();

		long startTime, endTime;

		for(int j = 0;j<5;j++) {
			startTime = System.nanoTime();
			for(int i = 0;i<Integer.MAX_VALUE;i++) {
				abstracto.doSomething();
			}
			endTime = System.nanoTime();
			
			
			System.out.println("Time for abstact class object "+((endTime-startTime)/1000000L));
		}

		System.out.println();


		for(int j = 0;j<5;j++) {
			startTime = System.nanoTime();
			for(int i = 0;i<Integer.MAX_VALUE;i++) {
				interfaceo.doSomething();
			}
			endTime = System.nanoTime();
			System.out.println("Time for interface object "+((endTime-startTime)/1000000L));
		}
	}
}

abstract class A{
	public abstract void doSomething();
}

interface B{
	void doSomething();
}

class AC extends A{

	@Override
	public void doSomething() {
		// TODO Auto-generated method stub
		
	}

}


class BC implements B{
	@Override
	public void doSomething() {

	}
}