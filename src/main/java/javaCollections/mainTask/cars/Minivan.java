package javaCollections.mainTask.cars;

import javaCollections.mainTask.types.Minivantype;

public class Minivan extends Taxicar {
    private Minivantype minivantype;

    public Minivan(String modelOfCar, int maxSpeed, double fuelConsumption, double costOfCar, Minivantype minivantype) {
        super(modelOfCar, maxSpeed, fuelConsumption, costOfCar);
        this.minivantype = minivantype;
    }

    @Override
    public String toString() {
        return "Minivan{" +
                modelOfCar +
                ", maxSpeed=" + getMaxSpeed() +
                ", fuelConsumption=" + getFuelConsumption() +
                '}';
    }
}
