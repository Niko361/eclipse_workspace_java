 

public class Location {
    private int x;
    private int y;

    public Location(int xCoord, int yCoord) {
        x = xCoord;
        y = yCoord; 
    }
    
    //Get the x and y coordinate in the form of a new Location
    public Location getLocation() {
        return new Location(x,y);
    }
    
    //Get the x or y coordinate
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    
    /**
     * Allow for setting the x and y values of the Location either by passing in a Location object,
     * or by passing in a seperate x and y integer.
     */
    public void setLocation(int xCoord, int yCoord) {
        x = xCoord;
        y = yCoord; 
    }
    public void setLocation(Location loc) {
        x = loc.getX();
        y = loc.getY(); 
    }
}
