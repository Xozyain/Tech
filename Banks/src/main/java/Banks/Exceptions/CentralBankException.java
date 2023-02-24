package Banks.Exceptions;

/**
 * The type Central bank exception.
 */
public class CentralBankException extends BaseException {
    private CentralBankException(String message) {
        super(message);
    }

    /**
     * Bank name duplicate central bank exception.
     *
     * @return the central bank exception
     */
    public static CentralBankException BankNameDuplicate() {
        return new CentralBankException("Bank with such name already exist");
    }

    /**
     * No such bank central bank exception.
     *
     * @return the central bank exception
     */
    public static CentralBankException NoSuchBank() {
        return new CentralBankException("No bank with this name");
    }
}
