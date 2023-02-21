package Banks.console.tools.clienthandlers;

import Banks.Models.Client;
import Banks.console.chain.IChainLink;

import java.util.Scanner;

public class AddressController implements IChainLink
{
	private Client.ClientBuilder builder;
	private IChainLink nextChainLink;

	public AddressController(Client.ClientBuilder builder)
	{
		this.builder = builder;
		setNextChainLink(null);
	}


	public final IChainLink getNextChainLink() { return nextChainLink; }
	public final void setNextChainLink(IChainLink value)
	{
		nextChainLink = value;
	}

	public final void Handle()
	{
		System.out.println("Set address for client (optional): ");
		String name = new Scanner(System.in).nextLine();

		if (!(name == null || name.isBlank()))
		{
			builder.SetAddress(name);
			if (getNextChainLink() != null)
			{
				getNextChainLink().Handle();
			}
		}
		else
		{
			if (getNextChainLink() != null)
			{
				getNextChainLink().Handle();
			}
		}
	}
}
