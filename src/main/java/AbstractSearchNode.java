import java.util.ArrayList;

public abstract class AbstractSearchNode
{
	private AbstractSearchNode parentNode;


	public AbstractSearchNode()
	{
		this.parentNode = null;
	}

	public AbstractSearchNode(AbstractSearchNode parentNode)
	{
		this.parentNode = parentNode;
	}

	public abstract boolean inGoalState();

	/**
	 * This method is used to set the children of the current node. Any subclass should set the variable childNodes
	 * in their implementation of this method.
	 */
	public abstract ArrayList<AbstractSearchNode> generateChildNodes();

	public abstract boolean equalsNode(AbstractSearchNode node);
	// might replace this with a recommendation to override the default equals method

	public AbstractSearchNode getParentNode()
	{
		return parentNode;
	}
}
