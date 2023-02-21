package Banks.console.tools.bankhandler;

import Banks.Entities.IBankAccount;
import Banks.Models.Bank;
import Banks.console.chain.IChainLink;

import java.util.Scanner;

public class ChooseAccountController implements IChainLink
{
	public ChooseAccountController(Bank bank)
	{
		this.bank = bank;
	}

	private Bank bank;
	public final Bank getBank()
	{
		return bank;
	}
	private IBankAccount bankAccount;
	public final IBankAccount getBankAccount()
	{
		return bankAccount;
	}
	private void setBankAccount(IBankAccount value)
	{
		bankAccount = value;
	}
	private IChainLink nextChainLink;
	public final IChainLink getNextChainLink()
	{
		return nextChainLink;
	}
	public final void setNextChainLink(IChainLink value)
	{
		nextChainLink = value;
	}

	public final void Handle()
	{
		var accountList = getBank().GetAccountList();

		for (int i = 0; i < accountList.toArray().length; i++)
		{
			System.out.println(String.format("%1$s) %2$s %3$s", i, accountList.get(i).getId(), accountList.get(i).getClient().getClientName()));
		}

		String selectedNumber = new Scanner(System.in).nextLine();

		try {
			int num = Integer.parseInt(selectedNumber);
			setBankAccount(accountList.get(num));
			return;
		} catch (NumberFormatException e) {
			System.out.println("Invalid input: " + selectedNumber + " is not a valid integer.");
			Handle();
		}

		if (getNextChainLink() != null)
		{
			getNextChainLink().Handle();
		}
		else
		{
			Handle();
		}
	}
}
