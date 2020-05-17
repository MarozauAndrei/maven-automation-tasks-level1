package task22;

import java.io.*;
import java.util.ArrayList;

public class UpperCase {
    public static void main(String[] args) {
        File dir = new File(DirectoryNames.DIRECTORY_WITH_RESULTS);
        if (!dir.exists()) {
            dir.mkdir();
        }
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/java/javaCollections/mainTask/Taxipool.java"));
             FileWriter fileWriter = new FileWriter("src/main/java/task.2.2.result/uppercase.txt")) {
            ArrayList<String> strings = new ArrayList<>();
            bufferedReader.lines().forEach(strings::add);

            for (String str : strings) {
                ArrayList<Integer> indexOfRegex = new ArrayList<>();
                indexOfRegex.add(0);
                char[] symbols = str.toCharArray();
                for (int i = 0; i < symbols.length; i++) {
                    if (symbols[i] == ' ' || symbols[i] == ',' || symbols[i] == '.' || symbols[i] == ';' || symbols[i] == '(' || symbols[i] == ')' || symbols[i] == '!' || symbols[i] == '=') {
                        indexOfRegex.add(i + 1);
                    }
                }
                indexOfRegex.add(symbols.length + 1);
                for (int i = 0; i < indexOfRegex.size() - 1; i++) {
                    if (indexOfRegex.get(i + 1) - indexOfRegex.get(i) > 3) {
                        for (int j = indexOfRegex.get(i); j < indexOfRegex.get(i + 1) - 1; j++) {
                            symbols[j] = Character.toUpperCase(symbols[j]);
                        }
                    }
                }
                for (char symbol : symbols) {
                    fileWriter.write(symbol);
                }
                fileWriter.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
