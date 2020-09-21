package AISearch;

import java.util.ArrayList;

public abstract class AbstractSearchNode
{
	private final AbstractSearchNode parentNode;
	private final Integer heuristicValue;
	private int nodeDepth;


	/**
	 * Constructs a new ASN. This constructor should be used when creating a root node.
	 */
	public AbstractSearchNode()
	{
		this.parentNode = null;
		this.heuristicValue = this.calculateHeuristic();
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
		this.heuristicValue = this.calculateHeuristic();
		this.nodeDepth = this.parentNode.getNodeDepth() + 1;
	}

	/**
	 * Calculates and returns the heuristic value of the distance in moves from the current node to the destination node.
	 * The value for the heuristic should be greater than or equal to the actual distance to the goal node
	 * but not less than that distance.
	 * A lower value for the heuristic will produce better performance.
	 *
	 * @return The heuristics value.
	 */
	public abstract Integer calculateHeuristic();

	public Integer getHeuristicValue()
	{
		return heuristicValue;
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
