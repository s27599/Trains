import java.util.*;

public class Route {

    private TrainStation source;
    private TrainStation destination;
    private ArrayList<TrainStation> route;
    private double length;

    public Route(TrainStation source, TrainStation destination) {
        this.source = source;
        this.destination = destination;
        route = new ArrayList<>();
        route = makeRoute(source,destination);
        length = countDistance();
    }

    private ArrayList<TrainStation>  makeRoute(TrainStation source, TrainStation destination){

         ArrayList<TrainStation> route = new ArrayList<>();
         TrainStation actStation = source;

            while (actStation != destination) {

                route.add(actStation);
                ArrayList<Conection> actConections = null;
                if (actStation != null) {
                    actConections = actStation.getConections();

                Map<TrainStation, Double> actConMap = new LinkedHashMap<>();
                for (int i = 0; i < actConections.size(); i++) {
                    actConMap.put(actConections.get(i).getEnd(), actConections.get(i).getEnd().calcDistance(destination));
                }
                TrainStation savedKey = null;

                double smallVal = Double.MAX_VALUE;
                boolean check = false;
                for (Map.Entry<TrainStation, Double> mapEntry : actConMap.entrySet()) {
                    if (mapEntry.getValue() < smallVal && !(route.contains(mapEntry.getKey()))) {
                        smallVal = mapEntry.getValue();
                        savedKey = mapEntry.getKey();
                        check = true;
                    }else if(!check){
                        savedKey = mapEntry.getKey();
//                        System.out.println("-------------------------------ALERT------------------------------------");
                    }
                }
                actStation = savedKey;
                }
                else return null;
            }
            route.add(actStation);
            return route;

    }


    private TrainStation getNext(TrainStation ts){
        if (!(ts == destination)) {
            int index = route.indexOf(ts);
            return route.get(index + 1);
        }else{
            return ts;
        }

    }

    private double countDistance(){
        double distance = 0;
        if (route != null) {
            for (TrainStation ts : route) {
                if (getNext(ts) != ts)
                    distance += ts.getConectionByEndName(getNext(ts)).getDistance();

            }
        }
    return distance;
    }




    public ArrayList<TrainStation> getRoute() {
        return route;
    }


    public double getLength() {
        return length;
    }

    @Override
    public String toString() {
        return "Route{" +
                "route=" + route +
                '}';
    }
}
