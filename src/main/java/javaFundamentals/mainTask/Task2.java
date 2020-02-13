package javaFundamentals.mainTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Task2 {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> list = new ArrayList<String>();

        while (true) {
            String s = reader.readLine();
            if (s.isEmpty()) break;
            else list.add(s);
        }
        for (int i = list.size() ; i>0 ; i--)
            System.out.println(list.get(i-1));
    }
}
