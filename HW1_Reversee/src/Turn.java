/**
 * Выполняемый игроком ход.
 */
final public class Turn {
    /**
     * Строка, в которую выполнен ход.
     */
    final int x;
    /**
     * Столбец, в который выполнен ход.
     */
    final int y;
    /**
     * Цвет поставленной фишки.
     */
    final ChipColour chipColour;

    /**
     * Счет выполненного хода.
     */
    final double score;

    /**
     * Конструктор хода.
     * @param x Строка, в которую выполнен ход.
     * @param y Столбец, в который выполнен ход.
     * @param chipColour Цвет поставленной фишки.
     * @param score Счет выполненного хода.
     */
    Turn(int x, int y, ChipColour chipColour, double score) {
        this.x = x;
        this.y = y;
        this.chipColour = chipColour;
        this.score = score;
    }
}