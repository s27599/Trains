import java.util.ArrayList;
import java.util.Objects;

public class TrainStation {

    private final int id;
    private final int xcord;
    private final int ycord;
    private final String name;
    private int onStationCounter;

    private ArrayList<Conection> conections;
    private static int trainCounter = 0;

    public TrainStation() {
        this.id = ++trainCounter;
        this.name = "stacja " + this.id;
        this.xcord = (int) (Math.random() * 100);
        this.ycord = (int) (Math.random() * 100);
        this.onStationCounter = 0;
        conections = new ArrayList<>();
    }

    public TrainStation(String name) {
        this.name = name;
        this.id = ++trainCounter;
        this.xcord = (int) (Math.random() * 100);
        this.ycord = (int) (Math.random() * 100);
        this.onStationCounter = 0;

        conections = new ArrayList<>();
    }

    public TrainStation(String name, int xcord, int ycord) {
        this.id = ++trainCounter;
        this.name = name;
        this.xcord = xcord;
        this.ycord = ycord;
        this.onStationCounter = 0;
        conections = new ArrayList<>();
    }


    public void conect(TrainStation secStation) {
        conections.add(new Conection(secStation, Math.sqrt(Math.pow(this.xcord - secStation.getXcord(), 2) + Math.pow(this.ycord - secStation.getYcord(), 2))));
    }


    public void deleteConection(TrainStation trainStation){
        conections.remove(this.getConectionByEndName(trainStation));
    }
    public double calcDistance(TrainStation first, TrainStation sec){
        return Math.sqrt(Math.pow(first.getXcord() - sec.getXcord(), 2) + Math.pow(first.getYcord() - sec.getYcord(), 2));
    }

    public double calcDistance(TrainStation sec){
        return Math.sqrt(Math.pow(this.xcord - sec.getXcord(), 2) + Math.pow(this.ycord - sec.getYcord(), 2));
    }


    public int getOnStationCounter() {
        return onStationCounter;
    }

    public void setOnStationCounter(int onStationCounter) {
        this.onStationCounter = onStationCounter;
    }

    public Conection getConectionByEndName(TrainStation end){
            for(Conection con : this.conections){
                if(con.getEnd() == end){
                    return con;
                }
            }
            return null;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getXcord() {
        return xcord;
    }

    public int getYcord() {
        return ycord;
    }

    public ArrayList<Conection> getConections() {
        return conections;
    }

    public ArrayList<TrainStation> getConectedTo() {
        ArrayList<TrainStation> TS = new ArrayList<>();
        conections.forEach((x) -> TS.add(x.getEnd()));
        return TS;
    }

    @Override
    public String toString() {
        return
                "Stacja: " + name;
    }

    public String fullInfoToString() {
        return "TrainStation{" +
                "name= " + this.name +
                ", id=" + id +
                ", xcord=" + xcord +
                ", ycord=" + ycord +
                '}';
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrainStation station = (TrainStation) o;
        return id == station.id && xcord == station.xcord && ycord == station.ycord && Objects.equals(name, station.name) && Objects.equals(conections, station.conections);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, xcord, ycord, name, conections);
    }


}

