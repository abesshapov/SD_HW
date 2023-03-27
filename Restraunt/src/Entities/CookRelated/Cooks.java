package Entities.CookRelated;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.HashSet;

/**
 * Список поваров ресторана.
 */
public class Cooks {
    /**
     * Множество поваров.
     */
    @JsonProperty("cookers")
    private HashSet<Cook> cooks;

    /**
     * Получение множества поваров.
     * @return множество поваров.
     */
    public HashSet<Cook> getCooks() {
        return cooks;
    }

    /**
     * Конструктор по множеству поваров.
     * @param cooks
     */
    public Cooks(HashSet<Cook> cooks) {
        this.cooks = cooks;
    }
}
