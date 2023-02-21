package Banks.console.tools.clienthandlers;

import Banks.Models.Client;
import Banks.Models.PassportNumber;
import Banks.console.chain.IChainLink;

import java.util.Scanner;

public class PassportController implements IChainLink
{
	private Client.ClientBuilder builder;
	private IChainLink nextChainLink;

	public PassportController(Client.ClientBuilder builder)
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
		System.out.println("Set client's passport (optional): ");
		String passportString = new Scanner(System.in).nextLine();
		if (passportString != null && Validation(passportString))
		{
			var passport = new PassportNumber(passportString);
			builder.SetPassport(passport);
			if (getNextChainLink() != null)
			{
				getNextChainLink().Handle();
			}
		}
	}

	public final boolean Validation(String passportString)
	{
		if (passportString.length() != 10)
		{
			return false;
		}
		return true;
	}
}
