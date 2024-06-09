import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class Carts {

    static Set<Cart> carts;

    public Carts() {
        carts = new LinkedHashSet<>();
    }


    public static void add(Cart cart){
        if(!carts.contains(cart))
             carts.add(cart);
    }

    public static Cart cartByID(int ID) {
        for (Cart cart : carts) {
            if (cart.getID() == ID) {
                return cart;
            }
        }
        return null;
    }

    public static Set<Cart> getCarts() {
        return carts;
    }

}
