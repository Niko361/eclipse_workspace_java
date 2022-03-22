
public class Route {
	private int straightLine;
	private int actualDistance;
	private String destination;
	private String origin;
	
	public Route(String origin, String destination, int actualDistance, int straightLine)
	{
		this.origin = origin;
		this.destination = destination;
		this.actualDistance = actualDistance;
		this.straightLine = straightLine;
	}
	
	public String getOrigin()
	{
		return origin;
	}
	
	public String getDestination()
	{
		return destination;
	}
	
	public int getactualDistance()
	{
		return actualDistance;
	}
	
	public int getstraightLine()
	{
		return straightLine;
	}
	
	public String toString()
	{
		String s = "";
		
		if(actualDistance == -1)
		{
			s += "Cannot travel from " + origin + " to " + destination;
			s += "however there is a straight line distance of " + straightLine;
		}
		else
		{
			s += "Travel from city " + origin + " to " + destination;
			s += " with a straight line distance of  " + straightLine;
			s += " and an actual distance of " + actualDistance;
		}
		
		return s;
	}

}
