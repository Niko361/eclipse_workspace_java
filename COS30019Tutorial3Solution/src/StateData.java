
/**
 * An abstract way of representing different StateDatas, so that different search implementations 
 * can handle different queries
 * 
 * @author Steven Morris
 * @version 18/03/2016
 */

import java.util.ArrayList;
import java.util.Stack;

public abstract class StateData
{
    //This is only to be used by searches that are concerned with a cost
    public abstract int getCost();
}
