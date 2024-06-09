import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Locomotive implements Runnable, Comparable<Locomotive> {
    private static int counter = 0;
    private String name;
    private int ID;

    //nav section
    private TrainStation homeStation;
    private TrainStation sourceStation;
    private TrainStation destinationStation;
    private TrainStation actualStation;
    private TrainStation actualDestStation;
    private Route route;
    private double distanceTraveled = 0; // ogólny przejechany dystans trasy
    private double actDistanceTraveled = 0; //przejechany dystans na aktualnym odcinku
    private double percentTraveled = 0;
    private double actpercentTraveled = 0;
    private double velocity = 140;//startowa prędkość

    private static int fastVariable = 3600000; // zmienna tylko po to żeby oglądanie czy wszystko działa nie trwało godzinami
    //po wywolaniu w main odpowiedniej funkcji pociagi przyspieszaja razy 100
    // domyslna wartosc 3600000 zeby wszystko przeliczalo sie zgodnie ze stanem faktycznym zmniejszanie ilosci zer
    // zwieksza ilosc dodawanej  odleglosci wzgledem predkosci pociagu

    //cart section
    private int maxCarriage;
    private int maxTowingCapacity;
    private int maxPowerConected;
    private Cart[] carts;
    private int conectedCarts = 0;
    private int powerConectedCarts = 0;
    private int towing = 0;

    public int getMaxCarriage() {
        return maxCarriage;
    }

    public int getConectedCarts() {
        return conectedCarts;
    }

    public Locomotive(String name, int maxCarriage, int maxTowingCapacity, int maxPowerConected,
                      TrainStation homeStation, TrainStation sourceStation, TrainStation destinationStation) {
        this.name = name;
        this.ID = ++counter;
        this.maxCarriage = maxCarriage;
        this.maxTowingCapacity = maxTowingCapacity;
        this.maxPowerConected = maxPowerConected;
        this.homeStation = homeStation;
        this.sourceStation = sourceStation;
        this.actualStation = sourceStation;
        this.destinationStation = destinationStation;
        this.carts = new Cart[this.maxCarriage];
        if (sourceStation != null && destinationStation != null)
            route = new Route(sourceStation, destinationStation);

    }

    public static void MakeITFAST(int mode){
        if(mode ==0)
            fastVariable = 36000;
        else
            fastVariable = 3600;
    }


    public void RandomCarts(){
        String[] names = {"Kowalski Transport", "Nowak Logistics", "Zielinski Shipping", "Mazur Freight", "Wójcik Haulage", "Kaczmarek Express",
                "Lewandowski Cargo", "Jankowski Distribution", "Adamczyk Transport", "Pawlak Trucking", "Szymanski Shipping",
                "Gorski Freight", "Krupa Logistics", "Michalak Transport", "Czerwinski Haulage", "Kwiatkowski Express",
                "Bak Trucking", "Wrobel Shipping", "Wojciechowski Transport", "Pawlikowski Distribution", "Czajka Cargo",
                "Urbanski Logistics", "Piotrowski Haulage", "Malinowski Freight", "Wrona Trucking", "Krawczyk Logistics",
                "Zalewski Transport", "Sikora Shipping", "Pietrzak Freight", "Mielczarek Haulage", "Marciniak Express",
                "Duda Cargo", "Sobczak Distribution", "Górecki Transport", "Rutkowski Trucking", "Kasprzyk Logistics",
                "Grabowski Shipping", "Klimczak Freight", "Olszewski Haulage", "Jaworski Express", "Wróblewski Cargo",
                "Kołodziej Distribution", "Adamski Transport", "Kaczorowski Trucking", "Pawlowski Shipping", "Głowacki Logistics",
                "Witkowski Freight", "Szymczak Haulage", "Wronski Express", "Nowacki Transport", "Mazurek Logistics",
                "Borowski Shipping"};


        Random random = new Random();
        int control = 0;
        for (int i = 0; i < this.maxCarriage+control; i++) {
            int type = random.nextInt(13)+1;
            switch (type){
                case 1:{
                    String name = names[random.nextInt(names.length)];
                    Cart toAdd = new LuggageMailCart(name,400,random.nextInt(28)+2,random.nextInt(28)+2);
                    Carts.add(toAdd);
                    try {
                        this.conect(toAdd);
                    } catch (TooManyCartsConected | TowingCapacityExceeded | TooManyPowerConected |
                             CartAlreadyConnected e) {
                        control++;
                        break;
                    }
                }break;
                case 2:{
                    String name = names[random.nextInt(names.length)];
                    if(this.powerConectedCarts<this.maxPowerConected) {
                        Cart toAdd = new MailCart(name, 300, random.nextInt(28) + 2 );
                        Carts.add(toAdd);
                        try {
                            this.conect(toAdd);
                        } catch (TooManyCartsConected | TowingCapacityExceeded | TooManyPowerConected |
                                 CartAlreadyConnected e) {
                            break;
                        }
                    }else{
                        control++;
                    }
                }break;
                case 3:{
                    String name = names[random.nextInt(names.length)];
                    if(this.powerConectedCarts<this.maxPowerConected) {
                        Cart toAdd = new PassengerCart(name, 250, random.nextInt(28) + 2);
                        Carts.add(toAdd);
                        try {
                            this.conect(toAdd);
                        } catch (TooManyCartsConected | TowingCapacityExceeded | TooManyPowerConected |
                                 CartAlreadyConnected e) {
                            break;
                        }
                    }else{
                        control++;
                    }
                }break;
                case 4:{
                    String name = names[random.nextInt(names.length)];
                    if(this.powerConectedCarts<this.maxPowerConected) {
                        Cart toAdd = new BuffetCart(name, 280, random.nextInt(28) + 2);
                        Carts.add(toAdd);
                        try {
                            this.conect(toAdd);
                        } catch (TooManyCartsConected | TowingCapacityExceeded | TooManyPowerConected |
                                 CartAlreadyConnected e) {
                            break;
                        }
                    }else{
                        control++;
                    }
                }break;
                case 5:{
                    String name = names[random.nextInt(names.length)];
                    Cart toAdd = new CargoCart(name,100);
                    Carts.add(toAdd);
                    try {
                        this.conect(toAdd);
                    } catch (TooManyCartsConected | TowingCapacityExceeded | TooManyPowerConected |
                             CartAlreadyConnected e) {
                        control++;
                        break;
                    }
                }break;
                case 6:{
                    String name = names[random.nextInt(names.length)];
                    Cart toAdd = new CargoHeavyCart(name,150, random.nextBoolean());
                    Carts.add(toAdd);
                    try {
                        this.conect(toAdd);
                    } catch (TooManyCartsConected | TowingCapacityExceeded | TooManyPowerConected |
                             CartAlreadyConnected e) {
                        control++;
                        break;
                    }
                }break;
                case 7:{
                    String name = names[random.nextInt(names.length)];
                    if(this.powerConectedCarts<this.maxPowerConected) {
                        Cart toAdd = new chillerCart(name, 200, random.nextInt(30) + -20,random.nextBoolean());
                        Carts.add(toAdd);
                        try {
                            this.conect(toAdd);
                        } catch (TooManyCartsConected | TowingCapacityExceeded | TooManyPowerConected |
                                 CartAlreadyConnected e) {
                            break;
                        }
                    }else{
                        control++;
                    }
                }break;
                case 8:{
                    String name = names[random.nextInt(names.length)];
                    Cart toAdd = new LiquidCart(name,220);
                    Carts.add(toAdd);
                    try {
                        this.conect(toAdd);
                    } catch (TooManyCartsConected | TowingCapacityExceeded | TooManyPowerConected |
                             CartAlreadyConnected e) {
                        control++;
                        break;
                    }
                }break;
                case 9:{
                    String name = names[random.nextInt(names.length)];
                    Cart toAdd = new gazCart(name,189, random.nextBoolean());
                    Carts.add(toAdd);
                    try {
                        this.conect(toAdd);
                    } catch (TooManyCartsConected | TowingCapacityExceeded | TooManyPowerConected |
                             CartAlreadyConnected e) {
                        control++;
                        break;
                    }
                }break;
                case 10:{
                    String name = names[random.nextInt(names.length)];
                    Cart toAdd = new ExplosivesCart(name,250, random.nextBoolean(),random.nextBoolean());
                    Carts.add(toAdd);
                    try {
                        this.conect(toAdd);
                    } catch (TooManyCartsConected | TowingCapacityExceeded | TooManyPowerConected |
                             CartAlreadyConnected e) {
                        control++;
                        break;
                    }
                }break;
                case 11:{
                    String name = names[random.nextInt(names.length)];
                    Cart toAdd = new ToxicCart(name,250, random.nextBoolean(),random.nextBoolean());
                    Carts.add(toAdd);
                    try {
                        this.conect(toAdd);
                    } catch (TooManyCartsConected | TowingCapacityExceeded | TooManyPowerConected |
                             CartAlreadyConnected e) {
                        control++;
                        break;
                    }
                }break;
                case 12:{
                    String name = names[random.nextInt(names.length)];
                    Cart toAdd = new LiquidToxicCart(name,250, random.nextBoolean());
                    Carts.add(toAdd);
                    try {
                        this.conect(toAdd);
                    } catch (TooManyCartsConected | TowingCapacityExceeded | TooManyPowerConected |
                             CartAlreadyConnected e) {
                        control++;
                        break;
                    }
                }break;
                default:{
                    control++;
                }break;

            }
            if (control>this.maxCarriage+5){
                break;
            }
        }



    }





    Thread velocityChanger = new Thread(() -> {
        Random random = new Random();
        boolean checker = false;
        while (!Thread.interrupted()) {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
//                System.out.println("pociag dojechal do stacji "+ actualStation);
                return;
            }

            double tmp = velocity * 0.03;
            if (random.nextBoolean() || velocity < 1) {
                velocity += tmp;
            } else {
                velocity -= tmp;
            }

            if (velocity > 200 && !checker) {
                try {
                    throw new RailroadHazard("Train id: " + ID + " name: " + name + "destination: " + destinationStation +
                            "velocity: " + velocity);
                } catch (RailroadHazard e) {
                    e.printStackTrace();
                }
                checker = true;
            } else if (checker && velocity <= 200) {
                checker = false;
            }
        }
    });

    private static double roundDec2(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    public void newroute() {
        this.route = new Route(sourceStation, destinationStation);
    }


    @Override
    public void run() {
        velocityChanger.start();
        while (!Thread.interrupted()) {
            if (route == null)
                route = new Route(sourceStation, destinationStation);
            ArrayList<TrainStation> routeList = route.getRoute();
            int actStationID = 0;

            actualStation = routeList.get(actStationID);
            while (actualStation != destinationStation) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
//                    saver.interrupt();
                    velocityChanger.interrupt();
                    System.out.println("pociag zatrzymany");
                    return;
                }
                actualDestStation = routeList.get(actStationID + 1);
                //sprawdzanie czy istnieje polaczenie na ktore chcemy wjechac
                if (actualStation.getConections().contains(actualStation.getConectionByEndName(actualDestStation)))
                    actualStation.setOnStationCounter(actualStation.getOnStationCounter() - 1);
                else//szukamy nowej trasy z naszej lokalizacji do stacji docelowej
                    route = new Route(actualStation, destinationStation);

                //kontrola obecnosci na stacji
                Conection actualConection = actualStation.getConectionByEndName(actualDestStation);
                actualDestStation.setOnStationCounter(actualDestStation.getOnStationCounter() + 1);

                if (actualConection.getWhoacquire() != this) {

                    try {
                        actualConection.getSemaphore().acquire();
                        actualConection.setWhoacquire(this);
                    } catch (InterruptedException e) {
                        System.out.println("pociag zatrzymano");
                        velocityChanger.interrupt();
                        return;
                    }

                }


                actDistanceTraveled += velocity / fastVariable; //  zamiast fastvariable powinno byc 3600000
                distanceTraveled += velocity / fastVariable;// ta zmienna jest tylko po to aby przyspieszyc program, wiecej info na gorze line26
                actpercentTraveled = ((actDistanceTraveled / actualStation.getConectionByEndName(actualDestStation).getDistance()) * 100);
                percentTraveled = ((distanceTraveled / route.getLength()) * 100);
//               // System.out.println(this.ID+" "+ velocity);
                if ((actDistanceTraveled / actualStation.getConectionByEndName(actualDestStation).getDistance()) * 100 >= 100) {


                    //jak przejedzie od stacji a do b
                    actualConection.release(); //zwolnij tor
                    actualConection.setWhoacquire(null);
                    actualConection.getSemaphore().release();
//                    System.out.println("zwolniony " + this.ID+" "+ actpercentTraveled);
                    actualDestStation.setOnStationCounter(actualDestStation.getOnStationCounter() - 1);

                    actStationID++;
                    actualStation = routeList.get(actStationID);
//                    System.out.println("na stacji " + actualStation);
                    actualStation.setOnStationCounter(actualStation.getOnStationCounter() + 1);

                    actDistanceTraveled = 0;

                    try {
                        Thread.sleep(2000);//czekanie miedzy stacjami
                    } catch (InterruptedException e) {
                        System.out.println("pociag zatrzymany");
                        velocityChanger.interrupt();
                        return;
                    }

//                    System.out.println(this.ID + " przejechany odcinek calej trasy " + roundDec2(percentTraveled) + "%");
                }


            }
            try {

                Thread.sleep(30000); //czekanie na zawrotce    ------------ zmienic na 30 000
            } catch (InterruptedException e) {

                velocityChanger.interrupt();
                System.out.println("pociag zatrzymany");
                return;
            }
            TrainStation tmp = destinationStation;
            destinationStation = sourceStation;
            sourceStation = tmp;
            distanceTraveled = 0;
            percentTraveled = 0;
            route = new Route(sourceStation, destinationStation);
//            System.out.println("nowa trasa " + route);

        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {

            velocityChanger.interrupt();
            System.out.println("pociag zatrzymany");
            return;
        }

        velocityChanger.interrupt();
    }


    public void conect(Cart cart) throws TooManyCartsConected, TowingCapacityExceeded, TooManyPowerConected, CartAlreadyConnected {
        if (!cart.isConected()) {
            if (conectedCarts + 1 <= maxCarriage) {
                if (towing + cart.getMass() <= maxTowingCapacity) {
                    if (cart.getNeedsTrainPower()) {
                        if (powerConectedCarts + 1 <= maxPowerConected) {
                            carts[conectedCarts] = cart;
                            cart.setConected(true);
                            cart.setConectedTo(this);
                            conectedCarts++;
                            powerConectedCarts++;
                            towing += cart.getMass();
                        } else {
                            throw new TooManyPowerConected("Max power Conected capacity is " + maxPowerConected);
                        }
                    } else {
                        carts[conectedCarts] = cart;
                        cart.setConected(true);
                        cart.setConectedTo(this);

                        conectedCarts++;
                        towing += cart.getMass();
                    }
                } else {
                    throw new TowingCapacityExceeded("Max towing capacity is " + this.maxTowingCapacity);
                }
            } else {
                throw new TooManyCartsConected("You can conect only " + this.maxCarriage + " carts");
            }

        } else {
            throw new CartAlreadyConnected("this cart is already conected");
        }
    }

    public void disconnect(Cart cart) throws CartNotConected {
        if (cart != null) {
            if ((Arrays.asList(carts).contains(cart))) {
                Cart[] tmp = new Cart[this.maxCarriage];
                int index = -1;
                for (int i = 0; (i < carts.length) && (index != i); i++) {
                    if (carts[i] == cart) {
                        index = i;
                    }
                }
                int j = 0;
                if (index >= 0)
                    for (int i = 0; i < carts.length; i++) {
                        if (i != index) {
                            tmp[j++] = carts[i];
                        }
                    }
                carts = tmp;
                towing -= cart.getMass();
                conectedCarts--;
                cart.setConected(false);
                cart.setConectedTo(null);

                if (cart.getNeedsTrainPower()) {
                    powerConectedCarts--;
                }
            } else {
                throw new CartNotConected("This cart is not connected to this train");
            }
        }
    }


    public void ShowConected() {
        for (Cart c : carts) {
            if (c != null)
                System.out.print(c + " ");
        }
    }

    public double getPercentTraveled() {
        return percentTraveled;
    }

    public double getActpercentTraveled() {
        return actpercentTraveled;
    }

    public Route getRoute() {
        return route;
    }

    public String getName() {
        return name;
    }

    public int getID() {
        return ID;
    }

    public Cart[] getCarts() {
        return carts;
    }

    public void setTowing(int towing) {
        this.towing = towing;
    }

    public int getTowing() {
        return towing;
    }

    public int getMaxTowingCapacity() {
        return maxTowingCapacity;
    }

    public TrainStation getHomeStation() {
        return homeStation;
    }

    public TrainStation getSourceStation() {
        return sourceStation;
    }

    public TrainStation getDestinationStation() {
        return destinationStation;
    }

    private String soutCarts(int mode) {
        boolean control = false;
        StringBuilder sb = new StringBuilder();

        List<Cart> sortedCarts = new ArrayList<>();
        for (Cart cart : carts) {
            if (cart != null) {
                sortedCarts.add(cart);
            }
        }
        sortedCarts.sort(null);

        for (Cart c : sortedCarts) {
            if (c != null) {
                sb.append('[');
                if (mode == 0) {
                    sb.append(c.getSmallInfo());
                } else {
                    sb.append(c.shortInf());
                }
                sb.append("] ");
                control = true;
            }
        }
        if (!control) {
            sb.append("brak wagonow");
        }
        return sb.toString();

    }


    public String giveInfo() {
        if (this.velocityChanger.isAlive()) {
            if (actpercentTraveled >= 100) {
                return"Pociag " + name + " ID: " + ID +" stacja docelowa: "+destinationStation+
                        "\n znajduje sie na stacji " + actualStation + "\n" +
                        "przejechal: "+roundDec2(percentTraveled)+"% calej trasy\n"+
                        "podlaczone wagony " + soutCarts(0)+ "\n";
            } else if (actpercentTraveled == 0) {
               return("Pociag " + name + " ID: " + ID +" stacja docelowa: "+destinationStation+
                       "\nczeka na zwolnienie odcinka na  stacji: " + actualStation +
                       "nastepna stacja " + actualDestStation)+ "\n"+
                       "przejechal: "+roundDec2(percentTraveled)+"% calej trasy\n"+
                       "podlaczone wagony\n" + soutCarts(0)+ "\n";

            } else {
                return "Pociag " + name + " ID: " + ID +" stacja docelowa: "+destinationStation+
                        "\n jedzie z predkoscia " + roundDec2(velocity) +
                        "\n miedzy stacjami: " + actualStation + " a " + actualDestStation + "\n" +
                        "przejechal " + roundDec2(actpercentTraveled) + "% tej trasy, co stanowi " + roundDec2(percentTraveled) + "% calej trasy\n" +
                        "podlaczone wagony " + soutCarts(0)+ "\n";
//
            }
        } else {
            return "Pociag " + name + " ID: " + ID +
                    "\n czeka na stacji " + actualStation + "\n" +
                    "podlaczone wagony " + soutCarts(0)+ "\n";
        }
    }


    @Override
    public String toString() {

        return "Pociag[" +
                "nazwa=" + name +
                ", stacja macierzysta=" + homeStation +
                ", stacja zrodlowa=" + sourceStation +
                ", stacja docelowa=" + destinationStation +
                ",\n podlaczone wagony: " + soutCarts(1) +
                ']';
    }

    @Override
    public int compareTo(Locomotive o) {
//        if(this.percentTraveled ==0 && o.percentTraveled ==0){
//            return this.ID-o.ID;
//        }else
            return (int)(o.percentTraveled*100 - this.percentTraveled*100);
    }
}
