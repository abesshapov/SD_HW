package Entities.MenuRelated;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.HashSet;

/**
 * Меню.
 */
public class Menu implements Serializable {
    /**
     * Список блюд.
     */
    @JsonProperty("menu_dishes")
    private HashSet<MenuDish> dishes;

    /**
     * Получить список блюд.
     * @return список блюд.
     */
    public HashSet<MenuDish> getMenu() {
        return dishes;
    }

    /**
     * Конструктор по списку блюд.
     * @param dishes список блюд.
     */
    public Menu(HashSet<MenuDish> dishes) {
        this.dishes = dishes;
    }
}
