public class NBody{
	public static double readRadius(String filepath){
		In in = new In(filepath);
		int numberofplanets = in.readInt();
		double radius = in.readDouble();
		return radius;
	}

	public static Planet[] readPlanets(String filepath){
		In in = new In(filepath);
		int numberofplanets = in.readInt();
		double radius = in.readDouble();
        Planet[] allplanets = new Planet[numberofplanets];
        for (int i =0; i < numberofplanets; i += 1) {
        	double xp = in.readDouble();
        	double yp = in.readDouble();
        	double xv = in.readDouble();
        	double yv = in.readDouble();
        	double m = in.readDouble();
        	String imgFN = in.readString();    
        	allplanets[i] = new Planet(xp,yp,xv,yv,m,imgFN);	
        }
        return allplanets;
	}

	public static void main(String args[]){
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		Planet[] planets = readPlanets(filename);
		double radius = readRadius(filename);

		StdDraw.enableDoubleBuffering();
		StdDraw.setScale(-radius, radius);
		StdDraw.clear();


		double t = 0.0;
		double[] xForces = new double[planets.length];
		double[] yForces = new double[planets.length];
		while(t < T){
			for(int i = 0; i < planets.length; i += 1){
				double xfi = planets[i].calcNetForceExertedByX(planets);
				xForces[i] = xfi;
				double yfi = planets[i].calcNetForceExertedByY(planets);
				yForces[i] = yfi;
			}
			for(int i = 0; i < planets.length; i += 1){
				planets[i].update(dt,xForces[i],yForces[i]);
			}
			StdDraw.picture(0, 0, "images/starfield.jpg");
			for(int i = 0; i < planets.length; i += 1){
				planets[i].draw();
			}
		
		StdDraw.show();
		StdDraw.pause(10);
		t = t + dt;
		}
		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
		}
	}
}