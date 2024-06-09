import java.util.Random;

public class LiquidCart extends CargoCart{



    private boolean faulty;

    public LiquidCart(String sender, int emptyMass) {
        Random random = new Random();
        this.type = CartType.LIQUID;
        this.faulty = random.nextInt(3) == 1;
        this.sender = sender;
        this.emptyMass = emptyMass;
        this.bruttoMass = emptyMass;
        this.conected = false;
    }


    @Override
    public void addCargo(int mass) throws NoSpaceException, TowingCapacityExceeded, ToHighTemp, FrozenCartException {
        Random random = new Random();
        super.addCargo(mass);
        if(faulty){
            if(random.nextBoolean()){
                cargoWeight /=2;
                bruttoMass = emptyMass+cargoWeight;
                System.out.println("wagon ma wadliwy wlew, stracono polowe zaladunku");
            }
        }
    }
    @Override
    public String getSmallInfo() {
        return "Wagon na materialy ciekle: nadawca: "+sender+ " Przewozi: " + cargoWeight + "kg towaru";
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if(this.conected){
            sb.append(" podlaczony do ");
            sb.append(conectedTo);
        }else{
            sb.append(" nie podlaczony");
        }
        return "Wagon na materialy ciekle: nadawca: "+sender +sb+" Przewozi: "+cargoWeight+"kg towaru";
    }
}
