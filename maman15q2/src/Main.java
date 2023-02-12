/**
 * Maman 15 Question 2
 * Author name - Omer Levy
 * Author ID - 209009117
 * Instructor - Roni Ben Ishay
 * Corse Nuber - 20554
 */


//The main
public class Main {
	public static void main(String[] args) {
	     try {
	     	// set the data object and the thread
	     	Data data = new Data(0, 0);
	         Thread t1 = new Thread(new Process1(data));
	         Thread t2 = new Thread(new Process2(data));
	         
	         // start t1, t2 runs
	         t1.start();
	         t2.start();
	         t1.join();
	         t2.join();
	         
	     } catch (InterruptedException catchError) {
	     	System.out.print("3: ");
	     	catchError.printStackTrace();
	     }
	 	
	}
}