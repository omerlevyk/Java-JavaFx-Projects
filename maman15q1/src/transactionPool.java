import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Maman 15 Question 1
 * Author name - Omer Levy
 * Author ID - 209009117
 * Instructor - Roni Ben Ishay
 * Corse Nuber - 20554
 */

// transactionPool - second class 
public class transactionPool {
	private final BlockingQueue<Transaction> transactions;
	
	// transactionPool set a pool for transactions
	public transactionPool(Transaction[] transactions) {
        this.transactions = new ArrayBlockingQueue<>(transactions.length);
        for (Transaction t : transactions) {
            this.transactions.add(t);
        }
    }
	
	// getTransaction poll a transaction from the pool and return it
	public Transaction getTransaction() throws InterruptedException {
		if (transactions != null) {
			return transactions.take();
		}
        return null;
    }
}