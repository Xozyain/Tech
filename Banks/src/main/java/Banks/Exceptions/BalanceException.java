package Banks.Exceptions;

public class BalanceException extends BaseException
{
	private BalanceException(String message)
	{
		super(message);
	}

	public static BalanceException NotEnoughMoney()
	{
		return new BalanceException("Not enough money on balance");
	}
}
