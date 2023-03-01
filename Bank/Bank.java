public class Bank {
	private Arraylist<Account> Accounts;
	private ReentrantLock TransactionWindow;
	
	public Bank() {
		this.Accounts = new Arraylist<Accounts>();
		this.TransactionWindows = new ReentrantLock();
	}

	public Arraylist<Account> getAccounts() {
		return this.Accounts;
	}
	public void addAccount(Account account) {
		// Added mutex, since adding an account sometimes triggers an array expansion/migration, meaning one thread can be doing a transaction affecting the old array after that part of the array was already copied over (or even part of it) resulting in a "cancelled" or partial transaction.
		TransactionWindow.lock();
		this.Accounts.add(account);
		TransactionWindow.unlock();
	}
	public void transfer(Transaction transaction) {
		Account sender = transaction.getSender();
		Account recipient = transaction.getRecipient();
		long amount = transaction.getAmount();

		if (sender.exists() && recipient.exists())
			sender.transferAmountTo(this.TransactionWindow, amount, recipient);
		else
			throw new AccountDoesNotExistException("Sender or Recipient account does not exist");
	}
	public boolean exists(Account account) {
		return accounts.contains(account);
	}
}
