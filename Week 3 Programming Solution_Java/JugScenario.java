
/**
 * Represents how jugs should be dealt with by the search that operates on them
 * 
 * @author Steven Morris
 * @version 21/03/2016
 */

import java.util.ArrayList;

public class JugScenario extends Scenario<Jugs>
{
    /**
     * Determines if there is only 1L between the two jugs.
     * 
     * @param data - The Jugs to check
     * @return - True if exactly 1L between them, false if not
     */
    public boolean isSolved(Jugs data)
    {
        return data.getLeft() + data.getRight() == 1;
    }

    /**
     * Finds the possible moves for the Jugs in the current state
     * 
     * @param state - The state to check the available moves for
     * @return - A list of Jugs with data reflecting the moves from the passed in state
     */
    public ArrayList<Jugs> determineMoveSet(State<Jugs> state)
    {
        ArrayList<Jugs> moves = new ArrayList<Jugs>();
        
        //The current values of left and right
        int left = state.getData().getLeft();
        int right = state.getData().getRight();
        
        //Check to see if we can  fill the left jug
        if(left < 3)
            moves.add(new Jugs(3, right, "Filled Left"));
            
        //Check to see if we can fill the right jug
        if(right < 5)
            moves.add(new Jugs(left, 5, "Filled Right"));
            
        //Check to see if we can empty the left jug
        if(left > 0)
            moves.add(new Jugs(0 , right, "Emptied Left"));
            
        //Check to see if we can empty the right jug
        if(right > 0)
            moves.add(new Jugs(left, 0, "Emptied Right"));
            
        //Check to see if we can pour the left jug into the right jug
        if(left > 0 && right < 5)
        {
            int tempLeft = left;
            int tempRight = right;
            
            while(tempLeft > 0 && tempRight < 5)
            {
                tempRight++;
                tempLeft--;
            }
            
            moves.add(new Jugs(tempLeft, tempRight, "Poured Left into Right"));
        }
        
        //Check to see if we can pour the right jug into the left jug
        if(left < 3 && right > 0)
        {
            int tempLeft = left;
            int tempRight = right;
            
            while(tempLeft < 3 && tempRight > 0)
            {
                tempRight--;
                tempLeft++;
            }
            
            moves.add(new Jugs(tempLeft, tempRight, "Poured Right into Left"));
        }
        
        return moves;
    }
}
