package Banks.console.tools.bankhandler;

import Banks.Entities.BankAccount;
import Banks.Models.Bank;
import Banks.console.chain.ChainLink;
import java.util.Scanner;

/**
 * The type Choose account controller.
 */
public class ChooseAccountController implements ChainLink
{
	private Bank bank;
	private BankAccount bankAccount;
	private ChainLink nextChainLink;

	/**
	 * Instantiates a new Choose account controller.
	 *
	 * @param bank the bank
	 */
	public ChooseAccountController(Bank bank)
	{
		this.bank = bank;
	}

	/**
	 * Gets bank.
	 *
	 * @return the bank
	 */
	public Bank getBank()
	{
		return bank;
	}

	/**
	 * Gets bank account.
	 *
	 * @return the bank account
	 */
	public BankAccount getBankAccount()
	{
		return bankAccount;
	}
	private void setBankAccount(BankAccount value)
	{
		bankAccount = value;
	}
	public ChainLink getNextChainLink()
	{
		return nextChainLink;
	}
	public final void setNextChainLink(ChainLink value)
	{
		nextChainLink = value;
	}

	public void handle()
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
			handle();
		}

		if (getNextChainLink() != null)
		{
			getNextChainLink().handle();
		}
		else
		{
			handle();
		}
	}
}
