import java.util.*;


public class BreadthFirstSearch extends SearchMethod
{
    public BreadthFirstSearch()
    {
        name = "BFS";
        FrontierNodes = new LinkedList<MazeState>();
        SearchedNodes = new ArrayList<MazeState>();
    }

    private MazeState popFrontier()
    {
        MazeState poppedMazeState = FrontierNodes.getFirst();
        FrontierNodes.removeFirst();
        SearchedNodes.add(poppedMazeState);
        return poppedMazeState;
    }



    public void Solve(MazeState startingMaze, ArrayList<MazeState> goalMazes)
    {
        FrontierNodes.add(startingMaze);

        GoalNodes = goalMazes;

        while(FrontierNodes.size() > 0)
        {
            MazeState currentNode = popFrontier();

            if(isSolved(currentNode))
            {
                currentNode.PrintDirections();
            }
            else
            {
                ArrayList<MazeState> frontierAdditions = currentNode.getPossibleMoveStates();
                AddToFrontier(frontierAdditions);
            }
        }
    }
}