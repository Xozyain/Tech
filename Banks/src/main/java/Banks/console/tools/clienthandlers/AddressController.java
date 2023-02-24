package Banks.console.tools.clienthandlers;

import Banks.Models.Client;
import Banks.console.chain.ChainLink;

import java.util.Scanner;

/**
 * The type Address controller.
 */
public class AddressController implements ChainLink {
    private Client.ClientBuilder builder;
    private ChainLink nextChainLink;

    /**
     * Instantiates a new Address controller.
     *
     * @param builder the builder
     */
    public AddressController(Client.ClientBuilder builder) {
        this.builder = builder;
        setNextChainLink(null);
    }


    public ChainLink getNextChainLink() {
        return nextChainLink;
    }
    public void setNextChainLink(ChainLink value) {
        nextChainLink = value;
    }

    public void handle() {
        System.out.println("Set address for client (optional): ");
        String name = new Scanner(System.in).nextLine();

        if (!(name == null || name.isBlank())) {
            builder.setAddress(name);
            if (getNextChainLink() != null) {
                getNextChainLink().handle();
            }
        } else {
            if (getNextChainLink() != null) {
                getNextChainLink().handle();
            }
        }
    }
}
