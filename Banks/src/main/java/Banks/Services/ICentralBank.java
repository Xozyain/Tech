package Banks.Services;

import Banks.Models.CreditConfig;
import Banks.Models.DebitConfig;
import Banks.Models.DepositConfig;
import Banks.Models.Money;

import java.util.UUID;

/**
 * The interface Central bank.
 */
public interface ICentralBank {
    /**
     * Register bank.
     *
     * @param name          the name
     * @param verifiedLimit the verified limit
     * @param creditConfig  the credit config
     * @param debitConfig   the debit config
     * @param depositConfig the deposit config
     */
    void registerBank(String name, Money verifiedLimit, CreditConfig creditConfig, DebitConfig debitConfig, DepositConfig depositConfig);

    /**
     * Transfer.
     *
     * @param bankNameFrom the bank name from
     * @param accountFrom  the account from
     * @param bankNameTo   the bank name to
     * @param accountTo    the account to
     * @param amount       the amount
     */
    void transfer(String bankNameFrom, UUID accountFrom, String bankNameTo, UUID accountTo, Money amount);

    /**
     * Notify banks.
     */
    void notifyBanks();
}
