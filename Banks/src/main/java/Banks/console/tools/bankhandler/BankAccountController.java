package Banks.console.tools.bankhandler;

import Banks.Models.AccountType;
import Banks.Models.Bank;
import Banks.Models.Client;
import Banks.Models.Money;
import Banks.console.chain.ChainLink;

import java.util.Scanner;

/**
 * The type Bank account controller.
 */
public class BankAccountController implements ChainLink {
    private Bank bank;
    private ChainLink nextChainLink;

    /**
     * Instantiates a new Bank account controller.
     *
     * @param bank the bank
     */
    public BankAccountController(Bank bank) {
        this.bank = bank;
        setNextChainLink(null);
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
        var chooseClientHandler = new ChooseClientController(getBank());
        chooseClientHandler.handle();
        Client client = null;
        try {
            if (chooseClientHandler.getClient() != null) client = chooseClientHandler.getClient();
            else throw new Exception("Client is null");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        System.out.println("Requests: debit, credit, deposit");
        String command = new Scanner(System.in).nextLine();

        switch (command) {
            case "deposit": {
                var balanceHandler = new BalanceController();
                balanceHandler.handle();
                Money balance;

                try {
                    if (balanceHandler.getAmount() != null) balance = balanceHandler.getAmount();
                    else throw new Exception("Balance is null");
                    getBank().CreateBankAccount(client, AccountType.Deposit, balance);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
                break;
            }

            case "debit": {
                getBank().CreateBankAccount(client, AccountType.Debit);
                break;
            }

            case "credit": {
                getBank().CreateBankAccount(client, AccountType.Credit);
                break;
            }
        }
    }
}
