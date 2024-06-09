public class BuffetCart extends Cart {


    private final int seatsNumber;
    private int occupiedSeats;

    private int productMass;

    public BuffetCart(String sender, int emptyMass, int seatsNumber) {
        type = CartType.BUFFET;
        needsTrainPower = true;
        this.id = ++Cart.counter;
        this.sender = sender;
        this.emptyMass = emptyMass;
        this.bruttoMass = emptyMass;
        this.seatsNumber = seatsNumber;
    }

    @Override
    public void addPerson() throws AllSeatsOccupiedException, TowingCapacityExceeded {
        if (conected) {
            if ((occupiedSeats + 1 <= seatsNumber) && ((bruttoMass + 80) + conectedTo.getTowing() <= conectedTo.getMaxTowingCapacity())) {
                occupiedSeats++;
                bruttoMass += 80; //przyjmujemy, że człowiek waży średnio ok 80 kg
                System.out.println("osoba dodana");
                conectedTo.setTowing(conectedTo.getTowing() + 80);
            } else if ((bruttoMass += 80) + conectedTo.getTowing() < conectedTo.getMaxTowingCapacity()) {
                throw new TowingCapacityExceeded("This train can not tow that much");
            } else {
                throw new AllSeatsOccupiedException("Max seats number in this cart is " + seatsNumber);
            }
        } else {
            if ((occupiedSeats + 1 <= seatsNumber)) {
                occupiedSeats++;
                System.out.println("osoba dodana");
                bruttoMass += 80; //przyjmujemy, że człowiek waży średnio ok 80 kg
            } else {
                throw new AllSeatsOccupiedException("Max seats number in this cart is " + seatsNumber);
            }
        }
    }


    @Override
    public void dropOffPerson() throws EmptyCartException {
        if (conected) {
            if (occupiedSeats - 1 >= 0) {
                occupiedSeats--;
                bruttoMass -= 80;
                System.out.println("osoba wysadzona");

                conectedTo.setTowing(conectedTo.getTowing() - 80);
            } else {
                throw new EmptyCartException("Cart is empty");
            }
        } else {
            if (occupiedSeats - 1 >= 0) {
                occupiedSeats--;
                bruttoMass -= 80;
                System.out.println("osoba wysadzona");

            } else {
                throw new EmptyCartException("Cart is empty");
            }
        }
    }


    @Override
    public void addCargo(int mass) throws TowingCapacityExceeded {
        if (conected) {
            if (((bruttoMass + mass) + conectedTo.getTowing() <= conectedTo.getMaxTowingCapacity())) {
                productMass += mass;
                bruttoMass += mass;
                conectedTo.setTowing(conectedTo.getTowing() + mass);
                System.out.println("ladunek zaladowany");

            } else {
                throw new TowingCapacityExceeded("This train can not tow that much");
            }
        } else {
            productMass += mass;
            bruttoMass += mass;
            System.out.println("ladunek zaladowany");

        }
    }

    @Override
    public void dropOffCargo(int mass) throws EmptyCartException {
        if (conected) {
            if (productMass - mass >= 0) {
                productMass -= mass;
                bruttoMass -= mass;
                System.out.println("ladunek wyladowany");

                conectedTo.setTowing(conectedTo.getTowing() - mass);
            } else {
                throw new EmptyCartException("Cart is empty");
            }
        } else if (productMass - mass >= 0) {
            productMass -= mass;
            bruttoMass -= mass;
            System.out.println("ladunek wyladowany");

        } else {
            throw new EmptyCartException("Cart is empty");
        }
    }

    @Override
    public String getSmallInfo() {
        return "Wagon restauracyjny: nadawca: " + sender+ " Przewozi: " + occupiedSeats + " osob i " + productMass + "kg towaru";
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
        return "Wagon restauracyjny: nadawca: " + sender + sb + " Przewozi: " + occupiedSeats + " osob i " + productMass + "kg towaru";
    }
}
