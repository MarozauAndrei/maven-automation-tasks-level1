package javaCollections.mainTask;

import javaCollections.mainTask.cars.Taxicar;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Taxipool {
    private List<? extends Taxicar> carList;

    public Taxipool(List<? extends Taxicar> carList) {
        this.carList = carList;
    }

    public List<Taxicar> getListOfCertainSpeed(List<? extends Taxicar> carList, int minSpeedLevel, int maxSpeedLevel) {
        Iterator<? extends Taxicar> iterator = carList.iterator();
        List<Taxicar> certainSpeed = new ArrayList<>();
        while (iterator.hasNext()) {
            Taxicar car = iterator.next();
            if (car.getMaxSpeed() >= minSpeedLevel && car.getMaxSpeed() <= maxSpeedLevel) { certainSpeed.add(car); }
        }
        return certainSpeed;
    }

    public double getCostOfTaxiPool() {
        double costOfTaxiPool = 0.0;
        for (Taxicar car : carList) { costOfTaxiPool = costOfTaxiPool + car.getCostOfCar(); }
        return costOfTaxiPool;
    }
}
