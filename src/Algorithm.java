import java.util.ArrayList;

public class Algorithm {
    private static ArrayList<Double[]> data, editedData;
    private static int trialCount = 4;

    public Algorithm(ArrayList<Double[]> data)
    {
        this.data = data;
    }

    public ArrayList<ArrayList<Integer>> trial (int totalTrucks) //does the actual algorithm
    {
        editedData = data;
        int len;

        double[][] oldLoc = new double[totalTrucks][2];//placeholder
        double[] newLoc;//placeholder
        ArrayList<Integer> dist = new ArrayList<>();//returning
        ArrayList<Integer> housesVisited = new ArrayList<>();//returning
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
            dist.add(0);//initialize dist
            housesVisited.add(0);//initialize housesVisited
        }

        if (totalTrucks == 3)//if there's 3 trucks, each truck goes from 1 side of town to the other
        {
            for (int j = 0; j < totalTrucks; j++)
            {
                newLoc = getFarthestLoc(oldLoc[j][0], oldLoc[j][1]);
                distance = (int)(Math.abs(newLoc[0] - (oldLoc[j][0])) + Math.abs(newLoc[1] - (oldLoc[j][1])));
                dist.set(j, dist.get(j) + distance);
                housesVisited.set(0, 1);
            }
        }

        len = editedData.size();
        int truckCounter = 0;
        for (int i = 0; i < len; i++)
        {
            newLoc = getClosestLoc(oldLoc[truckCounter][0], oldLoc[truckCounter][1]);
            distance = (int)(Math.abs(newLoc[0] - (oldLoc[truckCounter][0])) + Math.abs(newLoc[1] - (oldLoc[truckCounter][1])));
            dist.set(truckCounter, dist.get(truckCounter) + distance);
            housesVisited.set(truckCounter, housesVisited.get(truckCounter) + 1);
            if (++truckCounter == totalTrucks)//this increments truckCounter
            {
                truckCounter = 0;
            }
        }

        ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
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
        int[] cheapest = summarizeData(1, trials.get(0)); //initialized to trial 1 total cost
        int whichIsBest = 0; //after calculations, can determine how many trucks were rented for the cheapest solution, and access complete cost data
        int[] next;
        for (int i = 1; i < trialCount; i++) //keeps calculating total cost and compares
        {
            next = summarizeData(i, trials.get(i));
            if (next[0] < cheapest[0] && next[1] <= 24) {

            }
        }
        return trials.get(whichIsBest);
    }

    public static int[] summarizeData(int truckCount, ArrayList<ArrayList<Double>> data) //takes in trials data from alg() and returns total cost
    {
        ArrayList<Double> totalDistance = data.get(0);
        ArrayList<Double> housesVisited = data.get(0);

        //miles driven per truck (rounding up)
        ArrayList<Integer> milesPerTruck = new ArrayList<>();
        double miles;
        for (int i = 0; i < totalDistance.size(); i++)
        {
            miles = totalDistance.get(i) / 5000;
            milesPerTruck.set(i, (int) Math.ceil(miles));
        }

        //time taken per truck
        double[] time = new double[totalDistance.size()];
        double minutes;
        for (int i = 0; i < totalDistance.size(); i++)
        {
            minutes = 0.05*totalDistance.get(i) + 1.0*housesVisited.get(i);
            time[i] = minutes/60; //to hours
        }

        //calculations
        //gas
        int gas = 0;
        for (int i = 0; i < milesPerTruck.size(); i++)
        {
            gas += 5.00*milesPerTruck.get(i);
        }

        //employees
        int employees = 0;
        for (int i = 0; i < time.length; i++)
        {
            if (Math.ceil(time[i]) > 8)
            {
                employees += 30*8 + 45*Math.ceil(time[i]-8);
            }
            else
            {
                employees += 30*Math.ceil(time[i]);
            }
        }

        //trucks
        int trucks = 15000*truckCount;

        //maintenance
        int maintenanceJobs = 0;
        for (int i = 0; i < milesPerTruck.size(); i++)
        {
            maintenanceJobs += 1000*Math.floor(milesPerTruck.get(i) / 100);
        }

        return new int[0];//delete this
    }
}