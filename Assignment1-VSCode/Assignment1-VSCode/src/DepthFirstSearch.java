import java.util.*;


public class DepthFirstSearch extends SearchMethod
{
    public DepthFirstSearch()
    {
        name = "DFS";
        FrontierNodes = new LinkedList<MazeState>();
        SearchedNodes = new ArrayList<MazeState>();
    }

    private MazeState popLastFromFrontier()
    {
        MazeState poppedMazeState = FrontierNodes.getLast();
        FrontierNodes.removeLast();
        SearchedNodes.add(poppedMazeState);
        return poppedMazeState;
    }



    public void Solve(MazeState startingMaze, ArrayList<MazeState> goalMazes)
    {
        FrontierNodes.add(startingMaze);

        GoalNodes = goalMazes;

        while(FrontierNodes.size() > 0)
        {
            MazeState currentNode = popLastFromFrontier();

            if(isSolved(currentNode))
            {
                System.out.println("\nSolution found!");
                currentNode.PrintDirections();
                return;
            }
            else
            {
                ArrayList<MazeState> frontierAdditions = currentNode.getPossibleMoveStates();
                AddToFrontier(frontierAdditions);
            }
        }
        System.out.println("no solution found");
    }
}