package Banks.console.tools.clienthandlers;

import Banks.Models.Client;
import Banks.Models.ClientName;
import Banks.console.chain.IChainLink;

import java.util.Scanner;

public class NameController implements IChainLink
{
	private Client.ClientBuilder builder;
	private IChainLink nextChainLink;

	public NameController(Client.ClientBuilder builder)
	{
		this.builder = builder;
		setNextChainLink(null);
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
		System.out.println("Set client's name: ");
		String name = new Scanner(System.in).nextLine();
		if ((name == null || name.isBlank()))
		{
			Handle();
		}
		else
		{
			builder.SetName(new ClientName(name));
			if (getNextChainLink() != null)
			{
				getNextChainLink().Handle();
			}
		}
	}
}
