package AgentRelated.AgentMessages;
import jade.content.AgentAction;

/**
 * Сообщение агента.
 */
public class Message implements AgentAction {
    /**
     * Имя сообщения.
     */
    private String name;

    /**
     * Содержимое сообщения.
     */
    private String content;

    /**
     * Конструктор по параметрам.
     * @param name имя.
     * @param content содержимое.
     */
    public Message(String name, String content) {
        this.name = name;
        this.content = content;
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
     * Получить содержимое.
     * @return содержимое.
     */
    public String getContent() {
        return content;
    }

    /**
     * Задать содержимое.
     * @param content содержимое.
     */
    public void setContent(String content) {
        this.content = content;
    }
}
