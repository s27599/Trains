import java.util.Objects;

public abstract class Cart implements Comparable<Cart>{

    protected static int counter = 0;
    protected int id;
    protected CartType type;
    protected String sender;
    protected int emptyMass;
    protected int bruttoMass;
    protected boolean needsTrainPower;
    protected boolean conected;
    protected Locomotive conectedTo;






    public int getMass() {
        return bruttoMass;
    }

    public int getID() {
        return id;
    }

    public boolean isConected() {
        return conected;
    }

    public void setConected(boolean conected) {
        this.conected = conected;
    }

    public void setConectedTo(Locomotive conectedTo) {
        this.conectedTo = conectedTo;
    }

    public boolean getNeedsTrainPower() {
        return needsTrainPower;
    }

    public CartType getType() {
        return type;
    }

    public abstract String getSmallInfo();


    public void addLuggage() throws TowingCapacityExceeded, NoSpaceException, NotAllowedException {
        throw new NotAllowedException("ten wagon nie przewozi bagazu");
    }
    public void dropOffLuggage() throws NotAllowedException, EmptyCartException {
        throw new NotAllowedException("ten wagon nie przewozi bagazu");
    }

    public  void dropOffCargo(int mass) throws EmptyCartException, NotAllowedException {
        throw new NotAllowedException("ten wagon nie przewozi ladunku");
    }

    public void addCargo(int i) throws NoSpaceException, TowingCapacityExceeded, ToHighTemp, FrozenCartException, NotAllowedException {
        throw new NotAllowedException("nie mozna dodac ladunku do tego wagonu");
    }
    public void atack() throws NotAllowedException {
        throw new NotAllowedException("nie mozna atakowac tego wagonu");

    }

    public void addPerson() throws NotAllowedException, AllSeatsOccupiedException, TowingCapacityExceeded {
        throw new NotAllowedException("nie mozna dodac pasazerow do tego wagonu");

    }

    public void melt() throws NotAllowedException{
        throw new NotAllowedException("nie mozna odmrozic tego wagonu");
    }
    public boolean isFrozen() throws NotAllowedException{
        throw new NotAllowedException("ten wagon nie moze zamaznac");
    }

    public void dropOffPerson() throws NotAllowedException, EmptyCartException {
        throw new NotAllowedException("ten wagon nie ma pasazerow");

    }
    public void addMail() throws  NotAllowedException, TowingCapacityExceeded, NoSpaceException {
        throw new NotAllowedException("ten wagon nie przewozi poczty");

    }
    public void blow() throws NotAllowedException{
        throw new NotAllowedException("nie mozna wysadzic tego wagonu");
    }

    public void repair() throws NotAllowedException {
        throw new NotAllowedException("nie mozna naprawic tego wagonu");
    }
    public void upgrade() throws NotAllowedException {
        throw new NotAllowedException("nie mozna ulepszyc tego wagonu");
    }
    public void dropOffMail() throws  NotAllowedException, EmptyCartException {
        throw new NotAllowedException("ten wagon nie przewozi poczty");

    }
    public void clean() throws NotAllowedException{
        throw new NotAllowedException("nie można czyscic tego wagonu");
    }

    @Override
    public int compareTo(Cart o) {
        return this.bruttoMass-o.bruttoMass;
    }

    public String shortInf(){
        String com ;
        switch (type){
            case TOXIC -> com =  "Wagon na materialy toksyczne. Nadawca "+sender;
            case EXPLOSIVES -> com = "Wagon na materialy wybuchowe. Nadawca "+sender;
            case CHILLER -> com =  "Wagon chlodniczy. Nadawca "+sender;
            case LUGGAGEMAIL -> com =  "Wagon bagazowo-pocztowy. Nadawca "+sender;
            case LIQUIDTOXIC -> com =  "Wagon na ciekle materialy toksyczne. Nadawca "+sender;
            case CARGOHEAVY -> com =  "Wagon towarowy ciezki. Nadawca "+sender;
            case LIQUID -> com =  "Wagon na materialy ciekle. Nadawca "+sender;
            case BUFFET -> com =  "Wagon restauracyjny. Nadawca "+sender;
            case CARGO -> com =  "Wagon towarowy. Nadawca "+sender;
            case MAIL -> com =  "Wagon pocztowy. Nadawca "+sender;
            case GAZ -> com =  "Wagon na materialy gazowe. Nadawca "+sender;
            case PASSENGER -> com =  "Wagon pasazerski. Nadawca "+sender;
            default -> com = "Unknown type";
        }
        return com;
    }
}
