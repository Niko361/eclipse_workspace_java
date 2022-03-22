public class MazeState 
{
	public mazeCellState[][] Maze;
	public int Cost;
	
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
		this.Maze = new mazeCellState[in.Maze.length][in.Maze[0].length];
		this.Cost = in.Cost;
		for (int i=0; i < in.Maze[0].length; i++)
		{
			for (int j=0; j < in.Maze.length; j++)
			{							
				this.Maze[j][i]=in.Maze[j][i];
			}
		}
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
		Maze[x][y] = mazeCellState.Agent;
	}
	
	public void PrintMaze()
	{
		System.out.println("\nMaze State:");
		for (int i=0; i < (Maze.length+2); i++)
		{
			System.out.print("-");
		}
		
		System.out.println();
		
		for (int i=0; i < Maze[0].length; i++)
		{
			System.out.print("|");
			
			for (int j=0; j < Maze.length; j++)
			{							
				switch (Maze[j][i])
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
		
		for (int i=0; i < (Maze.length+2); i++)
		{
			System.out.print("-");
		}
		
		
		
	}

}