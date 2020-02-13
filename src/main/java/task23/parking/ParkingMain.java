package task23.parking;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ParkingMain {
    public static void main(String[] args) throws InterruptedException {
        int numberOfFreePlaces = 10;
        int waitingTime = 30;
        BlockingQueue<Car> parking = new LinkedBlockingQueue<>(numberOfFreePlaces);
        new Thread(() -> {
            for (int i = 1; i <= 20; i++) {
                try {
                    Car car = new Car("Car" + i);
                    TimeUnit.MILLISECONDS.sleep((int) (Math.random() * 200));
                    boolean receivingPlace = parking.offer(car, waitingTime, TimeUnit.MILLISECONDS);
                    if (receivingPlace) {
                        System.out.println(car + " stopped in the parking ");
                    } else {
                        System.out.println(car + " went away to other parking ");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 15; i++) {
                try {
                    TimeUnit.MILLISECONDS.sleep((int) (Math.random() * 500));
                    if (parking.peek() != null) {
                        System.out.println(parking.poll(1, TimeUnit.MILLISECONDS) + " left the parking ");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        TimeUnit.SECONDS.sleep(5);
        System.out.println("\n" + "Cars in the parking:  " + parking);
    }
}
