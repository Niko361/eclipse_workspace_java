import java.util.*;


public class DepthFirstSearch extends SearchMethod
{
    public DepthFirstSearch()
    {
        name = "DFS";
        FrontierNodes = new LinkedList<MazeState>();
        SearchedNodes = new ArrayList<MazeState>();
    }

    public Solution Solve(MazeState startingMaze, ArrayList<MazeState> goalMazes)
    {
        FrontierNodes.add(startingMaze);    
        GoalNodes = goalMazes;

        while(FrontierNodes.size() > 0)
        {
            MazeState currentNode = popLastFromFrontier();

            if(isSolved(currentNode))
            {
                return new Solution(SearchedNodes.size(), currentNode.directionList);
            }
            else
            {
                ArrayList<MazeState> frontierAdditions = currentNode.getPossibleMoveStates();
                
                //Reverses the order of the frontier additions Arraylist in order to maintain the order of the moved specified in the assignment specifications 
                //(because DFS uses a LIFO queue instead of a FIFO one, move instructions are popped in the oposite order from which they are added to the frontier)
                Collections.reverse(frontierAdditions);
                AddToFrontier(frontierAdditions);
            }
        }
        return null;
    }
}