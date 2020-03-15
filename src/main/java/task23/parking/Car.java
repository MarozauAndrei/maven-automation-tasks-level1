package task23.parking;

public class Car {
    private String carName;

    Car(String carName) {
        this.carName = carName;
    }

    @Override
    public String toString() {
        return carName;
    }
}
