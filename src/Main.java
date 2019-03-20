import java.util.ArrayList;
import java.util.Arrays;

public class Main {
	class DoubleArrayList extends ArrayList<Double> {}
	public static void main(String[] args)
	{
		int trialCount = 4;
		Cycle cycl = new Cycle(1);
        ArrayList<String[]> cycleData = cycl.getData();
        Double[][] arr = cycl.coords(cycleData);
		ArrayList<Double[]> data = new ArrayList<>(Arrays.asList(arr));
        Algorithm al = new Algorithm(data);
		ArrayList<Double>[][] trials = new DoubleArrayList[trialCount][2];

		for (int i = 1; i < trialCount; i++)//starts at 1 rented truck bc ignoring purchases
		{
			trials[i-1] = al.trial(i); //tests 0 to trialCount-1 rented trucks (ignore purchased trucks)
		}
		al.optimize(trials);

	}
}