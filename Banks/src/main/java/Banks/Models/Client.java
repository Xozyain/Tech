package Banks.Models;

import Banks.Entities.ISubscriber;
import Banks.Exceptions.ClientException;

import java.util.ArrayList;

public class Client implements ISubscriber
{
	private final ArrayList<String> _messages = new ArrayList<String>();
	ClientName clientName;
	PassportNumber passportNumber;
	String address;
	private Client(ClientName clientName, PassportNumber passportNumber, String address)
	{
		this.clientName = clientName;
		this.passportNumber = passportNumber;
		this.address = address;
	}

	public final String getAddress()
	{
		return address;
	}
	public final ClientName getClientName()
	{
		return clientName;
	}
	public final PassportNumber getPassport() { return passportNumber; }

	public final boolean getVerified()
	{
		if (getAddress() == null || getPassport() == null)
		{
			return false;
		}
		return true;
	}

	public final void ReceiveNotice(String message)
	{
		_messages.add(message);
	}

	public static class ClientBuilder
	{
		private ClientName _clientName = null;
		private String _address;
		private PassportNumber _passport = null;

		public final ClientBuilder SetName(ClientName clientName)
		{
			_clientName = clientName;
			return this;
		}

		public final ClientBuilder SetAddress(String address)
		{
			_address = address;
			return this;
		}

		public final ClientBuilder SetPassport(PassportNumber passport)
		{
			_passport = passport;
			return this;
		}

		public final Client GetClient()
		{
			if (_clientName == null)
			{
				throw ClientException.BuildWithoutName();
			}
			return new Client(_clientName, _passport, _address);
		}
	}
}
