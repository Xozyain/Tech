package Banks.console.tools.bankhandler;

import Banks.Models.Money;
import Banks.console.chain.IChainLink;

import java.math.BigDecimal;
import java.util.Scanner;

public class BalanceController implements IChainLink
{
	private IChainLink nextChainLink;
	private Money amount = null;

	public final IChainLink getNextChainLink()
	{
		return nextChainLink;
	}
	public final void setNextChainLink(IChainLink value)
	{
		nextChainLink = value;
	}

	public final Money getAmount()
	{
		return amount;
	}
	private void setAmount(Money value)
	{
		amount = value;
	}

	public final void Handle()
	{
		System.out.println("Write balance: ");
		String command = new Scanner(System.in).nextLine();

		try {
			int balance = Integer.parseInt(command);
			amount = new Money(new BigDecimal(balance));
		} catch (NumberFormatException e) {
			System.out.println("Invalid input: " + command + " is not a valid integer.");
			Handle();
		}
	}
}
