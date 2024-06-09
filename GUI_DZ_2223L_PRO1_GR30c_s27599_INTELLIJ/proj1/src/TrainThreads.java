import java.util.*;

public class TrainThreads  {

    private static Map<Integer,Thread> threads;
    private static int id = 0;
    public TrainThreads() {
        threads = new LinkedHashMap<>();
        Set<Locomotive> locset = Trains.getTrains();

        for (Locomotive loc:locset) {
            threads.put(++id,new Thread(loc));
        }
    }

    public static void add(Locomotive locomotive){
        if (!(threads.containsValue(locomotive)))
            threads.put(locomotive.getID(),new Thread(locomotive));
    }


    public static int getCount(){
        return threads.size();
    }


    public static Thread threadById(int ID){
        for(Map.Entry<Integer, Thread> entry : threads.entrySet()){
            if(entry.getKey() == ID){
                return entry.getValue();
            }
        }
        return null;
    }

      public static void remove(Locomotive train){
        if(threads.containsKey(train.getID())){
            threads.get(train.getID()).interrupt();
            threads.remove(train.getID());
            Trains.remove(train);
        }
    }

    public static Map<Integer, Thread> getThreads() {
        return threads;
    }


    public static void ALLON(){
        for(Map.Entry<Integer, Thread> entry : threads.entrySet()){
            if (!entry.getValue().isAlive()){
                entry.getValue().start();
            }

        }
    }
    public static void setThreads(Map<Integer, Thread> threads) {
        TrainThreads.threads = threads;
    }
}
