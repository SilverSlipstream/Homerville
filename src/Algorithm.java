import java.util.ArrayList;

public class Algorithm {

    private static ArrayList<Double[]> data;
    private static int trialCount = 4;

    public Algorithm(ArrayList<Double[]> data)
    {
        this.data = data;
    }

    public int[] alg(double[][] cyc)
    {
        int[][] trials = new int[trialCount][];
        for (int i = 1; i < trialCount; i++)//starts at 1 rented truck bc ignoring purchases
        {
            trials[i] = trial(i); //tests 0 to trialCount-1 rented trucks (ignore purchased trucks)
        }
        return optimize(trials);
    }

    public static int[] trial (int totalTrucks) //does the actual algorithm
    {
        int len = data.size();
        double[] oldLoc = {249.0, 219.5};
        double[] newLoc;
        for (int i = 0; i < len; i++)
        {
            newLoc = getClosestLoc(oldLoc[0], oldLoc[1]);

        }
        return new int[1];
    }

    public static double[] getClosestLoc(double currentx, double currenty)
    {
        double minDist = data.get(0)[0] + data.get(0)[1];
        int point = 0; //to identify which point we're going to and which to remove

        double [] closestpos = new double[2];
        for (int i = 0; i < data.size(); i++)
        {
            if(Math.abs(data.get(i)[0] - currentx) + Math.abs(data.get(i)[0] - currenty) < minDist)
            {
                minDist = Math.abs(data.get(i)[0] - currentx) + Math.abs(data.get(i)[1] - currenty);
                closestpos[0] = data.get(i)[0];
                closestpos[1] = data.get(i)[1];
                point = i;
            }
        }
        data.remove(data.get(point));

        return closestpos;
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
}


