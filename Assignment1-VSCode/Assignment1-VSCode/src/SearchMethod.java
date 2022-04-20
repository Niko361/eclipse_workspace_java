import java.util.ArrayList;
import java.util.LinkedList;

public abstract class SearchMethod 
{
    public String name;

    //This list of searched nodes is used to make sure the search method isn't returning to any previously searched states.
    protected ArrayList<MazeState> SearchedNodes;

    //A list of nodes to explore next.
    protected LinkedList<MazeState> FrontierNodes;

    protected  ArrayList<MazeState> GoalNodes;

    public abstract void Solve(MazeState startingMaze, ArrayList<MazeState> goalMazes);

    //used by every search method to check whether the goal state has been reached.
    protected boolean isSolved(MazeState currentMaze)
    {
        currentMaze.PrintDirections();
        
        for(MazeState goalMaze: GoalNodes)
        {
            if (currentMaze.CompareMazeStates(goalMaze))
            {
                return true;
            }
        }
        return false;
    }

    //adds the members of an MazeState ArrayList, checking for duplicates
    protected void AddToFrontier(ArrayList<MazeState> additions)
    {
        for(MazeState addition: additions)
        {
            boolean dupe = false;
            for(MazeState front: FrontierNodes)
            {
                if(front.CompareMazeStates(addition))
                {
                    dupe = true;
                }
            }
            for(MazeState searched: SearchedNodes) 
            {
                if(searched.CompareMazeStates(addition))
                {
                    dupe = true;
                }
            }
            if(!dupe)
            {
                FrontierNodes.add(addition);
            }
        }
    }
    
}

