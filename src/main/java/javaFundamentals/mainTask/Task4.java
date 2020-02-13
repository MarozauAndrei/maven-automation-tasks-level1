package javaFundamentals.mainTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Task4 {
    public static void main(String[] args) throws Exception {
        ArrayList<Integer> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String s = reader.readLine();
            if (s.isEmpty()) break;
            else {
                Integer number = Integer.parseInt(s);
                list.add(number);
            }
        }
        Integer sum = 0, proizv = 1;
        for (int i = 0 ; i < list.size() ; i++) {
            sum += list.get(i);
            proizv *= list.get(i);
        }
        System.out.println("Сумма аргументов = " + sum);
        System.out.println("Произведение аргументов = " + proizv);
    }
}
