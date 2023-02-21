package Banks.Entities;

import Banks.Models.*;

import java.util.UUID;

public interface IBankAccount
{
	UUID getId();
	Balance getBalance();
	Client getClient();
	AccountType getAccountType();
	boolean getVerified();
//C# TO JAVA CONVERTER WARNING: Nullable reference types have no equivalent in Java:
//ORIGINAL LINE: Transaction? FindTransaction(Guid transactionId);
	Transaction FindTransaction(UUID transactionId);
	void CancelTransaction(UUID transactionId);
	void Transfer(IBankAccount toAccount, Money amount);
	void GetTransfer(UUID fromAccountId, Money amount);
	void WithDraw(Money amount);
	void Deposit(Money amount);
	void BalanceUpdate();
}
