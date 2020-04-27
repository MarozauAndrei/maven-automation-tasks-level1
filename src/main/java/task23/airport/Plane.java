package task23.airport;

public class Plane extends Thread {
    private AirstripPool pool;
    private int numberOfPlane;

    Plane(AirstripPool pool, int numberOfPlane) {
        this.pool = pool;
        this.numberOfPlane = numberOfPlane;
    }
    public void run() {
        Airstrip airstrip = null;
        try {
            airstrip = pool.getAirstrip();
            airstrip.usingAirstrip(numberOfPlane);
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (airstrip != null) {
                pool.returnAirstrip(airstrip);
            }
        }
    }
}
