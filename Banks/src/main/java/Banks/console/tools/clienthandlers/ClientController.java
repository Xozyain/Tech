package Banks.console.tools.clienthandlers;

import Banks.Models.Bank;
import Banks.Models.Client;
import Banks.console.chain.ChainLink;

import java.util.Scanner;

/**
 * The type Client controller.
 */
public class ClientController implements ChainLink {

    /**
     * The Bank.
     */
    public Bank bank;
    /**
     * The Next chain link.
     */
    public ChainLink nextChainLink;

    /**
     * Instantiates a new Client controller.
     *
     * @param bank the bank
     */
    public ClientController(Bank bank) {
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

    @Override
    public ChainLink getNextChainLink() {
        return nextChainLink;
    }

    /**
     * @param value
     */
    @Override
    public void setNextChainLink(ChainLink value) {
        nextChainLink = value;
    }

    public void handle() {
        System.out.println("Commands: createClient, exit");
        String command = new Scanner(System.in).nextLine();
        if (command.isEmpty() || command.equals("exit")) {
            return;
        }

        switch (command) {
            case "createClient": {
                var builder = new Client.ClientBuilder();
                var name = new NameController(builder);
                var address = new AddressController(builder);
                var passport = new PassportController(builder);

                name.setNextChainLink(address);
                address.setNextChainLink(address);
                address.setNextChainLink(passport);

                name.handle();

                Client client = builder.getClient();
                bank.AddClient(client);
                break;
            }
        }

        if (nextChainLink != null)
            nextChainLink.handle();
        else
            handle();
    }
}
