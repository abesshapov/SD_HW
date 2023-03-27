package Entities.ProductRelated;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

/**
 * Продукт.
 */
public class Product {
    /**
     * Id.
     */
    @JsonProperty("prod_item_id")
    private int id;

    /**
     * Тип.
     */
    @JsonProperty("prod_item_type")
    private int type;

    /**
     * Название.
     */
    @JsonProperty("prod_item_name")
    private String name;

    /**
     * Компания.
     */
    @JsonProperty("prod_item_company")
    private String company;

    /**
     * Единица измерения.
     */
    @JsonProperty("prod_item_unit")
    private String unit;

    /**
     * Количество.
     */
    @JsonProperty("prod_item_quantity")
    private double quantity;

    /**
     * Цена.
     */
    @JsonProperty("prod_item_cost")
    private int price;

    /**
     * Время доставки.
     */
    @JsonProperty("prod_item_delivered")
    private Timestamp delivered;

    /**
     * Дата просрочки.
     */
    @JsonProperty("prod_item_valid_until")
    private Timestamp expirationDate;
}
