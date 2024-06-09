public class CargoHeavyCart extends CargoCart {

    protected boolean security;


    public CargoHeavyCart() {
        needsTrainPower = false;
        type = CartType.CARGO;
        this.cargoCapacity = 2500;
    }

    public CargoHeavyCart(String sender, int emptyMass, boolean security) {

        this.security = security;

        this.type = CartType.CARGOHEAVY;
        this.sender = sender;
        this.emptyMass = emptyMass;
        this.bruttoMass = emptyMass;
        this.conected = false;
        this.cargoCapacity = 2500;

    }

    @Override
    public void atack() {
        if (security) {
            System.out.println("powstrzymano atak");
        } else {
            System.out.println("okradziono wagon");
            this.cargoWeight = 0;
            this.bruttoMass = emptyMass;
        }
    }

    @Override
    public String getSmallInfo() {
        return "Wagon towarowy ciezki: nadawca: " + sender+ " Przewozi: " + cargoWeight + "kg towaru";
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
        return "Wagon towarowy ciezki: nadawca: " + sender + sb + " Przewozi: " + cargoWeight + "kg towaru";
    }
}
