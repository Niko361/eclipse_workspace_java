import java.util.LinkedList;

public abstract class SearchMethod 
{
    public String name;

    //This list of searched nodes is used to make sure the search method isn't returning to any previously searched states.
    public LinkedList<MazeState> SearchedNodes;

    //A list of nodes to explore next.
    public LinkedList<MazeState> FrontierNodes;
}
