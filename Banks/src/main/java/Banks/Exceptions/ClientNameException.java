package Banks.Exceptions;

public class ClientNameException extends BaseException
{
	private ClientNameException(String message)
	{
		super(message);
	}

	public static ClientNameException NullOrWhiteSpace()
	{
		return new ClientNameException("White space string");
	}
}
