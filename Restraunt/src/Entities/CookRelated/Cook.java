package Entities.CookRelated;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Класс повар.
 */
public class Cook {
    /**
     * Id повара.
     */
    @JsonProperty("cook_id")
    private int id;

    /**
     * Имя повара.
     */
    @JsonProperty("cook_name")
    private String name;

    /**
     * Активен ли повар.
     */
    @JsonProperty("cook_active")
    private boolean isActive;

    /**
     * Получение id повара.
     * @return id повара.
     */
    public int getId() {
        return id;
    }

    /**
     * Назначение id повара.
     * @param newId новый id.
     */
    public void setId(int newId) {
        id = newId;
    }

    /**
     * Получение имени повара.
     * @return имя повара.
     */
    public String getName() {
        return name;
    }

    /**
     * Назначение имени повара.
     * @param newName новое имя.
     */
    public void setName(String newName) {
        name = newName;
    }

    /**
     * Определение, является ли повар активным.
     * @return активен ли повар.
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * Назначение статуса активности повару.
     * @param newActivity новый статус активности.
     */
    public void setActivity(boolean newActivity) {
        isActive = newActivity;
    }
}
