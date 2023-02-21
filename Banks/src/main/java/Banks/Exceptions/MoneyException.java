package Banks.Exceptions;

public class MoneyException extends BaseException
{
	private MoneyException(String message)
	{
		super(message);
	}

	public static MoneyException NegativeMoney()
	{
		return new MoneyException("Money can not be negative");
	}
}
