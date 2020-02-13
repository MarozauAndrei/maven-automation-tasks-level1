package javaCollections.mainTask;

import javaCollections.mainTask.cars.*;
import javaCollections.mainTask.types.Minivantype;
import javaCollections.mainTask.types.Outlandertype;
import javaCollections.mainTask.types.Sedantype;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Runner {
    static List<Taxicar> carList = Arrays.asList(
            new Sportcar("Ford GT40", 320, 20.5, 250000.0, 650),
            new Sportcar("Porshe 911 GT3", 315, 18.9, 290000.0, 710),
            new Sedan("Lincoln", 220, 13.5, 85000.5, Sedantype.LIMUSIN),
            new Sedan("Audi A4", 250, 12.3, 65000.0, Sedantype.CABRIOLET),
            new Sedan("Jaguar SX", 240, 13.0, 110800.4, Sedantype.WITH_PANORAMIC_ROOF),
            new Sedan("BMW", 240, 9.8, 35000, Sedantype.WITH_CLOSED_ROOF),
            new Minivan("Citroen Escape", 190, 10.5, 18000, Minivantype.EIGHT_SEATER_MINIVAN),
            new Minivan("Opel Zafira", 170, 11.0, 14000, Minivantype.TEN_SEATER_MINIVAN),
            new Outlander("Jeep Cheroky", 190, 15.8, 25000, Outlandertype.FIVE_DOOR_OUTLANDER, 400),
            new Outlander("FW Novaro", 200, 18.4, 39990.99, Outlandertype.TWO_DOOR_OUTLANDER, 1200),
            new Outlander("Mitsubishi Pajero", 170, 13.8, 40000, Outlandertype.THREE_DOOR_OUTLANDER, 170),
            new Outlander("Cadilac RX", 220, 19.0, 54000, Outlandertype.FIVE_DOOR_OUTLANDER, 350)
    );
    public static void main(String[] args) {
        Taxipool taxiPool = new Taxipool(carList);
        System.out.println("Общая стоимость таксопарка составляет: $" + taxiPool.getCostOfTaxiPool());
        System.out.println("Сортировка по расходу толива:");
        carList.sort(Comparator.comparingDouble(Taxicar::getFuelConsumption));
        System.out.println(carList);
        int minSpeedLevel = 190, maxSpeedLevel = 200;
        System.out.println("Поиск автомобилей с диапазоном скорости от " + minSpeedLevel + " до " + maxSpeedLevel + " км/ч:");
        System.out.println(taxiPool.getListOfCertainSpeed(carList, minSpeedLevel, maxSpeedLevel));

    }
}
