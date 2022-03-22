/**
 * This class reads in the data that will then be passed to the desired search algorithm
 * 
 * @author Steven Morris
 * @version 21/03/2016
 */

import java.io.*;
import java.util.ArrayList;

public class Main
{  
    public Main(String fileName, String origin, String destination)
    {        
//        solveRouteProblem(fileName, origin, destination);
        solveJugsProblem();
    }
    
    /**
     * Solves the route problem, setting up the necessary Scenario and StateData to allow the search to gain the relevant information
     * 
     * @param fileName - Name of the file to read from
     * @param origin - The intial city of the search
     * @param destination - The goal city of the search
     */
    private void solveRouteProblem(String fileName, String origin, String destination)
    {
        //This will contain all of the routes in the file that we have read
        ArrayList<Route> routes = null;
        
        //Try and read the file, but if it fails, display the exception and exit
        try
        {
            routes = readRouteFile(fileName);
        }
        catch(IOException e)
        {
            e.printStackTrace();
            System.exit(1);
        }
        
        //Whether the destination and origin cities have been found in the list of Routes.
        boolean destFound = false;
        boolean origFound = false;
        
        //A list of the moves that can be made from the initial city
        ArrayList<Route> initialMoves = new ArrayList<Route>();
        
        //Find the route that has the same origin point as origin and the same for destination
        for(Route r : routes)
        {
            //Check to see if the initial city of this route matches our origin, and if so, save it as an initial move
            if(r.getOrigin().equals(origin))
            {
                initialMoves.add(r);
                origFound = true;
            }
            //Check to see if the destination has been found as the initial city in the current Route
            if(!destFound && r.getOrigin().equals(destination))
                destFound = true;
        }
        
        //Only try and set the search up if the origin and destinatoin were found in the Routes, or we will be sure that a successful path will not be found
        if(destFound && origFound)
        {
            //Create the Route scenario, which has Route specific implementation ready for the search
            RouteScenario rs = new RouteScenario(destination, routes);
            
            //Create the search object and pass in our Scenario
            BFS<Route> bfs = new BFS<>(rs);
            
            //If there was mmore than one possible move, add the full list and then commence the search with a data value of null, 
            //to make sure another node is not added to the frontier
            if(initialMoves.size() > 1)
            {
                bfs.addArrayToFrontier(initialMoves, null);
                bfs.search(new State<Route>(null, origin, null));
            }
            //Otherwise just add the one node and commence the search
            else
                bfs.search(new State<Route>(null, origin, initialMoves.get(0)));
        }
        else
        {
            //Let the user know that their destination was not acceptable
            if(!destFound)
                System.out.println(destination + " could not be found in the map file.");
                
            //Let the user that their origin was not acceptable
            if(!origFound)
                System.out.println(origin + " could not be found in the maps file.");
        }
    }
    
    private void solveJugsProblem()
    {
        //Create a new jugs scenario and pass it to a new search object.
        JugScenario jugs = new JugScenario();
        BFS<Jugs> bfs = new BFS<>(jugs);
        
        //Commence the search with an initial state of 0 0 : both jugs are empty.
        bfs.search(new State<Jugs>(null, "L: 0 R: 0 - Initial", new Jugs(0, 0, "Initial")));
    }
    
    /**
     * Reads an parses the file based on the file name provided to the function
     * 
     * @param fileName - The name of the file to open
     * @return - The list of Routes in the file
     * @throws IOException - Calling function will receive an output or input exception that has been encountered
     */
    public ArrayList<Route> readRouteFile(String fileName) throws IOException
    {
        ArrayList<Route> routes = new ArrayList<Route>();
        
        BufferedReader in = new BufferedReader(new FileReader(fileName));   
        String line;
        
        //Keep reading from the file while there are still more lines to read
        while((line = in.readLine()) != null)
        {
            //Split the read in line at each space, as that is the file format we are expecting
            String[] contents = line.split(" ");
            
            //Create the Route and fill it with the read in information
            routes.add(new Route(contents[0], contents[1], Integer.parseInt(contents[2]), Integer.parseInt(contents[3])));
        }
        
        return routes;
    }
    
    /**
     * @param args - 
     *        args[0] - The filename of the file we want to read from
     *        args[1] - The initial point of the search
     *        args[2] - The goal of the search
     */
    public static void main(String[] args)
    {
        new Main(args[0], args[1], args[2]);
    }
}
