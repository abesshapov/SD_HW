package Entities.RecipeRelated;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.HashSet;

/**
 * Рецепты.
 */
public class Recipes {
    /**
     * Множество рецептов.
     */
    @JsonProperty("dish_cards")
    private HashSet<Recipe> recipes;

    /**
     * Получить множество рецептов.
     * @return множество рецептов.
     */
    public HashSet<Recipe> getRecipes() {
        return recipes;
    }

    /**
     * Конструктор по множеству.
     * @param recipes множество.
     */
    public Recipes(HashSet<Recipe> recipes) {
        this.recipes = recipes;
    }
}
