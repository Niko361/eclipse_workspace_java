import java.io.*;
import java.util.ArrayList;

public class Main {
	
	


	public static void main(String[] args) 
	{
		if(args.length < 2)
		{
			System.out.println("Usage: mazeSolver <filename> <search-method>.");
			System.exit(1);
		}
		
		
		MazeState maze = readProblemFile(args[0]);


	}
	
	private static MazeState readProblemFile(String fileName) 
	{
		ArrayList<MazeState> goalMazes = new ArrayList<MazeState>();
		
		try
		{
			//create file reading objects
			FileReader reader = new FileReader(fileName);
			BufferedReader buffReader = new BufferedReader(reader);
			MazeState result;
			
			String mazeDimensions = buffReader.readLine();
			//split the string by a comma
			String[] bothDimensions = mazeDimensions.replaceAll("[\\[\\]]","").split(",");
		
			//work out the size of the puzzle
			int puzzleSizeY = Integer.parseInt(bothDimensions[0]);
			int puzzleSizeX = Integer.parseInt(bothDimensions[1]);
			
			result = new MazeState(puzzleSizeX, puzzleSizeY);
			

			
			
			//parse the agent's starting coordinates, they will be added after the goal states have been created.
			String startCoordinates = buffReader.readLine();
			String [] startCoordinatesArray = startCoordinates.replaceAll("[\\(\\)]","").split(",");
			int agentStartX = Integer.parseInt(startCoordinatesArray[0]);
			int agentStartY = Integer.parseInt(startCoordinatesArray[1]);
			
			
			//read the goal coordinates into a 2D array of integers, this will later be used to generate the goal states.
			String goalCoordsLineString = buffReader.readLine();
			String [] goalCoordsStringArray = goalCoordsLineString.replaceAll("[\\(\\)]","").trim().split("\\|");
			ArrayList<int[]> goalCoordsArrayList = new ArrayList<int[]>();
			
			String [] goalCoordsString;
			for(int i = 0; i < goalCoordsStringArray.length; i++)
			{
				goalCoordsString = goalCoordsStringArray[i].trim().split(",");
				int[] goalCoordsArray = new int[2];
				goalCoordsArray[0] = Integer.parseInt(goalCoordsString[0]);
				goalCoordsArray[1] = Integer.parseInt(goalCoordsString[1]);	
				goalCoordsArrayList.add(goalCoordsArray);
			}
			
			
			
			//read in the wall coordinates and dimensions, then feed them to MazeState's AddWall() function
			String wallLine = new String();
			String [] wallLineCoords;
			int [] wallCoords = new int [4];
			
			while((wallLine = buffReader.readLine()) != null)
			{
				wallLineCoords = wallLine.replaceAll("[\\(\\)]","").split(",");
				for (int i =0; i < wallLineCoords.length; i++)
				{
					wallCoords[i] = Integer.parseInt(wallLineCoords[i]);
				}
				result.AddWall(wallCoords[0], wallCoords[1], wallCoords[2], wallCoords[3]);	
			}
	    	   
		        
			//Create goal state mazes.
			for(int i = 0; i < goalCoordsArrayList.size(); i++)
			{
				MazeState goalMaze = new MazeState(result);
				goalMaze.AddAgent(goalCoordsArrayList.get(i)[0], goalCoordsArrayList.get(i)[1]);
				goalMazes.add(goalMaze);
				goalMaze.PrintMaze();

			}
						
			buffReader.close();

			result.AddAgent(agentStartX, agentStartY);
			result.PrintMaze();			
			
			return result;
		}
		catch(FileNotFoundException ex)
		{
			//The file didn't exist, show an error
			System.out.println("Error: File \"" + fileName + "\" not found.");
			System.out.println("Please check the path to the file.");
			System.exit(1);
		}
		catch(IOException ex)
		{
			//There was an IO error, show and error message
			System.out.println("Error in reading \"" + fileName + "\". Try closing it and programs that may be accessing it.");
			System.out.println("If you're accessing this file over a network, try making a local copy.");
			System.exit(1);
		}
		
		//this code should be unreachable. This statement is simply to satisfy Eclipse.
		return null;
	}

}