package task22;

import java.io.*;

public class ReplaceMain {
    public static void main(String[] args) {
        File dir = new File("src/main/java/task.2.2.result");
        if (!dir.exists()) {
            dir.mkdir();
        }
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/java/javaCollections/mainTask/Taxipool.java"));
             FileWriter fileWriter = new FileWriter("src/main/java/task.2.2.result/replace.txt")) {
            bufferedReader.lines().map(s->s.replaceAll("public", "private")).forEach(s -> {
                try {
                    fileWriter.write(s + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
