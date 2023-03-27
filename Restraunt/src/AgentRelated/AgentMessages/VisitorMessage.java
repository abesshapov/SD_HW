package AgentRelated.AgentMessages;
import Entities.MenuRelated.Menu;
import jade.content.AgentAction;

/**
 * Сообщение посетителя.
 */
public class VisitorMessage implements AgentAction {
    /**
     * Имя сообщения.
     */
    private String name;

    /**
     * Меню.
     */
    private Menu menu;

    /**
     * Конструктор по параметрам.
     * @param name имя.
     * @param menu меню.
     */
    public VisitorMessage(String name, Menu menu) {
        this.name = name;
        this.menu = menu;
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
    public void setName(String newName) {
        this.name = newName;
    }

    /**
     * Получить меню.
     * @return меню.
     */
    public Menu getMenu() {
        return menu;
    }

    /**
     * Задать меню.
     * @param menu меню.
     */
    public void setMenu(Menu menu) {
        this.menu = menu;
    }
}
