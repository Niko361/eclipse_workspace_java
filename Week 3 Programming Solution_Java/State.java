/**
 * A State contains information regarding how the "world" looks at a given point. <T> is generic, allowing States to contain any class
 * 
 * @author Steven Morris
 * @version 21/03/2016
 */

public class State<T>
{
    //This is an abstract class, which allows us to have any object stored in the state
    private T data;

    //Stores a pointer to the node that this is a child of
    private State parent;
    
    //The message is the string that will be printed when listing the solution
    private String message;
    
    private int cost;

    /**
     * Constructor for objects of class State
     */
    public State(State parent, String message, T data)
    {
        this.parent = parent;
        this.message = message;
        this.data = data;        
    }
    
    /**
     * Constructor for objects of class State
     */
    public State(State parent, String message, T data, int cost)
    {
        this.parent = parent;
        this.message = message;
        this.data = data; 
        this.cost = cost;
    }
    
    public State getParent()
    {
        return parent;
    }
    
    public T getData()
    {
        return data;
    }
    
    public String getMessage()
    {
        return message;
    }
    
    public int getCost()
    {
        return cost;
    }
}
