package task22;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StructureOfFiles {
    public static void main(String[] args) throws IOException {

        System.out.println("Write path to directory or file");     // for example "src/main/java" or "src/main/java/task.2.2.result/tree.txt"
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String pathToFile = reader.readLine();
        File dir = new File(pathToFile);
        if (dir.exists() && dir.isDirectory()) {
            new StructureOfFiles().writeDirectoryTree(pathToFile);
        } else {
            if (dir.exists() && dir.isFile()) {
                new StructureOfFiles().getFileContent(dir);
            } else {
                System.out.println("You've written wrong path");
            }
        }
    }

    private void writeDirectoryTree(String pathToFile) throws IOException {
        File dir = new File(DirectoryNames.DIRECTORY_WITH_RESULTS);
        if (!dir.exists()) {
            dir.mkdir();
        }
        File file = new File("src/main/java/task.2.2.result/tree.txt");
        try (Stream<Path> walk = Files.walk(Paths.get(pathToFile));
             FileWriter fileWriter = new FileWriter(file)) {
            List<String> listOfPath = walk.map(Path::toString).collect(Collectors.toList());
            listOfPath.forEach(x -> {
                try {
                    if (new File(x).getPath().equals(new File(pathToFile).getPath())) {
                        fileWriter.write(new StringBuilder().append(new File(x).getName()).append("\n").toString());
                    } else {
                        if (new File(x).isDirectory()) {
                            fileWriter.write(new StringBuilder().append("\n").append("|---").append(new File(x).getName()).append("\n").toString());
                        } else {
                            if (new File(x).isFile()) {
                                fileWriter.write(new StringBuilder().append("|   ").append(new File(x).getName()).append("\n").toString());
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
        System.out.println(new StringBuilder().append("\n").append("Results of program in file: ").append(file.getPath()));
    }

    private void getFileContent(File file) {
        ArrayList<String> arrayOfStrings = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            bufferedReader.lines().forEach(arrayOfStrings::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int countOfDirectories = 0;
        int countOfFiles = 0;
        int lengthNameOfDirectory = 0;
        for (String string : arrayOfStrings) {
            char[] symbols = string.toCharArray();
            if (symbols.length > 4 && symbols[0] == '|' && symbols[1] == '-' && symbols[2] == '-' && symbols[3] == '-') {
                countOfDirectories++;
                lengthNameOfDirectory += symbols.length - 4;
            } else {
                if (symbols.length > 4 && symbols[0] == '|' && symbols[1] == ' ' && symbols[2] == ' ' && symbols[3] == ' ') {
                    countOfFiles++;
                }
            }
        }
        System.out.println(new StringBuilder().append("\n").append("Directory -").append(arrayOfStrings.get(0)).append("- includes:"));
        System.out.println("Count of directories = " + countOfDirectories);
        System.out.println("Count of files = " + countOfFiles);
        if (countOfDirectories > 0) {
            System.out.println("Average count of files in directory = " + (countOfFiles / countOfDirectories));
            System.out.println("Average length of name of file = " + (lengthNameOfDirectory / countOfDirectories));
        }
    }
}
