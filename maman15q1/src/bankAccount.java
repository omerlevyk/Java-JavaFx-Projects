/**
 * Maman 15 Question 1
 * Author name - Omer Levy
 * Author ID - 209009117
 * Instructor - Roni Ben Ishay
 * Corse Nuber - 20554
 */

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

// first class for the bank account contractor
public class bankAccount {
	
	private final int accountNumber;
	private int balance;

	// bankAccount	 - constructor
	public bankAccount(int id, int bal) {
		this.accountNumber = id;
		this.balance = bal;
	}
	
	// transaction - add or subtract from the account balance keep balance above 0
	public synchronized void transaction(int amount) {
		while (getBalance() + amount < 0) {
			try {
				wait();
			} catch (InterruptedException catchError) {
				System.out.print("1: ");
				catchError.printStackTrace();
			}
		}
		this.balance += amount;
		notifyAll();
	}
	// getBalance return the balance amount
	public synchronized int getBalance() {
		return this.balance;
	}
}
