package src;
//import java.util.*;

public class Main {
	public static void main(String[] args)
	{
		Integer[][] data = Cycle.coords(Cycle.setCycle(1));
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