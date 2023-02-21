package Banks.Services;

import Banks.Entities.IBankAccount;
import Banks.Exceptions.CentralBankException;
import Banks.Models.*;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class CentralBank implements ICentralBank
{
	private final ArrayList<Bank> bankList = new ArrayList<Bank>();
	private final ArrayList<Client> clientList = new ArrayList<Client>();
	private final ArrayList<InterTransaction> interTransactionList = new ArrayList<InterTransaction>();

	public final void RegisterBank(String name, Money verifiedLimit, CreditConfig creditConfig, DebitConfig debitConfig, DepositConfig depositConfig)
	{
		var bank = new Bank(name, verifiedLimit, creditConfig, debitConfig, depositConfig);
		if (bankList.contains(bank))
		{
			throw CentralBankException.BankNameDuplicate();
		}
		bankList.add(bank);
	}

	public final void Transfer(String bankNameFrom, UUID accountFrom, String bankNameTo, UUID accountTo, Money amount)
	{
		Bank bankFrom = bankList.stream().filter(x -> x.getName() == bankNameFrom).findFirst().orElse(null);
		Bank bankTo = bankList.stream().filter(b -> b.getName().equals(bankNameTo)).findFirst().orElse(null);
		if (bankFrom == null || bankTo == null)
		{
			throw CentralBankException.NoSuchBank();
		}
		IBankAccount bankAccountFrom = bankFrom.GetAccount(accountFrom);
		IBankAccount bankAccountTo = bankTo.GetAccount(accountTo);
		bankAccountFrom.Transfer(bankAccountTo, amount);
	}

	public final void NotifyBanks()
	{
		bankList.forEach(x -> x.UpdateBalances());
	}

	public final boolean HasBank(String name)
	{
		Bank bank = bankList.stream().filter(x -> Objects.equals(x.getName(), name)).findFirst().orElse(null);
		return bank != null;
	}
}
