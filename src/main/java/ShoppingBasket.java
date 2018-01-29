import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ShoppingBasket {

    BigDecimal subtotal;
    BigDecimal total;

    private List<Item> items;
    private OfferService offerService;

    public ShoppingBasket(String[] itemDescriptions) {

        offerService = new OfferService();

        subtotal = new BigDecimal(0).setScale(2,BigDecimal.ROUND_HALF_UP);
        total = new BigDecimal(0).setScale(2,BigDecimal.ROUND_HALF_UP);

        populateBasket(itemDescriptions);
    }

    public void calculateBasket(){
        items.stream().forEach(System.out::println);

        subtotal = new BigDecimal(items.stream().mapToDouble(e -> e.price.doubleValue()).sum());
        System.out.println("subtotal : " + subtotal);

        offerService.calcOffers(items);

        total = new BigDecimal(items.stream().mapToDouble(e -> e.price.doubleValue()).sum());

        System.out.println("Total : " + total.setScale(2,BigDecimal.ROUND_HALF_UP));
    }

    private void populateBasket(String[] itemDescriptions) {
        items = new ArrayList();
        for (String item : itemDescriptions) {
            String itemName = item.toUpperCase();
            switch (itemName){
                case "BREAD":
                    items.add(new Item(new BigDecimal(0.80).setScale(2,BigDecimal.ROUND_HALF_UP),"Bread"));
                    break;
                case "SOUP":
                    items.add(new Item(new BigDecimal(0.65).setScale(2,BigDecimal.ROUND_HALF_UP),"Soup"));
                    break;
                case "MILK":
                    items.add(new Item(new BigDecimal(1.35).setScale(2,BigDecimal.ROUND_HALF_UP),"Milk"));
                    break;
                case "APPLES":
                    items.add(new Item(new BigDecimal(1.00).setScale(2,BigDecimal.ROUND_HALF_UP),"Apples"));
                    break;
                default:
                    break;
            }
        }
    }
}
