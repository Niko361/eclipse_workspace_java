import java.util.*;


public class AStar extends SearchMethod
{
    public AStar()
    {
        name = "A*";
        FrontierNodes = new LinkedList<MazeState>();
        SearchedNodes = new ArrayList<MazeState>();
    }


    public void Solve(MazeState startingMaze, ArrayList<MazeState> goalMazes, String fileName)
    {
        FrontierNodes.add(startingMaze);

        this.fileName = fileName;

        GoalNodes = goalMazes;

        while(FrontierNodes.size() > 0)
        {
            MazeState currentNode = popSmallestFCostFromFrontier();

            if(isSolved(currentNode))
            {
                
                System.out.println("\n" + name + " Solution found!\n");
                currentNode.PrintDirections();
                return;
            }
            else
            {
                ArrayList<MazeState> frontierAdditions = currentNode.getPossibleMoveStates();

                //Creates a new ArrayList of possible moves to add to the frontier, except with their Heuristic functions evaluated (shortest Manhattan distance to a goal state).
                //The FCost function of A* is equal to the Heuristic cost plus the GCost (total moves so far)
                ArrayList<MazeState> frontierAdditionsWithHeuristicCost = new ArrayList<MazeState>();
                for(MazeState curr : frontierAdditions)
                {
                    curr.HCost = curr.getShortestManhattanDistance(GoalNodes);
                    curr.FCost = curr.HCost+curr.GCost;
                    frontierAdditionsWithHeuristicCost.add(curr);
                }

                AddToFrontier(frontierAdditionsWithHeuristicCost);
            }
        }
        System.out.println("no solution found");
    }
}