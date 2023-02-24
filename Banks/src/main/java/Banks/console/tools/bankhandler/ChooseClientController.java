package Banks.console.tools.bankhandler;

import Banks.Models.Bank;
import Banks.Models.Client;
import Banks.console.chain.ChainLink;

import java.util.Scanner;

/**
 * The type Choose client controller.
 */
public class ChooseClientController implements ChainLink {
    private Bank bank;
    private Client client;
    private ChainLink nextChainLink;

    /**
     * Instantiates a new Choose client controller.
     *
     * @param bank the bank
     */
    public ChooseClientController(Bank bank) {
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

    /**
     * Gets client.
     *
     * @return the client
     */
    public Client getClient() {
        return client;
    }

    private void setClient(Client value) {
        client = value;
    }
    public ChainLink getNextChainLink() {
        return nextChainLink;
    }
    public void setNextChainLink(ChainLink value) {
        nextChainLink = value;
    }
    public void handle() {
        System.out.println("Choose client by number:");
        var clients = getBank().getClientList();

        for (int i = 0; i < clients.size(); i++) {
            System.out.printf("%1$s) %2$s%n", i, clients.get(i).getClientName());
        }

        String command = new Scanner(System.in).nextLine();

        try {
            int num = Integer.parseInt(command);
            setClient(clients.get(num));
            return;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input: " + command + " is not a valid integer.");
            handle();
        }

        if (getNextChainLink() != null) {
            getNextChainLink().handle();
        } else {
            handle();
        }
    }
}
