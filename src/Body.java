
public class Body {

	private double myXPos;
	private double myYPos;
	private double myXVel;
	private double myYVel;
	private double myMass;
	private String myFileName;
	
	final double G = 6.67e-11;
	
	public Body(double xp, double yp, double xv, double yv, 
		        double mass, String fileName) {
		
			myXPos = xp;
			myYPos = yp;
			myXVel = xv;
			myYVel = yv;
			myMass = mass;
			myFileName = fileName;
		
	}
	
	public Body(Body b) {
		
		myXPos = b.getX();
		myYPos = b.getY();
		myXVel = b.getXVel();
		myYVel = b.getYVel();
		myMass = b.getMass();
		myFileName = b.getName();
		
	}
	
	public double getX() {
		return myXPos;
	}
	public double getY() {
		return myYPos;
	}
	public double getXVel() {
		return myXVel;
	}
	public double getYVel() {
		return myYVel;
	}
	public double getMass() {
		return myMass;
	}
	public String getName() {
		return myFileName;
	}
	
	public double calcDistance(Body b) {
		
		return Math.sqrt(Math.pow((b.getX() - myXPos), 2) + Math.pow(b.getY() - myYPos, 2));
		
	}
	
	public double calcForceExertedBy(Body p) {
		
		return (G * (p.getMass() * myMass) / Math.pow(calcDistance(p), 2));
		
	}
	
	public double calcForceExertedByX(Body p) {
		
		double Fx;
		double dx = p.getX() - myXPos;
		
		Fx = (calcForceExertedBy(p) * dx) / calcDistance(p);
		return Fx;
	}
	
    public double calcForceExertedByY(Body p) {
		
		double Fy;
		double dy = p.getY() - myYPos;
		
		Fy = (calcForceExertedBy(p) * dy) / calcDistance(p);
		return Fy;
	}
    
    public double calcNetForceExertedByX(Body[] bodies) {
    	
    	double netFx = 0;
    	
    	for(Body b: bodies) {
    		
    		netFx += calcForceExertedByX(b);
    		
    	}
    	
    	return netFx;
    	
    }

    public double calcNetForceExertedByY(Body[] bodies) {
    	
    	double netFy = 0;
    	
    	for(Body b: bodies) {
    		
    		netFy += calcForceExertedByY(b);
    		
    	}
    	
    	return netFy;
    	
    }
    
    public void update(double deltaT, double xforce, double yforce) {
    	
    	double ax = xforce / myMass;
    	double ay = yforce / myMass;
    	
    	double nvx = myXVel + (deltaT * ax);
    	double nvy = myYVel + (deltaT * ay);
    	
    	double nx = myXPos + (deltaT * nvx);
    	double ny = myYPos + (deltaT * nvy);
    	
    	myXVel = nvx;
    	myYVel = nvy;
    	myXPos = nx;
    	myYPos = ny;
    	
    }
    
    public void draw() {
    	
    	StdDraw.picture(myXPos, myYPos, "images/" + myFileName);
    	
    }
}
