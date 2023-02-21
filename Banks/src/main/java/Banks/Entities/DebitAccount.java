package Banks.Entities;

import Banks.Exceptions.CreditAccountException;
import Banks.Exceptions.DebitAccountException;
import Banks.Exceptions.DebitConfigException;
import Banks.Models.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.UUID;

public class DebitAccount implements IBankAccount
{
	private final AccountType accountType;
	private Money verifiedLimit;
	private BigDecimal percent;
	private UUID id;
	private Balance balance;
	private Client client;
	private boolean verified;
	private int dayCounter;
	private Money gain;
	private ArrayList<Transaction> transactionHistory;
	public DebitAccount(UUID id, Client client, BigDecimal percent, Money verifiedLimit)
	{
		this.id = id;
		balance = new Balance();
		this.client = client;
		transactionHistory = new ArrayList<Transaction>();
		if (percent.compareTo(new BigDecimal(0)) < 0)
		{
			throw DebitConfigException.NegativePercent();
		}
		this.percent = percent;
		setDayCounter(0);
		setGain(new Money(new BigDecimal(0)));
		verified = client.getVerified();
		this.verifiedLimit = verifiedLimit;
		accountType = AccountType.Debit;
	}


	public final AccountType getAccountType()
	{
		return accountType;
	}
	public final Money getVerifiedLimit()
	{
		return verifiedLimit;
	}
	public final BigDecimal getPercent()
	{
		return percent;
	}
	public final UUID getId()
	{
		return id;
	}
	public final Balance getBalance()
	{
		return balance;
	}

	public final Client getClient()
	{
		return client;
	}
	public final boolean getVerified()
	{
		return verified;
	}
	private int getDayCounter()
	{
		return dayCounter;
	}
	private void setDayCounter(int value)
	{
		dayCounter = value;
	}
	private Money getGain()
	{
		return gain;
	}
	private void setGain(Money value)
	{
		gain = value;
	}
	private ArrayList<Transaction> getTransactionHistory()
	{
		return transactionHistory;
	}

	public final Transaction FindTransaction(UUID transactionId)
	{
		return getTransactionHistory().stream()
				.filter(x -> transactionId.equals(x.getId()))
				.findFirst()
				.orElse(null);
	}

	public final void Transfer(IBankAccount toAccount, Money amount)
	{
		if (!getVerified() && (amount.amount().compareTo(getVerifiedLimit().amount()) > 0))
		{
			throw DebitAccountException.MaxedOutCreditLimit();
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
		setGain(new Money(getGain().amount().add(getBalance().getAmount().multiply(getPercent()))));

		if (getDayCounter() % 30 == 0)
		{
			getBalance().Deposit(getGain());
			setGain(new Money(new BigDecimal(0)));
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
			throw DebitAccountException.CanNotFindTransaction();
		}
		getTransactionHistory().remove(transaction);
	}
}
