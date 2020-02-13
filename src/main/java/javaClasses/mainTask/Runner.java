package javaClasses.mainTask;

import java.util.Calendar;

public class Runner {
    public static void main(String[] args) {
        Car [] array = new Car[6];
        array [0] = new Car(1, "BMW", "525", 2005, "black", 4000.0, "EA5656");
        array [1] = new Car(2, "Opel", "Vectra", 2005, "purple", 2500.0, "EA3245");
        array [2] = new Car(3, "FW", "Passat", 2005, "grey", 3500.0, "EA3395");
        array [3] = new Car(4, "FW", "Tiguan", 2009, "black", 1100.0, "EA6699");
        array [4] = new Car(5, "FW", "Tiguan", 2015, "white", 1500.0, "EA6104");
        array [5] = new Car(6, "FW", "Tiguan", 2017, "pink", 1800.0, "EA1127");

        Calendar calendar = Calendar.getInstance(java.util.TimeZone.getDefault(), java.util.Locale.getDefault());
        calendar.setTime(new java.util.Date());
        int currentYear = calendar.get(java.util.Calendar.YEAR);

        System.out.println("Список автомобилей марки FW:");
        for (int i = 0; i < array.length; i++) {
            if (array[i].mark.equals("FW")) System.out.println(array[i]);
        }
        System.out.println();

        System.out.println("Список автомобилей Tiguan, которые эксплуатируются больше 3 лет:");
        for (int i = 0; i < array.length; i++) {
            if (array[i].model.equals("Tiguan") && (currentYear - array[i].year) > 3 ) System.out.println(array[i]);
        }
        System.out.println();

        System.out.println("Список автомобилей 2005 года, цена которых больше 3000:");
        for (int i = 0; i < array.length; i++) {
            if (array[i].year == 2005 && array[i].price > 3000) System.out.println(array[i]);
        }
    }
}
