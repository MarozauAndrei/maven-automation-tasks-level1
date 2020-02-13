package javaCollections.mainTask.cars;

public class Sportcar extends Taxicar {
    private int powerOfEngine;

    public Sportcar(String modelOfCar, int maxSpeed, double fuelConsumption, double costOfCar, int powerOfEngine) {
        super(modelOfCar, maxSpeed, fuelConsumption, costOfCar);
        this.powerOfEngine = powerOfEngine;
    }

    @Override
    public String toString() {
        return "SportCar{" +
                modelOfCar +
                ", maxSpeed=" + getMaxSpeed() +
                ", fuelConsumption=" + getFuelConsumption() +
                '}';
    }
}
