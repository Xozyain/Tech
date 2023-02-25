package Banks.Entities;

import Banks.Models.*;

import java.util.UUID;

/**
 * The interface Bank account.
 */
public interface BankAccount {
    /**
     * Gets id.
     *
     * @return the id
     */
    UUID getId();

    /**
     * Gets balance.
     *
     * @return the balance
     */
    Balance getBalance();

    /**
     * Gets client.
     *
     * @return the client
     */
    Client getClient();

    /**
     * Gets account type.
     *
     * @return the account type
     */
    AccountType getAccountType();

    /**
     * Gets verified.
     *
     * @return the verified
     */
    boolean getVerified();

    /**
     * Find transaction transaction.
     *
     * @param transactionId the transaction id
     * @return the transaction
     */
    Transaction findTransaction(UUID transactionId);

    /**
     * Cancel transaction.
     *
     * @param transactionId the transaction id
     */
    void cancelTransaction(UUID transactionId);

    /**
     * Transfer.
     *
     * @param toAccount the to account
     * @param amount    the amount
     */
    void transfer(BankAccount toAccount, Money amount);

    /**
     * Gets transfer.
     *
     * @param fromAccountId the from account id
     * @param amount        the amount
     */
    void getTransfer(UUID fromAccountId, Money amount);

    /**
     * With draw.
     *
     * @param amount the amount
     */
    void withDraw(Money amount);

    /**
     * Deposit.
     *
     * @param amount the amount
     */
    void deposit(Money amount);

    /**
     * Balance update.
     */
    void balanceUpdate();
}
