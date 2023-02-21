package Banks.Exceptions;

public class BankException extends BaseException
{
	private BankException(String message)
	{
		super(message);
	}

	public static BankException CanNotFindAccount()
	{
		return new BankException("Can not find account");
	}

	public static BankException DepositAccountNullMoney()
	{
		return new BankException("Can not create deposit account without money");
	}

	public static BankException ClientDuplicate()
	{
		return new BankException("Client already exist");
	}
}
