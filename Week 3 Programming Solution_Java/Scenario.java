/**
 * Abstract class Scenario - Provides basic information that will be necessary in every Scenario. Any specific information required of a Scenario will need to be implemented
 * in a child class
 * 
 * @author Steven Morris
 * @version 21/03/2016
 */

import java.util.ArrayList;
import java.util.Stack;

public abstract class Scenario<T>
{      
    /**
     * An abstract method that is required of all scenarios, as it will allow the search to prompt for possible moves from a given state. Each scenario will likely have
     * very different implementations of this function
     * 
     * @param state - The state to check the possible move list for
     * @return - A list containing the possible moves from the passed in state
     */
    public abstract ArrayList<T> determineMoveSet(State<T> state);   
    
    /**
     * An abstract method that is required of all scenarios, as it determines if the provided data represents a goal state. This is the state that we should stop the search at.
     * 
     * @param data - The data that we are checking our end goal against.
     * @return - True if the data is a solution, otherwise false
     */
    public abstract boolean isSolved(T data);
    
    /**
     * Displays the sequence of steps required to get to the goal state.
     * 
     * @param state - The final state in the sequence. This should be the last message we display on the screen
     * @param searched - The number of states that were explored during the search for the solution
     * @param discovered - The number of nodes that were discovered during the search for the solution
     */
    public void displaySolution(State<T> state, int searched, int discovered)
    {
        //Create a stack that will have each node in the solution chain added until we reach the starting node in the search
        Stack<String> stack = new Stack<>();
        
        //While the state is not null, we need to add it to the stack, and then make the state = to the states parent. This is a recursive 
        //solution that will get us to the top of the search tree
        while(state != null)
        {
            stack.push(state.getMessage());
            state = state.getParent();
        }
        
        System.out.println("Searched: " + searched + " Discovered: " + discovered);
        
        //Now we are able to print out the solution from the beginning to the end
        while(!stack.isEmpty())
            System.out.println(stack.pop());
    }
}
