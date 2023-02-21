package Banks.Exceptions;

public class DebitConfigException extends BaseException
{
	private DebitConfigException(String message)
	{
		super(message);
	}

	public static DebitConfigException NegativePercent()
	{
		return new DebitConfigException("Percent can not be negative");
	}
}
