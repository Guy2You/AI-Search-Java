import java.util.ArrayList;
import java.util.Collections;

public class AISearch
{

    private static AbstractSearchNode startNode;
    private static int solutionDepth = -1;

    public static AbstractSearchNode applyDepthFirstSearch(AbstractSearchNode originNode) throws Exception
    {
        return applyDepthFirstSearch(originNode, false);
    }


    public static AbstractSearchNode applyDepthFirstSearch(AbstractSearchNode originNode, boolean pruneExpandedNodes) throws Exception
    {
        startNode = originNode;
        solutionDepth = 0;
        if (startNode.inGoalState())
        {
            return startNode;
        }
        ArrayList<AbstractSearchNode> fringe = new ArrayList<>();
        ArrayList<AbstractSearchNode> nodesExpanded = new ArrayList<>();
        fringe.add(startNode);
        AbstractSearchNode currentNode;
        while (!fringe.isEmpty())
        {
            currentNode = fringe.remove(fringe.size() - 1);
            solutionDepth++;
            if (pruneExpandedNodes)
            {
                nodesExpanded.add(currentNode);
            }
            ArrayList<AbstractSearchNode> newNodes = currentNode.generateChildNodes();
            Collections.shuffle(newNodes);
            for (AbstractSearchNode node : newNodes)
            {
                if (node.inGoalState())
                {
                    solutionDepth++;
                    return node;
                }
                boolean addToFringe = true;
                if (pruneExpandedNodes)
                {
                    for (AbstractSearchNode exNode : nodesExpanded)
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

    public static AbstractSearchNode applyBreadthFirstSearch(AbstractSearchNode originNode) //TODO
    {
        startNode = originNode;
        if (startNode.inGoalState())
        {
            solutionDepth = 0;
            return startNode;
        }
        return originNode;
    }
}
