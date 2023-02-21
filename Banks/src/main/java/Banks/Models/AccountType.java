package Banks.Models;

public enum AccountType
{
	/** 
	 deposit account
	*/
	Deposit,

	/** 
	 debit account
	*/
	Debit,

	/** 
	 credit account
	*/
	Credit;

	public static final int SIZE = Integer.SIZE;

	public int getValue()
	{
		return this.ordinal();
	}

	public static AccountType forValue(int value)
	{
		return values()[value];
	}
}
