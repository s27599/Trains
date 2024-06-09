public class LuggageMailCart extends Cart {



    private final int luggageCapacity;
    private int luggageLoaded;

    private final int mailCapacity;
    private int mailLoaded;


//jeszcze jedno


    public LuggageMailCart(String sender, int emptyMass,  int luggageCapacity, int mailCapacity) {
        this.id = ++Cart.counter;
        needsTrainPower = false;
        type = CartType.LUGGAGEMAIL;
        this.sender = sender;
        this.emptyMass = emptyMass;
        this.bruttoMass = emptyMass;
        this.luggageCapacity = luggageCapacity;
        this.mailCapacity = mailCapacity;
        this.mailLoaded = 0;
        this.luggageLoaded = 0;
        this.conected = false;
    }

    @Override
    public void addLuggage() throws TowingCapacityExceeded, NoSpaceException {
        if (conected) {
            if ((luggageLoaded + 1 <= luggageCapacity) && ((bruttoMass + 20) + conectedTo.getTowing() <= conectedTo.getMaxTowingCapacity())) {
                luggageLoaded++;
                bruttoMass += 20; //przyjmujemy, że bagaż waży średnio 20kg
                conectedTo.setTowing(conectedTo.getTowing() + 20);
                System.out.println("bagaz zaladowany");
            } else if ((bruttoMass + 20) + conectedTo.getTowing() < conectedTo.getMaxTowingCapacity()) {
                throw new TowingCapacityExceeded("This train can not tow that much");
            } else {
                throw new NoSpaceException("This cart has no space.");
            }
        } else {
            if ((luggageLoaded + 1 <= luggageCapacity)) {
                luggageLoaded++;
                System.out.println("bagaz zaladowany");
                bruttoMass += 20; //przyjmujemy, że bagaż waży średnio ok 20 kg
            } else {
                throw new NoSpaceException("This cart has no space.");
            }
        }
    }

    @Override
    public void dropOffLuggage() throws EmptyCartException {
        if (conected) {
            if (luggageLoaded - 1 >= 0) {
                luggageLoaded--;
                bruttoMass -= 20;
                System.out.println("bagaz wyladowany");

                conectedTo.setTowing(conectedTo.getTowing() - 20);
            } else {
                throw new EmptyCartException("Cart is empty");
            }
        } else {
            if (luggageLoaded - 1 >= 0) {
                luggageLoaded--;
                bruttoMass -= 20;
                System.out.println("bagaz wyladowany");

            } else {
                throw new EmptyCartException("Cart is empty");
            }
        }
    }
    @Override
    public void addMail() throws TowingCapacityExceeded, NoSpaceException {
        if (conected) {
            if ((mailLoaded + 1 <= mailCapacity) && ((bruttoMass + 5) + conectedTo.getTowing() <= conectedTo.getMaxTowingCapacity())) {
                mailLoaded++;
                bruttoMass += 5; //przyjmujemy, że poczta waży średnio 5ks
                conectedTo.setTowing(conectedTo.getTowing() + 5);
            } else if ((bruttoMass + 5) + conectedTo.getTowing() < conectedTo.getMaxTowingCapacity()) {
                throw new TowingCapacityExceeded("This train can not tow that much");
            } else {
                throw new NoSpaceException("This cart has no space.");
            }
        } else {
            if ((mailLoaded + 1 <= mailCapacity)) {
                mailLoaded++;
                bruttoMass += 5; //przyjmujemy, że człowiek waży średnio ok 80 kg
            } else {
                throw new NoSpaceException("This cart has no space.");
            }
        }
    }

    @Override
    public void dropOffMail() throws EmptyCartException {
        if (conected) {
            if (mailLoaded - 1 >= 0) {
                mailLoaded--;
                bruttoMass -= 5;
                System.out.println("poczta wyladowana");

                conectedTo.setTowing(conectedTo.getTowing() - 5);
            } else {
                throw new EmptyCartException("Cart is empty");
            }
        } else {
            if (mailLoaded - 1 >= 0) {
                mailLoaded--;
                bruttoMass -= 5;
                System.out.println("poczta wyladowana");

            } else {
                throw new EmptyCartException("Cart is empty");
            }
        }
    }
    @Override
    public String getSmallInfo() {
        return "Wagon bagazowo-pocztowy: nadawca: "+sender+" Przewozi: "+mailLoaded+" sztuk paczek i "+luggageLoaded+" sztuk bagazu";
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
        return "Wagon bagazowo-pocztowy: nadawca: "+sender +sb+" Przewozi: "+mailLoaded+" sztuk paczek i "+luggageLoaded+" sztuk bagazu";
    }

}
