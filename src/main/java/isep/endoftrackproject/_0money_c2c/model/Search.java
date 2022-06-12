package isep.endoftrackproject._0money_c2c.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Search {
    private String keywords;
    private Double price = 20.0;
    //Provisional default values
    //TODO calculate pertinent values and store them in a specialized file (for example a .properties)
    private Double priceRange = 50.0;
    //TODO will have to be retrieved from the current user
    //Defaults to Eiffel Tower coordinates
    private Double latitude = 48.858093;
    private Double longitude = 2.294694;
    //TODO if no location provided, this value must be very very large to include any product
    //max distance between two points on Earth is 12450km
    private Double maxDistance = 1000.0;
    private Boolean available = true;
    private Item.Quality[] acceptedQualities = new Item.Quality[]{Item.Quality.NEW, Item.Quality.GOOD_QUALITY};

    // price and priceRange cannot be negative
    public void setPriceRange(Double priceRange) {
        if (priceRange < 0) throw new IllegalArgumentException("Price range cannot be negative");
        this.priceRange = priceRange;
    }

    public void setPrice(Double price) {
        if (price < 0) throw new IllegalArgumentException("Price cannot be negative");
        this.price = price;
    }

    public Double getPriceMin() {
        return (priceRange < price) ? price - priceRange : 0;
    }

    public Double getPriceMax() {
        return price + priceRange;
    }
}
