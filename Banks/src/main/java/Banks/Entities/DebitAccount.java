package Banks.Entities;

import Banks.Exceptions.CreditAccountException;
import Banks.Exceptions.DebitAccountException;
import Banks.Exceptions.DebitConfigException;
import Banks.Models.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.UUID;

/**
 * The type Debit account.
 */
public class DebitAccount implements BankAccount {
    private AccountType accountType;
    private Money verifiedLimit;
    private BigDecimal percent;
    private UUID id;
    private Balance balance;
    private Client client;
    private boolean verified;
    private int dayCounter;
    private Money gain;
    private ArrayList<Transaction> transactionHistory;

    /**
     * Instantiates a new Debit account.
     *
     * @param id            the id
     * @param client        the client
     * @param percent       the percent
     * @param verifiedLimit the verified limit
     */
    public DebitAccount(UUID id, Client client, BigDecimal percent, Money verifiedLimit) {
        this.id = id;
        balance = new Balance();
        this.client = client;
        transactionHistory = new ArrayList<Transaction>();
        if (percent.compareTo(new BigDecimal(0)) < 0) {
            throw DebitConfigException.NegativePercent();
        }
        this.percent = percent;
        setDayCounter(0);
        setGain(new Money(new BigDecimal(0)));
        verified = client.getVerified();
        this.verifiedLimit = verifiedLimit;
        accountType = AccountType.Debit;
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

    /**
     * Gets percent.
     *
     * @return the percent
     */
    public BigDecimal getPercent() {
        return percent;
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

    private int getDayCounter() {
        return dayCounter;
    }

    private void setDayCounter(int value) {
        dayCounter = value;
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

    public Transaction findTransaction(UUID transactionId) {
        return getTransactionHistory().stream()
                .filter(x -> transactionId.equals(x.id()))
                .findFirst()
                .orElse(null);
    }

    public void transfer(BankAccount toAccount, Money amount) {
        if (!getVerified() && (amount.amount().compareTo(getVerifiedLimit().amount()) > 0)) {
            throw DebitAccountException.MaxedOutCreditLimit();
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
        setGain(new Money(getGain().amount().add(getBalance().getAmount().multiply(getPercent()))));

        if (getDayCounter() % 30 == 0) {
            getBalance().deposit(getGain());
            setGain(new Money(new BigDecimal(0)));
        }
    }

    public void cancelTransaction(UUID transactionId) {
        Transaction transaction = transactionHistory.stream()
                .filter(x -> x.id().equals(transactionId))
                .findFirst()
                .orElse(null);
        if (transaction == null) {
            throw DebitAccountException.CanNotFindTransaction();
        }
        getTransactionHistory().remove(transaction);
    }
}
