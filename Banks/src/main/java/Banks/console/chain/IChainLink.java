package Banks.console.chain;

public interface IChainLink
{
	IChainLink getNextChainLink();
	void setNextChainLink(IChainLink value);

	void Handle();
}
