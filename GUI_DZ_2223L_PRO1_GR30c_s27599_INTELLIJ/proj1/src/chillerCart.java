import java.util.Random;

public class chillerCart extends CargoCart {


    private int temperature;
    private boolean NoFrost;
    private boolean frozen;

    public chillerCart(String sender, int emptyMass, int temperature, boolean NoFrost) {
        this.needsTrainPower = true;
        this.temperature = temperature;
        this.NoFrost = NoFrost;
        this.type = CartType.CHILLER;
        this.sender = sender;
        this.emptyMass = emptyMass;
        this.bruttoMass = emptyMass;
        this.conected = false;

    }


    @Override
    public void addCargo(int mass) throws NoSpaceException, TowingCapacityExceeded, ToHighTemp, FrozenCartException {
        Random random = new Random();
        if (!NoFrost && !frozen) {

            frozen = random.nextInt(3) == 1;

        }
        if (frozen) {
            throw new FrozenCartException("cart is frozen");
        } else if (temperature < 0)
            super.addCargo(mass);
        else {
            throw new ToHighTemp("this cart is not cold enough");
        }
    }

    @Override
    public void melt() {
        frozen = false;
        System.out.println("wagon odmrozony");
    }

    @Override
    public boolean isFrozen() {
        return frozen;
    }

    @Override
    public String getSmallInfo() {
        return "Wagon chlodniczy: nadawca: " + sender + " Przewozi: " + cargoWeight + "kg towaru";
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
        return "Wagon chlodniczy: nadawca: " + sender + sb + " Przewozi: " + cargoWeight + "kg towaru";
    }
}
