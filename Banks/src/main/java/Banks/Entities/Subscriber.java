package Banks.Entities;

/**
 * The interface Subscriber.
 */
public interface Subscriber {
    /**
     * Receive notice.
     *
     * @param message the message
     */
    void receiveNotice(String message);
}
