package Banks.Entities;

import Banks.Exceptions.CreditAccountException;
import Banks.Models.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.UUID;

public class CreditAccount implements IBankAccount
{
	private final AccountType accountType;
	private Money verifiedLimit;
	private UUID id;
	private Balance balance;
	private Client client;
	private boolean verified;
	private Money commission;
	private Money creditLimit;
	private int dayCounter;
	private ArrayList<Transaction> transactionHistory;
	public CreditAccount(UUID id, Client client, Money commission, Money creditLimit, Money verifiedLimit)
	{
		setDayCounter(0);
		balance = new Balance();
		transactionHistory = new ArrayList<Transaction>();
		this.commission = commission;
		this.creditLimit = creditLimit;
		this.id = id;
		this.client = client;
		this.dayCounter = 0;
		verified = client.getVerified();
		this.verifiedLimit = verifiedLimit;
		accountType = AccountType.Credit;
	}


	public final AccountType getAccountType()
	{
		return accountType;
	}
	public final Money getVerifiedLimit()
	{
		return verifiedLimit;
	}
	public final UUID getId()
	{
		return id;
	}
	public final void setId(UUID value)
	{
		id = value;
	}
	public final Balance getBalance()
	{
		return balance;
	}

	public final Client getClient()
	{
		return client;
	}

	public final void setClient(Client value)
	{
		client = value;
	}
	public final boolean getVerified()
	{
		return verified;
	}
	public final Money getCommission()
	{
		return commission;
	}
	public final Money getCreditLimit()
	{
		return creditLimit;
	}
	private int getDayCounter()
	{
		return dayCounter;
	}
	private void setDayCounter(int value)
	{
		dayCounter = value;
	}
	private ArrayList<Transaction> getTransactionHistory()
	{
		return transactionHistory;
	}

	public final Transaction FindTransaction(UUID transactionId)
	{
		return getTransactionHistory().stream()
				.filter(x -> transactionId.equals(x.getId()))
				.findFirst().orElse(null);
	}

	public final void Transfer(IBankAccount toAccount, Money amount)
	{
		if (!getVerified() && (amount.amount().compareTo(getVerifiedLimit().amount()) < 0))
		{
			throw CreditAccountException.MaxedOutCreditLimit();
		}
		if (getBalance().WithDraw(amount).getAmount().compareTo(new BigDecimal(0)) < 0)
		{
			getBalance().Deposit(amount);
			throw CreditAccountException.CreditLimitExceeded();
		}

		getBalance().WithDraw(amount);
		toAccount.Deposit(amount);
		getTransactionHistory().add(new Transaction(UUID.randomUUID(), getId(), toAccount.getId(), amount));
		toAccount.GetTransfer(getId(), amount);
	}

	public final void GetTransfer(UUID fromAccountId, Money amount)
	{
		getTransactionHistory().add(new Transaction(UUID.randomUUID(), fromAccountId, getId(), amount));
	}

	public final void WithDraw(Money amount)
	{
		if (!getVerified() && (amount.amount().compareTo(getVerifiedLimit().amount()) > 0))
		{
			throw CreditAccountException.CreditLimitExceeded();
		}
		getBalance().WithDraw(amount);
	}

	public final void Deposit(Money amount)
	{
		getBalance().Deposit(amount);
	}

	public final void BalanceUpdate()
	{
		setDayCounter(getDayCounter() + 1);
		if (getDayCounter() % 30 == 0)
		{
			getBalance().WithDraw(getCommission());
		}
	}

	public final void CancelTransaction(UUID transactionId)
	{
		Transaction transaction = transactionHistory.stream()
				.filter(x -> x.getId().equals(transactionId))
				.findFirst()
				.orElse(null);
		if (transaction == null)
		{
			throw CreditAccountException.CanNotFindTransaction();
		}
		getTransactionHistory().remove(transaction);
	}
}
