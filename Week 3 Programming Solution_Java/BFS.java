/**
 * Performs a Breadth First Search on the provided data, continuing until a goal state is reached
 * 
 * @author Steven Morris
 * @version 21/03/2016
 */

import java.util.ArrayList;
import java.util.LinkedList;

public class BFS<T>
{
    //We are using a LinkedList to represent the Queue
    private LinkedList<State<T>> frontier;
    
    //The object that defines how to interact with the data we are searching
    Scenario<T> scene;
    
    //The count of how many nodes ahve been discovered while searching, and how many have actually been explored
    private int discovered;
    private int searched;
    
    /**
     * Default constructor that initialises all instance variables to appropriate values
     */
    public BFS(Scenario<T> scene)
    {
        frontier = new LinkedList<State<T>>();
        this.scene = scene;
        discovered = 0;
        searched = 0;
    }
    
    /**
     * Performs a search starting at the intitial state. Continues with search until either a goal state is reached, or until no more states are in the frontier.
     */
    public void search(State<T> initial)
    {
        //In the case that states are initially added with the add array function, don't place the first node in the frontier, as it will be a double up
        if(initial.getData() != null)
            frontier.add(initial);
       
        State<T> state = null;
        
        //While there are still more nodes in the frontier to explore
        while(!frontier.isEmpty())
        {
            //Get and remove the first node in the frontier. Rememeber that we are dealing with a queue
            state = frontier.removeFirst();
            
            //We have now searched a node, so increment the number of nodes searched
            searched++;
           
            //Check to see if we have found the goal state
            if(scene.isSolved(state.getData()))
                break;
            
            //Determine what moves are possible from the state we are currently examining, and add all subsequent states to the frontier
            addArrayToFrontier(scene.determineMoveSet(state), state);
        }
        
        //Display the sequence of steps to get to the solution
        scene.displaySolution(state, searched, discovered);
    }
    
    /**
     * Adds a list of nodes to the frontier ready for searching
     * 
     * @param data - The list containing all of the states achieved after each possible move
     * @param parent - The state that the list is derived from
     */
    public void addArrayToFrontier(ArrayList<T> data, State<T> parent)
    {
        for(T s : data)
        {
            frontier.add(new State<T>(parent, s.toString(), s));
            
            //Increment discovered for every node that is added to the list
            discovered++;
        }
    }
}
