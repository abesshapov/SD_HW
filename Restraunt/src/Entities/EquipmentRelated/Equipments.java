package Entities.EquipmentRelated;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.HashSet;

/**
 * Список оборудования ресторана.
 */
public class Equipments {
    /**
     * Список оборудования.
     */
    @JsonProperty("equipment")
    private HashSet<Equipment> equipments;

    /**
     * Получение множества оборудования.
     * @return множество оборудования.
     */
    public HashSet<Equipment> getEquipment() {
        return equipments;
    }

    /**
     * Конструктор по множеству оборудования.
     * @param equipments множество оборудования.
     */
    public Equipments(HashSet<Equipment> equipments) {
        this.equipments = equipments;
    }
}
