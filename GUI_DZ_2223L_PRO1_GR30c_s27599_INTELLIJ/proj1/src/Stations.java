import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

public class Stations {
    static Set<TrainStation> stations;

    public Stations() {
       stations = new LinkedHashSet<>();
       //tworzenie stacji
        String[] tab = {"Skierniewice","Piotrków Trybunalski","Sosnowiec Maczki","Sosnowiec Główny","Łódź",
                "Łódź Fabryczna","Lublin","Lublin Główny","Gniezno","Toruń Główny","inowrocław","Gdynia Główna"
                ,"Koło","Bydgoszcz Wschód","Katowice","Kraków Główny","Ponętów","Chorzew ","Siemkowice","Leszno",
                "Krotoszyn","Zbąszyń","Zbąszynek","Poznań Główny","Poznań Garbary","Poznań Wschód","Kutno","Jarocin",
                "Ostrów Wlkp.","Mrozy","Myszków","Nakło nad Notecią","Nałęczów","Nasielsk","Nekla","Nowa Wieś Mochy",
                "Nowa Wieś Wielka","Oborniki Śląskie","Nowe Drezdenko","Oborzyska Stare","Okonek","Olsztyn Zachodni",
                "Opole Główne","Opole Groszowice","Osie","Osielec","Osowiec","Ostrowie Biebrzańskie","Ożarów Mazowiecki"
                ,"Pabianice","Pleszew","Płochocin","Płociczno koło Suwałk","Przemyśl Główny","Przeworsk","Przyłubie",
                "Pszczółki","Pszczyna","Puławy Miasto","Racibórz","Radom","Radomsko","Radymno","Radziwiłłów Mazowiecki"
                ,"Rogoźno Wielkopolskie","Rogów","Rokietnica","Roztoki Bystrzyckie","Ruda Śląska","Ruda Wielka","Rumia"
                ,"Ruszów","Rybnik","Rybnik Niedobczyce","Rybnik Paruszowiec","Rzepin","Szepietowo","Tarnowskie Góry",
                "Toruń Miasto","Tychy","Wałbrzych Główny","Wałbrzych Miasto","Warka","Warszawa Centralna",
                "Warszawa Wschodnia","Warszawa Zachodnia","Wasilków","Wejherowo","Węgliniec","Wieliczka Park"
                ,"Wierzchucin","Wilkoszewice","Władysławowo","Włocławek Zazamcze","Wolin","Wołczyn","Wrocław Psie Pole"
                ,"Wrocław Zachodni","Wronki","Września"};
        for (String s : tab) {
            stations.add(new TrainStation("\"" + s + "\""));
        }

        //łączenie stacji z losowymi innymi
        TrainStation[] trainsArray = stations.toArray(new TrainStation[0]);
        Random rand = new Random();

        for (TrainStation ts : stations) {
            int con = rand.nextInt(6)+5;
            while (ts.getConectedTo().size() != con) {
                int randtr = rand.nextInt(stations.size());
//
                if (trainsArray[randtr] != ts && !(ts.getConectedTo().contains(trainsArray[randtr]))) {

                    TrainStation RandomTrain = trainsArray[randtr];
                    ts.conect(RandomTrain);
                }
            }
        }
    }

    public static Set<TrainStation> getStations() {
        return stations;
    }

public static void add(TrainStation trainStation){
        if (!(stations.contains(trainStation)))
             stations.add(trainStation);

}

public static int getCount(){
        return stations.size();
}


public static TrainStation randomStation(){
        Random random = new Random();
    return (TrainStation) stations.toArray()[random.nextInt(stations.size())];
}

    public static TrainStation stationByID(int ID){
        for(TrainStation station : stations){
            if(station.getId() == ID){
                return station;
            }
        }
       return null;
    }
    public static TrainStation stationByName(String name){
        for(TrainStation station : stations){
            if(station.getName() == name){
                return station;
            }
        }
       return null;
    }


}
