
/**
 * 
 */

package maman11q1;
import java.util.Random;
import javax.swing.JOptionPane;

public class Bullseye {
    private final static int DEFAULT_SIZE = 4;
    private final static int ANSI_1 = 48;
    private final static int ANSI_9 = ANSI_1 + 9;
 
    public static int getHit(String str1, String str2) {
    	int returnVal = 0;
    	for (int i = 0; i < DEFAULT_SIZE; i++) {
    		for (int j = 0; j < DEFAULT_SIZE; j++) {
    			if (str1.charAt(i) == str2.charAt(j)) {
    				if (i != j) {	
    					returnVal++;
    				}
    			}
    		}
    	}
    	return returnVal;
    }
    
    public static int getBull(String str1, String str2) {
    	int returnVal = 0;
    	for (int i = 0; i < DEFAULT_SIZE; i++) {
    		if (str1.charAt(i) == str2.charAt(i)) {
    			returnVal++;
    		}
    	}
    	return returnVal;
    }
    
    public static boolean checkNum(String str) {
    	if (str.length() == DEFAULT_SIZE) {
    		for (int i = 0; i < DEFAULT_SIZE; i++) {
        		if (str.charAt(i) < ANSI_1 || str.charAt(i) > ANSI_9) {
        			return false;
        		}
        	}
    		return true;
    	}
    	return false;
    }
    
    public static String getRendomNum() {
    	Random randon = new Random();
    	int [] arr = {-1, -1, -1, -1};
    	int temp;
    	for (int i = 0; i < arr.length; i++) {
    		temp = randon.nextInt(9);
    		while (isInArr(temp, arr)) {
    			temp = randon.nextInt(9);
    		}
        	arr[i] = temp;
    	}
    	
    	return arrayToString(arr);
    }
    
    public static boolean isInArr(int temp, int[] arr) {
    	for (int i = 0; i < DEFAULT_SIZE; i++) {
    		if (temp == arr[i]) {
    			return true;
    		}
    	}
    	return false;
    }
    
    public static String arrayToString(int[] arr) {
    	String str = "";
    	for (int i = 0; i < arr.length; i++) {
    		str += arr[i];
    	}
    	return str;
    }
    
    public static void main(String args[]) {
    	boolean win = false, stopGame = false;
    	int tryCounter = 0;
    	String inputTemp = "";
    	
    	while (!stopGame) {
    		win = false;
	    	while (!win) {
	    		
	    		String targetNum = getRendomNum();
	    		/** print the next line for quick checking: */
    			/**JOptionPane.showMessageDialog(null, "the target number is: " + targetNum);*/
    			
	    		while (true) {
	    			
	    			tryCounter++;
	    			
	    			/** get the input Number correctly: */
		        	String inputNum = JOptionPane.showInputDialog("Please enter a four digit integer:");
		        	while (checkNum(inputNum) == false) {
		        		inputNum = JOptionPane.showInputDialog("The number is invalid. Please enter a four digit integer:");
		        	}
		        	
		        	/** did I win? */
		        	if (getBull(targetNum, inputNum) == DEFAULT_SIZE) {
		    			inputTemp = JOptionPane.showInputDialog("Well done! you have won at four Bullseye after " + tryCounter + " tries, the number was: " + targetNum + ".\n Would you like to play another round? \nEnter 'y' to contine or 'n' to stop");
		    			while (!inputTemp.equals("y") && !inputTemp.equals("n")) {
			        		inputTemp = JOptionPane.showInputDialog("invalid input.\nput 'y' to contine or 'n' to stop");
		    			}
		    			if (inputTemp.charAt(0) == 'y') {
		    				win = true;
		    				break;
		    			} else {
		    				win = true;
		    				stopGame = true;
		    				break;
		    			}
		        	} else {
		        		JOptionPane.showMessageDialog(null, "Your guess is: " + inputNum + "\nYou have "+ getHit(targetNum, inputNum) +" hits, and "+ getBull(targetNum, inputNum) +" Bullseye.");
		        	}
		    	}
	    	}
    	}
    }
}
