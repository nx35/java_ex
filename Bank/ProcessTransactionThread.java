public class ProcessTransactionThread extends Thread {
	private Transaction transaction;
	private Bank bank
	public ProcessTransactionThread(Transaction inputTransaction, Bank inputBank) {
		this.Transaction = inputTransaction;
		this.Bank = inputBank;
	}
	public void run() {
		System.out.println("Starting transaction");
		this.Bank.transfer(this.transaction);
		System.out.println("Transaction Successful:" + this.transaction.ToString());
	}
}

