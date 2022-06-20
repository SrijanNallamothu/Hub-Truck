package demo19524;


public class T extends Truck {
	
	private Hub last;
	// the Hub from which it last exited. 
	public synchronized Hub getLastHub(){

	return last;

	 }

	private Highway highway;
	private boolean W=false;
	private boolean H=false;

	public synchronized void enter(Highway hwy){

		highway = hwy;
		last = hwy.getStart();

		W = true;
		H = false;
		
	}

	int i=1;

	private	synchronized void set1(Location loc, int deltaT) {

		int x = getLoc().getX();
		int y = getLoc().getY();

		int distance = (highway.getMaxSpeed())*deltaT/1000;

		Location L = new Location(x,y);
		Double Hdistance = Math.sqrt(loc.distSqrd(L));

		int H = Hdistance.intValue();

		int slope = y-loc.getY()/x-loc.getX();

		//System.out.println(slope);
		Double xf= slope/Math.sqrt(1+(slope*slope));
		Double yf= slope/Math.sqrt(1+(slope*slope));

		int o = y-loc.getY();
		int n = x-loc.getX();

		Double p=Double.valueOf(x);
		Double q=Double.valueOf(y);



		if(H > distance ){

			//System.out.println("b");
			//System.out.println((y-L.getY()));
			//System.out.println((x-L.getX()));

			if (o<0 && n<0){

				
				p = p - xf*distance;
				q = q - yf*distance;

				//System.out.println("a");
			}
			else if( o>0 && n>0){

				p = p + xf*distance;
				q = q + yf*distance;

				//System.out.println("a");
			}
			else if( o<0 && n>0 ){


				p = p - xf*distance;
				q = q + yf*distance;


				//System.out.println("a");
			}
			else if(  o>0 && n<0){

				p = p + xf*distance;
				q = q - yf*distance;

			}
			x = (int)Math.round(p);
			y = (int)Math.round(q);

			Location l = new Location(x, y);
		 	this.setLoc(l);
		 	//System.out.println(l.getX()+","+l.getY());
			
		}

		else{

			this.setLoc(loc);

		}	
		 
		 //System.out.println(currx);
		 //System.out.println(curry);
	}
	private synchronized void set2(Location loc, int deltaT) {

		
		int x = getLoc().getX();
		int y = getLoc().getY();

		int distance = (highway.getMaxSpeed())*deltaT/1000;
		//System.out.println(distance);
		Location L = highway.getEnd().getLoc();
		Double Hdistance = Math.sqrt(loc.distSqrd(L));

		 int H = Hdistance.intValue();

		 int j = x-L.getX();

		 int slope;

		 if (j!=0){	
		 
		 slope = loc.getY()-L.getY()/loc.getX()-L.getX();
		}

		else{slope=0;}

		Double xf= slope/Math.sqrt(1+(slope*slope));
		//System.out.println(xf);
		Double yf= slope/Math.sqrt(1+(slope*slope));
		//System.out.println(yf);


		Double p=Double.valueOf(x);
		Double q=Double.valueOf(y);

		if(H > distance ){
			//System.out.println((y-L.getY()));
			int o = y-L.getY();
			//System.out.println("b");
			int n=x-L.getX();
			//System.out.println((y-L.getY()));

			if( o>0 && n>0){


				p = p + xf*distance;
				q = q + yf*distance;

				//System.out.println("a");
			}
			else if(o<0&&n<0){

				

				p = p - xf*distance;
				q = q - yf*distance;

				
			}
			else if( o>0 && n<0){

				
				

				p = p - xf*distance;
				q = q + yf*distance;

				//System.out.println("a");
			}
			else if( o<0 && n>0){

				
				

				p =  p - xf*distance;
				q = q + yf*distance;

				
			}

			x = (int)Math.round(p);
			y = (int)Math.round(q);
			Location l = new Location(x, y);

		 	this.setLoc(l);
		 	//System.out.println(l.getX()+","+l.getY());
			
		}

		else{

			this.setLoc(loc);

		}

	}


	

	
	protected synchronized void update(int deltaT){

		if(this.getStartTime() < i*deltaT){

		

		if(this.getDest().getX() == this.getLoc().getX() && this.getDest().getY() == this.getLoc().getY()) {
			return;
		}
		

		if(!H && !W) {
			Hub hub = Network.getNearestHub(this.getLoc());
			if(this.getLoc().getX() == hub.getLoc().getX() && this.getLoc().getY() == hub.getLoc().getY()) {

				if(hub.add(this))
				{
					H = true;
					
				}
			}
			
			this.setLoc(hub.getLoc());
		}

		else if(W) {
			Hub hub = highway.getEnd();
			if(this.getLoc().getX() == hub.getLoc().getX() && this.getLoc().getY() == hub.getLoc().getY()) {

				if(hub.add(this))
				{
					H = true;
					W = false;
					highway.remove(this);
				}

			}
			
			//System.out.println("c");
			set1(hub.getLoc(), deltaT);
		}

		else if(H) {
            

            Hub hub = highway.getEnd();
			
			//if(this.getLoc().getX() == hub.getLoc().getX() && this.getLoc().getY() == hub.getLoc().getY()){
			

				if(hub.add(this))
				{
					H = false;
					W = true;
					highway.remove(this);
				}

			//}
            System.out.println("b");
            set2(last.getLoc(), deltaT);
           	
            
		}

		}
		i=i+1;

	}	

}
		
		
		

	
	
