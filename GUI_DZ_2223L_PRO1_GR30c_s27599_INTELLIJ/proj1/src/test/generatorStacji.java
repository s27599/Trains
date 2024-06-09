package test;

public class generatorStacji {

    public static void main(String[] args) {

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



        for (int i = 0; i < 100; i++) {
            System.out.println("  stations.add(new TrainStation(\""+tab[i]+"\"));");
        }



/*
  stations.add(new TrainStation("Skierniewice"));
  stations.add(new TrainStation("Piotrków Trybunalski"));
  stations.add(new TrainStation("Sosnowiec Maczki"));
  stations.add(new TrainStation("Sosnowiec Główny"));
  stations.add(new TrainStation("Łódź"));
  stations.add(new TrainStation("Łódź Fabryczna"));
  stations.add(new TrainStation("Lublin"));
  stations.add(new TrainStation("Lublin Główny"));
  stations.add(new TrainStation("Gniezno"));
  stations.add(new TrainStation("Toruń Główny"));
  stations.add(new TrainStation("inowrocław"));
  stations.add(new TrainStation("Gdynia Główna"));
  stations.add(new TrainStation("Koło"));
  stations.add(new TrainStation("Bydgoszcz Wschód"));
  stations.add(new TrainStation("Katowice"));
  stations.add(new TrainStation("Kraków Główny"));
  stations.add(new TrainStation("Ponętów"));
  stations.add(new TrainStation("Chorzew "));
  stations.add(new TrainStation("Siemkowice"));
  stations.add(new TrainStation("Leszno"));
  stations.add(new TrainStation("Krotoszyn"));
  stations.add(new TrainStation("Zbąszyń"));
  stations.add(new TrainStation("Zbąszynek"));
  stations.add(new TrainStation("Poznań Główny"));
  stations.add(new TrainStation("Poznań Garbary"));
  stations.add(new TrainStation("Poznań Wschód"));
  stations.add(new TrainStation("Kutno"));
  stations.add(new TrainStation("Jarocin"));
  stations.add(new TrainStation("Ostrów Wlkp."));
  stations.add(new TrainStation("Mrozy"));
  stations.add(new TrainStation("Myszków"));
  stations.add(new TrainStation("Nakło nad Notecią"));
  stations.add(new TrainStation("Nałęczów"));
  stations.add(new TrainStation("Nasielsk"));
  stations.add(new TrainStation("Nekla"));
  stations.add(new TrainStation("Nowa Wieś Mochy"));
  stations.add(new TrainStation("Nowa Wieś Wielka"));
  stations.add(new TrainStation("Oborniki Śląskie"));
  stations.add(new TrainStation("Nowe Drezdenko"));
  stations.add(new TrainStation("Oborzyska Stare"));
  stations.add(new TrainStation("Okonek"));
  stations.add(new TrainStation("Olsztyn Zachodni"));
  stations.add(new TrainStation("Opole Główne"));
  stations.add(new TrainStation("Opole Groszowice"));
  stations.add(new TrainStation("Osie"));
  stations.add(new TrainStation("Osielec"));
  stations.add(new TrainStation("Osowiec"));
  stations.add(new TrainStation("Ostrowie Biebrzańskie"));
  stations.add(new TrainStation("Ożarów Mazowiecki"));
  stations.add(new TrainStation("Pabianice"));
  stations.add(new TrainStation("Pleszew"));
  stations.add(new TrainStation("Płochocin"));
  stations.add(new TrainStation("Płociczno koło Suwałk"));
  stations.add(new TrainStation("Przemyśl Główny"));
  stations.add(new TrainStation("Przeworsk"));
  stations.add(new TrainStation("Przyłubie"));
  stations.add(new TrainStation("Pszczółki"));
  stations.add(new TrainStation("Pszczyna"));
  stations.add(new TrainStation("Puławy Miasto"));
  stations.add(new TrainStation("Racibórz"));
  stations.add(new TrainStation("Radom"));
  stations.add(new TrainStation("Radomsko"));
  stations.add(new TrainStation("Radymno"));
  stations.add(new TrainStation("Radziwiłłów Mazowiecki"));
  stations.add(new TrainStation("Rogoźno Wielkopolskie"));
  stations.add(new TrainStation("Rogów"));
  stations.add(new TrainStation("Rokietnica"));
  stations.add(new TrainStation("Roztoki Bystrzyckie"));
  stations.add(new TrainStation("Ruda Śląska"));
  stations.add(new TrainStation("Ruda Wielka"));
  stations.add(new TrainStation("Rumia"));
  stations.add(new TrainStation("Ruszów"));
  stations.add(new TrainStation("Rybnik"));
  stations.add(new TrainStation("Rybnik Niedobczyce"));
  stations.add(new TrainStation("Rybnik Paruszowiec"));
  stations.add(new TrainStation("Rzepin"));
  stations.add(new TrainStation("Szepietowo"));
  stations.add(new TrainStation("Tarnowskie Góry"));
  stations.add(new TrainStation("Toruń Miasto"));
  stations.add(new TrainStation("Tychy"));
  stations.add(new TrainStation("Wałbrzych Główny"));
  stations.add(new TrainStation("Wałbrzych Miasto"));
  stations.add(new TrainStation("Warka"));
  stations.add(new TrainStation("Warszawa Centralna"));
  stations.add(new TrainStation("Warszawa Wschodnia"));
  stations.add(new TrainStation("Warszawa Zachodnia"));
  stations.add(new TrainStation("Wasilków"));
  stations.add(new TrainStation("Wejherowo"));
  stations.add(new TrainStation("Węgliniec"));
  stations.add(new TrainStation("Wieliczka Park"));
  stations.add(new TrainStation("Wierzchucin"));
  stations.add(new TrainStation("Wilkoszewice"));
  stations.add(new TrainStation("Władysławowo"));
  stations.add(new TrainStation("Włocławek Zazamcze"));
  stations.add(new TrainStation("Wolin"));
  stations.add(new TrainStation("Wołczyn"));
  stations.add(new TrainStation("Wrocław Psie Pole"));
  stations.add(new TrainStation("Wrocław Zachodni"));
  stations.add(new TrainStation("Wronki"));
  stations.add(new TrainStation("Września"));*/


    }
}
