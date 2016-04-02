package basicJava;
 
public class Hello { 
	public static void main(String []arg){	
		MyThread myThread = new MyThread();
		myThread.start();
		
		Mytask myTask = new Mytask();
		Thread thread = new Thread(myTask);
		thread.start();
	}
}

class MyThread extends Thread{
	 @Override
	public  void run(){
		System.out.println("do some thing");
		this.suspend();
		this.stop();
		this.resume();
	}
}


class Mytask implements Runnable{
	@Override
	public void run() {
		System.out.println("do some other thing");
	}
	
}
 
 
 


