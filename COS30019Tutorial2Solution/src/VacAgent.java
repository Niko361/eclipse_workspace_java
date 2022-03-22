import java.util.LinkedList;
import java.util.Random;

public class VacAgent {

    /**
     * Receives a list of percepts and determines what action is appropriate according to the status of the last item in the list.
     * 
     * This is where your implementation could improve upon how the agent moves around the floor.
     * At the moment all the agent does is move up, which clearly can't clean any tiles in any other column.
     * 
     * @param percepts - The list of percepts in the simulation so far.
     * @return - The next action to be produced by the agent.
     */
    public Action next(LinkedList<VacPercept> percepts) {
        
        //Set to a default value
        Action result = Action.NoOp;
        
        //If dirty, get rady to perform the Suck action, else get ready to move up.
        if (percepts.getLast().getStatus() == Status.Dirty) {
            result = Action.Suck;
        } else {
            //Generate random number from 0 - 3 inclusive
            Random rand = new Random();
            int dir = rand.nextInt(4);
            
            //Determine what Action to apply given our random number
            switch(dir)
            {
                case 0:
                    result = Action.Left;
                    break;
                case 1:
                    result = Action.Up;
                    break;
                case 2:
                    result = Action.Right;
                    break;
                case 3:
                    result = Action.Down;
                    break;
            }
        }
        
        return result;
    }
}
