import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

public class Conection
        implements Comparable<Conection> {

    private static int counter = 0;
    private int id;
    //    private TrainStation start;
    private TrainStation end;
    private double distance;
    private boolean occupied;
    private Locomotive occupant;
    private Semaphore semaphore;
    private Locomotive whoacquire ;


    public Conection(TrainStation end, double distance) {
        this.id = ++counter;
        this.end = end;
        this.distance = distance;
        this.occupied = false;
        this.whoacquire = null;
        this.semaphore = new Semaphore(1,true);
    }


    public Locomotive getWhoacquire() {
        return whoacquire;
    }

    public void setWhoacquire(Locomotive whoacquire) {
        this.whoacquire = whoacquire;
    }

    public Semaphore getSemaphore() {
        return semaphore;
    }

    public Locomotive getOccupant() {
        return occupant;
    }

    public TrainStation getEnd() {
        return end;
    }


    public synchronized void setOccupied(Locomotive loc) {
        this.occupied = true;
        occupant = loc;
    }

    public void release(){
        this.occupied = false;
        occupant = null;
    }


    public boolean isOccupied() {
        return occupied;
    }

    public double getDistance() {
        return distance;
    }


    public int getId() {
        return id;
    }


    @Override
    public String toString() {
        return "\tconection{" +
                "end=" + end +
                ", distance=" + distance +
                ", occupied=" + occupied +
                '}';
    }

    @Override
    public int compareTo(Conection o) {
        return (int) (this.distance - o.distance);
    }
}
