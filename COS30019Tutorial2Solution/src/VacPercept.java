 

public class VacPercept implements Percept {
	private Location loc;
	private Status status;
	
	public VacPercept(Location l, Status s) {
		loc = l;
		status = s;
	}
	
	public Location getLocation() {
		return loc;
	}
	
	public Status getStatus() {
		return status;
	}

}
