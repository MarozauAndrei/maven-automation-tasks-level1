package javaFundamentals.optionalTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Task1 {
    public static void main(String[] args) throws Exception {
        System.out.println("Введите количество чисел");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int[] numbers = new int[n];
        int[] lengthNumbers = new int[n];
        System.out.println("Введите " + n + " чисел");
        for (int i = 0; i < n; i++) numbers[i] = Integer.parseInt(reader.readLine()); // создаем массив исходных чисел
        for (int i = 0; i < n; i++) lengthNumbers[i] = getCountsOfDigits(numbers[i]); // создаем массив длин чисел
//        for (int i=0 ; i<n ; i++) System.out.println(numbers[i] + "   " + lengthNumbers[i]);; // вывод исходых чисел и их длин
        int minLength = lengthNumbers[0], minLengthNumber = numbers[0], maxLength = lengthNumbers[0], maxLengthNumber = numbers[0];
        double averageLength = 0.0 + lengthNumbers[0];
        for (int i = 1; i < n; i++) {
            averageLength += lengthNumbers[i];
            if (lengthNumbers[i] < minLength) {
                minLength = lengthNumbers[i];
                minLengthNumber = numbers[i];
            }
            if (lengthNumbers[i] > maxLength) {
                maxLength = lengthNumbers[i];
                maxLengthNumber = numbers[i];
            }
        }
        averageLength /= n;
        int changeNumber, changeLength;
        for (int i = 0; i < n-1; i++) {
            for (int j = i+1; j < n; j++) {
                if (lengthNumbers[i] > lengthNumbers[j]) {
                    changeNumber = numbers[i];
                    changeLength = lengthNumbers[i];
                    numbers[i] = numbers[j];
                    lengthNumbers[i] = lengthNumbers[j];
                    numbers[j] = changeNumber;
                    lengthNumbers[j] = changeLength;
                }
            }
        }

        System.out.println();
        System.out.println("Пункт 1:");
        System.out.println("Самое короткое число " + minLengthNumber + " , количество его символов = " + minLength);
        System.out.println("Самое длинное число " + maxLengthNumber + " , количество его символов = " + maxLength);
        System.out.println();
        System.out.println("Пункт 2: Вывод чисел в порядке возрастания их длины:");
        for (int i = 0; i < n; i++) System.out.print(numbers[i] + "  ");
        System.out.println();
        System.out.println();
        System.out.println("Пункт 3: Вывод чисел, длина которых больше средней длины по всем числам ");
//        System.out.println("Средняя длина = " + averageLength);
        for (int i = 0; i < n; i++) {
            if (lengthNumbers[i] > averageLength)
                System.out.println("Число: " + numbers[i] + " Длина: " + lengthNumbers[i]);
        }

    }
    public static int getCountsOfDigits(int number) {    // метод определения длины числа
        int count = (number == 0) ? 1 : 0;
        while (number != 0) {
            count++;
            number /= 10;
        }
        return count;
    }
}
