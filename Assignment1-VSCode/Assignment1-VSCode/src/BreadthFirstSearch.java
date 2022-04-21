import java.util.*;


public class BreadthFirstSearch extends SearchMethod
{
    public BreadthFirstSearch()
    {
        name = "BFS";
        FrontierNodes = new LinkedList<MazeState>();
        SearchedNodes = new ArrayList<MazeState>();
    }

    private MazeState popFirstFromFrontier()
    {
        MazeState poppedMazeState = FrontierNodes.getFirst();
        FrontierNodes.removeFirst();
        SearchedNodes.add(poppedMazeState);
        return poppedMazeState;
    }



    public Solution Solve(MazeState startingMaze, ArrayList<MazeState> goalMazes, String fileName)
    {
        FrontierNodes.add(startingMaze);
        this.fileName = fileName;
        GoalNodes = goalMazes;

        while(FrontierNodes.size() > 0)
        {
            MazeState currentNode = popFirstFromFrontier();

            if(isSolved(currentNode))
            {
                return new Solution(SearchedNodes.size(), currentNode.directionList);
            }
            else
            {
                ArrayList<MazeState> frontierAdditions = currentNode.getPossibleMoveStates();
                AddToFrontier(frontierAdditions);
            }
        }
        return null;
    }
}