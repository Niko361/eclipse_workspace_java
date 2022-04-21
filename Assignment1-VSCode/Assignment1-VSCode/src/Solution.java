//This class is used to store a solution to a problem.
import java.util.LinkedList;

public class Solution 
{
	public int numberofNodes;
	public LinkedList<direction> directionList;
	
	//initializes a new MazeState array with dimensions specified
	public Solution(int numNodes, LinkedList<direction> dirList)
	{
		numberofNodes = numNodes;
        directionList = dirList;
	}
}
