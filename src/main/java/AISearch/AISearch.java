package AISearch;

import java.util.ArrayList;
import java.util.Collections;

public class AISearch
{
	/**
	 * Applies depth first search on the node given to it.
	 * Any nodes that satisfy the node equality function on nodes that have already been expanded are skipped.
	 * Nodes will be expanded to a maximum depth of Integer.MAX_VALUE.
	 *
	 * @param originNode The root node of the depth first search.
	 * @return The first node found that matches the goal state.
	 * @throws GoalNodeNotFoundException If all nodes have been expanded to the depth limit and no goal node is found this exception will be thrown.
	 */
	public static AbstractSearchNode applyDepthFirstSearch(AbstractSearchNode originNode) throws GoalNodeNotFoundException
	{
		try
		{
			return applyDepthFirstSearch(originNode, true, Integer.MAX_VALUE);
		} catch (GoalNodeNotFoundException e)
		{
			throw new GoalNodeNotFoundException("No solution found at the maximum depth limit.");
		}
	}

	/**
	 * Applies depth first search on the node given to it.
	 * Nodes will be expanded to a maximum depth of Integer.MAX_VALUE.
	 *
	 * @param originNode         The root node of the depth first search.
	 * @param pruneExpandedNodes If this flag is set then then any nodes that satisfy the node equality function on nodes that have already been expanded are skipped.
	 * @return The first node found that matches the goal state.
	 * @throws GoalNodeNotFoundException If all nodes have been expanded to the depth limit and no goal node is found this exception will be thrown.
	 */
	public static AbstractSearchNode applyDepthFirstSearch(AbstractSearchNode originNode, boolean pruneExpandedNodes) throws GoalNodeNotFoundException
	{
		try
		{
			return applyDepthFirstSearch(originNode, pruneExpandedNodes, Integer.MAX_VALUE);
		} catch (GoalNodeNotFoundException e)
		{
			throw new GoalNodeNotFoundException("No solution found at the maximum depth limit.");
		}
	}

	/**
	 * Applies depth first search on the node given to it.
	 * Any nodes that satisfy the node equality function on nodes that have already been expanded are skipped.
	 *
	 * @param originNode The root node of the depth first search.
	 * @param depthLimit The depth of the deepest node that will be checked if it is in the goal state. Any nodes at this depth will not be added to the fringe.
	 * @return The first node found that matches the goal state.
	 * @throws GoalNodeNotFoundException If all nodes have been expanded to the depth limit and no goal node is found this exception will be thrown.
	 * @throws IllegalArgumentException  If the depth limit is too small this exception will be thrown.
	 */
	public static AbstractSearchNode applyDepthFirstSearch(AbstractSearchNode originNode, int depthLimit) throws GoalNodeNotFoundException, IllegalArgumentException
	{
		return applyDepthFirstSearch(originNode, true, depthLimit);
	}


	/**
	 * Applies depth first search on the node given to it.
	 *
	 * @param originNode         The root node of the depth first search.
	 * @param pruneExpandedNodes If this flag is set then then any nodes that satisfy the node equality function on
	 *                           nodes that have already been expanded are skipped.
	 * @param depthLimit         The depth of the deepest node that will be checked if it is in the goal state.
	 *                           Any nodes at this depth will not be added to the fringe.
	 * @return The first node found that matches the goal state.
	 * @throws GoalNodeNotFoundException If all nodes have been expanded to the depth limit and no goal node is found this exception will be thrown.
	 * @throws IllegalArgumentException  If the depth limit is too small this exception will be thrown.
	 */
	public static AbstractSearchNode applyDepthFirstSearch(AbstractSearchNode originNode, boolean pruneExpandedNodes, int depthLimit) throws GoalNodeNotFoundException, IllegalArgumentException
	{
		if (depthLimit < 1)
		{
			throw new IllegalArgumentException(String.format("A depth limit of %d is too low. Expected > 1.", depthLimit));
		}
		originNode.setNodeDepth(0);
		if (originNode.inGoalState())
		{
			return originNode;
		}
		ArrayList<AbstractSearchNode> fringe = new ArrayList<>();
		ArrayList<AbstractSearchNode> expandedNodes = new ArrayList<>();
		fringe.add(originNode);
		AbstractSearchNode currentNode;
		while (!fringe.isEmpty())
		{
			currentNode = fringe.remove(fringe.size() - 1);
			if (pruneExpandedNodes)
			{
				expandedNodes.add(currentNode);
			}
			ArrayList<AbstractSearchNode> newNodes = currentNode.generateChildNodes();
			Collections.shuffle(newNodes);
			for (AbstractSearchNode node : newNodes)
			{
				if (node.inGoalState())
				{
					return node;
				}
				boolean addToFringe = true;
				if (!(currentNode.getNodeDepth() < depthLimit - 1))
				{
					addToFringe = false;
				} else if (pruneExpandedNodes)
				{
					for (AbstractSearchNode exNode : expandedNodes)
					{
						if (node.equalsNode(exNode))
						{
							addToFringe = false;
							break;
						}
					}
				}
				if (addToFringe)
				{
					fringe.add(node);
				}
			}
		}
		throw new GoalNodeNotFoundException("No solution found up to a depth of: " + depthLimit);
	}

