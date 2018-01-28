import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ShoppingBasket {

    BigDecimal totalSaving;
    BigDecimal subtotal;
    BigDecimal total;

    private List<Item> items;

    public ShoppingBasket(String[] itemDescriptions) {

        subtotal = new BigDecimal(0).setScale(2,BigDecimal.ROUND_HALF_UP);
        total = new BigDecimal(0).setScale(2,BigDecimal.ROUND_HALF_UP);

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

    public void calculateBasket(){
        items.stream().forEach(System.out::println);

        subtotal = new BigDecimal(items.stream().mapToDouble(e -> e.price.doubleValue()).sum());
        System.out.println("subtotal : " + subtotal);

        calculateBreadOffer();

        calculateAppleOffer();

        total = new BigDecimal(items.stream().mapToDouble(e -> e.price.doubleValue()).sum());

        System.out.println("Total : " + total.setScale(2,BigDecimal.ROUND_HALF_UP));
    }

    private void calculateAppleOffer() {
        if(items.stream().anyMatch(e -> e.getDescription().equalsIgnoreCase("apples"))){
            double applesSaving = items.stream().filter(e -> e.description.equalsIgnoreCase("Apples")).mapToDouble(e -> {
                BigDecimal saving = e.price.divide(new BigDecimal(100)).multiply(new BigDecimal(10));

                e.price = e.price.subtract(saving);
                return saving.doubleValue();
            }).sum();

            System.out.println("Apples offer: " + new BigDecimal(applesSaving).setScale(2,BigDecimal.ROUND_HALF_UP));
        }
    }

    private void calculateBreadOffer() {
        long numberOfBread = items.stream().filter(e -> e.description.equalsIgnoreCase("bread")).count();
        long numberOfSoups = items.stream().filter(e -> e.description.equalsIgnoreCase("soup")).count();

        if(numberOfSoups !=0){

            if(numberOfBread !=0){
                long availableOffers = numberOfSoups/2;
                double breadSaving = items.stream().filter(e -> e.description.equalsIgnoreCase("bread")).limit(availableOffers).mapToDouble(e -> {
                    BigDecimal saving = e.price.divide(new BigDecimal(2));
                    e.price = saving;
                    return saving.doubleValue();
                }).sum();
                System.out.println("Bread offer : " + new BigDecimal(breadSaving).setScale(2,BigDecimal.ROUND_HALF_UP));
            }else {
                System.out.println("No Bread soup offer");
            }
        }else{
            System.out.println("No Bread soup offer");
        }
    }
}
