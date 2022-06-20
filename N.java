package demo19524;

import java.util.ArrayList;


// We assume only one instance of Network will be created for a program
public class N extends Network {


	
	ArrayList<Hub> hubs = new ArrayList<Hub>();
	ArrayList<Highway> highways = new ArrayList<Highway>();
	ArrayList<Truck> trucks = new ArrayList<Truck>();
	//ArrayList<Truck> trucks = new ArrayList<Truck>;

	// keep track of the following entities
	public void add(Hub hub){

	this.hubs.add(hub);
	}
	
	public void add(Highway hwy){

	this.highways.add(hwy);
	//System.out.println("a");
	}

	public void add(Truck truck){

	this.trucks.add(truck);
	}
	

	
	
	// start the simulation
	// derived class calls start on each of the Hubs and Trucks
	public synchronized void start(){
		for(int i=0;i<hubs.size();i++){

			hubs.get(i).start();
			//System.out.println("a");

		}

		for(int i=0;i<trucks.size();i++){

			trucks.get(i).start();
		}
	}

	// derived class calls draw on each hub, highway, and truck
	// passing in display
	public synchronized void redisplay(Display disp){
		
		for(int i=0;i<hubs.size();i++){

			hubs.get(i).draw(disp);


		}

		for(int i=0;i<highways.size();i++){

			highways.get(i).draw(disp);

		}

		for(int i=0;i<trucks.size();i++){

			trucks.get(i).draw(disp);
		}

	}
	
	protected synchronized  Hub findNearestHubForLoc(Location loc){

		double distance;
		int k=0;
		Location l = hubs.get(0).getLoc();
		distance = Math.sqrt(loc.distSqrd(l));

		for(int i=1;i<hubs.size();i++){

			Location l1 = hubs.get(i).getLoc();
			if(distance > Math.sqrt(loc.distSqrd(l1))){

				distance = Math.sqrt(loc.distSqrd(l1));
				k=i;
			}
		}

		return hubs.get(k);

	}
	

}
