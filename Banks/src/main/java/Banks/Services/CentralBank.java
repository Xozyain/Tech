package Banks.Services;

import Banks.Entities.BankAccount;
import Banks.Exceptions.CentralBankException;
import Banks.Models.*;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

/**
 * The type Central bank.
 */
public class CentralBank implements ICentralBank {
    private final ArrayList<Bank> bankList = new ArrayList<Bank>();
    private final ArrayList<Client> clientList = new ArrayList<Client>();
    private final ArrayList<InterTransaction> interTransactionList = new ArrayList<InterTransaction>();

    public final void registerBank(String name, Money verifiedLimit, CreditConfig creditConfig, DebitConfig debitConfig, DepositConfig depositConfig) {
        var bank = new Bank(name, verifiedLimit, creditConfig, debitConfig, depositConfig);
        if (bankList.contains(bank)) {
            throw CentralBankException.BankNameDuplicate();
        }
        bankList.add(bank);
    }

    public final void transfer(String bankNameFrom, UUID accountFrom, String bankNameTo, UUID accountTo, Money amount) {
        Bank bankFrom = bankList.stream().filter(x -> x.getName() == bankNameFrom).findFirst().orElse(null);
        Bank bankTo = bankList.stream().filter(b -> b.getName().equals(bankNameTo)).findFirst().orElse(null);
        if (bankFrom == null || bankTo == null) {
            throw CentralBankException.NoSuchBank();
        }
        BankAccount bankAccountFrom = bankFrom.getAccount(accountFrom);
        BankAccount bankAccountTo = bankTo.getAccount(accountTo);
        bankAccountFrom.transfer(bankAccountTo, amount);
    }

    public final void notifyBanks() {
        bankList.forEach(x -> x.UpdateBalances());
    }

    /**
     * Has bank boolean.
     *
     * @param name the name
     * @return the boolean
     */
    public final boolean HasBank(String name) {
        Bank bank = bankList.stream().filter(x -> Objects.equals(x.getName(), name)).findFirst().orElse(null);
        return bank != null;
    }
}
