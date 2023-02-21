package Banks.Models;

import Banks.Entities.*;
import Banks.Exceptions.BankException;

import java.math.BigDecimal;
import java.util.*;

public class Bank implements INotifier
{
	private CreditConfig creditConfig;
	private DebitConfig debitConfig;
	private DepositConfig depositConfig;
	private ArrayList<IBankAccount> accountList = new ArrayList<IBankAccount>();
	private ArrayList<Client> clientList = new ArrayList<Client>();
	private ArrayList<Client> subscriberList = new ArrayList<Client>();

	private Money verifiedLimit;
	private String name;

	public Bank(String name, Money verifiedLimit, CreditConfig creditConfig, DebitConfig debitConfig, DepositConfig depositConfig)
	{
		this.name = name;
		setVerifiedLimit(verifiedLimit);
		this.creditConfig = creditConfig;
		this.debitConfig = debitConfig;
		this.depositConfig = depositConfig;
	}


	public final Money getVerifiedLimit()
	{
		return verifiedLimit;
	}
	private void setVerifiedLimit(Money value)
	{
		verifiedLimit = value;
	}

	public final String getName()
	{
		return name;
	}
	public final boolean equals(Bank other)
	{
		if (null == other)
		{
			return false;
		}
		if (this == other)
		{
			return true;
		}
		return Objects.equals(getName(), other.getName());
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}

		if (!(obj instanceof Bank))
		{
			return false;
		}
		Bank other = (Bank) obj;

		return Objects.equals(getName(), other.getName());
	}

	@Override
	public int hashCode()
	{
		return getName().hashCode();
	}

	public final IBankAccount GetAccount(UUID accountId)
	{
		IBankAccount account = accountList.stream()
				.filter(x -> x.getId() == accountId)
				.findFirst()
				.orElse(null);

		if (account == null)
		{
			throw BankException.CanNotFindAccount();
		}
		return account;
	}


	public final IBankAccount CreateBankAccount(Client client, AccountType accountType)
	{
		return CreateBankAccount(client, accountType, null);
	}

	public final IBankAccount CreateBankAccount(Client client, AccountType accountType, Money amount)
	{
		switch (accountType)
		{
			case Debit:
				var debitAccount = new DebitAccount(UUID.randomUUID(), client, debitConfig.persent(), getVerifiedLimit());
				accountList.add(debitAccount);
				return debitAccount;
			case Credit:
				var creditAccount = new CreditAccount(UUID.randomUUID(), client, creditConfig.commission(), creditConfig.limit(), getVerifiedLimit());
				accountList.add(creditAccount);
				return creditAccount;
			case Deposit:
				if (amount == null)
				{
					throw BankException.CanNotFindAccount();
				}
				var depositAccount = new DepositAccount(UUID.randomUUID(), client, depositConfig.percents(), amount, getVerifiedLimit());
				accountList.add(depositAccount);
				return depositAccount;
		}

		return new CreditAccount(UUID.randomUUID(), client, creditConfig.commission(), creditConfig.commission(), getVerifiedLimit());
	}

	public final void CancelTransaction(UUID accountId, UUID transaction)
	{
		IBankAccount account = accountList.stream().filter(x -> accountId.equals(x.getId()))
				.findFirst()
			.orElse(null);
		if (account == null)
		{
			throw BankException.CanNotFindAccount();
		}
		account.CancelTransaction(transaction);
	}

	public final void ChangeDebitPercent(AccountType accountType, BigDecimal percent)
	{
		debitConfig = new DebitConfig(percent);
		accountList.stream()
				.filter(account -> account.getAccountType().equals(AccountType.Debit))
				.forEach(account -> Notify(account, "Debit percent has been changed"));
	}

	public final void ChangeDepositPercent(AccountType accountType, Map<Money, BigDecimal> percents)
	{
		depositConfig = new DepositConfig(depositConfig.timeLimit(), percents, depositConfig.amount());
		accountList.stream()
				.filter(account -> account.getAccountType() == AccountType.Deposit)
				.forEach(account -> Notify(account, "Deposit percent has been changed"));

	}

	public final void ChangeVerifiedLimit(Money amount)
	{
		setVerifiedLimit(amount);
	}

	public final void Subscribe(Client client)
	{
		subscriberList.add(client);
	}

	public final void UnSubscribe(Client client)
	{
		subscriberList.remove(client);
	}

	public final void Notify(IBankAccount account, String message)
	{
		account.getClient().ReceiveNotice("Deposit percent has been changed");
	}

	public final void AddClient(Client client)
	{
		if (clientList.contains(client))
		{
			throw BankException.ClientDuplicate();
		}
		clientList.add(client);
	}

	public final void UpdateBalances()
	{
		accountList.forEach(x -> x.BalanceUpdate());
	}

	public final List<Client> getClientList() {
		return Collections.unmodifiableList(clientList);
	}

	public final List<IBankAccount> GetAccountList()
	{
		return Collections.unmodifiableList(accountList);
	}
}
