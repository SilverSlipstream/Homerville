import java.util.ArrayList;

public class Algorithm {
    private static ArrayList<Double[]> data, editedData;
    private static int trialCount = 4;

    public Algorithm(ArrayList<Double[]> data)
    {
        this.data = data;
    }

    public ArrayList<ArrayList<Double>> trial (int totalTrucks) //does the actual algorithm
    {
        editedData = data;
        int len;

        double[][] oldLoc = new double[totalTrucks][2];//placeholder
        double[] newLoc;//placeholder
        ArrayList<Double> dist = new ArrayList<>();//returning
        ArrayList<Double> housesVisited = new ArrayList<>();//returning
        int distance;//placeholder

        double[] truck1 = {249.0, 219.5},
                truck2 = {250.0, 209.5},
                truck3 = {249.0, 209.5},
                truck4 = {250.0, 219.5};
        switch (totalTrucks)
        {
            case 4: oldLoc[3] = truck4;
            case 3: oldLoc[2] = truck3;
            case 2: oldLoc[1] = truck2;
            case 1: oldLoc[0] = truck1;
        }

        for (int i = 0; i < totalTrucks; i++)
        {
            dist.add(0.0);//initialize dist
        }

        if (totalTrucks == 3)//if there's 3 trucks, each truck goes from 1 side of town to the other
        {
            for (int j = 0; j < totalTrucks; j++)
            {
                newLoc = getFarthestLoc(oldLoc[j][0], oldLoc[j][1]);
                distance = (int)(Math.abs(newLoc[0] - (oldLoc[j][0])) + Math.abs(newLoc[1] - (oldLoc[j][1])));
                dist.set(j, dist.get(j) + distance);
                housesVisited.set(0, 1.0);
            }
        }

        len = editedData.size();
        int truckCounter = 0;
        for (int i = 0; i < len; i++)
        {
            newLoc = getClosestLoc(oldLoc[truckCounter][0], oldLoc[truckCounter][1]);
            distance = (int)(Math.abs(newLoc[0] - (oldLoc[i][0])) + Math.abs(newLoc[1] - (oldLoc[i][1])));
            dist.set(truckCounter, dist.get(truckCounter) + distance);
            housesVisited.set(truckCounter, housesVisited.get(truckCounter) + 1);
            if (++truckCounter == totalTrucks)//this increments truckCounter
            {
                truckCounter = 0;
            }
        }

        ArrayList<ArrayList<Double>> ret = new ArrayList<>();
        ret.add(dist);
        ret.add(housesVisited);
        return ret;
    }

    public static double[] getClosestLoc(double currentx, double currenty)
    {
        double minDist = editedData.get(0)[0] + editedData.get(0)[1];
        int point = 0; //to identify which point we're going to and which to remove
        double [] closestpos = new double[2];

        double dist;
        for (int i = 0; i < editedData.size(); i++)
        {
            dist = Math.abs(editedData.get(i)[0] - currentx) + Math.abs(editedData.get(i)[1] - currenty);
            if(dist < minDist)
            {
                minDist = dist;
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
        double dist;
        for (int i = 0; i < editedData.size(); i++)
        {
            dist = Math.abs(editedData.get(i)[0] - currentx) + Math.abs(editedData.get(i)[1] - currenty);
            if(dist > maxDist)
            {
                maxDist = dist;
                farthestpos[0] = editedData.get(i)[0];
                farthestpos[1] = editedData.get(i)[1];
                point = i;
            }
        }
        editedData.remove(editedData.get(point));

        return farthestpos;
    }

    public ArrayList<ArrayList<Double>> optimize(ArrayList<ArrayList<ArrayList<Double>>> trials) //finds the smallest trial cost
    {
        int[] cheapest = summarize(trials.get(0)); //initialized to trial 1 total cost
        int whichIsBest = 0; //after calculations, can determine how many trucks were rented for the cheapest solution, and access complete cost data
        int[] next;
        for (int i = 1; i < trialCount; i++) //keeps calculating total cost and compares
        {
            next = summarize(trials.get(i));
            if (next[0] < cheapest[0] && next[1] <= 24)
            {
                cheapest = next;
                whichIsBest = i;
            }
        }
        return trials.get(whichIsBest);
    }

    public static int[] summarize(ArrayList<ArrayList<Double>> data) //takes in trials data from alg() and returns total cost
    {
        ArrayList<Double> totalDistance = data.get(0);
        ArrayList<Double> housesVisited = data.get(0);

        //miles driven per truck (rounding up)
        ArrayList<Integer> milesPerTruck = new ArrayList<>();
        double miles;
        for (int i = 0; i < totalDistance.size(); i++)
        {
            miles = totalDistance.get(0) / 5000;
            if (miles % 1 != 0)//not whole number
            {
                miles += 1.0;
            }
            milesPerTruck.set(i, (int) miles);
        }

        //time taken per truck
        double[] time = new double[totalDistance.size()];
        for (int i = 0; i < totalDistance.size(); i++)
        {
            time[i] = 0.05*totalDistance.get(i) + 1.0*housesVisited.get(i);
        }

        //calculations
        //gas
        int gas = 0;
        for (int i = 0; i < milesPerTruck.size(); i++)
        {
            gas += 5.00*milesPerTruck.get(i);
        }

        //employees



        return new int[0];//delete this
    }
}


