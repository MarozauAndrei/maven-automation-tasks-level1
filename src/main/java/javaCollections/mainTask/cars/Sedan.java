package javaCollections.mainTask.cars;

import javaCollections.mainTask.types.Sedantype;

public class Sedan extends Taxicar {
    private Sedantype sedantype;

    public Sedan(String modelOfCar, int maxSpeed, double fuelConsumption, double costOfCar, Sedantype sedantype) {
        super(modelOfCar, maxSpeed, fuelConsumption, costOfCar);
        this.sedantype = sedantype;
    }

    @Override
    public String toString() {
        return "Sedan{" +
                modelOfCar +
                ", maxSpeed=" + getMaxSpeed() +
                ", fuelConsumption=" + getFuelConsumption() +
                '}';
    }
}
