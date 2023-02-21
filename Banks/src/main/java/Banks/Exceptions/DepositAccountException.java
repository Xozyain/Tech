package Banks.Exceptions;

public class DepositAccountException extends BaseException
{
	private DepositAccountException(String message)
	{
		super(message);
	}

	public static DepositAccountException MaxedOutCreditLimit()
	{
		return new DepositAccountException("Maxed out credit limit");
	}

	public static DepositAccountException CanNotFindTransaction()
	{
		return new DepositAccountException("Can not find transaction");
	}
}
