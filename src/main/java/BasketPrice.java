import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BasketPrice {

    public static void main(String[] args){
        ShoppingBasket shoppingBasket = new ShoppingBasket(args);
        shoppingBasket.calculateBasket();
    }
}
