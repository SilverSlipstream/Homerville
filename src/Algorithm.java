import java.util.ArrayList;

public class Algorithm {

    private static ArrayList<Double[]> data, editedData;
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

    public static double[] trial (int totalTrucks) //does the actual algorithm
    {
        editedData = data;
        int len;
        ArrayList<Double[]> oldLoc = new ArrayList<>();
        Double[] truck1 = {249.0, 219.5},
                truck2 = {250.0, 209.5},
                truck3 = {249.0, 209.5},
                truck4 = {250.0, 219.5};
        switch (totalTrucks)
        {
            case 4: oldLoc.add(0, truck4);
            case 3: oldLoc.add(0, truck3);
            case 2: oldLoc.add(0, truck2);
            case 1: oldLoc.add(0, truck1);
        }
        double[] newLoc;
        ArrayList<Double> dist = new ArrayList<>();
        double distance;
        if (totalTrucks == 3)
        {
            for (int j = 0; j< totalTrucks; j++)
            {
                newLoc = getFarthestLoc(oldLoc.get(j)[0], oldLoc.get(j)[1]);
            }
        }
        len = editedData.size();
        for (int i = 0; i < len; i++)
        {
            for (int j = 0; j< totalTrucks; j++)
            {
                newLoc = getClosestLoc(oldLoc.get(j)[0], oldLoc.get(j)[1], j, totalTrucks);
                distance = Math.abs(newLoc[0] - (oldLoc.get(0)[0]));
                dist.set(j, dist.get(j) + distance);
            }

        }
        double[] ret = {};
        return ret;
    }

    public static double[] getClosestLoc(double currentx, double currenty, int truckNum, int totalTrucks)
    {
        double minDist = editedData.get(0)[0] + editedData.get(0)[1];
        int point = 0; //to identify which point we're going to and which to remove

            double [] closestpos = new double[2];
        for (int i = 0; i < editedData.size(); i++)
        {
            if(Math.abs(editedData.get(i)[0] - currentx) + Math.abs(editedData.get(i)[0] - currenty) < minDist)
            {
                minDist = Math.abs(editedData.get(i)[0] - currentx) + Math.abs(editedData.get(i)[1] - currenty);
                closestpos[0] = editedData.get(i)[0];
                closestpos[1] = editedData.get(i)[1];
                point = i;
            }
        }
        editedData.remove(editedData.get(point));

        return closestpos;
    }
    public static double[] getFarthestLoc(double currentx, double currenty)
    {
        double maxDist = editedData.get(0)[0] + editedData.get(0)[1];
        int point = 0; //to identify which point we're going to and which to remove

        double [] farthestpos = new double[2];
        for (int i = 0; i < editedData.size(); i++)
        {
            if(Math.abs(editedData.get(i)[0] - currentx) + Math.abs(editedData.get(i)[0] - currenty) > maxDist)
            {
                maxDist = Math.abs(editedData.get(i)[0] - currentx) + Math.abs(editedData.get(i)[1] - currenty);
                farthestpos[0] = editedData.get(i)[0];
                farthestpos[1] = editedData.get(i)[1];
                point = i;
            }
        }
        editedData.remove(editedData.get(point));

        return farthestpos;
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


