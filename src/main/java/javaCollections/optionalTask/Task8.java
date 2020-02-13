package javaCollections.optionalTask;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Task8 {
    public static void main(String[] args) throws IOException {
        List<String> list = new ArrayList<>();
        Scanner in = new Scanner(new File("e:/java_source.txt"));
        while (in.hasNextLine()) { list.add(in.nextLine()); }
        Set<String > set = new HashSet<>();
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String [] str = iterator.next().split("[,;:.!?\\s]+");
            for (int i = 0; i<str.length ; i++) { set.add(str[i].toLowerCase()); }
        }
        System.out.println(set);
    }
}
