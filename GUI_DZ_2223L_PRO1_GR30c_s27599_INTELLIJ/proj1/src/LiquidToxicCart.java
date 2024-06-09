import java.util.Random;

public class LiquidToxicCart extends CargoHeavyCart {


    private boolean faulty;
    private boolean rainforced;
    private boolean mk2;
    private boolean toxic;

    public LiquidToxicCart(String sender, int emptyMass, boolean rainforced) {
        Random random = new Random();
        this.type = CartType.LIQUIDTOXIC;
        this.rainforced = rainforced;
        this.mk2 = false;
        this.faulty = random.nextInt(3) == 1;
        this.sender = sender;
        this.emptyMass = emptyMass;
        this.bruttoMass = emptyMass;
        this.conected = false;
        this.security = true;
        toxic = false;
    }


    @Override
    public void addCargo(int mass) throws NoSpaceException, TowingCapacityExceeded, ToHighTemp, FrozenCartException {
        Random random = new Random();
        if (!toxic) {
            if (!rainforced) {
                if (random.nextBoolean()) {
                    super.addCargo(mass);
                } else {
                    if (mass - 10 > 0)
                        super.addCargo(mass - 10);
                    this.toxic = true;

                    System.out.println("toksyny wydostały się z wagonu, wagonn staje się toksyczny");
                }
            } else {
                if (random.nextInt(4) == 1) {
                    if (mass - 10 > 0)
                        super.addCargo(mass - 10);
                    this.toxic = true;
                    System.out.println("toksyny wydostały się z wagonu, wagonn staje się toksyczny");
                } else {
                    super.addCargo(mass - 10);
                }
            }
            if (faulty) {
                if (random.nextBoolean()) {
                    cargoWeight /= 2;
                    bruttoMass = emptyMass + cargoWeight;
                    this.toxic = true;
                    System.out.println("wagon ma wadliwy wlew, polowa toksyn wylala sie. wymagane czyszczenie");
                }
            }
        }else{
            System.out.println("wagon pokryty toksynami. wymagane czyszczenie");
        }
    }

    @Override
    public void upgrade() {
        Random random = new Random();
        if (!mk2) {
            mk2 = true;
            System.out.println("wagon ulepszony");
            this.cargoCapacity = 2000;
            faulty = random.nextInt(3) == 1;;
            rainforced = true;

        } else {
            System.out.println("wagon jest już po ulepszeniu");
        }

    }

    @Override
    public void clean() {
        if (toxic) {
            toxic = false;
            System.out.println("wagon wyczyszczony");
        } else {
            System.out.println("wagon nie wymaga czyszczenia");
        }
    }


    @Override
    public String getSmallInfo() {
        return "Wagon na ciekle materialy toksyczne: nadawca: " + sender+ " Przewozi: " + cargoWeight + "kg toksyn";
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.conected) {
            sb.append(" Podlaczony do ");
            sb.append(conectedTo);
        } else {
            sb.append(" Nie podlaczony");
        }
        return "Wagon na ciekle materialy toksyczne: nadawca: " + sender + sb+" Przewozi: "+cargoWeight+"kg toksyn";
    }
}
