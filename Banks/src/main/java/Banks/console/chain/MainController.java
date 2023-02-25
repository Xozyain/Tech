package Banks.console.chain;

import Banks.Entities.BankAccount;
import Banks.Models.Bank;
import Banks.Models.Money;
import Banks.console.tools.bankhandler.BalanceController;
import Banks.console.tools.bankhandler.BankAccountController;
import Banks.console.tools.bankhandler.ChooseAccountController;
import Banks.console.tools.clienthandlers.ClientController;

import java.util.Objects;
import java.util.Scanner;

/**
 * The type Main controller.
 */
public class MainController implements ChainLink {
    private Bank bank;
    private ChainLink nextChainLink;

    /**
     * Instantiates a new Main controller.
     *
     * @param bank the bank
     */
    public MainController(Bank bank) {
        this.bank = bank;
    }

    /**
     * Gets bank.
     *
     * @return the bank
     */
    public Bank getBank() {
        return bank;
    }

    public ChainLink getNextChainLink() {
        return nextChainLink;
    }

    public void setNextChainLink(ChainLink value) {
        nextChainLink = value;
    }

    public void handle() {
        System.out.println("Requests: client, addBankAccount, makeTransaction");
        String command = new Scanner(System.in).nextLine();
        switch (command) {
            case "client": {
                var clientController = new ClientController(getBank());
                clientController.handle();
                break;
            }

            case "addBankAccount": {
                var accountController = new BankAccountController(getBank());
                accountController.handle();
                break;
            }

            case "makeTransaction": {
                System.out.println("Requests: transfer, withdraw, deposit");

                String requests = new Scanner(System.in).nextLine();
                switch (requests) {
                    case "transfer": {
                        var selectedFrom = new ChooseAccountController(getBank());
                        selectedFrom.handle();

                        var selectedTo = new ChooseAccountController(getBank());
                        selectedTo.handle();

                        BankAccount accountFrom = Objects.requireNonNull(selectedFrom.getBankAccount(), "Bank account cannot be null");
                        BankAccount accountTo = Objects.requireNonNull(selectedTo.getBankAccount(), "Bank account cannot be null");

                        var balanceController = new BalanceController();
                        balanceController.handle();

                        Money amount = Objects.requireNonNull(balanceController.getAmount(), "Amount cannot be null");
                        accountFrom.transfer(accountTo, amount);
                        break;
                    }

                    case "withdraw": {
                        var selectedAccount = new ChooseAccountController(getBank());
                        selectedAccount.handle();

                        BankAccount account = Objects.requireNonNull(selectedAccount.getBankAccount(), "Bank account cannot be null");
                        var moneyController = new BalanceController();
                        moneyController.handle();

                        Money amount = Objects.requireNonNull(moneyController.getAmount(), "Amount cannot be null");
                        account.withDraw(amount);
                        break;
                    }

                    case "deposit": {
                        var selectedAccount = new ChooseAccountController(getBank());
                        selectedAccount.handle();

                        BankAccount account = Objects.requireNonNull(selectedAccount.getBankAccount(), "Bank account cannot be null");
                        var moneyController = new BalanceController();
                        moneyController.handle();

                        Money amount = Objects.requireNonNull(moneyController.getAmount(), "Amount cannot be null");
                        account.deposit(amount);
                        break;
                    }
                }

                break;
            }
        }

        if (getNextChainLink() != null) {
            getNextChainLink().handle();
        } else {
            handle();
        }
    }
}
