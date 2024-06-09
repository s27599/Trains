import java.util.Objects;

public class PassengerCart extends Cart {


    private final int seatsNumber;
    private int occupiedSeats;
    //2 cechy

    public PassengerCart(String sender, int emptyMass, int seatsNumber) {
        this.id = ++Cart.counter;
        type = CartType.PASSENGER;
        needsTrainPower = true;
        this.sender = sender;
        this.emptyMass = emptyMass;
//        this.bruttoMass = bruttoMass;
        this.bruttoMass = emptyMass;
        this.seatsNumber = seatsNumber;
        this.occupiedSeats = 0;
    }
    @Override
    public void addPerson() throws AllSeatsOccupiedException, TowingCapacityExceeded {
        if (conected) {
            if ((occupiedSeats + 1 <= seatsNumber) && ((bruttoMass + 80) + conectedTo.getTowing() <= conectedTo.getMaxTowingCapacity())) {
                occupiedSeats++;
                bruttoMass += 80; //przyjmujemy, że człowiek waży średnio ok 80 kg
                conectedTo.setTowing(conectedTo.getTowing() + 80);
                System.out.println("osoba dodana");
            } else if ((bruttoMass + 80) + conectedTo.getTowing() < conectedTo.getMaxTowingCapacity()) {
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
                System.out.println("osoba wysiadzona");

                conectedTo.setTowing(conectedTo.getTowing() - 80);
            } else {
                throw new EmptyCartException("Cart is empty");
            }
        } else {
            if (occupiedSeats - 1 >= 0) {
                occupiedSeats--;
                bruttoMass -= 80;
                System.out.println("osoba wysiadzona");

            } else {
                throw new EmptyCartException("Cart is empty");
            }
        }
    }


    @Override
    public String getSmallInfo() {
        return "Wagon pasazerski: nadawca: "+sender+ " Przewozi "+occupiedSeats+" osob";
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
        return "Wagon pasazerski: nadawca: "+sender +sb+ " Przewozi "+occupiedSeats+" osob";
    }

}
