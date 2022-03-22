
/**
 * handles the information and fucntions necessary to allow the search to find a solution to the Route problems
 * 
 * @author Steven Morris
 * @version 19/03/2016
 */

import java.util.ArrayList;

public class RouteScenario extends Scenario<Route>
{
    private ArrayList<Route> routes;
    private String solvedCondition;
    
    RouteScenario(String condition, ArrayList<Route> routes)
    {
        solvedCondition = condition;
        this.routes = routes;
    }
    
    /**
     * Checks to see if the current Route has an origin that matches the goal
     * 
     * @param data - The Route to check
     * @return - True if origin and goal are equal, otherwise false
     */
    public boolean isSolved(Route data)
    {
        return data.getOrigin().equals(solvedCondition);
    }
    
    public ArrayList<Route> determineMoveSet(State<Route> state)
    {
        ArrayList<Route> moves = new ArrayList<Route>();
        
        //Go through all available routes and see what ones will be added to the queue
        for(Route r : routes)
            //Create the state and add it to the queue if the actual distance is -1
            if(((Route)state.getData()).getDestination().equals(r.getOrigin()) && r.getActualCost() != -1)
                moves.add(r);
        
        return moves;
    }
}
