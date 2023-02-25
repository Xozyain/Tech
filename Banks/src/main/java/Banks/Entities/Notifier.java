package Banks.Entities;

import Banks.Models.Client;

/**
 * The interface Notifier.
 */
public interface Notifier {
    /**
     * Subscribe.
     *
     * @param client the client
     */
    void subscribe(Client client);

    /**
     * Un subscribe.
     *
     * @param client the client
     */
    void unSubscribe(Client client);

    /**
     * Notify.
     *
     * @param account the account
     * @param message the message
     */
    void notify(BankAccount account, String message);
}
