package Entities.EquipmentRelated;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.HashSet;

/**
 * Типы оборудования.
 */
public class EquipmentTypes {
    /**
     * Типы оборудования.
     */
    @JsonProperty("equipment_type")
    private HashSet<EquipmentType> equipmentTypes;

    /**
     * Получение типов оборудования.
     * @return типы оборудования.
     */
    public HashSet<EquipmentType> getEquipmentTypes() {
        return equipmentTypes;
    }

    /**
     * Конструктор по списку типов оборудования.
     * @param equipmentTypes типы оборудования.
     */
    public EquipmentTypes(HashSet<EquipmentType> equipmentTypes) {
        this.equipmentTypes = equipmentTypes;
    }
}
