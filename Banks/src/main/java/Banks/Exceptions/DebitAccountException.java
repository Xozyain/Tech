package Banks.Exceptions;

public class DebitAccountException extends BaseException
{
	private DebitAccountException(String message)
	{
		super(message);
	}

	public static DebitAccountException MaxedOutCreditLimit()
	{
		return new DebitAccountException("Maxed out credit limit");
	}

	public static DebitAccountException CanNotFindTransaction()
	{
		return new DebitAccountException("Can not find transaction");
	}
}
