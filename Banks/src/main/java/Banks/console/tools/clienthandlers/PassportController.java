package Banks.console.tools.clienthandlers;

import Banks.Models.Client;
import Banks.Models.PassportNumber;
import Banks.console.chain.ChainLink;

import java.util.Scanner;

/**
 * The type Passport controller.
 */
public class PassportController implements ChainLink {
    private Client.ClientBuilder builder;
    private ChainLink nextChainLink;

    /**
     * Instantiates a new Passport controller.
     *
     * @param builder the builder
     */
    public PassportController(Client.ClientBuilder builder) {
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
        System.out.println("Set client's passport (optional): ");
        String passportString = new Scanner(System.in).nextLine();
        if (passportString != null && Validation(passportString)) {
            var passport = new PassportNumber(passportString);
            builder.SetPassport(passport);
            if (getNextChainLink() != null) {
                getNextChainLink().handle();
            }
        }
    }

    /**
     * Validation boolean.
     *
     * @param passportString the passport string
     * @return the boolean
     */
    public boolean Validation(String passportString) {
        if (passportString.length() != 10) {
            return false;
        }
        return true;
    }
}
