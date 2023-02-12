import java.util.concurrent.ThreadLocalRandom;

/**
 * Maman 15 Question 1
 * Author name - Omer Levy
 * Author ID - 209009117
 * Instructor - Roni Ben Ishay
 * Corse Nuber - 20554
 */

// BankClerk - the third class
public class BankClerk extends Thread {
    private final transactionPool transactionPool;
    private final bankAccount[] accounts;
    
    // BankClerk - a constructor
    public BankClerk(transactionPool transactionPool, bankAccount[] accounts) {
        this.transactionPool = transactionPool;
        this.accounts = accounts;
    }

    // run - get an action from the pool and use it an an account
    @Override
    public void run() {
        while (true) {
            Transaction t = null;
            try {
                t = transactionPool.getTransaction();
                if (t == null) {
                    break;
                }
            } catch (InterruptedException catchError) {
            	System.out.print("2: ");
            	catchError.printStackTrace();
            }
            
            accounts[t.accountNumber].transaction(t.amount);
            
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(100));
            } catch (InterruptedException catchError) {
            	System.out.print("3: ");
            	catchError.printStackTrace();
            }
        }
    }
}
