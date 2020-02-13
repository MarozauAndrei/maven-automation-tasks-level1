package task23.airport;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

class AirstripPool {
    private final Semaphore semaphore = new Semaphore(5, true);
    private final Queue<Airstrip> airstrip = new LinkedList<>();

    AirstripPool(Queue<Airstrip> source) {
        airstrip.addAll(source);
    }

    Airstrip getAirstrip() throws InterruptedException {
        semaphore.acquire();
        return airstrip.poll();
    }

    void returnAirstrip(Airstrip resource) {
        airstrip.add(resource);
        semaphore.release();
    }
}
