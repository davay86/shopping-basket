import java.math.BigDecimal;

public class Item {

    BigDecimal price;
    String description;

    public Item(BigDecimal price, String description) {
        this.price = price;
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "-" + description + " : " + price.setScale(2,BigDecimal.ROUND_HALF_UP);
    }
}
