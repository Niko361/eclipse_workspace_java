import java.io.*;
import java.util.ArrayList;


public class Main {
	
	public ArrayList<Route> routes;
	
	public Main(String fileName) 
	{
		routes = new ArrayList<Route>();
		
		try
		{
			readFile(fileName);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		for(Route r : routes)
		{
			System.out.println(r.toString());
		}
		
	}
	
	public void readFile(String fileName) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader(fileName));
		String line;
		
		while((line = in.readLine()) != null)
		{
			String[] contents = line.split(" ");
			
			Route route = new Route(contents[0], contents[1], Integer.parseInt(contents[2]), Integer.parseInt(contents[3]));
			routes.add(route);
		}
		
		in.close();
		
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main(args[0]);
	}
	
	
	

}
