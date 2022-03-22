/**
 * A Route contains the name of the origin city, and the destiantion city. It is use to determine if a viable path exists
 * 
 * @author Steven Morris
 * @version 19/03/2016
 */

import java.util.ArrayList;

public class Route extends StateData
{
    private String destination;
    private String origin;
    private int actualCost;
    private int straightLineCost;
    
    private int cost;

    public Route(String origin, String destination, int actualCost, int straightLineCost)
    {        
        this.origin = origin;
        this.destination = destination;
        this.actualCost = actualCost;
        this.straightLineCost = straightLineCost;
        
        cost = 0;
    }
    
    public String getDestination()
    {
        return destination;
    }
    
    public String getOrigin()
    {
        return origin;
    }
    
    public int getActualCost()
    {
        return actualCost;
    }
    
    public int getCost()
    {
        return cost;
    }
    
    public String toString()
    {
        return origin;
    }
}
