import java.util.*;

public class Main {


    public static void main(String[] args) throws RuntimeException {

        System.out.println("Uruchamianie programu");
        System.out.print(".");
        Stations stations = new Stations();
        System.out.print(".");
        Carts carts = new Carts();
        System.out.print(".");
        Trains trains = new Trains();
        System.out.print(".");
        TrainThreads trainThreads = new TrainThreads();
        System.out.print(".\n");
        Thread saver = new Thread(new FIleSaver());
        saver.start();
        TrainThreads.ALLON();
        Locomotive.MakeITFAST(1);// mode 0 -> fast, mode 1 -> very fast
        // odkomentowanie tej /\ linijki spowoduje przyspieszenie symulacji wiecej info w klasie Locomotive line26
        boolean exit = false;
        Scanner scanner = new Scanner(System.in);


        do {
            System.out.println("1 - dodaj lokomotywę");
            System.out.println("2 - dodaj wagon");
            System.out.println("3 - dodaj stacje");
            System.out.println("4 - polacz stacje do innej stacji");
            System.out.println("5 - polacz wagon do lokomotywy");
            System.out.println("6 - dodaj osoby/ladunek do wagonu");
            System.out.println("7 - zabierz osoby/ladunek z wagonu");
            System.out.println("8 - inne opcje wagonu");
            System.out.println("9 - usuwanie obiektow/odłączanie obiektów");
            System.out.println("10 - uruchamianie pociagu");
            System.out.println("11 - zatrzymywanie pociagu");
            System.out.println("12 - raport pociagu");
            System.out.println("13 - wyjscie");
            System.out.print("podaj numer opcji: ");

            int selected;
            try {
                selected = scanner.nextInt();

                scanner.nextLine();
                System.out.println("\n");


                switch (selected) {
                    case 1: {
                        System.out.println("Podaj: ");

                        try {

                            System.out.println("-nazwę: ");
                            String name = scanner.nextLine();
                            System.out.println("-maksymalna ilosc wagonow: ");
                            int maxCarriage = scanner.nextInt();
                            scanner.nextLine();
                            if (maxCarriage <= 0) {
                                System.out.println("liczba wagonow musi byc dodatnia");
                                break;
                            }
                            System.out.println("-maksymalny uciag w kg: ");
                            int maxTowing = scanner.nextInt();
                            scanner.nextLine();
                            if (maxTowing <= 0) {
                                System.out.println("uciag musi byc dodatni");
                                break;
                            }
                            System.out.println("-maksymalna ilosc polaczen elektrycznych: ");
                            int maxpower = scanner.nextInt();
                            scanner.nextLine();
                            if (maxpower <= 0) {
                                System.out.println("liczba polaczen elektrycznych musi byc dodatnia");
                                break;
                            } else if (maxpower > maxCarriage) {
                                System.out.println("maksymalna liczba polaczen musi byc mniejsza od maksymalnej ilosci wagonow");
                                break;
                            }
                            Stations.getStations().forEach(s -> {
                                System.out.println("ID: " + s.getId() + " " + s);
                            });
                            System.out.println("oraz wprowadz:\n-ID wybranej stacji macierzystej: ");
                            TrainStation home = Stations.stationByID(scanner.nextInt());
                            scanner.nextLine();
                            System.out.println("-ID stacji poczatkowej: ");
                            TrainStation source = Stations.stationByID(scanner.nextInt());
                            scanner.nextLine();
                            System.out.println("-ID stacji docelowej: ");
                            TrainStation dest = Stations.stationByID(scanner.nextInt());
                            scanner.nextLine();
                            if(home != null && source!=null && dest!=null) {
                                if (dest != source) {
                                    Locomotive newLoc = new Locomotive(name, maxCarriage, maxTowing, maxpower, home, source, dest);
                                    Trains.add(newLoc);
                                    TrainThreads.add(newLoc);
                                    System.out.println("lokomotywa dodana");
                                } else {
                                    System.out.println("stacja docelowa nie moze byc rowna poczatkowej");
                                    break;
                                }
                            }else{
                                System.out.println("bledne ID stacji");
                            }

//                    System.out.println(name+" "+maxCarriage+" "+ maxTowing+ " "+ maxpower+" "+ home+" "+ source+ " "+dest);

                        } catch (InputMismatchException e) {
                            System.out.println("nieprawidlowe dane");
                            scanner.nextLine();
                        }

                    }
                    break;
                    case 2: {


                        System.out.println("wybierz typ wagonu: ");
                        System.out.println("1 - Pasazerski");
                        System.out.println("2 - Pocztowy");
                        System.out.println("3 - Bagazowo-pocztowy");
                        System.out.println("4 - Restauracyjny");
                        System.out.println("5 - Towarowy");
                        System.out.println("6 - Towarowy ciezki");
                        System.out.println("7 - Chlodniczy");
                        System.out.println("8 - Na materialy ciekle");
                        System.out.println("9 - Na materialy gazowe");
                        System.out.println("10 - Na materialy wybuchowe");
                        System.out.println("11 - Na materialy toksyczne ");
                        System.out.println("12 - Na ciekle materialy toksyczne ");

                        try {
                            int type = scanner.nextInt();
                            System.out.println("nastepnie podaj: ");
                            scanner.nextLine();
                            System.out.println("nadawce: ");
                            String sender = scanner.nextLine();
                            System.out.println("mase pustego wagonu: ");
                            int emptyMass = scanner.nextInt();
                            scanner.nextLine();
                            int x;
                            int y;
                            boolean z;
                            boolean zz;
                            switch (type) {
                                case 1://passenger
                                    System.out.println("ilosc miejsc na pazazerow: ");
                                    x = scanner.nextInt();
                                    scanner.nextLine();
                                    Carts.add(new PassengerCart(sender, emptyMass, x));
                                    break;
                                case 2://mail
                                    System.out.println("ilosc miejsc na przesylki: ");
                                    x = scanner.nextInt();
                                    scanner.nextLine();
                                    Carts.add(new MailCart(sender, emptyMass, x));

                                    break;
                                case 3://lugage mail
                                    System.out.println("ilosc miejsc na bagaz: ");
                                    x = scanner.nextInt();
                                    scanner.nextLine();
                                    System.out.println("ilosc miejsc na przesylki: ");
                                    y = scanner.nextInt();
                                    scanner.nextLine();
                                    Carts.add(new LuggageMailCart(sender, emptyMass, x, y));
                                    break;
                                case 4://buffet
                                    System.out.println("ilosc miejsc na pazazerow: ");
                                    x = scanner.nextInt();
                                    scanner.nextLine();
                                    Carts.add(new BuffetCart(sender, emptyMass, x));
                                    break;
                                case 5:
                                    //cargo
                                    Carts.add(new CargoCart(sender, emptyMass));
                                    break;
                                case 6://heavy cargo
                                    System.out.println("czy posiada ochrone:\n 0 - nie, 1 - tak ");
                                    x = scanner.nextInt();
                                    scanner.nextLine();
                                    if (x == 0) {
                                        z = false;
                                    } else if (x == 1) {
                                        z = true;
                                    } else {
                                        System.out.println("bledne dane");
                                        break;
                                    }

                                    Carts.add(new CargoHeavyCart(sender, emptyMass, z));

                                    break;
                                case 7://chiller
                                    System.out.println("temperature w wagonie: ");
                                    x = scanner.nextInt();
                                    System.out.println("czy posiada technologię NoFrost:\n 0 - nie, 1 - tak  ");
                                    y = scanner.nextInt();
                                    if (y == 0) {
                                        z = false;
                                    } else if (y == 1) {
                                        z = true;
                                    } else {
                                        System.out.println("bledne dane");
                                        break;
                                    }
                                    scanner.nextLine();

                                    Carts.add(new chillerCart(sender, emptyMass, x, z));

                                    break;
                                case 8://liq

                                    break;
                                case 9://gaz
                                    System.out.println("czy iskrzy :\n 0 - nie, 1 - tak ");
                                    x = scanner.nextInt();
                                    scanner.nextLine();
                                    if (x == 0) {
                                        z = false;
                                    } else if (x == 1) {
                                        z = true;
                                    } else {
                                        System.out.println("bledne dane");
                                        break;
                                    }

                                    Carts.add(new gazCart(sender, emptyMass, z));

                                    break;
                                case 10://explosives
                                    System.out.println("czy posiada ekstra ochrone:\n 0 - nie, 1 - tak ");
                                    x = scanner.nextInt();
                                    scanner.nextLine();
                                    if (x == 0) {
                                        z = false;
                                        System.out.println("czy posiada podstawowa ochrone:\n 0 - nie, 1 - tak ");
                                        y = scanner.nextInt();
                                        scanner.nextLine();
                                        if (y == 0) {
                                            zz = false;
                                        } else if (y == 1) {
                                            zz = true;
                                        } else {
                                            System.out.println("bledne dane");
                                            break;
                                        }

                                    } else if (x == 1) {
                                        z = true;
                                        zz = true;
                                    } else {
                                        System.out.println("bledne dane");
                                        break;
                                    }

                                    Carts.add(new ExplosivesCart(sender, emptyMass, zz, z));

                                    break;
                                case 11://toxic
                                    System.out.println("czy jest wzmocniony :\n 0 - nie, 1 - tak ");
                                    x = scanner.nextInt();
                                    scanner.nextLine();
                                    if (x == 0) {
                                        z = false;
                                    } else if (x == 1) {
                                        z = true;
                                    } else {
                                        System.out.println("bledne dane");
                                        break;
                                    }
                                    System.out.println("czy posiada  ochrone:\n 0 - nie, 1 - tak ");
                                    y = scanner.nextInt();
                                    scanner.nextLine();
                                    if (y == 0) {
                                        zz = false;
                                    } else if (y == 1) {
                                        zz = true;
                                    } else {
                                        System.out.println("bledne dane");
                                        break;
                                    }

                                    Carts.add(new ToxicCart(sender, emptyMass, z, zz));

                                    break;
                                case 12://toxicliq
                                    System.out.println("czy jest wzmocniony :\n 0 - nie, 1 - tak ");
                                    x = scanner.nextInt();
                                    scanner.nextLine();
                                    if (x == 0) {
                                        z = false;
                                    } else if (x == 1) {
                                        z = true;
                                    } else {
                                        System.out.println("bledne dane");
                                        break;
                                    }

                                    Carts.add(new LiquidToxicCart(sender, emptyMass, z));
                                    break;
                                default:
                                    System.out.println("bledny typ");
                                    break;
                            }


                        } catch (InputMismatchException e) {
                            System.out.println("nieprawidlowe dane");
                            scanner.nextLine();
                        }
                    }
                    break;
                    case 3: {

                        try {
                            System.out.println("Podaj nazwe stacji: ");
                            String name = scanner.nextLine();
                            Stations.add(new TrainStation(name));

                        } catch (InputMismatchException e) {
                            System.out.println("nieprawidlowe dane");
                            scanner.nextLine();
                        }
                    }
                    break;
                    case 4: {
                        Stations.getStations().forEach(s -> {
                            System.out.println("ID: " + s.getId() + " " + s);
                        });
                        System.out.println("wprowadz id stacji poczatkowej");
                        int first = scanner.nextInt();
                        scanner.nextLine();
//                    Stations.stationByID(first).getConectedTo().forEach(System.out::println);

                        System.out.println("wprowadz id stacji koncowej");
                        int sec = scanner.nextInt();
                        scanner.nextLine();
                        if (sec == first) {
                            System.out.println("nie można polaczyć stacji do samej siebie");
                            break;
                        }
                        try {
                            if (!(Stations.stationByID(first).getConectedTo().contains(Stations.stationByID(sec)))) {
                                Stations.stationByID(first).conect(Stations.stationByID(sec));
                                System.out.println("polaczenie dodane");
                            }
                        } catch (NullPointerException e) {
                            System.out.println("bledne dane");
                        }

//                    Stations.stationByID(first).getConectedTo().forEach(System.out::println);
                    }
                    break;
                    case 5: {

                        Carts.getCarts().forEach(c -> {
                            if (!c.isConected())
                                System.out.println("ID: " + c.getID() + " " + c.getSmallInfo());
                        });
                        System.out.println("wprowadz id wagonu ktory chcesz polaczyc");
                        int first = scanner.nextInt();

//                    Stations.stationByID(first).getConectedTo().forEach(System.out::println);


                        try {
                            if (!Carts.cartByID(first).isConected()) {
                                try {
                                    Trains.getTrains().forEach(t -> {
                                        if(t.getMaxCarriage()>t.getConectedCarts())
                                             System.out.println("ID: " + t.getID() + " " + t);
                                    });
                                    System.out.println("wprowadz id lokomotywy do ktorej chcesz polaczyc wagon");
                                    int sec = scanner.nextInt();
                                    scanner.nextLine();

                                    Trains.trainByID(sec).conect(Carts.cartByID(first));
                                    System.out.println("wagon podlaczony pomyslnie");
                                } catch (TooManyCartsConected | TowingCapacityExceeded | TooManyPowerConected |
                                         CartAlreadyConnected e) {
                                    System.out.println(e);
                                }
                            } else {
                                System.out.println("wagon jest już podlaczony");
                            }
                        } catch (NullPointerException ex) {
                            System.out.println("Bledne dane");
                        }
                    }
                    break;
                    case 6: {
                        Carts.getCarts().forEach(c -> {
                            System.out.println("ID: " + c.getID() + " " + c.getSmallInfo());
                        });
                        System.out.println("podaj ID wagonu do ktorego chcesz dodac osoby / zaladowac towar");
                        int choice = scanner.nextInt();
                        scanner.nextLine();
                        Cart selectedCart = Carts.cartByID(choice);
                        switch (selectedCart.getType()) {
                            case PASSENGER: {
                                try {
                                    selectedCart.addPerson();
                                } catch (NotAllowedException | AllSeatsOccupiedException | TowingCapacityExceeded e) {
                                    System.out.println(e);
                                }
                            }
                            break;
                            case GAZ: {
                                System.out.println("podaj ilosc gazu do zaladowania ");
                                int cargo = scanner.nextInt();
                                scanner.nextLine();
                                try {
                                    selectedCart.addCargo(cargo);
                                } catch (NotAllowedException | TowingCapacityExceeded | NoSpaceException | ToHighTemp |
                                         FrozenCartException e) {
                                    System.out.println(e);
                                }
                            }
                            break;
                            case MAIL: {

                                try {
                                    selectedCart.addMail();
                                } catch (NotAllowedException | TowingCapacityExceeded | NoSpaceException e) {
                                    System.out.println(e);
                                }
                            }
                            break;
                            case CARGO, CHILLER, CARGOHEAVY, EXPLOSIVES: {
                                System.out.println("podaj ilosc ladunku do zaladowania ");
                                int cargo = scanner.nextInt();
                                scanner.nextLine();
                                try {
                                    selectedCart.addCargo(cargo);
                                } catch (NotAllowedException | TowingCapacityExceeded | NoSpaceException | ToHighTemp |
                                         FrozenCartException e) {
                                    System.out.println(e);
                                }
                            }
                            break;
                            case TOXIC, LIQUIDTOXIC: {
                                System.out.println("podaj ilosc toksyn do zaladowania ");
                                int cargo = scanner.nextInt();
                                scanner.nextLine();
                                try {
                                    selectedCart.addCargo(cargo);
                                } catch (NotAllowedException | TowingCapacityExceeded | NoSpaceException | ToHighTemp |
                                         FrozenCartException e) {
                                    System.out.println(e);
                                }
                            }
                            break;
                            case BUFFET: {
                                System.out.println("0 - ladowanie produktow, 1-dodawanie osob");
                                int ch = scanner.nextInt();
                                scanner.nextLine();
                                switch (ch) {
                                    case 0: {//produkty
                                        System.out.println("podaj ilosc produktow do zaladowania ");
                                        int cargo = scanner.nextInt();
                                        scanner.nextLine();
                                        try {
                                            selectedCart.addCargo(cargo);
                                        } catch (NotAllowedException | TowingCapacityExceeded | NoSpaceException |
                                                 ToHighTemp | FrozenCartException e) {
                                            System.out.println(e);
                                        }
                                    }
                                    break;
                                    case 1: {
                                        try {
                                            selectedCart.addPerson();
                                        } catch (NotAllowedException | AllSeatsOccupiedException |
                                                 TowingCapacityExceeded e) {
                                            System.out.println(e);
                                        }
                                    }
                                    break;
                                    default:
                                        System.out.println("Bledna opcja");
                                        break;
                                }

                            }
                            break;
                            case LIQUID: {
                                System.out.println("podaj ilosc cieczy do zaladowania ");
                                int cargo = scanner.nextInt();
                                scanner.nextLine();
                                try {
                                    selectedCart.addCargo(cargo);
                                } catch (NotAllowedException | TowingCapacityExceeded | NoSpaceException | ToHighTemp |
                                         FrozenCartException e) {
                                    System.out.println(e);
                                }
                            }
                            break;
                            case LUGGAGEMAIL: {
                                System.out.println("0 - ladowanie bagazu, 1-dodawanie poczty");
                                int ch = scanner.nextInt();
                                scanner.nextLine();
                                switch (ch) {
                                    case 0: {//bagaz
                                        try {
                                            selectedCart.addLuggage();
                                        } catch (NotAllowedException | TowingCapacityExceeded | NoSpaceException e) {
                                            System.out.println(e);
                                        }
                                    }
                                    break;
                                    case 1: {
                                        try {
                                            selectedCart.addMail();
                                        } catch (NotAllowedException | TowingCapacityExceeded | NoSpaceException e) {
                                            System.out.println(e);
                                        }
                                    }
                                    break;
                                    default:
                                        System.out.println("Bledna opcja");
                                        break;
                                }
                            }
                            break;
                            default:
                                System.out.println("blad");
                                break;

                        }

                    }
                    break;
                    case 7: {

                        Carts.getCarts().forEach(c -> {
                            System.out.println("ID: " + c.getID() + " " + c.getSmallInfo());
                        });
                        System.out.println("podaj ID wagonu do ktorego chcesz wysadzic osoby / wyladowac towar");
                        int choice = scanner.nextInt();
                        scanner.nextLine();
                        Cart selectedCart = Carts.cartByID(choice);
                        switch (selectedCart.getType()) {
                            case PASSENGER: {
                                try {
                                    selectedCart.dropOffPerson();
                                } catch (NotAllowedException | EmptyCartException e) {
                                    System.out.println(e);
                                }
                            }
                            break;
                            case GAZ: {
                                System.out.println("podaj ilosc gazu do wyladowania ");
                                int cargo = scanner.nextInt();
                                scanner.nextLine();
                                try {
                                    selectedCart.dropOffCargo(cargo);
                                } catch (NotAllowedException | EmptyCartException e) {
                                    System.out.println(e);
                                }
                            }
                            break;
                            case MAIL: {

                                try {
                                    selectedCart.dropOffMail();
                                } catch (NotAllowedException | EmptyCartException e) {
                                    System.out.println(e);
                                }
                            }
                            break;
                            case CARGO, CHILLER, CARGOHEAVY, EXPLOSIVES: {
                                System.out.println("podaj ilosc ladunku do wyladowania ");
                                int cargo = scanner.nextInt();
                                scanner.nextLine();
                                try {
                                    selectedCart.dropOffCargo(cargo);
                                } catch (NotAllowedException | EmptyCartException e) {
                                    System.out.println(e);
                                }
                            }
                            break;
                            case TOXIC, LIQUIDTOXIC: {
                                System.out.println("podaj ilosc toksyn do wyladowania ");
                                int cargo = scanner.nextInt();
                                scanner.nextLine();
                                try {
                                    selectedCart.dropOffCargo(cargo);
                                } catch (NotAllowedException | EmptyCartException e) {
                                    System.out.println(e);
                                }
                            }
                            break;
                            case BUFFET: {
                                System.out.println("0 - wyladowanie produktow, 1-wysadzanie osob");
                                int ch = scanner.nextInt();
                                scanner.nextLine();
                                switch (ch) {
                                    case 0: {//produkty
                                        System.out.println("podaj ilosc produktow do wyladowania ");
                                        int cargo = scanner.nextInt();
                                        scanner.nextLine();
                                        try {
                                            selectedCart.dropOffCargo(cargo);
                                        } catch (NotAllowedException | EmptyCartException e) {
                                            System.out.println(e);
                                        }
                                    }
                                    break;
                                    case 1: {
                                        try {
                                            selectedCart.dropOffPerson();
                                        } catch (NotAllowedException | EmptyCartException e) {
                                            System.out.println(e);
                                        }
                                    }
                                    break;
                                    default:
                                        System.out.println("Bledna opcja");
                                        break;
                                }

                            }
                            break;
                            case LIQUID: {
                                System.out.println("podaj ilosc cieczy do wyladowania ");
                                int cargo = scanner.nextInt();
                                scanner.nextLine();
                                try {
                                    selectedCart.dropOffCargo(cargo);
                                } catch (NotAllowedException | EmptyCartException e) {
                                    System.out.println(e);
                                }
                            }
                            break;
                            case LUGGAGEMAIL: {
                                System.out.println("0 - wyladowywanie bagazu, 1-wyladowanie poczty");
                                int ch = scanner.nextInt();
                                scanner.nextLine();
                                switch (ch) {
                                    case 0: {//bagaz
                                        try {
                                            selectedCart.dropOffLuggage();
                                        } catch (NotAllowedException | EmptyCartException e) {
                                            System.out.println(e);
                                        }
                                    }
                                    break;
                                    case 1: {
                                        try {
                                            selectedCart.dropOffMail();
                                        } catch (NotAllowedException | EmptyCartException e) {
                                            System.out.println(e);
                                        }
                                    }
                                    break;
                                    default:
                                        System.out.println("Bledna opcja");
                                        break;
                                }
                            }
                            break;
                            default:
                                System.out.println("blad");
                                break;

                        }


                    }
                    break;
                    case 8: {
                        Carts.getCarts().forEach(c -> {

                            System.out.println("ID: " + c.getID() + " " + c.getSmallInfo());
                        });
                        System.out.println("podaj ID wagonu do ktorego chcesz zobaczyc dodatkowe opcje");
                        int choice = scanner.nextInt();
                        scanner.nextLine();
                        Cart selectedCart = Carts.cartByID(choice);
                        switch (selectedCart.getType()) {

                            case GAZ: {
                                System.out.println("Opcje:");
                                System.out.println("1-naprawa");
                                int checked = scanner.nextInt();
                                scanner.nextLine();
                                if (checked == 1) {
                                    try {
                                        selectedCart.repair();
                                    } catch (NotAllowedException e) {
                                        System.out.println(e);
                                    }
                                } else {
                                    System.out.println("bledna opcja");
                                    break;
                                }

                            }
                            break;
                            case CHILLER: {
                                System.out.println("Opcje:");
                                System.out.println("1-odmrazanie");
                                int checked = scanner.nextInt();
                                scanner.nextLine();
                                if (checked == 1) {
                                    try {
                                        selectedCart.melt();
                                    } catch (NotAllowedException e) {
                                        System.out.println(e);
                                    }
                                } else {
                                    System.out.println("bledna opcja");
                                    break;
                                }
                            }
                            break;
                            case EXPLOSIVES: {
                                System.out.println("Opcje:");
                                System.out.println("1-wysadzanie");
                                System.out.println("2-naprawa");
                                System.out.println("3-atak");
                                int checked = scanner.nextInt();
                                scanner.nextLine();
                                switch (checked) {
                                    case 1:
                                        try {
                                            selectedCart.blow();
                                        } catch (NotAllowedException e) {
                                            System.out.println(e);
                                        }
                                        break;
                                    case 2:
                                        try {
                                            selectedCart.repair();
                                        } catch (NotAllowedException e) {
                                            System.out.println(e);
                                        }
                                        break;
                                    case 3:
                                        try {
                                            selectedCart.atack();
                                        } catch (NotAllowedException e) {
                                            System.out.println(e);
                                        }
                                        break;
                                    default:
                                        System.out.println("bledna opcja");
                                        break;
                                }
                            }
                            break;

                            case CARGOHEAVY: {
                                System.out.println("Opcje:");
                                System.out.println("1-atak");

                                int checked = scanner.nextInt();
                                scanner.nextLine();
                                if (checked == 1) {
                                    try {
                                        selectedCart.atack();
                                    } catch (NotAllowedException e) {
                                        System.out.println(e);
                                    }
                                } else {
                                    System.out.println("bledna opcja");
                                    break;
                                }
                            }
                            break;
                            case TOXIC, LIQUIDTOXIC: {
                                System.out.println("Opcje:");
                                System.out.println("1-atak");
                                System.out.println("2-ulepszanie");
                                System.out.println("3-czyszczenie toksyn");

                                int checked = scanner.nextInt();
                                scanner.nextLine();


                                switch (checked) {
                                    case 1:
                                        try {
                                            selectedCart.atack();
                                        } catch (NotAllowedException e) {
                                            System.out.println(e);
                                        }
                                        break;
                                    case 2:
                                        try {
                                            selectedCart.upgrade();
                                        } catch (NotAllowedException e) {
                                            System.out.println(e);
                                        }
                                        break;
                                    case 3:
                                        try {
                                            selectedCart.clean();
                                        } catch (NotAllowedException e) {
                                            System.out.println(e);
                                        }
                                        break;
                                    default:
                                        System.out.println("bledna opcja");
                                        break;
                                }

                            }
                            break;

                            case LUGGAGEMAIL: {
                                System.out.println("0 - wyladowywanie bagazu, 1-wyladowanie poczty");
                                int ch = scanner.nextInt();
                                scanner.nextLine();
                                switch (ch) {
                                    case 0: {//bagaz
                                        try {
                                            selectedCart.dropOffLuggage();
                                        } catch (NotAllowedException | EmptyCartException e) {
                                            System.out.println(e);
                                        }
                                    }
                                    break;
                                    case 1: {
                                        try {
                                            selectedCart.dropOffMail();
                                        } catch (NotAllowedException | EmptyCartException e) {
                                            System.out.println(e);
                                        }
                                    }
                                    break;
                                    default:
                                        System.out.println("Bledna opcja");
                                        break;
                                }
                            }
                            break;
                            default:
                                System.out.println("brak opcji");
                                break;

                        }

                    }
                    break;
                    case 9: {
                        System.out.println("jakiego typu obiekt chcesz usunac:");
                        System.out.println("1 - pociag");
                        System.out.println("2 - wagon");
                        System.out.println("3 - stacja");
                        System.out.println("4 - rozlaczanie stacji");
                        System.out.println("5 - rozlaczanie wagonu od pociagu");
                        int choice = scanner.nextInt();
                        scanner.nextLine();
                        switch (choice) {
                            case 1: {
                                try {
                                    Trains.getTrains().forEach(t -> {
                                        System.out.println("ID: " + t.getID() + " " + t);
                                    });
                                    System.out.println("wprowadz id lokomotywy ktora chcesz usunac");
                                    int sec = scanner.nextInt();
                                    scanner.nextLine();
                                    for (Cart cart : Trains.trainByID(sec).getCarts()) {
                                        try {
                                            Trains.trainByID(sec).disconnect(cart);
                                        } catch (CartNotConected e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    if (TrainThreads.getThreads().containsKey(Trains.trainByID(sec).getID())) {
                                        TrainThreads.getThreads().get(Trains.trainByID(sec).getID()).interrupt();
                                        TrainThreads.getThreads().remove(Trains.trainByID(sec).getID());
                                    }
                                    Trains.remove(Trains.trainByID(sec));
                                    System.out.println("lokomotywa usunieta");


                                } catch (NullPointerException e) {
                                    System.out.println("bledne dane");
                                }
                            }
                            break;
                            case 2: {
                                try {
                                    Carts.getCarts().forEach(c -> {
                                        System.out.println("ID: " + c.getID() + " " + c.getSmallInfo());
                                    });
                                    System.out.println("wprowadz id wagonu ktory chcesz usunac");
                                    int sec = scanner.nextInt();
                                    scanner.nextLine();
                                    if (Carts.cartByID(sec).isConected()) {
                                        try {
                                            Carts.cartByID(sec).conectedTo.disconnect(Carts.cartByID(sec));
                                        } catch (CartNotConected e) {
                                            e.printStackTrace();
                                        }
                                    }

                                    Carts.getCarts().remove(Carts.cartByID(sec));
                                    System.out.println("wagon usuniety");

                                } catch (NullPointerException e) {
                                    System.out.println("bledne dane");
                                }
                            }
                            break;
                            case 3: {
                                Stations.getStations().forEach(s -> {
                                    System.out.println("ID: " + s.getId() + " " + s);
                                });
                                System.out.println("wprowadz id stacji, ktora chcesz usunac: ");
                                TrainStation delStation = Stations.stationByID(scanner.nextInt());
                                scanner.nextLine();
                                try {
                                    boolean arg = true;
                                    for (Locomotive loc : Trains.getTrains()) {
                                        if (loc.getSourceStation() == delStation || loc.getDestinationStation() == delStation
                                                || loc.getHomeStation() == delStation) {
                                            arg = false;
                                            System.out.println("pociag " + loc + "\n jest zwiazany z ta stacja");
                                        }
//                                        if(loc.getRoute().getRoute().contains(delStation)){
//                                            arg = false;
//                                            System.out.println("pociag "+loc+"zawiera ta stacje w trasie");
//                                        }
                                    }
                                    if (arg) {
                                        if (delStation.getOnStationCounter() == 0) {
                                            Stations.getStations().remove(delStation);
                                            System.out.println("stacja usunieta");
                                        } else {
                                            System.out.println("nie mozna usunac danej stacji. znajduje się na niej pociag, lub zmierza w jej strone");
                                        }
                                    }
                                } catch (NullPointerException e) {
                                    System.out.println("bledne dane");
                                }
                            }
                            break;
                            case 4: {

                                Stations.getStations().forEach(s -> {
                                    System.out.println("ID: " + s.getId() + " " + s);
                                });
                                System.out.println("wprowadz id stacji od ktorej chcesz odlaczyc: ");
                                TrainStation targetStation = Stations.stationByID(scanner.nextInt());
                                scanner.nextLine();
                                try {
                                    targetStation.getConectedTo().forEach(s -> {
                                        System.out.println("ID " + s.getId() + " " + s);
                                    });
                                    System.out.println("wprowadz id stacji ktora chcesz odlaczyc: ");
                                    TrainStation targetconected = Stations.stationByID(scanner.nextInt());
                                    scanner.nextLine();
                                    if (targetStation.getConectionByEndName(targetconected).getWhoacquire() == null) {
                                        targetStation.deleteConection(targetconected);
                                    } else {
                                        System.out.println("nie mozna usunac tego polaczenia");
                                    }


                                } catch (NullPointerException e) {
                                    System.out.println("bledne dane");
                                }
                            }
                            break;
                            case 5: {

                                Trains.getTrains().forEach(t -> {
                                    System.out.println("ID: " + t.getID() + " " + t);
                                });
                                System.out.println("wprowadz id lokomotywy od ktorej chcesz odpiac wagon");
                                Locomotive targetLoc = Trains.trainByID(scanner.nextInt());
                                scanner.nextLine();
                                try {
                                    for (Cart cart : targetLoc.getCarts()) {
                                        System.out.println(cart);
                                    }
                                } catch (NullPointerException e) {
                                    System.out.println("bledna wartosc");
                                }
                                System.out.println("wprowadz id wagonu ktory chcesz odpiac ");
                                int cartId = scanner.nextInt();
                                scanner.nextLine();
                                for (Cart cart : targetLoc.getCarts()) {
                                    if (cart.getID() == cartId) {
                                        try {
                                            targetLoc.disconnect(cart);
                                        } catch (CartNotConected e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }

                            }
                            break;
                            default: {
                                System.out.println("bledna opcja");
                            }
                            break;
                        }
                    }
                    break;
                    case 10: {
                        for (Map.Entry<Integer, Thread> entry : TrainThreads.getThreads().entrySet()) {
                            if (!entry.getValue().isAlive())
                                System.out.println("ID " + entry.getKey() + " " + Trains.trainByID(entry.getKey()));
                        }
                        System.out.println("wybierz ID pociagu do uruchomienia ");
                        int onId = scanner.nextInt();
                        scanner.nextLine();
                        boolean control = false;
                        for (Map.Entry<Integer, Thread> entry : TrainThreads.getThreads().entrySet()) {
                            if (!entry.getValue().isAlive()) {
                                if (entry.getKey().equals(onId)) {
                                    entry.getValue().start();
                                    System.out.println("pociag uruchomiony");
                                    control = true;
                                }
                            }
                        }
                        if (!control) {
                            System.out.println("bledne dane");
                        }

                    }
                    break;
                    case 11: {
                        for (Map.Entry<Integer, Thread> entry : TrainThreads.getThreads().entrySet()) {
                            if (entry.getValue().isAlive())
                                System.out.println("ID " + entry.getKey() + " " + Trains.trainByID(entry.getKey()));
                        }
                        System.out.println("wybierz ID pociagu do zatrzymania ");
                        int offId = scanner.nextInt();
                        scanner.nextLine();
                        boolean control = false;
                        for (Map.Entry<Integer, Thread> entry : TrainThreads.getThreads().entrySet()) {
                            if (entry.getValue().isAlive()) {
                                if (entry.getKey().equals((Integer) offId)) {
                                    entry.getValue().interrupt();
                                    control = true;
//                                    System.out.println("pociag ");
                                    break;
                                }
                            }
                        }
                        if (!control) {
                            System.out.println("bledne dane");
                            break;
                        }

                    }
                    break;
                    case 12:

                        Trains.getTrains().forEach(t -> {
                            System.out.println("ID: " + t.getID() + " " + t);
                        });
                        System.out.println("wybierz ID pociagu ktorego raport chcesz uzyskac ");
                        Locomotive infoLoc = Trains.trainByID(scanner.nextInt());
                        scanner.nextLine();
                        try {
                            System.out.println(infoLoc.giveInfo());
                        } catch (NullPointerException e) {
                            e.printStackTrace();
                            System.out.println("bledne dane");
                        }
                        break;
                    case 13:
                        System.out.println("wychodzenie");
                        for (Map.Entry<Integer, Thread> entry : TrainThreads.getThreads().entrySet()) {
                            entry.getValue().interrupt();
                        }
                        saver.interrupt();
                        exit = true;
                        return;
                    default:
                        System.out.println("bledna opcja. wprowadz poprawny numer");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("nieprawidlowe dane");
                selected = 0;
                System.out.println("nacisnij enter aby przejsc dalej");
                scanner.nextLine();
            }
            System.out.println("nacisnij enter aby przejsc dalej");
            scanner.nextLine();
            System.out.println();
        } while (!exit);


    }


}
