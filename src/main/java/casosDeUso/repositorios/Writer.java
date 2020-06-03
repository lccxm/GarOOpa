package casosDeUso.repositorios;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
class Writer{
public static void main(String args[]) throws IOException {
    List<String[]> lst = new ArrayList<>();
    String[] a = {"advsasd", "fgabdfafdha", "fbadfhbadhaaaaaa"};
    String[] b = {"bfdbf", "brtnb", "bsb"};
    lst.add(a);
    lst.add(b);
    Writer.toCSV("blaaa.txt", lst);

}

public static void toCSV(String fileName, List<String[]> lst) throws IOException {
    File file = new File(fileName);
    FileWriter writer = new FileWriter(file);
    for (String[] l : lst){
        for (int i=0; i<l.length; i++){
            writer.write(l[i]);
            writer.write(",");
        }
        writer.write("\n");              
    }
    writer.close();
}
}