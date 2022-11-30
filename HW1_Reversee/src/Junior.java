/**
 * Игрок - неопытный компьютер.
 */
public class Junior extends Player {
    /**
     * Конструктор компьютера.
     * @param chipColour Цвет используемых фишек.
     */
    Junior(ChipColour chipColour) {
        super("Computer" + Math.random(), chipColour);
    }

    /**
     * Определение возможных ходов.
     * @param field Игровое поле текущей игры.
     * @return Двумерный массив координат возможных ходов.
     */
    int[][] findPossibleTurnVariants(Field field) {
        int possibleTurns = 0;
        for (int i = 0; i < field.size; i++) {
            for (int j = 0; j < field.size; j++) {
                if (field.field[i][j].colour == ChipColour.ACHIEVABLE) {
                    possibleTurns++;
                }
            }
        }
        int[][] possibleTurnsCoordinates = new int[possibleTurns][2];
        int foundVariants = 0;
        for (int i = 0; i < field.size; i++) {
            for (int j = 0; j < field.size; j++) {
                if (field.field[i][j].colour == ChipColour.ACHIEVABLE) {
                    possibleTurnsCoordinates[foundVariants] = new int[2];
                    possibleTurnsCoordinates[foundVariants][0] = i;
                    possibleTurnsCoordinates[foundVariants++][1] = j;
                }
            }
        }
        return possibleTurnsCoordinates;
    }

    /**
     * Выполнение хода компьютером.
     * @param field Игровое поле текущей игры.
     * @param chipColour Используемый для хода цвет.
     * @return Выполненный ход.
     */
    @Override
    Turn makeATurn(Field field, ChipColour chipColour) {
        int[][] possibleTurnsCoordinates = findPossibleTurnVariants(field);
        return findBestTurn(possibleTurnsCoordinates, field, this.chipColour);
    }

    /**
     * Определение счета для фишки, поставленной в левый верхний угол.
     * @param field Игровое поле текущей игры.
     * @param chipColour Используемый для хода цвет.
     * @return Счет для фишки.
     */
    double scoreForLeftTopCorner(Field field, ChipColour chipColour) {
        double score = 2 * (field.field[0][0].checkLowerNeighbor(field, chipColour) +
                field.field[0][0].checkRightNeighbor(field, chipColour)) +
                field.field[0][0].checkRightLowerNeighbor(field, chipColour) + 0.8;
        return score;
    }

    /**
     * Определение счета для фишки, поставленной в правый верхний угол.
     * @param field Игровое поле текущей игры.
     * @param chipColour Используемый для хода цвет.
     * @return Счет для фишки.
     */
    double scoreForRightTopCorner(Field field, ChipColour chipColour) {
        double score = 2 * (field.field[0][field.size - 1].checkLowerNeighbor(field, chipColour) +
                field.field[0][field.size - 1].checkLeftNeighbor(field, chipColour)) +
                field.field[0][field.size - 1].checkLeftLowerNeighbor(field, chipColour) + 0.8;
        return score;
    }

    /**
     * Определение счета для фишки, поставленной в левый нижний угол.
     * @param field Игровое поле текущей игры.
     * @param chipColour Используемый для хода цвет.
     * @return Счет для фишки.
     */
    double scoreForLeftBottomCorner(Field field, ChipColour chipColour) {
        double score = 2 * (field.field[field.size - 1][0].checkHigherNeighbor(field, chipColour) +
                field.field[field.size - 1][0].checkRightNeighbor(field, chipColour)) +
                field.field[field.size - 1][0].checkRightHigherNeighbor(field, chipColour) + 0.8;
        return score;
    }

    /**
     * Определение счета для фишки, поставленной в правый нижний угол.
     * @param field Игровое поле текущей игры.
     * @param chipColour Используемый для хода цвет.
     * @return Счет для фишки.
     */
    double scoreForRightBottomCorner(Field field, ChipColour chipColour) {
        double score = 2 * (field.field[field.size - 1][field.size - 1].checkHigherNeighbor(field, chipColour) +
                field.field[field.size - 1][field.size - 1].checkLeftNeighbor(field, chipColour)) +
                field.field[field.size - 1][field.size - 1].checkLeftHigherNeighbor(field, chipColour) + 0.8;
        return score;
    }

