package javaFundamentals.mainTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Task3 {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int number = Integer.parseInt(reader.readLine());
        int [] array = new int[number];
        for (int i = 0; i < number; i++) {
            array [i] = (int) ( Math.random() * 100 );
        }
        for (int i = 0; i < number; i++) {
            System.out.println(array[i]);
        }
        for (int i = 0; i < number; i++) {
            System.out.print(array[i] + " ");
        }
    }
}
