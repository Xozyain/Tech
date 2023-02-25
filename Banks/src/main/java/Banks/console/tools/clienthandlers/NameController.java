package Banks.console.tools.clienthandlers;

import Banks.Models.Client;
import Banks.Models.ClientName;
import Banks.console.chain.ChainLink;

import java.util.Scanner;

/**
 * The type Name controller.
 */
public class NameController implements ChainLink {
    private Client.ClientBuilder builder;
    private ChainLink nextChainLink;

    /**
     * Instantiates a new Name controller.
     *
     * @param builder the builder
     */
    public NameController(Client.ClientBuilder builder) {
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
        System.out.println("Set client's name: ");
        String name = new Scanner(System.in).nextLine();
        if ((name == null || name.isBlank())) {
            handle();
        } else {
            builder.setName(new ClientName(name));
            if (getNextChainLink() != null) {
                getNextChainLink().handle();
            }
        }
    }
}
