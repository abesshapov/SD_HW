package Entities.IngredientRelated;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Ингредиент.
 */
public class Ingredient {
    /**
     * Тип ингредиента.
     */
    @JsonProperty("prod_type")
    private int type;

    /**
     * Количество ингредиента.
     */
    @JsonProperty("prod_quantity")
    private double quantity;
}