	/**
	 * Applies breadth first search on the node given to it.
	 * Any nodes that satisfy the node equality function on nodes that have already been expanded are skipped.
	 * Nodes will be expanded to a maximum depth of Integer.MAX_VALUE.
	 *
	 * @param originNode The root node of the breadth first search.
	 * @return The first node found that matches the goal state.
	 * @throws GoalNodeNotFoundException If all nodes have been expanded to the depth limit and no goal node is found this exception will be thrown.
	 */
	public static AbstractSearchNode applyBreadthFirstSearch(AbstractSearchNode originNode) throws GoalNodeNotFoundException
	{
		try
		{
			return applyBreadthFirstSearch(originNode, true, Integer.MAX_VALUE);
		} catch (GoalNodeNotFoundException e)
		{
			throw new GoalNodeNotFoundException("No solution found at the maximum depth limit.");
		}
	}

	/**
	 * Applies breadth first search on the node given to it.
	 * Nodes will be expanded to a maximum depth of Integer.MAX_VALUE.
	 *
	 * @param originNode         The root node of the breadth first search.
	 * @param pruneExpandedNodes If this flag is set then then any nodes that satisfy the node equality function on
	 *                           nodes that have already been expanded are skipped.
	 * @return The first node found that matches the goal state.
	 * @throws GoalNodeNotFoundException If all nodes have been expanded to the depth limit and no goal node is found this exception will be thrown.
	 */
	public static AbstractSearchNode applyBreadthFirstSearch(AbstractSearchNode originNode, boolean pruneExpandedNodes) throws GoalNodeNotFoundException
	{
		try
		{
			return applyBreadthFirstSearch(originNode, pruneExpandedNodes, Integer.MAX_VALUE);
		} catch (GoalNodeNotFoundException e)
		{
			throw new GoalNodeNotFoundException("No solution found at the maximum depth limit.");
		}
	}

	/**
	 * Applies breadth first search on the node given to it.
	 * Any nodes that satisfy the node equality function on nodes that have already been expanded are skipped.
	 *
	 * @param originNode The root node of the breadth first search.
	 * @param depthLimit The depth of the deepest node that will be checked if it is in the goal state.
	 *                   Any nodes at this depth will not be added to the fringe.
	 * @return The first node found that matches the goal state.
	 * @throws GoalNodeNotFoundException If all nodes have been expanded to the depth limit and no goal node is found this exception will be thrown.
	 * @throws IllegalArgumentException  If the depth limit is too small this exception will be thrown.
	 */
	public static AbstractSearchNode applyBreadthFirstSearch(AbstractSearchNode originNode, int depthLimit) throws GoalNodeNotFoundException, IllegalArgumentException
	{
		return applyBreadthFirstSearch(originNode, true, depthLimit);
	}

	/**
	 * Applies breadth first search on the node given to it.
	 *
	 * @param originNode         The root node of the breadth first search.
	 * @param pruneExpandedNodes If this flag is set then then any nodes that satisfy the node equality function on
	 *                           nodes that have already been expanded are skipped.
	 * @param depthLimit         The depth of the deepest node that will be checked if it is in the goal state.
	 *                           Any nodes at this depth will not be added to the fringe.
	 * @return The first node found that matches the goal state.
	 * @throws GoalNodeNotFoundException If all nodes have been expanded to the depth limit and no goal node is found this exception will be thrown.
	 * @throws IllegalArgumentException  If the depth limit is too small this exception will be thrown.
	 */
	public static AbstractSearchNode applyBreadthFirstSearch(AbstractSearchNode originNode, boolean pruneExpandedNodes, int depthLimit) throws GoalNodeNotFoundException, IllegalArgumentException
	{
		if (depthLimit < 1)
		{
			throw new IllegalArgumentException(String.format("A depth limit of %d is too low. Expected > 1.", depthLimit));
		}
		originNode.setNodeDepth(0);
		if (originNode.inGoalState())
		{
			return originNode;
		}
		ArrayList<AbstractSearchNode> fringe = new ArrayList<>();
		ArrayList<AbstractSearchNode> expandedNodes = new ArrayList<>();
		fringe.add(originNode);
		AbstractSearchNode currentNode;
		while (!fringe.isEmpty())
		{
			currentNode = fringe.remove(0);
			if (pruneExpandedNodes)
			{
				expandedNodes.add(currentNode);
			}
			ArrayList<AbstractSearchNode> newNodes = currentNode.generateChildNodes();
			for (AbstractSearchNode node : newNodes)
			{
				if (node.inGoalState())
				{
					return node;
				}
				boolean addToFringe = true;
				if (!(currentNode.getNodeDepth() < depthLimit - 1))
				{
					addToFringe = false;
				} else if (pruneExpandedNodes)
				{
					for (AbstractSearchNode exNode : expandedNodes)
					{
						if (node.equalsNode(exNode))
						{
							addToFringe = false;
							break;
						}
					}
				}
				if (addToFringe)
				{
					fringe.add(node);
				}
			}
		}
		throw new GoalNodeNotFoundException("No solution found up to a depth of: " + depthLimit);
	}
}

class GoalNodeNotFoundException extends Exception
{
	public GoalNodeNotFoundException()
	{
		super("There was no goal node found.");
	}

	public GoalNodeNotFoundException(String message)
	{
		super(message);
	}
}