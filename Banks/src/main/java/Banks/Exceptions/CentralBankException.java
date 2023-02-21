package Banks.Exceptions;

public class CentralBankException extends BaseException
{
	private CentralBankException(String message)
	{
		super(message);
	}

	public static CentralBankException BankNameDuplicate()
	{
		return new CentralBankException("Bank with such name already exist");
	}

	public static CentralBankException NoSuchBank()
	{
		return new CentralBankException("No bank with this name");
	}
}
