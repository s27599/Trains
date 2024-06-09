public class Presentation {
    public static void main(String[] args) {


        TrainStation staion1 = new TrainStation("kowalewo");
        TrainStation staion2 = new TrainStation();
        TrainStation station3 = new TrainStation("janewo", 12, 53);

        staion1.conect(staion2);
        staion2.conect(station3);
        station3.conect(staion1);

        Locomotive locomotive = new Locomotive("tomek", 1234, 12323, 25, station3, staion1, staion2);
        Cart cart = new chillerCart("kowalski", 12, -5, false);
        Cart cart2 = new ExplosivesCart("kowalski", 12, false, false);
        Cart cart3 = new ToxicCart("kowalski", 12, false, false);
        Cart cart4 = new gazCart("kowalski", 12, true);
        Cart cart5 = new LiquidCart("kowalski", 12);
        Cart cart6 = new LuggageMailCart("kowalski", 12, 23, 23);

        try {
            locomotive.conect(cart);
            locomotive.conect(cart2);
            locomotive.conect(cart3);
            locomotive.conect(cart4);
            locomotive.conect(cart5);
            locomotive.conect(cart6);

        } catch (TooManyCartsConected | TowingCapacityExceeded | TooManyPowerConected | CartAlreadyConnected e) {
            e.printStackTrace();
        }


        try {
            cart.addCargo(123);
        } catch (FrozenCartException | NotAllowedException | NoSpaceException | TowingCapacityExceeded | ToHighTemp e) {
            e.printStackTrace();
        }

        try{
            if (cart.isFrozen()) {
                cart.melt();
            }
            cart.addCargo(123);
            System.out.println(cart.getSmallInfo());
        } catch (FrozenCartException | NotAllowedException | NoSpaceException | TowingCapacityExceeded | ToHighTemp e) {
            e.printStackTrace();
        }




        try {
            cart2.addCargo(123);
            cart2.atack();
            cart2.addCargo(1233);
            cart2.blow();
            cart2.repair();
            System.out.println(cart2.getSmallInfo());
        } catch (NoSpaceException | TowingCapacityExceeded | ToHighTemp | FrozenCartException | NotAllowedException e) {
            throw new RuntimeException(e);
        }

        try {
            cart3.addCargo(123);
            cart3.addCargo(124);
            cart3.addCargo(126);
            cart3.clean();
            cart3.upgrade();
            System.out.println(cart3.getSmallInfo());
        } catch (NoSpaceException | TowingCapacityExceeded | ToHighTemp | FrozenCartException | NotAllowedException e) {
            throw new RuntimeException(e);
        }
        try {
            cart4.addCargo(123);
            cart4.addCargo(123);
            cart4.addCargo(123);
            cart4.repair();
            cart4.addCargo(123);
            System.out.println(cart4.getSmallInfo());
        } catch (NoSpaceException | TowingCapacityExceeded | ToHighTemp | FrozenCartException | NotAllowedException e) {
            throw new RuntimeException(e);
        }
        try {
            cart5.addCargo(123);
            cart5.addCargo(123);
            cart5.addCargo(123);
            System.out.println(cart5.getSmallInfo());
        } catch (NoSpaceException | TowingCapacityExceeded | ToHighTemp | FrozenCartException | NotAllowedException e) {
            throw new RuntimeException(e);
        }
        try {
            cart6.addMail();
            cart6.addMail();
            cart6.addMail();
            cart6.addLuggage();
            cart6.addLuggage();
            cart6.addLuggage();
            cart6.addLuggage();
        } catch (NoSpaceException | TowingCapacityExceeded  | NotAllowedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(cart6.getSmallInfo());


        System.out.println(locomotive);


        Thread th1 = new Thread(locomotive);
        th1.start();
        System.out.println(locomotive.giveInfo());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(locomotive.giveInfo());


        th1.interrupt();
    }
}
