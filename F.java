package demo19524;

public class F extends Factory {

	public Network createNetwork(){

		N theNet = new N();
		return theNet;
	}
	public Highway createHighway(){

		H highway = new H();
		return highway;
	}
	public Hub createHub(Location location){

		Hu hub = new Hu(location);
		return hub;

	}
	public Truck createTruck(){

		T truck = new T();
		return truck;
	}
	

}
