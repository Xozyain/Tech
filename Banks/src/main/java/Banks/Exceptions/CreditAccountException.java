package Banks.Exceptions;

public class CreditAccountException extends BaseException
{
	private CreditAccountException(String message)
	{
		super(message);
	}

	public static CreditAccountException CreditLimitExceeded()
	{
		return new CreditAccountException("Credit limit exceeded");
	}

	public static CreditAccountException MaxedOutCreditLimit()
	{
		return new CreditAccountException("Maxed out credit limit");
	}

	public static CreditAccountException CanNotFindTransaction()
	{
		return new CreditAccountException("Can not find transaction");
	}
}
