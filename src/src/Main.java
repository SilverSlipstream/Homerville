package src;
//import java.util.*;

import java.util.ArrayList;

public class Main {
	public static void main(String[] args)
	{
		Cycle cycl = new Cycle(1);
		ArrayList<String[]> cycleData = cycl.getData();
		Double[][] data = cycl.coords(cycleData);
		for (int i = 0; i < data.length; i++)
		{
    		for (int j = 0; j < data[i].length; j++)
    		{
        		System.out.print(data[i][j] + " ");
    		}
    		System.out.println();
		}
	}
}