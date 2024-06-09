public class MailCart extends Cart {


    private final int mailCapacity;
    private int mailLoaded;
    //jedna cecha


    public MailCart(String sender, int emptyMass, int mailCapacity) {
        this.id = ++Cart.counter;
        type = CartType.MAIL;
        needsTrainPower = true;
        this.sender = sender;
        this.emptyMass = emptyMass;
        this.bruttoMass = emptyMass;
        this.mailCapacity = mailCapacity;
        this.mailLoaded = 0;
    }

    @Override
    public void addMail() throws TowingCapacityExceeded, NoSpaceException {
        if (conected) {
            if ((mailLoaded + 1 <= mailCapacity) && ((bruttoMass + 5) + conectedTo.getTowing() <= conectedTo.getMaxTowingCapacity())) {
                mailLoaded++;
                bruttoMass += 5; //przyjmujemy, że poczta waży średnio 5kg
                conectedTo.setTowing(conectedTo.getTowing() + 5);
                System.out.println("poczta zaladowana");

            } else if ((bruttoMass + 5) + conectedTo.getTowing() < conectedTo.getMaxTowingCapacity()) {
                throw new TowingCapacityExceeded("This train can not tow that much");
            } else {
                throw new NoSpaceException("This cart has no space.");
            }
        } else {
            if ((mailLoaded + 1 <= mailCapacity)) {
                mailLoaded++;
                System.out.println("poczta zaladowana");
                bruttoMass += 5; //przyjmujemy, że poczta waży średnio ok 5 kg
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
        return "Wagon pocztowy: nadawca: "+sender+" Przewozi "+mailLoaded+" sztuk paczek";
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
        return "Wagon pocztowy: nadawca: "+sender +sb+" Przewozi "+mailLoaded+" sztuk paczek";
    }

}
