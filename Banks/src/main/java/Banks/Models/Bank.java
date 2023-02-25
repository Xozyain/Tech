package Banks.Models;

import Banks.Entities.*;
import Banks.Exceptions.BankException;

import java.math.BigDecimal;
import java.util.*;

/**
 * The type Bank.
 */
public class Bank implements Notifier {
    private final CreditConfig creditConfig;
    private DebitConfig debitConfig;
    private DepositConfig depositConfig;
    private final ArrayList<BankAccount> accountList = new ArrayList<BankAccount>();
    private final ArrayList<Client> clientList = new ArrayList<Client>();
    private final ArrayList<Client> subscriberList = new ArrayList<Client>();

    private Money verifiedLimit;
    private final String name;

    /**
     * Instantiates a new Bank.
     *
     * @param name          the name
     * @param verifiedLimit the verified limit
     * @param creditConfig  the credit config
     * @param debitConfig   the debit config
     * @param depositConfig the deposit config
     */
    public Bank(String name, Money verifiedLimit, CreditConfig creditConfig, DebitConfig debitConfig, DepositConfig depositConfig) {
        this.name = name;
        setVerifiedLimit(verifiedLimit);
        this.creditConfig = creditConfig;
        this.debitConfig = debitConfig;
        this.depositConfig = depositConfig;
    }


    /**
     * Gets verified limit.
     *
     * @return the verified limit
     */
    public final Money getVerifiedLimit() {
        return verifiedLimit;
    }

    private void setVerifiedLimit(Money value) {
        verifiedLimit = value;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public final String getName() {
        return name;
    }

    /**
     * Equals boolean.
     *
     * @param other the other
     * @return the boolean
     */
    public final boolean equals(Bank other) {
        if (null == other) {
            return false;
        }
        if (this == other) {
            return true;
        }
        return Objects.equals(getName(), other.getName());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Bank other)) {
            return false;
        }

        return Objects.equals(getName(), other.getName());
    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }

    /**
     * Get account bank account.
     *
     * @param accountId the account id
     * @return the bank account
     */
    public final BankAccount getAccount(UUID accountId) {
        BankAccount account = accountList.stream()
                .filter(x -> x.getId() == accountId)
                .findFirst()
                .orElse(null);

        if (account == null) {
            throw BankException.CanNotFindAccount();
        }
        return account;
    }


    /**
     * Create bank account bank account.
     *
     * @param client      the client
     * @param accountType the account type
     * @return the bank account
     */
    public final BankAccount CreateBankAccount(Client client, AccountType accountType) {
        return CreateBankAccount(client, accountType, null);
    }

    /**
     * Create bank account bank account.
     *
     * @param client      the client
     * @param accountType the account type
     * @param amount      the amount
     * @return the bank account
     */
    public final BankAccount CreateBankAccount(Client client, AccountType accountType, Money amount) {
        switch (accountType) {
            case Debit:
                var debitAccount = new DebitAccount(UUID.randomUUID(), client, debitConfig.persent(), getVerifiedLimit());
                accountList.add(debitAccount);
                return debitAccount;
            case Credit:
                var creditAccount = new CreditAccount(UUID.randomUUID(), client, creditConfig.commission(), creditConfig.limit(), getVerifiedLimit());
                accountList.add(creditAccount);
                return creditAccount;
            case Deposit:
                if (amount == null) {
                    throw BankException.CanNotFindAccount();
                }
                var depositAccount = new DepositAccount(UUID.randomUUID(), client, depositConfig.percents(), amount, getVerifiedLimit());
                accountList.add(depositAccount);
                return depositAccount;
        }

        return new CreditAccount(UUID.randomUUID(), client, creditConfig.commission(), creditConfig.commission(), getVerifiedLimit());
    }

    /**
     * Cancel transaction.
     *
     * @param accountId   the account id
     * @param transaction the transaction
     */
    public final void CancelTransaction(UUID accountId, UUID transaction) {
        BankAccount account = accountList.stream().filter(x -> accountId.equals(x.getId()))
                .findFirst()
                .orElse(null);
        if (account == null) {
            throw BankException.CanNotFindAccount();
        }
        account.cancelTransaction(transaction);
    }

    /**
     * Change debit percent.
     *
     * @param accountType the account type
     * @param percent     the percent
     */
    public final void ChangeDebitPercent(AccountType accountType, BigDecimal percent) {
        debitConfig = new DebitConfig(percent);
        accountList.stream()
                .filter(account -> account.getAccountType().equals(AccountType.Debit))
                .forEach(account -> notify(account, "Debit percent has been changed"));
    }

    /**
     * Change deposit percent.
     *
     * @param accountType the account type
     * @param percents    the percents
     */
    public final void ChangeDepositPercent(AccountType accountType, Map<Money, BigDecimal> percents) {
        depositConfig = new DepositConfig(depositConfig.timeLimit(), percents, depositConfig.amount());
        accountList.stream()
                .filter(account -> account.getAccountType() == AccountType.Deposit)
                .forEach(account -> notify(account, "Deposit percent has been changed"));

    }

    /**
     * Change verified limit.
     *
     * @param amount the amount
     */
    public final void ChangeVerifiedLimit(Money amount) {
        setVerifiedLimit(amount);
    }

    public final void subscribe(Client client) {
        subscriberList.add(client);
    }

    public final void unSubscribe(Client client) {
        subscriberList.remove(client);
    }

    public final void notify(BankAccount account, String message) {
        account.getClient().receiveNotice("Deposit percent has been changed");
    }

    /**
     * Add client.
     *
     * @param client the client
     */
    public final void AddClient(Client client) {
        if (clientList.contains(client)) {
            throw BankException.ClientDuplicate();
        }
        clientList.add(client);
    }

    /**
     * Update balances.
     */
    public final void UpdateBalances() {
        accountList.forEach(x -> x.balanceUpdate());
    }

    /**
     * Gets client list.
     *
     * @return the client list
     */
    public final List<Client> getClientList() {
        return Collections.unmodifiableList(clientList);
    }

    /**
     * Get account list list.
     *
     * @return the list
     */
    public final List<BankAccount> GetAccountList() {
        return Collections.unmodifiableList(accountList);
    }
}
