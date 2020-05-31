public class Planet{
	public double xxPos, yyPos, xxVel, yyVel, mass;
	public String imgFileName;

	public Planet(double xP, double yP, double xV, 
		double yV, double m, String img){
		xxPos = xP;
		yyPos = yP; 
		xxVel = xV; 
		yyVel = yV; 
		mass = m;
		imgFileName = img;
	}

	public Planet(Planet p) {
		this.xxPos = p.xxPos;
		this.yyPos = p.yyPos;		
		this.xxVel = p.xxVel;		
		this.yyVel = p.yyVel;
		this.mass = p.mass;
		this.imgFileName = p.imgFileName;
	}

	public double calcDistance(Planet p){
		double xdistance = this.xxPos - p.xxPos;
		double ydistance = this.yyPos - p.yyPos;
		double distance = Math.sqrt(xdistance * xdistance + ydistance * ydistance);
		return distance;
	}

	public double calcForceExertedBy(Planet p){
		double distance = this.calcDistance(p);
		double force = 6.67e-11*this.mass*p.mass/(distance*distance);
		return force;
	}

	public double calcForceExertedByX(Planet p){
		double force = this.calcForceExertedBy(p);
		double xdistance = p.xxPos-this.xxPos;
		double distance = this.calcDistance(p);
		double xforce = force*xdistance/distance;
		return xforce;
	}

	public double calcForceExertedByY(Planet p){
		double force = this.calcForceExertedBy(p);
		double ydistance = p.yyPos-this.yyPos;
		double distance = this.calcDistance(p);
		double yforce = force*ydistance/distance;
		return yforce;
	}

	public double calcNetForceExertedByX(Planet[] allPlanets){
		double xnetforce = 0;
		for (int i = 0; i < allPlanets.length; i += 1) {
			if(this.equals(allPlanets[i])){
				continue;
			}
			else{
			xnetforce = xnetforce + this.calcForceExertedByX(allPlanets[i]);
			}
		}
		return xnetforce;
	}
	public double calcNetForceExertedByY(Planet[] allPlanets){
		double ynetforce = 0;
		for (int i = 0; i < allPlanets.length; i += 1) {
			if(this.equals(allPlanets[i])){
				continue;
			}
			else{
			ynetforce = ynetforce + this.calcForceExertedByY(allPlanets[i]);
			}
		}
		return ynetforce;
	}

	public void update(double dt,double fX,double fY){
		double ax = fX/this.mass;
		double ay = fY/this.mass;
		this.xxVel = this.xxVel + ax*dt;
		this.yyVel = this.yyVel + ay*dt;
		this.xxPos = this.xxPos + this.xxVel*dt;
		this.yyPos = this.yyPos + this.yyVel*dt;		
	}

	public void draw(){
		StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
	}
}
