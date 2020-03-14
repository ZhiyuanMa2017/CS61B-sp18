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
}