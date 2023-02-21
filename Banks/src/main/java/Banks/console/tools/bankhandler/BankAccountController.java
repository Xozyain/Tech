package Banks.console.tools.bankhandler;

import Banks.Models.AccountType;
import Banks.Models.Bank;
import Banks.Models.Client;
import Banks.Models.Money;
import Banks.console.chain.IChainLink;

import java.util.Scanner;

public class BankAccountController implements IChainLink
{
	private Bank bank;
	private IChainLink nextChainLink;
	public BankAccountController(Bank bank)
	{
		this.bank = bank;
		setNextChainLink(null);
	}


	public final Bank getBank()
	{
		return bank;
	}
	public final IChainLink getNextChainLink()
	{
		return nextChainLink;
	}
	public final void setNextChainLink(IChainLink value)
	{
		nextChainLink = value;
	}

	public final void Handle() {
		var chooseClientHandler = new ChooseClientController(getBank());
		chooseClientHandler.Handle();
		Client client = null;
		try{
			if (chooseClientHandler.getClient() != null) client = chooseClientHandler.getClient();
			else throw new Exception("Client is null");
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
		}

		System.out.println("Requests: debit, credit, deposit");
		String command = new Scanner(System.in).nextLine();

		switch (command)
		{
			case "deposit":
			{
				var balanceHandler = new BalanceController();
				balanceHandler.Handle();
				Money balance;

				try{
					if (balanceHandler.getAmount() != null) balance = balanceHandler.getAmount();
					else throw new Exception("Balance is null");
					getBank().CreateBankAccount(client, AccountType.Deposit, balance);
				}
				catch(Exception ex){
					System.out.println(ex.getMessage());
				}
				break;
			}

			case "debit":
			{
				getBank().CreateBankAccount(client, AccountType.Debit);
				break;
			}

			case "credit":
			{
				getBank().CreateBankAccount(client, AccountType.Credit);
				break;
			}
		}
	}
}
