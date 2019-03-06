package src;

import java.util.*;
import java.io.*;


public class Cycle
{
    public static ArrayList<String[]> setCycle(int x)
    {
        ArrayList<String[]> cyc = new ArrayList<String[]>();
        int bart;
        int lisa;
        try {
            File file = new File("cycledata/cycle"+x+".txt");
            Scanner scanner = new Scanner(file);
            int count = 1;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.equals("Bart Complex")) {
                    bart = Integer.valueOf(scanner.nextLine());
                } else if (line.equals("Lisa Complex")) {
                    lisa = Integer.valueOf(scanner.nextLine());
                } else if (count > 2) {
                    String[] arr = line.split(",");
                    arr[0] = arr[0].substring(0,arr[0].length()-1);
                    arr[1] = arr[1].substring(0,arr[1].length()-1);
                    
                   
                    //System.out.println(Arrays.toString(arr));
                    cyc.add(arr);
                }
                count++;
                // arr[0] = line.substring(0,line.find('s'));
                // arr[1] = line.substring(line.find(',')+1,line.find('a'));
                // arr[2] = line.substring(line.find('a')+1);
                
            }
            scanner.close();
            
        
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        return cyc;
    }

    public static Integer[][] coords(ArrayList<String[]> al)
    {
        Integer[][] array = new Integer[al.size()][2];
        char letter;
        int house;
        for (int i=0;i<array.length;i++)
        {
            letter = al.get(i)[2].charAt(0);
            house = returnnumberbasedonletter(letter);
            array[i][0] = Integer.parseInt(al.get(i)[0]) * 2;
            array[i][1] = Integer.parseInt(al.get(i)[1]) * 10 + house;
            
        }
        return array;
    }
    public static int returnnumberbasedonletter(char letter)
    {
        letter = Character.toLowerCase(letter);
        int temp = (int)letter;
        int temp_integer = 96; //for lower case
       
        return temp-temp_integer;
    }
}
