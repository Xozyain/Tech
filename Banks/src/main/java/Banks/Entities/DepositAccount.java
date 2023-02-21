package Banks.Entities;

import Banks.Exceptions.CreditAccountException;
import Banks.Exceptions.DepositAccountException;
import Banks.Models.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

public class DepositAccount implements IBankAccount
{
	private AccountType accountType;
	private BigDecimal percent = new BigDecimal(0);
	private Money verifiedLimit;
	private UUID id;
	private Balance balance;
	private Client client;
	private boolean verified;
	private Money gain;
	private ArrayList<Transaction> transactionHistory;
	private Money amount;
	private int dayCounter;
	public DepositAccount(UUID id, Client client, Map<Money, BigDecimal> percents, Money amount, Money verifiedLimit)
	{
		this.id = id;
		this.client = client;
		this.percent = percents.get(amount);
		balance = new Balance();
		transactionHistory = new ArrayList<Transaction>();
		this.amount = amount;
		setDayCounter(0);
		setGain(new Money(new BigDecimal(0)));
		verified = client.getVerified();
		this.verifiedLimit = verifiedLimit;
		accountType = AccountType.Deposit;
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

	public final BigDecimal getPercent()
	{
		return percent;
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

	private Money getAmount()
	{
		return amount;
	}
	private int getDayCounter()
	{
		return dayCounter;
	}
	private void setDayCounter(int value)
	{
		dayCounter = value;
	}

	public final Transaction FindTransaction(UUID transactionId)
	{
		return  getTransactionHistory().stream()
				.filter(x -> transactionId.equals(x.getId()))
				.findFirst()
				.orElse(null);
	}

	public final void Transfer(IBankAccount toAccount, Money amount)
	{
		if (!getVerified() && (amount.amount().compareTo(getVerifiedLimit().amount()) > 0))
		{
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
			throw DepositAccountException.MaxedOutCreditLimit();
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
		Transaction transaction = getTransactionHistory().stream()
				.filter(x -> transactionId.equals(x.getId()))
				.findFirst()
				.orElse(null);
		if (transaction == null)
		{
			throw DepositAccountException.CanNotFindTransaction();
		}
		getTransactionHistory().remove(transaction);
	}
}
