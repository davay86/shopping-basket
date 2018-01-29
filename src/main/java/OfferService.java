import java.math.BigDecimal;
import java.util.List;

public class OfferService {

    public void calcOffers(List<Item> items){
              calculateAppleOffer(items);
              calculateBreadOffer(items);
    }

    private void calculateAppleOffer(List<Item> items) {
        if(items.stream().anyMatch(e -> e.getDescription().equalsIgnoreCase("apples"))){
            double applesSaving = items.stream().filter(e -> e.description.equalsIgnoreCase("Apples")).mapToDouble(e -> {
                BigDecimal saving = e.price.divide(new BigDecimal(100)).multiply(new BigDecimal(10));

                e.price = e.price.subtract(saving);
                return saving.doubleValue();
            }).sum();

            System.out.println("Apples offer: " + new BigDecimal(applesSaving).setScale(2,BigDecimal.ROUND_HALF_UP));
        }
    }

    private void calculateBreadOffer(List<Item> items) {
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
