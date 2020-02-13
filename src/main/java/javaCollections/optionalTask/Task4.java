package javaCollections.optionalTask;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Task4 {
    public static void main(String[] args) throws FileNotFoundException {
        List<String> list = new ArrayList<>();
        list.add("В лесу родилась елочка,");
        list.add("В лесу она росла.");
        list.add("Зимой и летом стройная,");
        list.add("Зеленая была.");
        list.sort(((o1, o2) -> o1.length() - o2.length()));
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) { System.out.println(iterator.next()); }
    }
}
