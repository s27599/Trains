package test;

public class trainGenerator {
    //trains.add(new Locomotive("tomek", random.nextInt(6) +7, random.nextInt(7000) + 5000;, random.nextInt(3)+2,
    //                Stations.stationByID(random.nextInt(Stations.getCount())), Stations.stationByID(random.nextInt(Stations.getCount())),
    //                Stations.stationByID(random.nextInt(Stations.getCount()))));
    public static void main(String[] args) {
        String[] tab = {"Kowalski Transport", "Nowak Logistics", "Zielinski Shipping", "Mazur Cargo", "Wójcik Trucking",
                "Kaczmarek Express", "Lewandowski Cargo", "Jankowski Distribution", "Adamczyk Transport", "Pawlak Trucking",
                "Szymanski Shipping", "Gorski Cargo", "Krupa Logistics", "Michalak Transport", "Czerwinski Express",
                "Kwiatkowski Express", "Bak Trucking", "Wrobel Shipping", "Wojciechowski Transport", "Pawlikowski Distribution",
                "Czajka Cargo", "Urbanski Logistics", "Piotrowski Express", "Malinowski Cargo", "Wrona Trucking",};


        for (String s : tab) {
            System.out.println("  trains.add(new Locomotive(\"" + s + "\", random.nextInt(6) +7, random.nextInt(7000) + 5000;, random.nextInt(3)+2,\n" +
                    "Stations.stationByID(random.nextInt(Stations.getCount())), Stations.stationByID(random.nextInt(Stations.getCount()))," +
                    "Stations.stationByID(random.nextInt(Stations.getCount()))));");
        }
//        for (String s : tab) {
//            System.out.println(  trains.add(new Locomotive "\"" + s + "\"", random.nextInt(6) +7, random.nextInt(7000) + 5000;, random.nextInt(3)+2,
//                    Stations.stationByID(random.nextInt(Stations.getCount())), Stations.stationByID(random.nextInt(Stations.getCount())), +
//                    Stations.stationByID(random.nextInt(Stations.getCount()))));
//        }


    }
}
