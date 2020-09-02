import java.util.ArrayList;
import java.util.Collections;

public class AISearch
{
    /**
     * Applies depth first search on the node given to it.
     * Any nodes that satisfy the node equality function on nodes that have already been expanded are skipped.
     * @param originNode The root node of the depth first search.
     * @return The first node found that matches the goal state.
     * @throws Exception If no node is found that matches the goal state then an exception is thrown.
     */
    public static AbstractSearchNode applyDepthFirstSearch(AbstractSearchNode originNode) throws Exception
    {
        return applyDepthFirstSearch(originNode, true);
    }

    /**
     * Applies depth first search on the node given to it.
     * @param originNode The root node of the depth first search.
     * @param pruneExpandedNodes If this flag is set then then any nodes that satisfy the node equality function on nodes that have already been expanded are skipped.
     * @return The first node found that matches the goal state.
     * @throws Exception If no node is found that matches the goal state then an exception is thrown.
     */
    public static AbstractSearchNode applyDepthFirstSearch(AbstractSearchNode originNode, boolean pruneExpandedNodes) throws Exception
    {
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
                if (pruneExpandedNodes)
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
        throw new Exception("No solution found at any depth");
    }

    /**
     * Applies breadth first search on the node given to it.
     * Any nodes that satisfy the node equality function on nodes that have already been expanded are skipped.
     * @param originNode The root node of the breadth first search.
     * @return The first node found that matches the goal state.
     * @throws Exception If no node is found that matches the goal state an exception is thrown.
     */
    public static AbstractSearchNode applyBreadthFirstSearch(AbstractSearchNode originNode) throws Exception
    {
        return applyBreadthFirstSearch(originNode, true);
    }

    /**
     * Applies breadth first search on the node given to it.
     * @param originNode The root node of the breadth first search.
     * @param pruneExpandedNodes If this flag is set then then any nodes that satisfy the node equality function on nodes that have already been expanded are skipped.
     * @return The first node found that matches the goal state.
     * @throws Exception If no node is found that matches the goal state an exception is thrown.
     */
    public static AbstractSearchNode applyBreadthFirstSearch(AbstractSearchNode originNode, boolean pruneExpandedNodes) throws Exception
    {
        originNode.setNodeDepth(0);
        if (originNode.inGoalState())
        {
            return originNode;
        }
        ArrayList<AbstractSearchNode> fringe = new ArrayList<>();
        ArrayList<AbstractSearchNode> expandedNodes = new ArrayList<>();
        fringe.add(originNode);
        AbstractSearchNode currentNode;
        while(!fringe.isEmpty())
        {
            currentNode = fringe.get(0);
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
                if (pruneExpandedNodes)
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
        throw new Exception("No solution found at any depth");
    }
}
