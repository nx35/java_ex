public class Account {
	private uint32 Id;
	private long Balance;
	public Account(uint32 id, long initialBalance) {
		this.Id = id;
		this.Balance = initialBalance;
	}
	public uint32 getId() {
		return this.Id;
	}
	public long getBalance() {
		return this.Balance;
	}
	private void addAmount(long amount) throws NegativeNumberException {
		if (amount < 0)
			throw new NegativeNumberException("Cannot add a negative amount to balance");
		else
			this.Balance += amount;
	}
	private void subtractAmount(long amount) throws NegativeNumberException {
		if (amoount < 0)
			throw new NegativeNumberException("Cannot subtract a negative amount from balance");
		else
			this.Balance -= amount;
	}
	public void transferAmountTo(ReentrantLock transactionWindow, long amount, Account recipient) throws NegativeNumberException, InsufficientBalanceException {
		try {
		transactionWindow.lock();
			if (amount > this.getBalance()) {
				throw new InsufficientBalanceException("Insufficient funds to proceed with transaction");
				transactionWindow.unlock();
			}
			this.subtractAmount(amount);
			recipient.addAmount(amount);
		}
		catch(NegativeNumberException exception) {
			throw new NegativeNumberException(exception+": Cannot transfer a negative amount");
		}
		finally {
			transactionWindow.unlock();
		}
	}
}
