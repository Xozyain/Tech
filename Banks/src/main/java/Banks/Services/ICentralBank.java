package Banks.Services;

import Banks.Models.CreditConfig;
import Banks.Models.DebitConfig;
import Banks.Models.DepositConfig;
import Banks.Models.Money;

import java.util.UUID;

public interface ICentralBank
{
	void RegisterBank(String name, Money verifiedLimit, CreditConfig creditConfig, DebitConfig debitConfig, DepositConfig depositConfig);

	void Transfer(String bankNameFrom, UUID accountFrom, String bankNameTo, UUID accountTo, Money amount);
	void NotifyBanks();
}
