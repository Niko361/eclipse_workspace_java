 

public class VacEnv extends Environment {
    // floor is an array of vectors; each vector is a column of the floor
    Status[][] floor;
    
    //The location of the vacuum
    Location vac;
    
    int EastBound; // valid X-coordinate is from 0 to EastBound
    int SouthBound; // valid Y-coordinate is from 0 to SouthBound
    
    /**
     * Constructor for VacEnv, accepts arguement, and also sets EastBound and SouthBound based on the size of the array
     * 
     * @param floorFF - A 2D representation of the floor, containing the status of each tile
     * @param vFF - The location of the vacuum, in the form of a Location (internally as x,y)
     */
    public VacEnv(Status[][] floorFF, Location vFF) {
        floor = floorFF;
        vac = vFF;
        EastBound = floor.length - 1; 
        SouthBound = floor[0].length - 1; 
    }
    
    /**
     * Determines how many of the tiles have been cleaned/start clean on the floor. The higher the score, the more tiles that are clean
     * 
     * @return - The score based on number of clean tiles.
     */
    public int measure() {
        int score = 0;
        
        for (int i = 0; i <= EastBound; i++)
            for (int j = 0; j <= SouthBound; j++)
                if (floor[i][j] == Status.Clean) score++;
                
        return score;
    }
    
    /**
     * Updates a particular tile on the floor with the provided Status
     * 
     * @param col - The x position of the tile
     * @param row - The y position of the tile
     * @param newSt - The state to apply to the tile
     */
    public void updateFloor(int col, int row, Status newSt) {
        floor[col][row] = newSt;
    }
    
    /**
     * Move the vacuum to a new location on the floor
     * 
     * @param newLoc - The location to move the vacuum to
     */
    public void updateLoc(Location newLoc) {
        vac = newLoc;
    }
    
    public Location getVacLoc() {
        return vac;
    }
    
    public Status getStatusAtLoc() {
        return floor[vac.getX()][vac.getY()];
    }

    /**
     * Updates the location of the vacuum based on the Action that is received. Makes sure to check that the Action will result with the vacuum ending up
     * in a valid location
     * 
     * @param nextAct - The Action containing the direction the agent will try to move
     */
    public void updateVacLoc(Action nextAct) {
        if ((nextAct == Action.Up) && // agent moves up
            (vac.getY() > 0) && // current location is not at the top row of the map
            (floor[vac.getX()][vac.getY() - 1] != Status.Nil)) { // the cell above current cell is a valid room
            vac = new Location(vac.getX(), vac.getY() - 1); // agent's location is now in the new location
        } else 
        if ((nextAct == Action.Down) && // agent moves down
            (vac.getY() < SouthBound) && // current location is not at the bottom row of the map
            (floor[vac.getX()][vac.getY() + 1] != Status.Nil)) { // the cell below current cell is a valid room
                vac = new Location(vac.getX(), vac.getY() + 1); // agent's location is now in the new location
        } else 
        if ((nextAct == Action.Left) && // agent moves left
            (vac.getX() > 0) && // current location is not at the left-most column of the map
            (floor[vac.getX() - 1][vac.getY()] != Status.Nil)) { // the cell at the left of the current cell is a valid room
                vac = new Location(vac.getX() - 1, vac.getY()); // agent's location is now in the new location
        } else
        if ((nextAct == Action.Right) && // agent moves right
            (vac.getX() < EastBound) && // current location is not at the right-most column of the map
            (floor[vac.getX() + 1][vac.getY()] != Status.Nil)) { // the cell at the right of the current cell is a valid room
                vac = new Location(vac.getX() + 1, vac.getY()); // agent's location is now in the new location
        }
    }
}
