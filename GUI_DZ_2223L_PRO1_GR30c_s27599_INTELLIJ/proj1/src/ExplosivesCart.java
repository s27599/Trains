import java.util.Random;

public class ExplosivesCart extends CargoHeavyCart {


    private boolean extraProtection;
    private boolean broken;


    public ExplosivesCart(String sender, int emptyMass, boolean basicSecurity, boolean extraProtection) {
        this.type = CartType.EXPLOSIVES;
        this.extraProtection = extraProtection;
        this.security = basicSecurity;
        this.sender = sender;
        this.emptyMass = emptyMass;
        this.bruttoMass = emptyMass;
        this.conected = false;
    }


    @Override
    public void addCargo(int mass) throws NoSpaceException, TowingCapacityExceeded, ToHighTemp, FrozenCartException {
        if(!broken) {
            super.addCargo(mass);
        }else{
            System.out.println("wagon zepsuty");
        }
    }

    @Override
    public void atack() {
        Random random = new Random();
        if (extraProtection) {
            System.out.println("powstrzymano atak");
        } else {
            if (security) {
                if (random.nextBoolean()) {
                    System.out.println("okradziono wagon");
                    this.cargoWeight = 0;
                    this.bruttoMass = emptyMass;
                } else {
                    System.out.println("powstrzymano atak");
                }
            } else {
                System.out.println("okradziono wagon");
                this.cargoWeight = 0;
                this.bruttoMass = emptyMass;
            }

        }
    }

    @Override
    public void blow() {
        if (cargoWeight > 0 && !extraProtection) {
            cargoWeight = 0;
            bruttoMass = emptyMass;
            System.out.println("wagon eksplodowal, potrzebna naprawa");
            broken = true;
        } else if (cargoWeight > 0) {
            System.out.println("wysadzenie zakonczone niepowodzeniem");
        }else{
            System.out.println("wagon pusty, nie ma ladunkow wybuchowych");
        }

    }

    @Override
    public void repair() {
        if (broken) {
            broken = false;
            System.out.println("wagon naprawiony");
        } else {
            System.out.println("z wagonem wszystko dobrze");
        }

    }
    @Override
    public String getSmallInfo() {
        return "Wagon na materialy wybuchowe: nadawca: "+sender+ " Przewozi: " + cargoWeight + "kg towaru";
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
        return "Wagon na materialy wybuchowe: nadawca: "+sender +sb+" Przewozi: "+cargoWeight+"kg towaru";
    }
}
