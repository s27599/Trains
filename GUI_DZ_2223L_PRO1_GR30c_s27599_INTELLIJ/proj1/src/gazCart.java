import java.util.Random;

public class gazCart extends CargoCart {


    private boolean sparking;
    private boolean broken;

    public gazCart(String sender, int emptyMass, boolean sparking) {
        this.type = CartType.GAZ;
        this.sparking = sparking;
        this.sender = sender;
        this.emptyMass = emptyMass;
        this.bruttoMass = emptyMass;
        this.conected = false;
    }

    @Override
    public void addCargo(int mass) throws NoSpaceException, TowingCapacityExceeded, ToHighTemp, FrozenCartException {
        Random random = new Random();
        if(!broken) {
            super.addCargo(mass);
            if (sparking) {
                if (random.nextBoolean()) {
                    cargoWeight = 0;
                    bruttoMass = emptyMass;
                    System.out.println("wagon eksplodowal, potrzebna naprawa");
                    broken = true;
                }
            }
        }else{
            System.out.println("wagon zniszczony. wymagana naprawa");
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
        return "Wagon na materialy gazowe: nadawca: "+sender+ " Przewozi: " + cargoWeight + "kg towaru";
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
        return "Wagon na materialy gazowe: nadawca: "+sender +sb+" Przewozi: "+cargoWeight+"kg towaru";
    }
}
