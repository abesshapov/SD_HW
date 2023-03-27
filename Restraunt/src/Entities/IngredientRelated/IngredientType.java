package Entities.IngredientRelated;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Тип ингредиента.
 */
public class IngredientType {
    /**
     * Id типа ингредиента.
     */
    @JsonProperty("prod_type_id")
    private int id;

    /**
     * Название типа ингредиента.
     */
    @JsonProperty("prod_type_name")
    private String name;

    /**
     * Является ли ингредиент едой.
     */
    @JsonProperty("prod_is_food")
    private boolean isFood;

    /**
     * Получение id типа ингредиента.
     * @return id типа ингредиента.
     */
    public int getId() {
        return id;
    }

    /**
     * Назначение id типа ингредиента.
     * @param newId новый id.
     */
    public void setId(int newId) {
        id = newId;
    }

    /**
     * Получение названия типа.
     * @return название типа.
     */
    public String getName() {
        return name;
    }

    /**
     * Назначение названия типа.
     * @param newName новое название.
     */
    public void setName(String newName) {
        name = newName;
    }

    /**
     * Определение, является ли ингредиент едой.
     * @return ингредиент - еда.
     */
    public boolean isActive() {
        return isFood;
    }

    /**
     * Назначение статуса съедобности ингредиенту.
     * @param newIsFood новый статус съедобности.
     */
    public void setActivity(boolean newIsFood) {
        isFood = newIsFood;
    }

}
