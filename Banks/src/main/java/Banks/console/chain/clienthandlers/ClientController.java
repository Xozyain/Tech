package Banks.console.chain.clienthandlers;//package banks.console.chain.clienthandlers;
//
//import banks.console.tools.clienthandlers.*;
//import banks.models.*;
//import banks.console.chain.*;
//import java.util.*;
//
//public class ClientController implements IChainLink
//{
//	public ClientController(Bank bank)
//	{
//		Bank = bank;
//	}
//
//	private Bank Bank;
//	public final Bank getBank()
//	{
//		return Bank;
//	}
////C# TO JAVA CONVERTER WARNING: Nullable reference types have no equivalent in Java:
////ORIGINAL LINE: public IChainLink? NextChainLink {get;set;}
//	private IChainLink NextChainLink;
//	public final IChainLink getNextChainLink()
//	{
//		return NextChainLink;
//	}
//	public final void setNextChainLink(IChainLink value)
//	{
//		NextChainLink = value;
//	}
//
//	public final void Handle()
//	{
//		System.out.println("Commands: createClient, exit");
////C# TO JAVA CONVERTER WARNING: Nullable reference types have no equivalent in Java:
////ORIGINAL LINE: string? command = System.Console.ReadLine();
//		String command = new Scanner(System.in).nextLine();
//		if (tangible.StringHelper.isNullOrEmpty(command) || Objects.equals(command, "exit"))
//		{
//			return;
//		}
//
//		switch (command)
//		{
//			case "createClient":
//			{
//				var builder = new Client.ClientBuilder();
//				var name = new NameController(builder);
//				var address = new AddressController(builder);
//				var passport = new PassportController(builder);
//
//				name.setNextChainLink(address);
//				address.setNextChainLink(address);
//				address.setNextChainLink(passport);
//
//				name.Handle();
//
//				Client client = builder.GetClient();
//				getBank().AddClient(client);
//				break;
//			}
//		}
//
//		if (getNextChainLink() != null)
//		{
//			getNextChainLink().Handle();
//		}
//		else
//		{
//			Handle();
//		}
//	}
//}
