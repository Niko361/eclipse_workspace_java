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

    private boolean isSolved(MazeState currentMaze)
    {
        for(MazeState goalMaze: GoalNodes)
        {
            if (currentMaze.CompareMazeStates(goalMaze))
            {
                return true;
            }
        }

        return false;
    }

    public direction[] Solve(MazeState startingMaze, ArrayList<MazeState> goalMazes)
    {
        FrontierNodes.add(startingMaze);

        while(FrontierNodes.size() > 0)
        {
            MazeState currentNode = popFrontier();

            if(isSolved(currentNode))
            {
                //insert direction solver here
            }
            else
            {
                
            }
        }

        //return null if direction not found
        return null;
    }
}