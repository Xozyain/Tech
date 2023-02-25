package Banks.console.tools.bankhandler;

import Banks.Models.Money;
import Banks.console.chain.ChainLink;
import java.math.BigDecimal;
import java.util.Scanner;

/**
 * The type Balance controller.
 */
public class BalanceController implements ChainLink {
    private ChainLink nextChainLink;
    private Money amount = null;

    public ChainLink getNextChainLink() {
        return nextChainLink;
    }

    public void setNextChainLink(ChainLink value) {
        nextChainLink = value;
    }

    /**
     * Gets amount.
     *
     * @return the amount
     */
    public Money getAmount() {
        return amount;
    }

    private void setAmount(Money value) {
        amount = value;
    }

    public void handle() {
        System.out.println("Write balance: ");
        String command = new Scanner(System.in).nextLine();

        try {
            int balance = Integer.parseInt(command);
            amount = new Money(new BigDecimal(balance));
        } catch (NumberFormatException e) {
            System.out.println("Invalid input: " + command + " is not a valid integer.");
            handle();
        }
    }
}
