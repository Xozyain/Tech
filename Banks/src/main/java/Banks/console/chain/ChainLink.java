package Banks.console.chain;

/**
 * The interface Chain link.
 */
public interface ChainLink {
    /**
     * Gets next chain link.
     *
     * @return the next chain link
     */
    ChainLink getNextChainLink();

    /**
     * Sets next chain link.
     *
     * @param value the value
     */
    void setNextChainLink(ChainLink value);

    /**
     * Handle.
     */
    void handle();
}
