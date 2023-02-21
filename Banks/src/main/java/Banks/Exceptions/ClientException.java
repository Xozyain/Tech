package Banks.Exceptions;

public class ClientException extends BaseException
{
	private ClientException(String message)
	{
		super(message);
	}

	public static ClientException BuildWithoutName()
	{
		return new ClientException("Can not build client without name");
	}
}
