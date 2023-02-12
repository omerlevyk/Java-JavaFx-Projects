/**
 * Maman 14 Question 2
 * Author name - Omer Levy
 * Author ID - 209009117
 * Instructor - Roni Ben Ishay
 * Course Number - 20554
 */

import java.util.ArrayList;
import java.util.Iterator;

public class myQueue <T> {
	
	private ArrayList<T>[] arrayPriority;
	private int maxPriority;
	
	private final static int MAX_N = 9;
	private final static int MIN_N = 0;
	
	// Constructor
	public myQueue(int n) {
		n--;
		maxPriority = n>MAX_N ? MAX_N:n;
		
		arrayPriority[0].add(null);
		for (int i = 0; i < maxPriority; i++) {
			arrayPriority[i] = new ArrayList<T>();
        }
	}
	// add - get an element and his priority and adds it to the arrayPriority
	public void add(T element, int priority) {
		if (MAX_N < priority || MIN_N > priority) {
			priority = maxPriority;
		}
		arrayPriority[priority].add(element);		
	}
	
	// poll -  pop the first element in the highest priority and push it back
	public T poll() {
		T temp = null;
		for (int i = 0; i < maxPriority; i++) {
			if (null != arrayPriority[i]) {
				temp =  arrayPriority[i].get(0);
				arrayPriority[i].remove(0);
				arrayPriority[i].add(temp);
			}
		}
		return temp;
	}
	
	// contains - get an element and checks of the ArrayList contain it
	public boolean contains(T element) {
		T temp;
		
		for (int i = 0; i < maxPriority; i++) {
			for (int j = 0; j < arrayPriority[i].size(); i++) {
				temp = arrayPriority[i].get(j);
				if (temp == element) {
					return true;
				}
			}
		}
		return false;
	}
	// remove - removes the highest element form the ArrayList 
	public boolean remove(T element) {
		T temp;
		
		for (int i = 0; i < maxPriority; i++) {
			for (int j = 0; j < arrayPriority[i].size(); i++) {
				temp = arrayPriority[i].get(j);
				if (temp == element) {
					arrayPriority[i].remove(j);
					return true;
				}
			}
		}
		return false;
	}
	
	// size - count the amount of all elements in all priorities
	public int size() {
		int counter = 0;
		for (int i = 0; i < maxPriority; i++) {
			counter += arrayPriority[i].size() -1;
		}
		return counter;
	}
	
	// iterator - iterate the ArrayList to a readable state
	public void iterator() {
		ArrayList <T> temp = new ArrayList <>();
		for (int i = 0; i < maxPriority; i++) {
			temp.addAll(arrayPriority[i]);
		}
		
		Iterator<T> iterator = temp.iterator();
		while (iterator.hasNext()) {
			 T i = iterator.next();
			 System.out.print(i + " ");
		}		
	}
}

// סעיף ב
class testclass1 {
	public static void main(String[] args) {
		//Check the contractor
		myQueue <String> array = new myQueue <String> (3);
		
		//Check the add() function
		array.add("a", 1);
		array.add("b", 2);
		array.add("c", 2);
		array.add("d", 1);
		array.add("e", 3);
		array.add("f", 5);
		
		//Check the poll() function
		String temp;
		temp = array.poll();
		System.out.println(temp);
		
		temp = array.poll();
		System.out.println(temp);
		
	}
}
// סעיף ג
class CustomerInquiries {
	private String name;
	private int id;
	private String details;
	// setting a constructor and get functions
	public CustomerInquiries(String Name, int ID, String Details) {
		name = Name;
		id = ID;
		details = Details;	
	}
	public String getName() {return name;}
	public int getId() {return id;}
	 
	public static void main(String[] args) {
		// Create a contractor
		myQueue <CustomerInquiries> array = new myQueue <CustomerInquiries> (2);
		
		CustomerInquiries c1 = new CustomerInquiries("a", 123, "qwer");
		CustomerInquiries c2 = new CustomerInquiries("b", 234, "qwer");
		CustomerInquiries c3 = new CustomerInquiries("c", 123, "qwer");
		CustomerInquiries c4 = new CustomerInquiries("d", 567, "asdf");
		//Some adds
		array.add(c1, 2);
		array.add(c2, 0);
		array.add(c3, 1);
		array.add(c4, 0);
		//Check poll and remove
		CustomerInquiries c5 = array.poll();
		System.out.println(c5.getName() + ", " + c5.getId());
		array.remove(c3);
		//Check contains
		if (array.contains(c1)) {
			System.out.println("contains function works");
		} else {
			System.out.println("contains function isn't working");
		}
	}
}





