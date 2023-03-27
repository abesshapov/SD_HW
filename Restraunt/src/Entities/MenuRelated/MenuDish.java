package Entities.MenuRelated;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

/**
 * Блюдо в меню.
 */
public class MenuDish implements Serializable {
    /**
     * Id блюда в меню.
     */
    @JsonProperty("menu_dish_id")
    private int id;

    /**
     * Id рецепта.
     */
    @JsonProperty("menu_dish_card")
    private int recipeId;

    /**
     * Цена блюда.
     */
    @JsonProperty("menu_dish_price")
    private int price;

    /**
     * Доступно ли блюдо.
     */
    @JsonProperty("menu_dish_active")
    private boolean isActive;

    /**
     * Конструктор по параметрам.
     * @param id id блюда.
     * @param recipeId id рецепта.
     * @param price цена.
     * @param isActive доступность.
     */
    public MenuDish(int id, int recipeId, int price, boolean isActive) {
        this.id = id;
        this.recipeId = recipeId;
        this.price = price;
        this.isActive = isActive;
    }

    /**
     * Получить id блюда.
     * @return id блюда.
     */
    public int getId() {
        return id;
    }

    /**
     * Задать Id блюда.
     * @param newId id блюда.
     */
    public void setId(int newId) {
        id = newId;
    }

    /**
     * Получить id рецепта.
     * @return id рецепта.
     */
    public int getRecipeId() {
        return recipeId;
    }

    /**
     * Задать id рецепта.
     * @param newRecipeId id рецепта.
     */
    public void setRecipeId(int newRecipeId) {
        recipeId = newRecipeId;
    }

    /**
     * Получить цену.
     * @return цена.
     */
    public int getPrice() {
        return price;
    }

    /**
     * Задать цену.
     * @param newPrice цена
     */
    public void setPrice(int newPrice) {
        price = newPrice;
    }

    /**
     * Доступно ли блюдо.
     * @return доступно ли.
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * Задание доступности.
     * @param newActivity новая доступность.
     */
    public void setAvailable(boolean newActivity) {
        isActive = newActivity;
    }
}