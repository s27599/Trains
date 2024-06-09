public class CargoCart extends Cart {


    protected int cargoCapacity;
    protected int cargoWeight;

    public CargoCart() {
        this.id = ++Cart.counter;
        needsTrainPower = false;
        type = CartType.CARGO;
        this.cargoCapacity = 1000;
        this.cargoWeight = 0;
    }

    public CargoCart(String sender, int emptyMass) {
        this.id = ++Cart.counter;
        needsTrainPower = false;
        type = CartType.CARGO;
        this.sender = sender;
        this.emptyMass = emptyMass;
        this.bruttoMass = emptyMass;
        this.conected = false;
        this.cargoCapacity = 1000;
        this.cargoWeight = 0;

    }

    @Override
    public void addCargo(int mass) throws NoSpaceException, TowingCapacityExceeded, ToHighTemp, FrozenCartException {
        if (conected) {
            if ((cargoWeight + mass <= cargoCapacity) && ((bruttoMass + mass) + conectedTo.getTowing() <= conectedTo.getMaxTowingCapacity())) {
                cargoWeight += mass;
                bruttoMass += mass;
                System.out.println("towar zaladowany");
                conectedTo.setTowing(conectedTo.getTowing() + mass);
            } else if ((bruttoMass + mass) + conectedTo.getTowing() < conectedTo.getMaxTowingCapacity()) {
                throw new TowingCapacityExceeded("This train can not tow that much");
            } else {
                throw new NoSpaceException("Max cargo mass in this cart is " + cargoCapacity);
            }
        } else {
            if ((cargoWeight + mass <= cargoCapacity)) {
                cargoWeight += mass;
                bruttoMass += mass;
                System.out.println("towar zaladowany");
            } else {
                throw new NoSpaceException("Max cargo mass in this cart is " + cargoCapacity);
            }
        }
    }

    @Override
    public void dropOffCargo(int mass) throws EmptyCartException {
        if (conected) {
            if (cargoWeight - mass >= 0) {
                cargoWeight -= mass;
                bruttoMass -= mass;
                System.out.println("towar wyladowany");
                conectedTo.setTowing(conectedTo.getTowing() - mass);
            } else {
                throw new EmptyCartException("Cart is empty");
            }
        } else {
            if (cargoWeight - mass >= 0) {
                cargoWeight -= mass;
                bruttoMass -= mass;
                System.out.println("towar wyladowany");
            } else {
                throw new EmptyCartException("Cart is empty");
            }
        }
    }

    @Override
    public String getSmallInfo() {
        return "Wagon towarowy: nadawca: " + sender+ " Przewozi: " + cargoWeight + "kg towaru";
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
        return "Wagon towarowy: nadawca: " + sender + sb + " Przewozi: " + cargoWeight + "kg towaru";
    }


}
