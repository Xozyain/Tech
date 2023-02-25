package Banks.test;//package banks.test;
//
//import banks.models.*;
//import banks.services.*;
//import Xunit.*;
//import java.util.*;
//import java.math.*;
//
//public class BankTest
//{
//	public final void RegisterBank_CentralBankHasBank()
//	{
//		var creditConfig = new CreditConfig(new Money(500), new Money(10));
//		var debitConfig = new DebitConfig(3);
//		var percents = new HashMap<Money, BigDecimal>(Map.ofEntries(Map.entry(new Money(50), 4)));
//		var depositConfig = new DepositConfig(10, percents, new Money(50));
//
//		var bank = new Bank("MYbank", new Money(100), creditConfig, debitConfig, depositConfig);
//
//		var centBank = new CentralBank();
//		centBank.RegisterBank("MYbank", new Money(100), creditConfig, debitConfig, depositConfig);
//		Assert.True(centBank.HasBank("MYbank"));
//	}
//}
