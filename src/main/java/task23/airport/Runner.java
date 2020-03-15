package task23.airport;

import java.util.LinkedList;

public class Runner {
    public static void main(String[] args) {
        LinkedList<Airstrip> listOfAirstrips = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            listOfAirstrips.add(new Airstrip(i + 1));
        }
        AirstripPool airstripPool = new AirstripPool(listOfAirstrips);
        for (int i = 0; i < 10; i++) {
            new Plane(airstripPool, i + 1).start();
        }
    }
}
