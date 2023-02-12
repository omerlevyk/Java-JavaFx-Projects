import java.util.Random;

/**
 * Maman 15 Question 2
 * Author name - Omer Levy
 * Author ID - 209009117
 * Instructor - Roni Ben Ishay
 * Corse Nuber - 20554
 */


//second class for the first Processor
public class Process1 extends Thread {
	private int NUMBER_OF_RUNS = 10;
	private int MAX_NUM = 1000;
	private Data data;
 
 // Process1 - constructor
 public Process1(Data data) {
     this.data = data;
 }
 
 // run - set new x,y for each run
 public void run() {
     for (int i = 0; i < NUMBER_OF_RUNS; i++) {
         data.update(new Random().nextInt(MAX_NUM), new Random().nextInt(MAX_NUM));
         //System.out.println("x: " +data.getXorY(true)+ " , y: " + data.getXorY(false));
         try {
             Thread.sleep(100);
         } catch (InterruptedException catchError) {
         	System.out.print("1: ");
         	catchError.printStackTrace();
         }
     }
 }
}
