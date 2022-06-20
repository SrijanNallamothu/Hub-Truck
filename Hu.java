package demo19524;

import java.util.ArrayList;
import java.util.HashMap;

public class Hu extends Hub {

	public Hu(Location loc) {
		super(loc);
	
	}

	ArrayList<Truck> trucks = new ArrayList<Truck>();
	
	// add a Truck to the queue of the Hub.
	// returns false if the Q is full and add fails
	public synchronized boolean add(Truck truck){

		if(trucks.size()< this.getCapacity()){
			trucks.add(truck);
			return true;
		}

		else{return false;}

	}

	protected synchronized void remove(Truck truck){

		int k=0;

		for(int i=0;i<trucks.size();i++){

			if(trucks.get(i)==truck){
				k=i;		
			}

		}

		trucks.remove(k);
	}

	// provides routing information
	public synchronized Highway getNextHighway(Hub last, Hub dest){

		for(Highway H : getHighways())
        {
            
            HashMap<Hub,Boolean> A = new HashMap<>();

            A.put(this,true);
            DFS(H.getEnd(),A);
            if(A.getOrDefault(dest,false))
            {
                return H;
            }

        }
        return null;
	}
	
	// to be implemented in derived classes. Called at each time step
	protected synchronized void processQ(int deltaT){

	 for (int i=0;i<trucks.size();i++)
        {
            
            Hub End = Network.getNearestHub(trucks.get(i).getDest());
            if(End != this)
            {
            	Highway next = getNextHighway(this,End);
                if(next.add(trucks.get(i)))
                {                	
                    
                    trucks.get(i).enter(next);
                    this.remove(trucks.get(i));
                }
            }
            else
            {
                this.remove(trucks.get(i));
            }
        }
		

	}

	public synchronized void DFS(Hub hub,HashMap<Hub,Boolean> A)
    {
        A.put(hub,true);
        for(Highway highway: hub.getHighways())
        {
            if(!A.getOrDefault(highway.getEnd(),false))
            {
                DFS(highway.getEnd(), A);
            }
        }
    }
		
	

}
