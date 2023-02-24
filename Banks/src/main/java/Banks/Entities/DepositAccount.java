package Banks.Entities;

import Banks.Exceptions.CreditAccountException;
import Banks.Exceptions.DepositAccountException;
import Banks.Models.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

/**
 * The type Deposit account.
 */
public class DepositAccount implements BankAccount {
    private AccountType accountType;
    private BigDecimal percent = new BigDecimal(0);
    private Money verifiedLimit;
    private UUID id;
    private Balance balance;
    private Client client;
    private boolean verified;
    private Money gain;
    private ArrayList<Transaction> transactionHistory;
    private Money amount;
    private int dayCounter;

    /**
     * Instantiates a new Deposit account.
     *
     * @param id            the id
     * @param client        the client
     * @param percents      the percents
     * @param amount        the amount
     * @param verifiedLimit the verified limit
     */
    public DepositAccount(UUID id, Client client, Map<Money, BigDecimal> percents, Money amount, Money verifiedLimit) {
        this.id = id;
        this.client = client;
        this.percent = percents.get(amount);
        balance = new Balance();
        transactionHistory = new ArrayList<Transaction>();
        this.amount = amount;
        setDayCounter(0);
        setGain(new Money(new BigDecimal(0)));
        verified = client.getVerified();
        this.verifiedLimit = verifiedLimit;
        accountType = AccountType.Deposit;
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

    public Balance getBalance() {
        return balance;
    }

    public Client getClient() {
        return client;
    }

    public boolean getVerified() {
        return verified;
    }

    /**
     * Gets percent.
     *
     * @return the percent
     */
    public BigDecimal getPercent() {
        return percent;
    }

    private Money getGain() {
        return gain;
    }

    private void setGain(Money value) {
        gain = value;
    }

    private ArrayList<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    private Money getAmount() {
        return amount;
    }

    private int getDayCounter() {
        return dayCounter;
    }

    private void setDayCounter(int value) {
        dayCounter = value;
    }

    public Transaction findTransaction(UUID transactionId) {
        return getTransactionHistory().stream()
                .filter(x -> transactionId.equals(x.id()))
                .findFirst()
                .orElse(null);
    }

    public void transfer(BankAccount toAccount, Money amount) {
        if (!getVerified() && (amount.amount().compareTo(getVerifiedLimit().amount()) > 0)) {
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
            throw DepositAccountException.MaxedOutCreditLimit();
        }
        getBalance().withdraw(amount);
    }

    public void deposit(Money amount) {
        getBalance().deposit(amount);
    }

    public void balanceUpdate() {
        setDayCounter(getDayCounter() + 1);
        setGain(new Money(getGain().amount().add(getBalance().getAmount().multiply(getPercent()))));

        if (getDayCounter() % 30 == 0) {
            getBalance().deposit(getGain());
            setGain(new Money(new BigDecimal(0)));
        }
    }

    public void cancelTransaction(UUID transactionId) {
        Transaction transaction = getTransactionHistory().stream()
                .filter(x -> transactionId.equals(x.id()))
                .findFirst()
                .orElse(null);
        if (transaction == null) {
            throw DepositAccountException.CanNotFindTransaction();
        }
        getTransactionHistory().remove(transaction);
    }
}
