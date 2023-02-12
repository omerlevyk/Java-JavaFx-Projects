
/**
 * Maman 15 Question 2
 * Author name - Omer Levy
 * Author ID - 209009117
 * Instructor - Roni Ben Ishay
 * Corse Nuber - 20554
 */


//third class for the second Processor
public class Process2 extends Thread {
	private int NUMBER_OF_RUNS = 10;
    private Data data;
    
    // Process2 - constructor
    public Process2(Data data) {
        this.data = data;
    }
    
    // run - print the difference for each run
    public void run() {
        for (int i = 0; i <= NUMBER_OF_RUNS; i++) {
        	if (i != 0) {
        		System.out.println(i + " run: " + data.getDiff());
        	}
            try {
                Thread.sleep(100);
            } catch (InterruptedException catchError) {
            	System.out.print("2: ");
            	catchError.printStackTrace();
            }
        }
    }
}