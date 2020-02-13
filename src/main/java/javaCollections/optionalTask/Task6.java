package javaCollections.optionalTask;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Task6 {
    public static void main(String[] args) throws FileNotFoundException {
        List<String> list = new ArrayList<>();
        Scanner in = new Scanner(new File("e:/java_source.txt"));
        while (in.hasNextLine()) { list.add(in.nextLine()); }
        Collections.sort(list);
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) { System.out.println(iterator.next()); }
    }
}
