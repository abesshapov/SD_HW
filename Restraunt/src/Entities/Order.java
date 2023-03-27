package Entities;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

/**
 * Заказ.
 */
public class Order implements Serializable {
    /**
     * Id блюда.
     */
    @JsonProperty("order_dish_id")
    private int id;

    /**
     * Блюдо из меню.
     */
    @JsonProperty("menu_dish")
    private int dishId;

    /**
     * Получить Id блюда.
     * @return id блюда.
     */
    public int getId() {
        return id;
    }

    /**
     * Задать Id Блюда.
     * @param newId id блюда.
     */
    public void setId(int newId) {
        id = newId;
    }

    /**
     * Получить блюдо из меню.
     * @return блюдо из меню.
     */
    public int getDishId() {
        return dishId;
    }

    /**
     * Задать блюдо из меню.
     * @param newDishId блюдо из меню.
     */
    public void setDishId(int newDishId) {
        dishId = newDishId;
    }
}