    /**
     * Определение счета для фишки, поставленной на верхнюю грань поля.
     * @param field Игровое поле текущей игры.
     * @param chipColour Используемый для хода цвет.
     * @return Счет для фишки.
     */
    double scoreForTopEdge(Field field, ChipColour chipColour, int[] coordinates) {
        double score = 2 * (field.field[0][coordinates[1]].checkRightNeighbor(field, chipColour) +
                field.field[0][coordinates[1]].checkLeftNeighbor(field, chipColour)) +
                field.field[0][coordinates[1]].checkRightLowerNeighbor(field, chipColour) +
                field.field[0][coordinates[1]].checkLeftLowerNeighbor(field, chipColour) +
                field.field[0][coordinates[1]].checkLowerNeighbor(field, chipColour) + 0.4;
        return score;
    }

    /**
     * Определение счета для фишки, поставленной на нижнюю грань поля.
     * @param field Игровое поле текущей игры.
     * @param chipColour Используемый для хода цвет.
     * @return Счет для фишки.
     */
    double scoreForBottomEdge(Field field, ChipColour chipColour, int[] coordinates) {
        double score = 2 * (field.field[field.size - 1][coordinates[1]].checkRightNeighbor(field, chipColour) +
                field.field[field.size - 1][coordinates[1]].checkLeftNeighbor(field, chipColour)) +
                field.field[field.size - 1][coordinates[1]].checkRightHigherNeighbor(field, chipColour) +
                field.field[field.size - 1][coordinates[1]].checkLeftHigherNeighbor(field, chipColour) +
                field.field[field.size - 1][coordinates[1]].checkHigherNeighbor(field, chipColour) + 0.4;
        return score;
    }

    /**
     * Определение счета для фишки, поставленной на левую грань поля.
     * @param field Игровое поле текущей игры.
     * @param chipColour Используемый для хода цвет.
     * @return Счет для фишки.
     */
    double scoreForLeftEdge(Field field, ChipColour chipColour, int[] coordinates) {
        double score = 2 * (field.field[coordinates[0]][0].checkHigherNeighbor(field, chipColour) +
                field.field[coordinates[0]][0].checkLowerNeighbor(field, chipColour)) +
                field.field[coordinates[0]][0].checkRightLowerNeighbor(field, chipColour) +
                field.field[coordinates[0]][0].checkRightHigherNeighbor(field, chipColour) +
                field.field[coordinates[0]][0].checkRightNeighbor(field, chipColour) + 0.4;
        return score;
    }

    /**
     * Определение счета для фишки, поставленной на правую грань поля.
     * @param field Игровое поле текущей игры.
     * @param chipColour Используемый для хода цвет.
     * @return Счет для фишки.
     */
    double scoreForRightEdge(Field field, ChipColour chipColour, int[] coordinates) {
        double score = 2 * (field.field[coordinates[0]][field.size - 1].checkHigherNeighbor(field, chipColour) +
                field.field[coordinates[0]][field.size - 1].checkLowerNeighbor(field, chipColour)) +
                field.field[coordinates[0]][field.size - 1].checkLeftHigherNeighbor(field, chipColour) +
                field.field[coordinates[0]][field.size - 1].checkLeftLowerNeighbor(field, chipColour) +
                field.field[coordinates[0]][field.size - 1].checkLeftNeighbor(field, chipColour) + 0.4;
        return score;
    }

    /**
     * Определение счета для фишки, поставленной внутрь поля.
     * @param field Игровое поле текущей игры.
     * @param chipColour Используемый для хода цвет.
     * @return Счет для фишки.
     */
    double scoreForInnerChip(Field field, ChipColour chipColour, int[] coordinates) {
        double score = field.field[coordinates[0]][coordinates[1]].checkLeftNeighbor(field, chipColour) +
                field.field[coordinates[0]][coordinates[1]].checkLeftHigherNeighbor(field, chipColour) +
                field.field[coordinates[0]][coordinates[1]].checkHigherNeighbor(field, chipColour) +
                field.field[coordinates[0]][coordinates[1]].checkRightHigherNeighbor(field, chipColour) +
                field.field[coordinates[0]][coordinates[1]].checkRightNeighbor(field, chipColour) +
                field.field[coordinates[0]][coordinates[1]].checkRightLowerNeighbor(field, chipColour) +
                field.field[coordinates[0]][coordinates[1]].checkLowerNeighbor(field, chipColour) +
                field.field[coordinates[0]][coordinates[1]].checkLeftLowerNeighbor(field, chipColour);
        return score;
    }

