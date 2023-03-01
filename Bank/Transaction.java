public class Transaction {
	// TODO: add Id, timestamp & create hash upon instantiation
	private long Amount;
	private Account Sender;
	private Account Recipient;
	public Transaction(long amount, Account sender, Account recipient) {
		this.Amount = amount;
		this.Sender = sender;
		this.Recipeint = recipient;
	}
	public long getAmount() {
		return this.Amount;
	}
	public Account getSender() {
		return this.Sender;
	}
	public Account getRecipient() {
		return this.Recipient;
	}
	public String toString() {
		return "sender:"+this.Sender.getId()+" | recipient:"+this.Recipient.getId()+" | amount:"+this.Amount;
	}
}
