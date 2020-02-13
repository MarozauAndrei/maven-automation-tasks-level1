package javaCollections.optionalTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Task2 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int number = Integer.parseInt(reader.readLine());
        Deque<Integer> queueOfNumbers = new ArrayDeque<>();
        while (number % 10 > 0) {
            queueOfNumbers.offer(number % 10);
            number = number / 10;
        }
        while (!queueOfNumbers.isEmpty()) {
            System.out.print(queueOfNumbers.poll());
        }
    }
}
