package Banks.console.tools.clienthandlers;

import Banks.Models.Bank;
import Banks.Models.Client;
import Banks.console.chain.IChainLink;

import java.util.Scanner;

public class ClientController implements IChainLink {

    public Bank bank;
    public IChainLink nextChainLink;
    public ClientController(Bank bank)
    {
        this.bank = bank;
    }

    public Bank getBank() {
        return bank;
    }

    @Override
    public IChainLink getNextChainLink() {
        return nextChainLink;
    }

    /**
     * @param value
     */
    @Override
    public void setNextChainLink(IChainLink value) {
        nextChainLink = value;
    }

    public void Handle()
    {
        System.out.println("Commands: createClient, exit");
        String command = new Scanner(System.in).nextLine();
        if (command.isEmpty()|| command.equals("exit"))
        {
            return;
        }

        switch (command)
        {
            case "createClient":
            {
                var builder = new Client.ClientBuilder();
                var name = new NameController(builder);
                var address = new AddressController(builder);
                var passport = new PassportController(builder);

                name.setNextChainLink(address);
                address.setNextChainLink(address);
                address.setNextChainLink(passport);

                name.Handle();

                Client client = builder.GetClient();
                bank.AddClient(client);
                break;
            }
        }

        if (nextChainLink != null)
            nextChainLink.Handle();
        else
            Handle();
    }
}
