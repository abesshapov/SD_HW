package AgentRelated.AgentMessages;
import Entities.Order;
import jade.content.AgentAction;
import java.util.HashSet;

/**
 * Сообщение от заказа.
 */
public class OrderMessage implements AgentAction {
    /**
     * Имя.
     */
    private String name;

    /**
     * Меню.
     */
    private HashSet<Order> menu;

    /**
     * Id.
     */
    private int id;

    /**
     * Конструктор по параметрам.
     * @param name имя.
     * @param menu меню.
     * @param id id.
     */
    public OrderMessage(String name, HashSet<Order> menu, int id) {
        this.name = name;
        this.menu = menu;
        this.id = id;
    }

    /**
     * Получить имя.
     * @return имя.
     */
    public String getName() {
        return name;
    }

    /**
     * Задать имя.
     * @param newName имя.
     */
    public void setLocalName(String newName) {
        name = newName;
    }

    /**
     * Получить меню.
     * @return меню.
     */
    public HashSet<Order> getMenu() {
        return menu;
    }

    /**
     * Задать меню.
     * @param menu меню.
     */
    public void setMenu(HashSet<Order> menu) {
        this.menu = menu;
    }

    /**
     * Получить id.
     * @return id.
     */
    public int getId() {
        return id;
    }

    /**
     * Задать id.
     * @param newId id.
     */
    public void setId(int newId) {
        id = newId;
    }
}
