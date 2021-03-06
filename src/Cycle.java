import java.util.*;
import java.io.*;


public class Cycle
{
    private int bart, lisa;
    private ArrayList<String[]> cyc = new ArrayList<String[]>();

    public Cycle(int x)
    {
        try {
            File file = new File("cycledata/cycle"+x+".txt");
            Scanner scanner = new Scanner(file);
            int count = 1;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.equals("Bart Complex")){
                    bart = Integer.parseInt(scanner.nextLine());
                } else if (line.equals("Lisa Complex")) {
                    lisa = Integer.parseInt(scanner.nextLine());
                } else if (count > 1) {
                    String[] arr = line.split(",");
                    arr[0] = arr[0].substring(0,arr[0].length()-1); //street
                    arr[1] = arr[1].substring(0,arr[1].length()-1); //avenue
                    cyc.add(arr);
                }
                else {
                    count++;
                }
            }
            scanner.close();
        
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Double[][] coords(ArrayList<String[]> al)
    {
        Double[][] array = new Double[al.size()][2];
        char letter;
        double house;
        double street;
        for (int i=0;i<array.length;i++) {

            street = (al.get(i)[2].length() == 1) ? 0.5 : 0.5;

            letter = al.get(i)[2].charAt(0);
            house = letterToNum(letter);

            array[i][0] = (Double.parseDouble(al.get(i)[0]) - 1) * 2 + street;
            array[i][1] = (Double.parseDouble(al.get(i)[1]) - 1) * 10 + house;
        }
        return array;

    }

    public static int letterToNum(char letter)
    {
        letter = Character.toLowerCase(letter);
        int temp = (int)letter;
        int convertToInt = 96 + 1; //for lower case, the "+ 1" to start at 0

        return temp - convertToInt;
    }
    public ArrayList<String[]> getData()
    {
        return cyc;
    }
    public int getBart()
    {
        return bart;
    }
    public int getLisa()
    {
        return lisa;
    }
}
