import java.io.*;
import java.util.ArrayList;

public class Main {

	public static ArrayList<SearchMethod> searchMethods = new ArrayList<SearchMethod>();
	public static void main(String[] args) 
	{
		if (args.length < 2) {
			System.out.println("Usage: mazeSolver <filename> <search-method>.");
			System.exit(1);
		}

		String fileName = args[0];
		String methodName = args[1];

		InitMethods();

		ArrayList<MazeState> goalMazes = new ArrayList<MazeState>();


		MazeState startingMaze = readProblemFile(fileName, goalMazes);
		SearchMethod thisMethod = null;

		
		//determine which method the user wants to use to solve the puzzles
		for(SearchMethod method : searchMethods)
		{
			//do they want this one?
			if(method.name.compareTo(methodName) == 0)
			{
				//yes, use this method.
				thisMethod = method;
			}
		}
		
		//Has the method been implemented?
		if(thisMethod == null)
		{
			//No, give an error
			System.out.println("Search method identified by " + methodName + " not implemented. Methods are case sensitive.");
			System.exit(1);
		}

		Solution solution = thisMethod.Solve(startingMaze, goalMazes);

		if(solution != null)
		{
			PrintSolution(fileName, thisMethod.name, solution);
		}
		else
		{
			System.out.println("No Solution Found.");
			System.exit(1);
		}
		/*
		**This section is only used for testing purposes
		for (SearchMethod meth: searchMethods)
		{
			PrintSolution(fileName, meth.name, meth.Solve(startingMaze, goalMazes));
		}*/
	}

	public static void PrintSolution(String fileName, String methodName, Solution solution)
	{
		System.out.println(fileName + " " + methodName + " " + solution.numberofNodes);
		for(direction dir: solution.directionList)
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



	public static void InitMethods()
	{
		searchMethods.add(new BreadthFirstSearch());
		searchMethods.add(new DepthFirstSearch());
		searchMethods.add(new IterativeDeepeningSearch());
		searchMethods.add(new GreedyBestFirstSearch());
		searchMethods.add(new AStar());
		searchMethods.add(new WeightedAStar());
	}

	private static MazeState readProblemFile(String fileName, ArrayList<MazeState> goalMazes) 
	{
		try {
			// create file reading objects
			FileReader reader = new FileReader(fileName);
			BufferedReader buffReader = new BufferedReader(reader);
			MazeState emptyMaze;

			String mazeDimensions = buffReader.readLine();
			// split the string by a comma
			String[] bothDimensions = mazeDimensions.replaceAll("[\\[\\]]", "").split(",");

			// work out the size of the puzzle
			int puzzleSizeY = Integer.parseInt(bothDimensions[0]);
			int puzzleSizeX = Integer.parseInt(bothDimensions[1]);

			emptyMaze = new MazeState(puzzleSizeX, puzzleSizeY);

			// parse the agent's starting coordinates, they will be added to an empty maze
			// after the walls have been populated
			String startCoordinates = buffReader.readLine();
			String[] startCoordinatesArray = startCoordinates.replaceAll("[\\(\\)]", "").split(",");
			int agentStartX = Integer.parseInt(startCoordinatesArray[0]);
			int agentStartY = Integer.parseInt(startCoordinatesArray[1]);

			// read the goal coordinates into a 2D array of integers, this will later be
			// used to generate the goal states.
			String goalCoordsLineString = buffReader.readLine();
			String[] goalCoordsStringArray = goalCoordsLineString.replaceAll("[\\(\\)]", "").trim().split("\\|");
			ArrayList<int[]> goalCoordsArrayList = new ArrayList<int[]>();

			String[] goalCoordsString;
			for (int i = 0; i < goalCoordsStringArray.length; i++) {
				goalCoordsString = goalCoordsStringArray[i].trim().split(",");
				int[] goalCoordsArray = new int[2];
				goalCoordsArray[0] = Integer.parseInt(goalCoordsString[0]);
				goalCoordsArray[1] = Integer.parseInt(goalCoordsString[1]);
				goalCoordsArrayList.add(goalCoordsArray);
			}

			// read in the wall coordinates and dimensions, then feed them to MazeState's
			// AddWall() function
			String wallLine = new String();
			String[] wallLineCoords;
			int[] wallCoords = new int[4];

			while ((wallLine = buffReader.readLine()) != null) {
				wallLineCoords = wallLine.replaceAll("[\\(\\)]", "").split(",");
				for (int i = 0; i < wallLineCoords.length; i++) {
					wallCoords[i] = Integer.parseInt(wallLineCoords[i]);
				}
				emptyMaze.AddWall(wallCoords[0], wallCoords[1], wallCoords[2], wallCoords[3]);
			}

			// Create goal state mazes.
			for (int i = 0; i < goalCoordsArrayList.size(); i++) {
				MazeState goalMaze = new MazeState(emptyMaze);
				goalMaze.AddAgent(goalCoordsArrayList.get(i)[0], goalCoordsArrayList.get(i)[1]);
				goalMazes.add(goalMaze);
			}

			// Add the agent's starting location to an empty maze.
			MazeState startingMaze = new MazeState(emptyMaze);
			startingMaze.AddAgent(agentStartX, agentStartY);

			buffReader.close();

			return startingMaze;
		} catch (FileNotFoundException ex) {
			// The file didn't exist, show an error
			System.out.println("Error: File \"" + fileName + "\" not found.");
			System.out.println("Please check the path to the file.");
			System.exit(1);
		} catch (IOException ex) {
			// There was an IO error, show and error message
			System.out.println(
					"Error in reading \"" + fileName + "\". Try closing it and programs that may be accessing it.");
			System.out.println("If you're accessing this file over a network, try making a local copy.");
			System.exit(1);
		}
		return null;
	}

}
