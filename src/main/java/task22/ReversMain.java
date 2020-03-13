package task22;

import java.io.*;

public class ReversMain {
    public static void main(String[] args) {
        File dir = new File("src/main/java/task.2.2.result");
        if (!dir.exists()) {
            dir.mkdir();
        }
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/java/javaCollections/mainTask/Taxipool.java"));
             FileWriter fileWriter = new FileWriter("src/main/java/task.2.2.result/revers.txt")) {
            bufferedReader.lines().map(s-> new StringBuffer(s).reverse()).forEach(s -> {
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
