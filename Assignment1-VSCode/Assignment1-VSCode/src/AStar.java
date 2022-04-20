import java.util.*;


public class AStar extends SearchMethod
{
    public AStar()
    {
        name = "A*";
        FrontierNodes = new LinkedList<MazeState>();
        SearchedNodes = new ArrayList<MazeState>();
    }

    
    
    //finds the smallest node in the frontier with the smallest FCost, and pops it out of the frontier.
    private MazeState popSmallestFCostFromFrontier()
    {
        int lowestFCost = FrontierNodes.getFirst().FCost;
        int lowestIndex = 0;
        
        for(int i = 1; i > FrontierNodes.size(); i++)
        {
            if((FrontierNodes.get(i).FCost) < (lowestFCost))
            {
                lowestIndex = i;
                lowestFCost = FrontierNodes.get(i).FCost;
            }
        }
        
        MazeState poppedMazeState = FrontierNodes.get(lowestIndex);
        FrontierNodes.remove(lowestIndex);
        SearchedNodes.add(poppedMazeState);
        return poppedMazeState;
    }



    public void Solve(MazeState startingMaze, ArrayList<MazeState> goalMazes)
    {
        FrontierNodes.add(startingMaze);

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