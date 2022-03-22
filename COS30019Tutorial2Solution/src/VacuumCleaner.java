/**
 * 
 */
 

import java.io.*;
//import java.util.*;

/**
 * @author bvo
 *
 */
public class VacuumCleaner {

    /**
     * @param args - [0] The name of the file to read from
     *             - [1] Location of the vacuum agent in the form [x,y]
     *             - [2] Life time of the agent
     */
    public static void main(String[] args) {
        //args contains:
        //  [0] - filename containing floor map
        //  [1] - vac location
        //  [2] - maximum number of iterations
                        
        //Get the floor map from the file
        Status[][] floor = readProblemFile(args[0]);
        
        /*
        //This code will demonstrate what the floor looks like by printing each tile out to the console. It is currently commented out as it is not 
        //necessary in the fucntionality of the program, only for debugging
        for (int j = 0; j < floor[0].length; j++) {
            for (int i = 0; i < f   loor.length; i++) {
                System.out.print(floor[i][j]);
            }
            System.out.println();
        }
        */           
                
        //Parse the coordinates that were passed by the user. The input we receieve is in the form [x, y].
        String location = args[1];
        location = location.substring(1, location.length() - 1); // Get rid of braces.
        
        //Split the string at the comma, and handle any number of spaces directly after it
        String[] coords = location.split(",\\s*");
        
        //Add each of the coordinates to seperate variables, and create a Location based on those variables
        int x = Integer.parseInt(coords[0]);
        int y = Integer.parseInt(coords[1]);
        Location loc = new Location(x,y);
        
        //Make sure that the agent is within the bounds of the floor. If not, show and error and exit the application
        if(x >= floor[0].length || x < 0 || y < 0 || y >= floor.length) {
            System.out.println("ERROR: Make sure that the agent is within the bounds of the floor.");
            System.exit(-1);
        }
        
        //Make sure that the agent isn't initially placed on a Nil tile
        if(floor[x][y] == Status.Nil) {
            System.out.println("ERROR: make sure that the agent does not start on a Nil tile.");
            System.exit(-1);
        }
        
        //Create the environment that the agent will move around in
        VacEnv ve = new VacEnv(floor, loc);
        
        //Set the time the agent has to live
        String maxLoopS = args[2];
        int maxLoop = Integer.parseInt(maxLoopS);
        
        //Make sure that the agent can actually perform actions based on how long it hs to live.
        if(maxLoop <= 0) {
            System.out.println("WARNING: The agent will not be able to complete an action with a life time of " + maxLoop  + ". Please enter a positive whole number.");
            System.exit(-1);
        }        
        
        //Create the Vacuum agent (only has a default constructor)
        VacAgent va = new VacAgent();
        
        //Prepare and run the simulation, providing the environment and the agent
        SimulateVac simVac = new SimulateVac(va, ve);
        simVac.execute(maxLoop);        
    }
    
    /**
     * Reads the file that has been provided to the program. Information is stored in a 2D array that represents the floor
     * Static ensures that only one file can be read.
     * 
     * @param fileName - The name of the file to read from
     * @return - The 2D array with the floor information
     */
    private static Status[][] readProblemFile(String fileName)
    {   
        try
        {
            //create file reading objects
            FileReader reader = new FileReader(fileName);
            BufferedReader map = new BufferedReader(reader);
            
            String mapDimension = map.readLine();
            
            //split the string by letter "x"
            String[] bothDimensions = mapDimension.split("x");
        
            //work out the "physical" size of the map
            int mapWidth = Integer.parseInt(bothDimensions[0]);
            int mapHeight = Integer.parseInt(bothDimensions[1]);
            
            //Create the 2D array, providing the values for the size of the floor as the size each dimension should be
            Status[][] floor = new Status[mapWidth][mapHeight];
            
            //fill in the initial state
            for (int j = 0; j < mapHeight; j++) {
                // each line represents a row
                String startStateString = map.readLine();
                
                //split the string by spaces 
                String[] cellStatus = startStateString.split(" ");
                
                //Determine what information is portrayed in each of the array indicies, each of which represent a tile on the floor.
                //We are using an Enumerator to represent the states of the tiles, so we will be checking what string is provided, and
                //applying the appropraite state from the Enum
                for (int i = 0; i < mapWidth; i++) {
                    if (cellStatus[i].equals("clean")) { floor[i][j] = Status.Clean;}
                    else if (cellStatus[i].equals("dirty")) { floor[i][j] = Status.Dirty;}
                    else { floor[i][j] = Status.Nil;}
                }
            }
                            
            //Close the resource, and return the array
            map.close();
            return floor;
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
