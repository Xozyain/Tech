package Banks.Entities;

import Banks.Exceptions.CreditAccountException;
import Banks.Models.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.UUID;

/**
 * The type Credit account.
 */
public class CreditAccount implements BankAccount {
    private AccountType accountType;
    private Money verifiedLimit;
    private UUID id;
    private Balance balance;
    private Client client;
    private boolean verified;
    private Money commission;
    private Money creditLimit;
    private int dayCounter;
    private ArrayList<Transaction> transactionHistory;

    /**
     * Instantiates a new Credit account.
     *
     * @param id            the id
     * @param client        the client
     * @param commission    the commission
     * @param creditLimit   the credit limit
     * @param verifiedLimit the verified limit
     */
    public CreditAccount(UUID id, Client client, Money commission, Money creditLimit, Money verifiedLimit) {
        setDayCounter(0);
        balance = new Balance();
        transactionHistory = new ArrayList<Transaction>();
        this.commission = commission;
        this.creditLimit = creditLimit;
        this.id = id;
        this.client = client;
        this.dayCounter = 0;
        verified = client.getVerified();
        this.verifiedLimit = verifiedLimit;
        accountType = AccountType.Credit;
    }


    public AccountType getAccountType() {
        return accountType;
    }

    /**
     * Gets verified limit.
     *
     * @return the verified limit
     */
    public Money getVerifiedLimit() {
        return verifiedLimit;
    }

    public UUID getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param value the value
     */
    public void setId(UUID value) {
        id = value;
    }

    public Balance getBalance() {
        return balance;
    }

    public Client getClient() {
        return client;
    }

    /**
     * Sets client.
     *
     * @param value the value
     */
    public void setClient(Client value) {
        client = value;
    }

    public boolean getVerified() {
        return verified;
    }

    /**
     * Gets commission.
     *
     * @return the commission
     */
    public Money getCommission() {
        return commission;
    }

    /**
     * Gets credit limit.
     *
     * @return the credit limit
     */
    public Money getCreditLimit() {
        return creditLimit;
    }

    private int getDayCounter() {
        return dayCounter;
    }

    private void setDayCounter(int value) {
        dayCounter = value;
    }

    private ArrayList<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    public Transaction findTransaction(UUID transactionId) {
        return getTransactionHistory().stream()
                .filter(x -> transactionId.equals(x.id()))
                .findFirst().orElse(null);
    }

    public void transfer(BankAccount toAccount, Money amount) {
        if (!getVerified() && (amount.amount().compareTo(getVerifiedLimit().amount()) < 0)) {
            throw CreditAccountException.MaxedOutCreditLimit();
        }
        if (getBalance().withdraw(amount).getAmount().compareTo(new BigDecimal(0)) < 0) {
            getBalance().deposit(amount);
            throw CreditAccountException.CreditLimitExceeded();
        }

        getBalance().withdraw(amount);
        toAccount.deposit(amount);
        getTransactionHistory().add(new Transaction(UUID.randomUUID(), getId(), toAccount.getId(), amount));
        toAccount.getTransfer(getId(), amount);
    }

    public void getTransfer(UUID fromAccountId, Money amount) {
        getTransactionHistory().add(new Transaction(UUID.randomUUID(), fromAccountId, getId(), amount));
    }

    public void withDraw(Money amount) {
        if (!getVerified() && (amount.amount().compareTo(getVerifiedLimit().amount()) > 0)) {
            throw CreditAccountException.CreditLimitExceeded();
        }
        getBalance().withdraw(amount);
    }

    public void deposit(Money amount) {
        getBalance().deposit(amount);
    }

    public void balanceUpdate() {
        setDayCounter(getDayCounter() + 1);
        if (getDayCounter() % 30 == 0) {
            getBalance().withdraw(getCommission());
        }
    }

    public void cancelTransaction(UUID transactionId) {
        Transaction transaction = transactionHistory.stream()
                .filter(x -> x.id().equals(transactionId))
                .findFirst()
                .orElse(null);
        if (transaction == null) {
            throw CreditAccountException.CanNotFindTransaction();
        }
        getTransactionHistory().remove(transaction);
    }
}
