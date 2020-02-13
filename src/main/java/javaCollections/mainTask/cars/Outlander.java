package javaCollections.mainTask.cars;

import javaCollections.mainTask.types.Outlandertype;

public class Outlander extends Taxicar {
    private Outlandertype outlandertype;
    private int trunkCapacity;

    public Outlander(String modelOfCar, int maxSpeed, double fuelConsumption, double costOfCar, Outlandertype outlandertype, int trunkCapacity) {
        super(modelOfCar, maxSpeed, fuelConsumption, costOfCar);
        this.outlandertype = outlandertype;
        this.trunkCapacity = trunkCapacity;
    }

    @Override
    public String toString() {
        return "Outlander{" +
                modelOfCar +
                ", maxSpeed=" + getMaxSpeed() +
                ", fuelConsumption=" + getFuelConsumption() +
                '}';
    }
}
