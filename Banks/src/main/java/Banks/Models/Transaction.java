package Banks.Models;

import java.util.UUID;

public class Transaction
{
	private Money amount;
	private UUID id;
	private UUID accountWithdraw;
	private UUID accountDeposit;
	public Transaction(UUID id, UUID accountWithdraw, UUID accountDeposit, Money amount)
	{
		this.id = id;
		this.accountWithdraw = accountWithdraw;
		this.accountDeposit = accountDeposit;
		this.amount = amount;
	}
	public final Money getAmount()
	{
		return amount;
	}
	public final UUID getId()
	{
		return id;
	}
	public final UUID getAccountWithdraw()
	{
		return accountWithdraw;
	}
	public final UUID getAccountDeposit()
	{
		return accountDeposit;
	}
	@Override
	public boolean equals(Object obj)
	{
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof Transaction)) {
			return false;
		}
		Transaction other = (Transaction) obj;
		return getId().equals(other.getId());
	}

	@Override
	public int hashCode()
	{
		return getId().hashCode();
	}
}
