
/**
 * This program will read a provided file containing map data and parse it appropriately.
 * 
 * @author Steven Morris
 * @version 24/02/2016
 */

import java.io.*;
import java.util.ArrayList;

public class Main
{
    public ArrayList<Route> routes;

    public Main(String fileName)
    {
        routes = new ArrayList<Route>();
        
        try
        {
            readFile(fileName);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        
        for(Route r : routes)
            System.out.println(r.toString());
    }

    /**
     * Reads an parses the file based on the file name provided to the function
     * 
     * @param fileName - The name of the file to open
     * @throws IOException - Calling function will receive an output or input exception that has been encountered
     */
    public void readFile(String fileName) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader(fileName));   
        String line;
        
        //Keep reading from the file while there are still more lines to read
        while((line = in.readLine()) != null)
        {
            //Split the read in line at each space, as that is the file format we are expecting
            String[] contents = line.split(" ");
            
            //Create a new Route that stores the information from the read in line and then add it to the list
            Route route = new Route(contents[0], contents[1], Integer.parseInt(contents[2]), Integer.parseInt(contents[3]));
            routes.add(route);
        }
        
        in.close();
    }
    
    public static void main(String[] args)
    {
        new Main(args[0]);
    }
}
