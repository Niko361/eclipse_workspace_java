
/**
 * A City contains information pertaining to a City on a map, which could be used in traversal
 * 
 * @author Steven Morris
 * @version 24/02/2016
 */
public class Route
{
    private int straightLine;
    private int actualDistance;
    private String destination;
    private String origin;
    
    public Route(String origin, String destination, int actualDistance, int straightLine)
    {
        this.origin = origin;
        this.destination = destination;
        this.actualDistance = actualDistance;
        this.straightLine = straightLine;
    }
    
    public String getOrigin()
    {
        return origin;
    }
    
    public String getDestination()
    {
        return destination;
    }
    
    public int getActualDistance()
    {
        return actualDistance;
    }

    public int getStraightLine()
    {
        return straightLine;
    }
    
    /**
     * A function that will allow us to easily print out all necessary Route information
     * 
     * @return - The String that contains all the necessary Route information
     */
    public String toString()
    {
        String s = "";
        
        if(actualDistance == -1)
        {
            s += "Cannot travel from " + origin + " to " + destination;
            s += " however there is a straight line distance of " + straightLine;
        }
        else
        {
            s += "Travel from city " + origin + " to " + destination;
            s += " with a straight line distance of " + straightLine;
            s += " and an actual distance of " + actualDistance;
        }
            
        return s;
    }
}
