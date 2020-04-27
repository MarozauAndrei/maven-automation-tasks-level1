package task23.airport;

import java.util.concurrent.TimeUnit;

public class Airstrip {
    private int numberOfAirstrip;

    Airstrip(int numberOfAirstrip) {
        super();
        this.numberOfAirstrip = numberOfAirstrip;
    }

    void usingAirstrip(int numberOfPlane) {
        try {
            System.out.println("Самолет " + numberOfPlane + " начал выходить на полосу № " + numberOfAirstrip);
            System.out.println("Полоса № " + numberOfAirstrip + " " + '\"' + "приняла" + '\"' + " самолет " + numberOfPlane);
            TimeUnit.SECONDS.sleep(3);
            System.out.println("Самолет " + numberOfPlane + " взлетел с полосы № " + numberOfAirstrip + "\n" + "Полоса № " + numberOfAirstrip + " освободилась ");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
