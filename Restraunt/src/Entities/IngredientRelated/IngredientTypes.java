package Entities.IngredientRelated;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.HashSet;

/**
 * Типы ингредиентов.
 */
public class IngredientTypes {
    /**
     * Типы ингредиентов.
     */
    @JsonProperty("product_types")
    private HashSet<IngredientType> ingredientTypes;

    /**
     * Получение типов ингредиентов.
     * @return типы ингредиентов.
     */
    public HashSet<IngredientType> getIngredientTypes() {
        return ingredientTypes;
    }

    /**
     * Конструктор по множеству типов.
     * @param ingredientTypes множество типов.
     */
    public IngredientTypes(HashSet<IngredientType> ingredientTypes) {
        this.ingredientTypes = ingredientTypes;
    }
}
