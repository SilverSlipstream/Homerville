import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;

public class Cycle
{
    public ArrayList<String[]> setCycle(int x)
    {
        ArrayList<String[]> cyc = new ArrayList<String[]>();
        String line;
        String[] arr = new String[3];
        try {
            File file = new File("cycle"+x+".txt");
            Scanner scanner = new Scanner(file);
            int i = 0;
            while (scanner.hasNext()) {
                if (i>1) {
                    line = scanner.next();
                    arr[0] = line.substring(0,line.indexOf("s"));
                    arr[1] = line.substring(line.indexOf(",")+1,line.indexOf("a"));
                    arr[2] = line.substring(line.indexOf("a")+2);
                    cyc.add(arr);
                }
                else {
                    i++;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return cyc;
    }
}
