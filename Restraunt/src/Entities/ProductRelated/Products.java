package Entities.ProductRelated;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.HashSet;

/**
 * Продукты.
 */
public class Products {
    /**
     * Множество продуктов.
     */
    @JsonProperty("products")
    private HashSet<Product> products;

    /**
     * Получить множество продуктов.
     * @return множество.
     */
    public HashSet<Product> getProducts() {
        return products;
    }

    /**
     * Конструктор по множеству.
     * @param products множество.
     */
    public Products(HashSet<Product> products) {
        this.products = products;
    }
}
