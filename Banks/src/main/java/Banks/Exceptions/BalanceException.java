package Banks.Exceptions;

/**
 * The type Balance exception.
 */
public class BalanceException extends BaseException {
    private BalanceException(String message) {
        super(message);
    }

    /**
     * Not enough money balance exception.
     *
     * @return the balance exception
     */
    public static BalanceException NotEnoughMoney() {
        return new BalanceException("Not enough money on balance");
    }
}
