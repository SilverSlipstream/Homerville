package src;

public class Algorithm {
    public static Double[][] data;
    public Algorithm(Double[][] data)
    {
        this.data = data;
    }
    public static double[] getClosestLoc(double currentx, double currenty)
    {
        double min= data[0][0] + data[0][1];
        int point = 0;
        
        double [] closestpos = new double[2];
        for (int i = 0; i < data.length; i++)
        {
            
            for (int j = 0; j < data[i].length; j+=2)
            {
                
                if((data[i][j] - currentx) + (data[i][j + 1] - currenty) < min)
                {
                    min = (data[i][j] - currentx) + (data[i][j + 1] - currenty);
                    closestpos[0]= data[i][j];
                    closestpos[1] = data[i][j+1];
                    point = i;
                }
            }
         
        
      
            
        
    }
    for (int j = 0; j < 6; j++)
         {
            data[point][j] = 0.0;
         }
      return closestpos;
   
}
/*

 private static int trialCount = 4;

    public int[] alg(int[][] cyc)
    {
        int[][] trials = new int[trialCount][];
        for (int i = 0; i < trialCount; i++)
        {
            trials[i] = trial(cyc, i); //tests 0 to trialCount-1 rented trucks (ignore purchased trucks)
        }
        return optimize(trials);
    }
    public static int[] trial (int[][] cycle, int totalTrucks) //does the actual algorithm
    {
        
        for (int i = 0; i < 10000; i++)
        {
            Double[] newLoc = getClosestLoc();

        }
        return new int[1];
    }
    public static int[] optimize(int[][] trials) //finds the smallest trial cost
    {
        int cheapest = totalCost(trials[0]); //initialized to trial 1 total cost
        int next;
        int whichIsSmallest = 0; //after calculations, can determine how many trucks were rented for the cheapest solution, and access complete cost data
        for (int i = 1; i < trialCount; i++) //keeps calculating total cost and compares
        {
            next = totalCost(trials[i]);
            if (next < cheapest){
                cheapest = next;
                whichIsSmallest = i;
            }
        }
        return trials[whichIsSmallest];
    }
    public static int totalCost(int[] data) //takes in trials data from alg() and returns total cost
    {
        return 0;//delete this
    }
    */ 

}


