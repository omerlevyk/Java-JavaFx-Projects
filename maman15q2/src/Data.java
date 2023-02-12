/**
 * Maman 15 Question 2
 * Author name - Omer Levy
 * Author ID - 209009117
 * Instructor - Roni Ben Ishay
 * Corse Nuber - 20554
 */

// first class for Data object
public class Data {
    private int x;
    private int y;
    
    // Data - constructor
    public Data(int x, int y) {
        this.x = x;
        this.y = y;
    }
    // getDiff - return the difference between x to y
    public synchronized int getDiff() {
        return Math.abs(x - y);
    }
    
    // update - set new x,y for data object
    public synchronized void update(int x, int y) {
        this.x += x;
        this.y += y;
    }
    // getXorY - get x or y just for checking
    public synchronized int getXorY(boolean bool) {
        if (bool) {
        	return this.x;
        }
        return this.y;
    	
    }
}