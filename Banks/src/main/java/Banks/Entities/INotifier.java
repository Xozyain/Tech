package Banks.Entities;

import Banks.Models.Client;

public interface INotifier
{
	void Subscribe(Client client);
	void UnSubscribe(Client client);
	void Notify(IBankAccount account, String message);
}
