/**
 * @author Jack Wood
 * CS201
 * Body object for the Nbody assignment
 */

public class Body {

	private double myXPos;
	private double myYPos;
	private double myXVel;
	private double myYVel;
	private double myMass;
	private String myFileName;
	
	private final double G = 6.67e-11;
	
	/**
	 * Constructor for body object based on parameters
	 * @param xp: X position 
	 * @param yp: Y position
	 * @param xv: X velocity
	 * @param yv: Y velocity
	 * @param mass: mass of the body
	 * @param fileName: name of the file
	 * @return new body 
	 */
	public Body(double xp, double yp, double xv, double yv, 
		        double mass, String fileName) {
		
			myXPos = xp;
			myYPos = yp;
			myXVel = xv;
			myYVel = yv;
			myMass = mass;
			myFileName = fileName;
		
	}
	
	/**
	 * Default constructor for body
	 * @param b: creates body based on values of input body, b
	 * @return new body
	 */
	public Body(Body b) {
		
		myXPos = b.getX();
		myYPos = b.getY();
		myXVel = b.getXVel();
		myYVel = b.getYVel();
		myMass = b.getMass();
		myFileName = b.getName();
		
	}
	
	/**
	 * Getter method for x position
	 * @return double value that is the X Position of body
	 */
	public double getX() {
		return myXPos;
	}
	
	/**
	 * Getter method for y position
	 * @return double value that is the Y Position of body
	 */
	public double getY() {
		return myYPos;
	}
	
	/**
	 * Getter method for x velocity
	 * @return double value that is the X velocity of body
	 */
	public double getXVel() {
		return myXVel;
	}
	
	/**
	 * Getter method for y velocity
	 * @return double value that is the Y velocity of body
	 */
	public double getYVel() {
		return myYVel;
	}
	
	/**
	 * Getter method for mass
	 * @return double value that is the Mass of body
	 */
	public double getMass() {
		return myMass;
	}
	
	/**
	 * Getter method for name of file
	 * @return String that contains the file name
	 */
	public String getName() {
		return myFileName;
	}
	
	/**
	 * Calculates the distance from body b to the body the method is being called on
	 * @param b: body
	 * @return double value that is the distance from body b to the body the 
	 *         method is being called on
	 */
	public double calcDistance(Body b) {
		
		return Math.sqrt(Math.pow((b.getX() - myXPos), 2) + Math.pow(b.getY() - myYPos, 2));
		
	}
	
	/**
	 * Returns the force exerted by Body p on to the object the method is being called on
	 * @param p: Body that is exerting the force 
	 * @return double value that is the force exerted by Body p on to the object the 
	 *         method is being called on
	 */
	public double calcForceExertedBy(Body p) {
		
		return (G * (p.getMass() * myMass) / Math.pow(calcDistance(p), 2));
		
	}
	
	/**
	 * Returns the x-force exerted by Body p on the body that the method is being called on
	 * @param p: the Body exerting the x-force
	 * @return double value that is the x-force exerted by Body p on to the object the 
	 *         method is being called on
	 */
	public double calcForceExertedByX(Body p) {
		
		double Fx;
		double dx = p.getX() - myXPos;
		
		Fx = (calcForceExertedBy(p) * dx) / calcDistance(p);
		return Fx;
	}
	
	/**
	 * Returns the y-force exerted by Body p on the body that the method is being called on
	 * @param p: the Body exerting the y-force
	 * @return double value that is the y-force exerted by Body p on to the object the 
	 *         method is being called on
	 */
    public double calcForceExertedByY(Body p) {
		
		double Fy;
		double dy = p.getY() - myYPos;
		
		Fy = (calcForceExertedBy(p) * dy) / calcDistance(p);
		return Fy;
	}
    
    /**
     * Returns the net x-force by summing all x-forces from all other bodies
     * @param bodies: the array that contains all bodies
     * @return double value that is the net x-force acting on the body the method is being called
     */
    public double calcNetForceExertedByX(Body[] bodies) {
    	
    	double netFx = 0;
    	
    	for(Body b: bodies) {
    		if(! b.equals(this))
    			
    			netFx += calcForceExertedByX(b);
    		
    	}
    	
    	return netFx;
    	
    }

    /**
     * Returns the net x-force by summing all x-forces from all other bodies
     * @param bodies: the array that contains all bodies
     * @return double value that is the net x-force acting on the body the method is being called
     */
    public double calcNetForceExertedByY(Body[] bodies) {
    	
    	double netFy = 0;
    	
    	for(Body b: bodies) {
    		if(! b.equals(this))
    			netFy += calcForceExertedByY(b);
    		
    	}
    	
    	return netFy;
    	
    }
    
    
    /**
     * Updates the location of the body based on the time delay and forces
     * @param deltaT: the time delay
     * @param xforce: x-force acting on the body
     * @param yforce: y-force acting on the body
     */
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
    
    /**
     * Draws the body based on the x and y positions
     */
    public void draw() {
    	
    	StdDraw.picture(myXPos, myYPos, "images/" + myFileName);
    	
    }
}
