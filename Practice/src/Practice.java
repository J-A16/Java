import java.util.*;

public class Practice{
	public static void main(String[] args){
		Thread a = new Thread(new printA());
		Thread b = new Thread(new printB(a));

		a.start();
		b.start();

	}
}

class printA implements Runnable{
	public void run(){
	try{
		for(int i=0;i<1000;i++){
			/*NOTE: YOU CAN CHOOSE TO IGNORE IT, AND NOT DO ANYTHING IN THE CATCH BLOCK EITHER*/
			if(Thread.interrupted()){
				System.out.print("interrupted");
			}
			System.out.print("A");
		}
	}catch(Throwable t){
		System.out.println(t);
	}
	}
}

class printB implements Runnable{
	Thread other;

	printB(Thread x){
		other = x;
	}
		
	public void run(){
	try{
		for(int i=0;i<1000;i++){
		other.interrupt();
			System.out.print("B");
		}
	}catch(Throwable t){}
	}
}
