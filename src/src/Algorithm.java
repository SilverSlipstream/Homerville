package src;

public class Algorithm {
    private static int maxTests = 4;

    public int[] someFunction(int[][] cycle) //idk what to name this
    {
        int[][] trials = new int[maxTests][];
        for (int i = 0; i < maxTests; i++)
        {
            trials[i] = alg(cycle, i); //tests 0 to maxTests-1 rented trucks (ignore purchased trucks)
        }
        return optimize(trials);
    }
    public static int[] alg (int[][] cycle, int totalTrucks) //does the actual algorithm
    {
        return new int[0];//delete this
    }
    public static int calculate(int[] data) //takes in trials data from alg() and returns total cost
    {
        return 0;//delete this
    }
    public static int[] optimize(int[][] trials) //finds the smallest trial cost
    {
        int cheapest = calculate(trials[0]); //initialized to trial 1 total cost
        int next;
        int whichIsSmallest = 0; //after calculations, can determine how many trucks were rented for the cheapest solution, and access complete cost data
        for (int i = 1; i < maxTests; i++) //keeps calculating total cost and compares
        {
            next = calculate(trials[i]);
            if (next < cheapest){
                cheapest = next;
                whichIsSmallest = i;
            }
        }
        return trials[whichIsSmallest];
    }
}
