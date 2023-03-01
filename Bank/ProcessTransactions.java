public class ProcessTransactions {
	public static void main(String[] args) {
		Bank bank = new Bank();
		for (int i=0; i<8; i++)
			bank.addAccount(new Account(uint32(i)), 10.0);
		ArrayList<Account> accounts = bank.getAccounts();
		for (int i=0; i<4; i++) {
			ProcessTransactionThread transactionThread = new ProcessTransactionThread(new Transaction(
			transactionThread.start(2.1+i, accounts.get(uint32(i)), accounts.get(uint32(i+4));
			System.out.println("--------------Finito-------------");
		}
	}
}
