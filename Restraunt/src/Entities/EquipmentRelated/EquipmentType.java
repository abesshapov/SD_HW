package Entities.EquipmentRelated;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Тип оборудования.
 */
public class EquipmentType {
    /**
     * Id типа оборудования.
     */
    @JsonProperty("equip_type_id")
    private int id;

    /**
     * Название типа оборудования.
     */
    @JsonProperty("equip_type_name")
    private String name;

    /**
     * Получение id типа оборудования.
     * @return id типа оборудования.
     */
    public int getId() {
        return id;
    }

    /**
     * Назначение id типа оборудования.
     * @param newId новый id.
     */
    public void setId(int newId) {
        id = newId;
    }

    /**
     * Получение названия типа оборудования.
     * @return тип оборудования.
     */
    public String getName() {
        return name;
    }

    /**
     * Назначение названия типа оборудования.
     * @param newName новое название.
     */
    public void setName(String newName) {
        name = newName;
    }
}
