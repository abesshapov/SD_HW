/**
 * Игрок.
 */
public abstract class Player {
    /**
     * Имя игрока.
     */
    final String name;
    /**
     * Цвет используемых фишек.
     */
    ChipColour chipColour;
    /**
     * Лучший результат в текущей игровой сессии.
     */
    int bestResult;

    /**
     * Конструктор игрока.
     * @param name Имя игрока
     * @param chipColour Цвет используемых фишек.
     */
    Player(String name, ChipColour chipColour) {
        this.name = name;
        this.chipColour = chipColour;
        bestResult = 0;
    }

    /**
     * Выполнение игроком хода.
     * @param field Игровое поле.
     * @param chipColour Цвет используемых фишек.
     * @return Выполненный ход.
     */
    abstract Turn makeATurn(Field field, ChipColour chipColour);
}
