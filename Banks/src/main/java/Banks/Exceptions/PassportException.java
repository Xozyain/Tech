package Banks.Exceptions;

public class PassportException extends BaseException
{
	private PassportException(String message)
	{
		super(message);
	}

	public static PassportException WrongPassportFormat()
	{
		return new PassportException("Wrong passport format");
	}
}
