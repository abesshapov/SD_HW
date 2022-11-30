import java.util.concurrent.TimeUnit;


/**
 * Игрок - опытный компьютер.
 */
public class Senior extends Junior {
    /**
     * Конструктор опытного компьютера.
     * @param chipColour Используемый цвет.
     */
    Senior(ChipColour chipColour) {
        super(chipColour);
    }

    /**
     * Выполнение хода опытным компьюетром.
     * @param field Игровое поле.
     * @param chipColour Используемый цвет.
     * @return Выполненный ход.
     */
    @Override
    Turn makeATurn(Field field, ChipColour chipColour) {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (Exception e) {

        }
        int[][] possibleTurnsCoordinates = findPossibleTurnVariants(field);
        return findBestSeniorTurn(possibleTurnsCoordinates, field, this.chipColour);
    }

    /**
     * Определение лучшего хода с учетом хода противника.
     * @param possibleTurnsCoordinates Возможные ходы.
     * @param field Игровое поле.
     * @param chipColour Используемый цвет.
     * @return Лучший найденный ход.
     */
    Turn findBestSeniorTurn(int[][] possibleTurnsCoordinates, Field field, ChipColour chipColour) {
        Turn bestTurn = new Turn(-1, -1, chipColour, -13);
        for (int[] coordinates : possibleTurnsCoordinates) {
            int[][] consideredTurn = new int[1][2];
            consideredTurn[0] = coordinates;
            Turn seniorBestTurn = super.findBestTurn(consideredTurn, field, chipColour);
            Field fieldAfterTurn = new Field(field.field);
            fieldAfterTurn.reinitializeFieldAfterTurn(seniorBestTurn.x - 1, seniorBestTurn.y - 1, chipColour);
            ChipColour nextColour = chipColour == ChipColour.WHITE? ChipColour.BLACK:ChipColour.WHITE;
            Turn bestEnemyTurn = super.makeATurn(fieldAfterTurn, nextColour);
            if (bestTurn.score < seniorBestTurn.score - bestEnemyTurn.score) {
                bestTurn = new Turn(seniorBestTurn.x, seniorBestTurn.y, chipColour,seniorBestTurn.score - bestEnemyTurn.score);
            }
        }
        return bestTurn;
    }
}