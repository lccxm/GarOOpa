package casosDeUso;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class GetRawData {
    public static void main(String[] args) throws FileNotFoundException {
        List<String[]> lst = GetRawData.fromCSV("a.txt");
        System.out.println(lst.get(0)[2]);
    }
    public static List<String[]> fromCSV(String fileName) throws FileNotFoundException {
        String pathPrefix = "../resources/";
        fileName = pathPrefix + fileName;
        List<String[]> lst = new LinkedList<>();
        File f = new File(fileName);
        Scanner in = new Scanner(f);
        String[] out;
        while(in.hasNextLine()){
            out = in.nextLine().split(",");
            lst.add(out);
        }
        in.close();
        return lst;
    }
}
