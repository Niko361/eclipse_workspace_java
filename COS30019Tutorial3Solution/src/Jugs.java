
/**
 * The Jugs class represents the state of the jugs at a given moment in time
 * 
 * @author Steven Morris
 * @version 21/03/2016
 */
public class Jugs extends StateData
{
    //Right and left store the number of litres in each of the jugs
    private int left;
    private int right;
    
    private String message;
    private int cost;
    
    public Jugs(int left, int right, String message)
    {
        this.left = left;
        this.right = right;
        this.message = "L: " + left + " R: " + right + " - " + message;
    }
    
    public int getLeft()
    {
        return left;
    }
    
    public int getRight()
    {
        return right;
    }
    
    public int getCost()
    {
        return cost;
    }
    
    public String toString()
    {
        return message;
    }
}
