import java.util.*;


public class IterativeDeepeningSearch extends SearchMethod
{
    int maxDepth;
    
    public IterativeDeepeningSearch()
    {
        name = "IDS";
        FrontierNodes = new LinkedList<MazeState>();
        SearchedNodes = new ArrayList<MazeState>();
    }

    public Solution DepthLimitedSolve(MazeState startingMaze, ArrayList<MazeState> goalMazes, int maxDepth)
    {
        FrontierNodes.add(startingMaze);    
        GoalNodes = goalMazes;

        for(int i = 0; i < maxDepth; i++)
        {
            while((FrontierNodes.size() > 0))
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
        }
        return null;
    }

    public Solution Solve(MazeState startingMaze, ArrayList<MazeState> goalMazes)
    {
        //there cannot be more search nodes than there are cells in the maze, therefore the maximum depth before IDS gives up is set to the total number of cells in the maze.
        maxDepth = (MazeState.Maze.length)*(MazeState.Maze[0].length);

        Solution sol = null;

        for(int i = 1; i < maxDepth; i++)
        {
            sol = DepthLimitedSolve(startingMaze, goalMazes, i);

            if(sol != null)
            {
                return sol;
            }
        }

        return null;
    }
}