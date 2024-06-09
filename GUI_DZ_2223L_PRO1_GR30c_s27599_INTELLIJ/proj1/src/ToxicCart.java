import java.util.Random;

public class ToxicCart extends CargoHeavyCart {


    private boolean rainforced;
    private boolean mk2;
    private  boolean toxic ;


    public ToxicCart(String sender, int emptyMass, boolean rainforced, boolean security) {
        this.type = CartType.TOXIC;
        this.rainforced = rainforced;
        this.security = security;
        this.sender = sender;
        this.emptyMass = emptyMass;
        this.bruttoMass = emptyMass;
        this.conected = false;
        this.toxic = false;
        this.mk2 = false;
    }

    @Override
    public void addCargo(int mass) throws NoSpaceException, TowingCapacityExceeded, ToHighTemp, FrozenCartException {
        Random random = new Random();
        if(!toxic) {
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
        }else{
            System.out.println("wagon pokryty toksynami. Wymagane czyszczenie");
        }
    }

    @Override
    public void upgrade() {
        if (!mk2) {
            mk2 = true;
            System.out.println("wagon ulepszony");
            this.cargoCapacity = 2000;
            rainforced = true;

        } else {
            System.out.println("wagon jest już po ulepszeniu");
        }

    }

    @Override
    public void clean(){
        if(toxic){
            toxic= false;
            System.out.println("wagon wyczyszczony");
        }else {
            System.out.println("wagon nie wymaga czyszczenia");
        }
    }

    @Override
    public String getSmallInfo() {
        return "Wagon na materialy toksyczne: nadawca: "+sender+ " Przewozi: " + cargoWeight + "kg toksyn";
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if(this.conected){
            sb.append(" Podlaczony do ");
            sb.append(conectedTo);
        }else{
            sb.append(" Nie podlaczony");
        }
        return "Wagon na materialy toksyczne: nadawca: "+sender +sb+" Przewozi "+cargoWeight+"kg toksyn";
    }
}

