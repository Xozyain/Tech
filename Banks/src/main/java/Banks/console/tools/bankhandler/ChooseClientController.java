package Banks.console.tools.bankhandler;

import Banks.Models.Bank;
import Banks.Models.Client;
import Banks.console.chain.IChainLink;

import java.util.Scanner;

public class ChooseClientController implements IChainLink
{
	public ChooseClientController(Bank bank)
	{
		this.bank = bank;
		setNextChainLink(null);
	}

	private Bank bank;
	public final Bank getBank()
	{
		return bank;
	}
	private Client client;
	public final Client getClient()
	{
		return client;
	}
	private void setClient(Client value)
	{
		client = value;
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
		System.out.println("Choose client by number:");
		var clients = getBank().getClientList();

		for (int i = 0; i < clients.size(); i++)
		{
			System.out.printf("%1$s) %2$s%n", i, clients.get(i).getClientName());
		}

		String command = new Scanner(System.in).nextLine();

		try {
			int num = Integer.parseInt(command);
			setClient(clients.get(num));
			return;
		} catch (NumberFormatException e) {
			System.out.println("Invalid input: " + command + " is not a valid integer.");
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
