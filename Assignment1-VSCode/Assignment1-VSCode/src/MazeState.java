import java.util.*;

public class MazeState 
{
	public static mazeCellState[][] Maze;
	public int GCost = 0;
	public double HCost;
	public double FCost;

	private int[] agentLocationXY = new int[2];
	public LinkedList<direction> directionList = new LinkedList<direction>();
	
	//initializes a new MazeState array with dimensions specified
	public MazeState(int x, int y)
	{
		Maze = new mazeCellState[x][y];
		
		for (int i=0; i < Maze.length; i++)
		{
			for (int j=0; j < Maze[0].length; j++)
			{
				Maze[i][j] = mazeCellState.Empty;
			}
		}
	}
	
	public MazeState(MazeState in)
	{
		this.agentLocationXY[0] = in.agentLocationXY[0];
		this.agentLocationXY[1] = in.agentLocationXY[1];
		this.GCost = in.GCost;

		for(direction dir: in.directionList)
		{
			this.directionList.add(dir);
		}
	}

	public void PrintDirections()
	{
		for(direction dir: directionList)
		{
			switch(dir)
			{
				case Up:
					System.out.print(" up;");
					break;
				case Left:
					System.out.print(" left;");
					break;
				case Down:
					System.out.print(" down;");
					break;
				case Right:
					System.out.print(" right;");
					break;
			}
		}
		System.out.println();
	}
	
	//adds a wall with top-left corner coordinates and dimensions specified 
	//need to add bounds checking
	public void AddWall(int x, int y, int width, int height)
	{
		for (int i=x; i < (x+width); i++)
		{
			for (int j=y; j < (y+height); j++)
			{
				Maze[i][j] = mazeCellState.Wall;
			}
		}
	}
	
	public void AddAgent(int x, int y)
	{
		agentLocationXY[0] = x;
		agentLocationXY[1] = y;
	}

	//returns true if maze state argument is the same as this maze state, and false otherwise.
	//only compares the location of the agent and location of walls
	public boolean CompareMazeStates(MazeState in)
	{
		if ((this.agentLocationXY[0] != in.agentLocationXY[0]) || (this.agentLocationXY[1] != in.agentLocationXY[1]))
		{
			return false;
		}
		return true;
	}

	public ArrayList<direction> getPossibleMoves()
	{
		ArrayList<direction> result = new ArrayList<direction>();

		if(agentLocationXY[1] > 0)
		{
			if((Maze[agentLocationXY[0]][agentLocationXY[1]-1]) == mazeCellState.Empty)
			{
				result.add(direction.Up);
			}
		}
		if(agentLocationXY[0] > 0) 
		{
			if((Maze[agentLocationXY[0]-1][agentLocationXY[1]]) == mazeCellState.Empty)
			{
				result.add(direction.Left);
			}
		}

		if(agentLocationXY[1] < (Maze[0].length-1))
		{
			if((Maze[agentLocationXY[0]][agentLocationXY[1]+1]) == mazeCellState.Empty)
			{
				result.add(direction.Down);
			}
		}	

		if(agentLocationXY[0] < (Maze.length-1))
		{
			if((Maze[agentLocationXY[0]+1][agentLocationXY[1]]) == mazeCellState.Empty)
			{
				result.add(direction.Right);
			}
		}
			


		return result;
	}

	public MazeState MoveAgent(direction dir)
	{
		MazeState result = new MazeState(this);


		switch(dir)
		{
			case Up:
				result.agentLocationXY[1]--;
				break;
			case Left:
				result.agentLocationXY[0]--;
				break;
			case Down:
				result.agentLocationXY[1]++;
				break;
			case Right:
				result.agentLocationXY[0]++;
				break;
		}

		result.directionList.add(dir);
		result.GCost++;

		return result;
	}

	public ArrayList<MazeState> getPossibleMoveStates()
	{
		ArrayList<MazeState> result = new ArrayList<MazeState>();

		ArrayList<direction> possibleDirections = getPossibleMoves();

		for(direction dir: possibleDirections)
		{
			result.add(this.MoveAgent(dir));
		}

		return result;
	}
	
	public void PrintMaze()
	{
		mazeCellState[][] printableMaze = new mazeCellState[Maze.length][Maze[0].length];

		for (int i=0; i < Maze[0].length; i++)
		{
			for (int j=0; j < Maze.length; j++)
			{							
				printableMaze[j][i] = Maze[j][i];
			}
		}

		printableMaze[agentLocationXY[0]][agentLocationXY[1]] = mazeCellState.Agent;
		
		System.out.println("\nMaze State:");
		for (int i=0; i < (printableMaze.length+2); i++)
		{
			System.out.print("-");
		}
		
		System.out.println();
		
		for (int i=0; i < printableMaze[0].length; i++)
		{
			System.out.print("|");
			
			for (int j=0; j < printableMaze.length; j++)
			{							
				switch (printableMaze[j][i])
				{
					case Empty:
						System.out.print(".");
						break;
					case Wall:
						System.out.print("#");
						break;
					case Agent:
						System.out.print("X");
						break;
				}
			}
			System.out.println("|");
		}
		
		for (int i=0; i < (printableMaze.length+2); i++)
		{
			System.out.print("-");
		}

		System.out.println();
	}

	public int GetManhattanDistance(MazeState in)
	{
		return Math.abs(in.agentLocationXY[0] - this.agentLocationXY[0]) + Math.abs(in.agentLocationXY[1] - this.agentLocationXY[1]);
	}

	public int getShortestManhattanDistance(ArrayList<MazeState> inputNodes)
	{
		ArrayList<Integer> distances = new ArrayList<Integer>();
		for(MazeState in: inputNodes)
		{
			distances.add(this.GetManhattanDistance(in));
		}

		Collections.sort(distances);
		return distances.get(0);	
	}

}
