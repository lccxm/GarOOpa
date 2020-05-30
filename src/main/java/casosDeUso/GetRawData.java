package casosDeUso;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class GetRawData {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(GetRawData.fromCSV("a.txt"));
    }
    public static List<String[]> fromCSV(String fileName) throws FileNotFoundException {
        List<String[]> lst = new LinkedList<>();
        File f = new File(fileName);
        Scanner in = new Scanner(f);
        String[] out;
        while(in.hasNextLine()){
            out = in.nextLine().split(",");
            lst.add(out);
        }
        return lst;
    }
}
