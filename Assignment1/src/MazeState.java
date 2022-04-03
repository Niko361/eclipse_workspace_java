import java.io.*;
import java.util.ArrayList;

public class MazeState 
{
	private mazeCellState[][] Maze;
	public int Cost;
	private int[] agentLocationXY = new int[2];
	
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
		agentLocationXY[0] = x;
		agentLocationXY[1] = y;
	}

	//returns true if maze state argument is the same as this maze state, and false otherwise.
	//compares all variables other than cost because cost isn't relevant to the physical state of the maze.
	public boolean CompareMazeStates(MazeState in)
	{
		if ((this.Maze.length != in.Maze.length) || (this.Maze[0].length != in.Maze[0].length) 
		|| (this.agentLocationXY[0] != in.agentLocationXY[0] ||	this.agentLocationXY[0] != in.agentLocationXY[1]))
		{
			return false;
		}

		for (int i=0; i < this.Maze[0].length; i++)
		{
			for (int j=0; j < this.Maze.length; j++)
			{							
				if(this.Maze[j][i] != in.Maze[j][i])
				{
					return false;
				}
			}
		}


		return true;
	}

	public ArrayList<direction> getPossibleMoves()
	{
		ArrayList<direction> result = new ArrayList<direction>();

		if((agentLocationXY[1] > 0) && ((this.Maze[agentLocationXY[0]][agentLocationXY[1]-1]) == mazeCellState.Empty))
		{
			result.add(direction.Up);
		}
		if((agentLocationXY[0] > 0) && ((this.Maze[agentLocationXY[0]-1][agentLocationXY[1]]) == mazeCellState.Empty))
		{
			result.add(direction.Left);
		}
		if((agentLocationXY[1] < (Maze.length-1)) && ((this.Maze[agentLocationXY[0]][agentLocationXY[1]+1]) == mazeCellState.Empty))
		{
			result.add(direction.Right);
		}

		if((agentLocationXY[0] < (Maze.length-1)) && ((this.Maze[agentLocationXY[0]+1][agentLocationXY[1]]) == mazeCellState.Empty))
		{
			result.add(direction.Right);
		}

		return result;
	}
	
	public void PrintMaze()
	{
		MazeState printableMaze = new MazeState(this);

		printableMaze.Maze[agentLocationXY[0]][agentLocationXY[1]] = mazeCellState.Agent;


		
		System.out.println("\nMaze State:");
		for (int i=0; i < (printableMaze.Maze.length+2); i++)
		{
			System.out.print("-");
		}
		
		System.out.println();
		
		for (int i=0; i < printableMaze.Maze[0].length; i++)
		{
			System.out.print("|");
			
			for (int j=0; j < printableMaze.Maze.length; j++)
			{							
				switch (printableMaze.Maze[j][i])
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
