import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

public class Trains {
    static Set<Locomotive> trains;

    public Trains() {
        Random random = new Random();
        trains = new LinkedHashSet<>();

        String[] tab = {"Szybki Ekspres", "Gwiazda Polnocy", "Zloty Express", "TurboExpres", "Dynamiczny Express",
                "Promien Predkosci", "Rakieta Kolejowa", "Horyzontalny Huragan", "Szynowy Supersonic", "Mega Express",
                "Ekskluzywny Express", "Skrzydla Kolej", "Srebrna Strzala", "Wedrowiec Zmierzchu", "Kaskadowy Kolejorz",
                "Express Maximus", "Krysztalowy Pociag", "Powietrzny Pociag", "Lsnaca Lokomotywa", "Fioletowa rakieta",
                "Magnetyczny pocisk", "Oplywowy Express", "Spokojna strzala", "Transkontynentalny poskramiacz",
                "Kolejowa Blyskawica"};
        for (String s : tab) {

            Locomotive newLoc = new Locomotive( "\"" + s + "\"", random.nextInt(6) +7,
                    random.nextInt(7000) + 5000, random.nextInt(3)+3,
                    Stations.randomStation(), Stations.randomStation(),Stations.randomStation());
        newLoc.RandomCarts();
              trains.add(newLoc);
        }

    }


    public static void add(Locomotive train) {
        if (!trains.contains(train))
            trains.add(train);
    }

    public static void remove(Locomotive train){
        if(trains.contains(train)){
            trains.remove(train);
            train=null;
        }
    }


    public static Locomotive trainByID(int ID) {
        for (Locomotive train : trains) {
            if (train.getID() == ID) {
                return train;
            }
        }
        return null;
    }

    public static Locomotive trainByName(String name) {
        for (Locomotive train : trains) {
            if (train.getName() == name) {
                return train;
            }
        }
        return null;
    }

    public static Set<Locomotive> getTrains() {
        return trains;
    }
}
