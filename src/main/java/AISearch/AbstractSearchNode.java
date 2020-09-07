package AISearch;

import java.util.ArrayList;

public abstract class AbstractSearchNode
{
	private final AbstractSearchNode parentNode;
	private int nodeDepth;


	/**
	 * Constructs a new ASN. This constructor should be used when creating a root node.
	 */
	public AbstractSearchNode()
	{
		this.parentNode = null;
		nodeDepth = 0;
	}

	/**
	 * Constructs a new ASN. This constructor should be used when creating a child node.
	 *
	 * @param parentNode The parent of the new node.
	 */
	public AbstractSearchNode(AbstractSearchNode parentNode)
	{
		this.parentNode = parentNode;
		this.nodeDepth = this.parentNode.getNodeDepth() + 1;
	}

	public int getNodeDepth()
	{
		return nodeDepth;
	}

	public void setNodeDepth(int nodeDepth)
	{
		this.nodeDepth = nodeDepth;
	}

	public abstract boolean inGoalState();

	/**
	 * This method is used to set the children of the current node.
	 *
	 * @return The child nodes of the current node.
	 */
	public abstract ArrayList<AbstractSearchNode> generateChildNodes();

	public abstract boolean equalsNode(AbstractSearchNode node);

	public AbstractSearchNode getParentNode()
	{
		return parentNode;
	}
}
