package Entities.OperationRelated;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.HashSet;

/**
 * Типы операций.
 */
public class OperationTypes {
    /**
     * Множество типов операций.
     */
    @JsonProperty("operation_types")
    private HashSet<OperationType> operationTypes;

    /**
     * Получить множество операций.
     * @return множество операций.
     */
    public HashSet<OperationType> getOperationTypes() {
        return operationTypes;
    }

    /**
     * Конструктор по множеству типов.
     * @param operationTypes множество типов.
     */
    public OperationTypes(HashSet<OperationType> operationTypes) {
        this.operationTypes = operationTypes;
    }
}
