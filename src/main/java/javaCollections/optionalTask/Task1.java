package javaCollections.optionalTask;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Task1 {
        public static void main(String[] args) throws FileNotFoundException {
        Deque<String> stack = new ArrayDeque<>();
        Scanner in = new Scanner(new File("e:/java_source.txt"));
        while (in.hasNextLine()) { stack.push(in.nextLine()); }

        PrintStream fileOut = new PrintStream("e:/java_result.txt");
        while (!stack.isEmpty()) {fileOut.println(stack.pop()); }
        fileOut.close();
    }
}
