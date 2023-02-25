package Banks.Models;

import Banks.Entities.Subscriber;
import Banks.Exceptions.ClientException;

import java.util.ArrayList;

/**
 * The type Client.
 */
public class Client implements Subscriber {
    private final ArrayList<String> messages = new ArrayList<String>();
    /**
     * The Client name.
     */
    ClientName clientName;
    /**
     * The Passport number.
     */
    PassportNumber passportNumber;
    /**
     * The Address.
     */
    String address;

    private Client(ClientName clientName, PassportNumber passportNumber, String address) {
        this.clientName = clientName;
        this.passportNumber = passportNumber;
        this.address = address;
    }

    /**
     * Gets address.
     *
     * @return the address
     */
    public final String getAddress() {
        return address;
    }

    /**
     * Gets client name.
     *
     * @return the client name
     */
    public final ClientName getClientName() {
        return clientName;
    }

    /**
     * Gets passport.
     *
     * @return the passport
     */
    public final PassportNumber getPassport() {
        return passportNumber;
    }

    /**
     * Gets verified.
     *
     * @return the verified
     */
    public final boolean getVerified() {
        return getAddress() != null && getPassport() != null;
    }

    public final void receiveNotice(String message) {
        messages.add(message);
    }

    /**
     * The type Client builder.
     */
    public static class ClientBuilder {
        private ClientName clientName = null;
        private String address;
        private PassportNumber passport = null;

        /**
         * Set name client builder.
         *
         * @param clientName the client name
         * @return the client builder
         */
        public final ClientBuilder setName(ClientName clientName) {
            this.clientName = clientName;
            return this;
        }

        /**
         * Set address client builder.
         *
         * @param address the address
         * @return the client builder
         */
        public final ClientBuilder setAddress(String address) {
            this.address = address;
            return this;
        }

        /**
         * Set passport client builder.
         *
         * @param passport the passport
         * @return the client builder
         */
        public final ClientBuilder SetPassport(PassportNumber passport) {
            this.passport = passport;
            return this;
        }

        /**
         * Get client client.
         *
         * @return the client
         */
        public final Client getClient() {
            if (clientName == null) {
                throw ClientException.BuildWithoutName();
            }
            return new Client(clientName, passport, address);
        }
    }
}
