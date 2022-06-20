package demo19524;

import java.util.ArrayList;


// Highways are unidirectional

public class H extends Highway {
	
	ArrayList<Truck> trucks = new ArrayList<Truck>();
	
	// returns true if Highway is not full
	// i.e. number of trucks is below capacity
	public synchronized boolean hasCapacity(){


		if(trucks.size()< this.getCapacity()){
			
			return true;
		}

		else{return false;}}
	
	// fails if already at capacity
	public synchronized boolean add(Truck truck){

		if(trucks.size()< this.getCapacity()){
			trucks.add(truck);
			//System.out.println(truck.getTruckName());
			return true;
		}

		else{return false;}

	}
	public synchronized void remove(Truck truck){
	
		int k=0;

		for(int i=0;i<trucks.size();i++){

			if(trucks.get(i)==truck){
				k=i;		
			}

		}

		trucks.remove(k);
	}
	
}
	