    /**
     * Лучший возможный ход в угол.
     * @param field Игровое поле.
     * @param chipColour Цвет поставленной фишки.
     * @param coordinates Координаты хода.
     * @param bestTurn Лучший ход на данный момент.
     * @return Лучший ход.
     */
    Turn bestCornerTurn(Field field, ChipColour chipColour, int[] coordinates, Turn bestTurn) {
        if (coordinates[0] == 0 && coordinates[1] == 0) {
            double score = scoreForLeftTopCorner(field, chipColour);
            if (bestTurn.score < score) {
                return new Turn(coordinates[0] + 1, coordinates[1] + 1, chipColour, score);
            }
        } else if (coordinates[0] == 0 && coordinates[1] == field.size - 1) {
            double score = scoreForRightTopCorner(field, chipColour);
            if (bestTurn.score < score) {
                return new Turn(coordinates[0] + 1, coordinates[1] + 1, chipColour, score);
            }
        } else if (coordinates[0] == field.size - 1 && coordinates[1] == 0) {
            double score = scoreForLeftBottomCorner(field, chipColour);
            if (bestTurn.score < score) {
                return new Turn(coordinates[0] + 1, coordinates[1] + 1, chipColour, score);
            }
        } else if (coordinates[0] == field.size - 1 && coordinates[1] == field.size - 1) {
            double score = scoreForRightBottomCorner(field, chipColour);
            if (bestTurn.score < score) {
                return new Turn(coordinates[0] + 1, coordinates[1] + 1, chipColour, score);
            }
        }
        return bestTurn;
    }

    /**
     * Лучший возможный ход на грань.
     * @param field Игровое поле.
     * @param chipColour Цвет поставленной фишки.
     * @param coordinates Координаты хода.
     * @param bestTurn Лучший ход на данный момент.
     * @return Лучший ход.
     */
    Turn bestEdgeTurn(Field field, ChipColour chipColour, int[] coordinates, Turn bestTurn) {
        if (coordinates[0] == 0) {
            double score = scoreForTopEdge(field, chipColour, coordinates);
            if (bestTurn.score < score) {
                return new Turn(coordinates[0] + 1, coordinates[1] + 1, chipColour, score);
            }
        } else if (coordinates[0] == field.size - 1) {
            double score = scoreForBottomEdge(field, chipColour, coordinates);
            if (bestTurn.score < score) {
                return new Turn(coordinates[0] + 1, coordinates[1] + 1, chipColour, score);
            }
        } else if (coordinates[1] == 0) {
            double score = scoreForLeftEdge(field, chipColour, coordinates);
            if (bestTurn.score < score) {
                return new Turn(coordinates[0] + 1, coordinates[1] + 1, chipColour, score);
            }
        } else if (coordinates[1] == field.size - 1) {
            double score = scoreForRightEdge(field, chipColour, coordinates);
            if (bestTurn.score < score) {
                return new Turn(coordinates[0] + 1, coordinates[1] + 1, chipColour, score);
            }
        }
        return bestTurn;
    }

    /**
     * Лучший возможный ход внутрь поля.
     * @param field Игровое поле.
     * @param chipColour Цвет поставленной фишки.
     * @param coordinates Координаты хода.
     * @param bestTurn Лучший ход на данный момент.
     * @return Лучший ход.
     */
    Turn bestInnerTurn(Field field, ChipColour chipColour, int[] coordinates, Turn bestTurn) {
        double score = scoreForInnerChip(field, chipColour, coordinates);
        if (bestTurn.score < score) {
            return new Turn(coordinates[0] + 1, coordinates[1] + 1, chipColour, score);
        }
        return bestTurn;
    }

    /**
     * Определение лучшего возможного хода.
     * @param possibleTurnsCoordinates Возможные ходы.
     * @param field Игровое поле.
     * @param chipColour Используемый цвет.
     * @return Лучший возможный ход.
     */
    Turn findBestTurn(int[][] possibleTurnsCoordinates, Field field, ChipColour chipColour) {
        Turn bestTurn = new Turn(-1, -1, chipColour, 0);
        for (int[] coordinates : possibleTurnsCoordinates) {
            if ((coordinates[0] == 0  && coordinates[1] == 0) || (coordinates[0] == 0 && coordinates[1]
                    == field.size - 1)
                    || (coordinates[0] == field.size - 1 && coordinates[1] == 0)
                    || (coordinates[0] == field.size - 1 && coordinates[1] == field.size - 1)) {
                bestTurn = bestCornerTurn(field, chipColour, coordinates, bestTurn);
            } else if (coordinates[0] == 0 || coordinates[1] == 0 || coordinates[0] == field.size - 1
                    || coordinates[1] == field.size - 1) {
                bestTurn = bestEdgeTurn(field, chipColour, coordinates, bestTurn);
            } else {
                bestTurn = bestInnerTurn(field, chipColour, coordinates, bestTurn);
            }
        }
        return bestTurn;
    }
}