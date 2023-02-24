package Banks.Exceptions;

/**
 * The type Bank exception.
 */
public class BankException extends BaseException {
    private BankException(String message) {
        super(message);
    }

    /**
     * Can not find account bank exception.
     *
     * @return the bank exception
     */
    public static BankException CanNotFindAccount() {
        return new BankException("Can not find account");
    }

    /**
     * Deposit account null money bank exception.
     *
     * @return the bank exception
     */
    public static BankException DepositAccountNullMoney() {
        return new BankException("Can not create deposit account without money");
    }

    /**
     * Client duplicate bank exception.
     *
     * @return the bank exception
     */
    public static BankException ClientDuplicate() {
        return new BankException("Client already exist");
    }
}
