import java.util.LinkedList;


public class BreadthFirstSearch extends SearchMethod
{
    public BreadthFirstSearch()
    {
        name = "BFS"
        FrontierNodes = new LinkedList<MazeState>();
        SearchedNodes = new LinkedList<MazeState>();
    }

    private MazeState popFrontier()
    {
        MazeState poppedMazeState = FrontierNodes.getFirst();
        SearchedNodes.add(poppedMazeState);
        return poppedMazeState;
    }

    private boolean isSolved(MazeState currentMaze)
    {
        for(MazeState goalMaze .. goalMazes)
        {
            if (currentMaze.CompareMazeStates(goalMaze))
            {
                return true;
            }
        }

        return false;
    }

    public direction[] Solve(startingMaze MazeState, ArrayList<MazeState> goalMazes)
    {
        FrontierNodes.Add(startingMaze);

        while(Frontier.size() > 0))
        {
            MazeState currentNode = popFrontier();

            if(isSolved(currentNode));
            {
                //insert direction solver here
            }
            else
            {
                
            }
        }
    }
}