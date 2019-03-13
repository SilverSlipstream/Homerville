package src;

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
                } else if (count > 2) {
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
        Double  [][] array = new Double[al.size()][2];
        char letter;
        int house;
        double street;
        for (int i=0;i<array.length;i++)
        {
            street = (al.get(i)[2].length() == 1) ? 0.5 : -0.5;     //if house name is single letter, west side.
                                                                    //therefore, street is to the east, and +0.5 distance east
            letter = al.get(i)[2].charAt(0);
            house = numToLetter(letter);
            array[i][0] = Double.parseDouble(al.get(i)[0]) * 2 + street;
            array[i][1] = Double.parseDouble(al.get(i)[1]) * 10 + house;

        }
        return array;
    }
    public static int numToLetter(char letter)
    {
        letter = Character.toLowerCase(letter);
        int temp = (int)letter;
        int temp_integer = 96; //for lower case

        return temp-temp_integer;
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
