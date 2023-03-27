package Entities;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.sql.Timestamp;
import java.util.HashSet;

/**
 * Посетитель.
 */
public class Visitor {
    /**
     * Имя.
     */
    @JsonProperty("vis_name")
    private String name;

    /**
     * Время оформления заказа.
     */
    @JsonProperty("vis_ord_started")
    private Timestamp start;

    /**
     * Время закрытия заказа.
     */
    @JsonProperty("vis_ord_ended")
    private Timestamp end;

    /**
     * Цена.
     */
    @JsonProperty("vis_ord_total")
    private int total;

    /**
     * Заказ.
     */
    @JsonProperty("vis_ord_dishes")
    private HashSet<Order> order;
}
