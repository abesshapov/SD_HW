package Entities.OperationRelated;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Тип операции.
 */
public class OperationType {
    /**
     * Id типа.
     */
    @JsonProperty("oper_type_id")
    private int id;

    /**
     * Имя типа.
     */
    @JsonProperty("oper_type_name")
    private String name;
}