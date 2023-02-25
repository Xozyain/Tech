package Banks.Models;

/**
 * The enum Account type.
 */
public enum AccountType {
    /**
     * deposit account
     */
    Deposit,

    /**
     * debit account
     */
    Debit,

    /**
     * credit account
     */
    Credit;

    /**
     * Gets value.
     *
     * @return the value
     */
    public int getValue() {
        return this.ordinal();
    }

    /**
     * For value account type.
     *
     * @param value the value
     * @return the account type
     */
    public static AccountType forValue(int value) {
        return values()[value];
    }
}
