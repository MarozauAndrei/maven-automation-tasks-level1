package task22;

import java.io.*;
import java.util.ArrayList;

public class WriterSortMain {
    public static void main(String[] args) {
        File dir = new File("src/main/java/task.2.2.result");
        if (!dir.exists()) {
            dir.mkdir();
        }
        File file = new File("src/main/java/task.2.2.result/input22.txt");
        int countOfNumbers = 10;
        try (FileWriter writer = new FileWriter(file)) {
            for (int i = 0; i < countOfNumbers; i++) {
                writer.write((int) (Math.random() * 100) + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
             FileWriter fileWriter = new FileWriter("src/main/java/task.2.2.result/output22.txt")) {
            ArrayList<Integer> numbers = new ArrayList<>();
            bufferedReader.lines().map(Integer::parseInt).forEach(numbers::add);
            numbers.stream().sorted().forEach(s -> {
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
