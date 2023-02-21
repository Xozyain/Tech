package Banks.Models;

public class InterTransaction
{
	public InterTransaction(Transaction transaction, String bankNameFrom, String bankNameTo)
	{
		Transaction = transaction;
		BankNameFrom = bankNameFrom;
		BankNameTo = bankNameTo;
	}

	private String BankNameTo;
	public final String getBankNameTo()
	{
		return BankNameTo;
	}
	private String BankNameFrom;
	public final String getBankNameFrom()
	{
		return BankNameFrom;
	}
	private Transaction Transaction;
	public final Transaction getTransaction()
	{
		return Transaction;
	}
}
