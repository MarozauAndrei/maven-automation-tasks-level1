package javaCollections.mainTask.cars;

public class Taxicar {
    String modelOfCar;
    private int maxSpeed;
    private double fuelConsumption;
    private double costOfCar;

    public Taxicar(String modelOfCar, int maxSpeed, double fuelConsumption, double costOfCar) {
        this.modelOfCar = modelOfCar;
        this.maxSpeed = maxSpeed;
        this.fuelConsumption = fuelConsumption;
        this.costOfCar = costOfCar;
    }

    public int getMaxSpeed() { return maxSpeed; }

    public double getFuelConsumption() { return fuelConsumption; }

    public double getCostOfCar() { return costOfCar; }

    @Override
    public String toString() {
        return "Car{" +
                "modelOfCar='" + modelOfCar + '\'' +
                ", maxSpeed=" + maxSpeed +
                ", fuelConsumption=" + fuelConsumption +
                '}';
    }
}
