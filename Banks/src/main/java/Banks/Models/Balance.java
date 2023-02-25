package Banks.Models;

import java.math.BigDecimal;

/**
 * The type Balance.
 */
public class Balance {
    private BigDecimal money = new BigDecimal(0);

    /**
     * Gets amount.
     *
     * @return the amount
     */
    public BigDecimal getAmount() {
        return money;
    }

    /**
     * With draw balance.
     *
     * @param money the money
     * @return the balance
     */
    public Balance withdraw(Money money) {
        this.money = this.money.subtract(money.amount());
        return this;
    }

    /**
     * Deposit balance.
     *
     * @param money the money
     * @return the balance
     */
    public Balance deposit(Money money) {
        this.money = this.money.add(money.amount());
        return this;
    }
}
