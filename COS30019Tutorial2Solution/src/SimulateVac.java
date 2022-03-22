import java.util.*;

public class SimulateVac {
    
    VacAgent va;
    VacEnv ve;

    public SimulateVac(VacAgent initAgent, VacEnv initEnv) {
        va = initAgent;
        ve= initEnv;
    }
    
    /**
     * Runs the simulation, taking into account the life time of the agent.
     * 
     * @param maxLoop - The life time of the agent
     */
    public void execute(int maxLoop) {
        int count = 0;
        LinkedList<VacPercept> allPercepts = new LinkedList<VacPercept>();
        int totalReward = 0;
        
        //Continue looping through the application while the life time of the agent has not been exceeded.
        while (count < maxLoop) {
            //Determine where the agent is located
            Location vLoc = ve.getVacLoc();
            
            //Determine what the status of the tile is at the location
            Status vStatus = ve.getStatusAtLoc();
            
            //Create a new Percept, based on the status of the tile
            VacPercept vp = new VacPercept(vLoc, vStatus);
            allPercepts.add(vp); // add the new percept to the end of the list
            
            //Determine what the next appropriate action is based on what the vacuum has perceived in the current tile
            Action nextAct = va.next(allPercepts);
            
            //If the action is to suck at the current location, update the current tile so that it is Clean
            if (nextAct == Action.Suck) {
                ve.updateFloor(vLoc.getX(), vLoc.getY(), Status.Clean);
            } else {
                //If the action requires movement in any direction, then pass the action to the vacuum update function
                if ((nextAct == Action.Down) || 
                    (nextAct == Action.Left) || 
                    (nextAct == Action.Right) || 
                    (nextAct == Action.Up)) {
                        ve.updateVacLoc(nextAct);                   
                }
            }                      
        
            //Prints out the action that was performed, and the location of the vacuum BEFORE having moved
            System.out.println(nextAct + " " + vLoc.getX() + "," + vLoc.getY());
            
            //Add the score of the floor in its current state with the score previously obtained throughout the simulation
            //Only add to the score if the agent performed the SUCK action.
            if(nextAct == Action.Suck) {
                totalReward += ve.measure();
            }
                
            count++;     
        }
        
        //print out the final score of the agent
        System.out.println("This agent's performance is " + totalReward);
    }
}
