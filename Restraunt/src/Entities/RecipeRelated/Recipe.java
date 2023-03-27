package Entities.RecipeRelated;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import Entities.Process;

/**
 * Карточка рецепта.
 */
public class Recipe {
    /**
     * Id.
     */
    @JsonProperty("card_id")
    private int id;

    /**
     * Название.
     */
    @JsonProperty("dish_name")
    private String name;

    /**
     * Описание.
     */
    @JsonProperty("card_descr")
    private String description;

    /**
     * Время готовки.
     */
    @JsonProperty("card_time")
    private double time;

    /**
     * Процесс готовки.
     */
    @JsonUnwrapped
    private Process process;
}
