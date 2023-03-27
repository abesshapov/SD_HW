package Entities.EquipmentRelated;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Класс оборудование.
 */
public class Equipment {
    /**
     * Id оборудования.
     */
    @JsonProperty("equip_id")
    private int id;

    /**
     * Тип оборудования.
     */
    @JsonProperty("equip_type")
    private int equipmentType;

    /**
     * Название оборудования.
     */
    @JsonProperty("equip_name")
    private String name;

    /**
     * Активно ли оборудование.
     */
    @JsonProperty("equip_active")
    private boolean isActive;

    /**
     * Получение id оборудования.
     * @return id оборудования.
     */
    public int getId() {
        return id;
    }

    /**
     * Назначение id оборудования.
     * @param newId новый id.
     */
    public void setId(int newId) {
        id = newId;
    }

    /**
     * Получение названия оборудования.
     * @return название повара.
     */
    public String getName() {
        return name;
    }

    /**
     * Назначение названия оборудования.
     * @param newName новое название.
     */
    public void setName(String newName) {
        name = newName;
    }

    /**
     * Получение типа оборудования.
     * @return тип оборудования.
     */
    public int getEquipmentType() {
        return equipmentType;
    }

    /**
     * Назначение нового типа оборудования.
     * @param newEquipmentType новый тип оборудования.
     */
    public void setEquipmentType_id(int newEquipmentType) {
        equipmentType = newEquipmentType;
    }

    /**
     * Определение, является ли оборудование активным.
     * @return активно ли оборудование.
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * Назначение статуса активности оборудованию.
     * @param newActivity новый статус активности.
     */
    public void setActivity(boolean newActivity) {
        isActive = newActivity;
    }
}