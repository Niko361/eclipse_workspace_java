public class Route 
{
    public MazeState origin;
    public MazeState destination;
    public int distance;

    public Route(MazeState origin, MazeState destination, int distance)
    {
        this.origin = origin;
        this.destination = destination;
        this.distance = distance;
    }

    //checks whether the route given is the same as this one
    public boolean isSame(Route route)
    {
        return (((this.origin.CompareMazeStates(route.origin)) && (this.destination.CompareMazeStates(route.destination))) || 
        ((this.origin.CompareMazeStates(route.destination)) && (this.destination.CompareMazeStates(route.origin))));
    }


}
