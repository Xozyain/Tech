package Banks.Models;

import java.math.BigDecimal;

public class Balance
{
	private BigDecimal money = new BigDecimal(0);

	public final BigDecimal getAmount()
	{
		return money;
	}
	public final Balance WithDraw(Money money)
	{
		this.money = this.money.subtract(money.amount());
		return this;
	}

	public final Balance Deposit(Money money)
	{
		this.money = this.money.add(money.amount());
		return this;
	}
}
