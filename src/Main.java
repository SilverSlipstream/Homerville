//import java.util.*;

import java.util.ArrayList;

public class Main {
	public static void main(String[] args)
	{
		Cycle cycl = new Cycle(1);
        ArrayList<String[]> cycleData = cycl.getData();
        Double[][] data = cycl.coords(cycleData);
        Algorithm al = new Algorithm(data);
		
		for (int i = 0; i < data.length; i++)
		{
			System.out.println("Dev Locations for this point");
            int x = 1;
    		for (int j = 0; j < data[i].length; j++)
    		{
    			if((j == 0 )|| (j == 2) || (j == 4))
    			{
    			System.out.println("Dev" + x);
    			System.out.println("X");
                x++;
    			}
    			else
    			{
    				System.out.println("Y");
    			}
        		System.out.println(data[i][j] + " ");
    		}
    		
		}
        double [] closestpos = al.getClosestLoc(1, 1);
        System.out.println(closestpos[0]);
        System.out.println(closestpos[1]);

	}
}