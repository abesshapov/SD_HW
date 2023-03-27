package Entities.OperationRelated;
import Entities.IngredientRelated.Ingredient;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.HashSet;

/**
 * Операция.
 */
public class Operation {
    /**
     * Тип операции.
     */
    @JsonProperty("oper_type_id")
    private int operationTypeId;

    /**
     * Тип оборудования.
     */
    @JsonProperty("equip_type")
    private int equipType;

    /**
     * Время проделывания операции.
     */
    @JsonProperty("oper_time")
    private int time;

    /**
     * Асинхронность.
     */
    @JsonProperty("oper_async_point")
    private int async;

    /**
     * Требуемые ингредиенты.
     */
    @JsonProperty("oper_products")
    private HashSet<Ingredient> ingredients;
}
