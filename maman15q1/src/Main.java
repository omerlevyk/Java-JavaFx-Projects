import java.util.concurrent.ThreadLocalRandom;

/**
 * Maman 15 Question 1
 * Author name - Omer Levy
 * Author ID - 209009117
 * Instructor - Roni Ben Ishay
 * Corse Nuber - 20554
 */


//BankDepartment - the fourth class 
public class Main {
	private static int NUMBER_OF_ACCOUNT = 5;
	private static int NUMBER_OF_TRANSACTIONS = 50;
	private static int NUMBER_OF_CLREKS = 2*NUMBER_OF_ACCOUNT;
	private static int MAX_AMOUNT = 2000;
	private static int MIN_AMOUNT = 1000;
	
	// main
    public static void main(String[] args) {
    	
    	// make an array of accounts
    	bankAccount[] accounts = new bankAccount[NUMBER_OF_ACCOUNT];
        for (int i = 0; i < accounts.length; i++) {
            accounts[i] = new bankAccount(i, 0);
        }
        
        // make an array of transactions
        Transaction[] transactions = new Transaction[NUMBER_OF_TRANSACTIONS];
        for (int i = 0; i < transactions.length; i++) {
            int accountNumber = ThreadLocalRandom.current().nextInt(NUMBER_OF_ACCOUNT);
            int amount = ThreadLocalRandom.current().nextInt(MAX_AMOUNT) - MIN_AMOUNT;
            transactions[i] = new Transaction(accountNumber, amount);
        }

        transactionPool transactionPool = new transactionPool(transactions);
        
        // make an array of clerks
        BankClerk[] clerks = new BankClerk[NUMBER_OF_CLREKS];
        for (int i = 0; i < clerks.length; i++) {
            clerks[i] = new BankClerk(transactionPool, accounts);
            clerks[i].start();
        }

        for (int i = 0; i < accounts.length; i++) {
            System.out.println("Account " + (i+1) + ": " + accounts[i].getBalance());
        }
    }
}