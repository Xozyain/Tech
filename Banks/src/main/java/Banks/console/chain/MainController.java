package Banks.console.chain;

import Banks.Entities.IBankAccount;
import Banks.Models.Bank;
import Banks.Models.Money;
import Banks.console.tools.bankhandler.BalanceController;
import Banks.console.tools.bankhandler.BankAccountController;
import Banks.console.tools.bankhandler.ChooseAccountController;
import Banks.console.tools.clienthandlers.ClientController;

import java.util.Objects;
import java.util.Scanner;

public class MainController implements IChainLink
{
	private Bank bank;
	private IChainLink nextChainLink;
	public MainController(Bank bank)
	{
		this.bank = bank;
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

	public final void Handle()
	{
		System.out.println("Requests: client, addBankAccount, makeTransaction");
		String command = new Scanner(System.in).nextLine();
		switch (command)
		{
			case "client":
			{
				var clientController = new ClientController(getBank());
				clientController.Handle();
				break;
			}

			case "addBankAccount":
			{
				var accountController = new BankAccountController(getBank());
				accountController.Handle();
				break;
			}

			case "makeTransaction":
			{
				System.out.println("Requests: transfer, withdraw, deposit");

				String requests = new Scanner(System.in).nextLine();
				switch (requests)
				{
					case "transfer":
					{
						var selectedFrom = new ChooseAccountController(getBank());
						selectedFrom.Handle();

						var selectedTo = new ChooseAccountController(getBank());
						selectedTo.Handle();

						IBankAccount accountFrom = Objects.requireNonNull(selectedFrom.getBankAccount(), "Bank account cannot be null");
						IBankAccount accountTo = Objects.requireNonNull(selectedTo.getBankAccount(), "Bank account cannot be null");

						var balanceController = new BalanceController();
						balanceController.Handle();

						Money amount = Objects.requireNonNull(balanceController.getAmount(), "Amount cannot be null");
						accountFrom.Transfer(accountTo, amount);
						break;
					}

					case "withdraw":
					{
						var selectedAccount = new ChooseAccountController(getBank());
						selectedAccount.Handle();

						IBankAccount account = Objects.requireNonNull(selectedAccount.getBankAccount(), "Bank account cannot be null");
						var moneyController = new BalanceController();
						moneyController.Handle();

						Money amount = Objects.requireNonNull(moneyController.getAmount(), "Amount cannot be null");
						account.WithDraw(amount);
						break;
					}

					case "deposit":
					{
						var selectedAccount = new ChooseAccountController(getBank());
						selectedAccount.Handle();

						IBankAccount account = Objects.requireNonNull(selectedAccount.getBankAccount(), "Bank account cannot be null");
						var moneyController = new BalanceController();
						moneyController.Handle();

						Money amount = Objects.requireNonNull(moneyController.getAmount(), "Amount cannot be null");
						account.Deposit(amount);
						break;
					}
				}

				break;
			}
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
