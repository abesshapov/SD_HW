/**
 * Фишка на игровом поле.
 */
public class Chip {
    /**
     * Номер строки, в которой расположена фишка.
     */
    private final int x;
    /**
     * Номер столбца, в котором расположена фишка.
     */
    private final int y;
    /**
     * Цвет фишки на текущий момент.
     */
    ChipColour colour;

    /**
     * Конструктор фишки.
     * @param x номер строки, в которой будет расположена фишка.
     * @param y номер столбца, в котором будет расположена фишка.
     * @param colour первичный цвет фишки.
     */
    Chip(int x, int y, ChipColour colour) {
        this.x = x;
        this.y = y;
        this.colour = colour;
    }

    /**
     * Получение цвета фишки.
     * @return char-представление цвета фишки.
     */
    char getChipColour() {
        if (this.colour == ChipColour.BLACK) {
            return '●';
        }
        if (this.colour == ChipColour.WHITE) {
            return '○';
        }
        if (this.colour == ChipColour.ACHIEVABLE) {
            return '+';
        }
        return ' ';
    }

    /**
     * Определение нужды перекрашивания фишки после хода.
     * @param field поле, на котором лежит фишка.
     * @param lastTurnX строчка, в которую поставили новую фишку.
     * @param lastTurnY столбец, в который поставили новую фишку.
     * @param placedColour цвет поставленной фишки.
     * @return цвет, в который нужно перекрасить фишку после хода.
     */
    ChipColour analyzeAlignment(Field field, int lastTurnX, int lastTurnY, ChipColour placedColour) {
        if (this.colour == ChipColour.ACHIEVABLE || this.colour == ChipColour.UNACHIEVABLE) {
            return analyzeUnpaintedChipAlignment(field, lastTurnX, lastTurnY, placedColour);
        }
        return analyzePaintedChipAlignment(field, lastTurnX, lastTurnY, placedColour);
    }

    /**
     * Определение нужды перекрашивания незакрашенной фишки после хода
     * @param field поле, на котором лежит фишка.
     * @param lastTurnX строчка, в которую поставили новую фишку.
     * @param lastTurnY столбец, в который поставили новую фишку.
     * @param placedColour цвет поставленной фишки.
     * @return цвет, в который нужно перекрасить фишку после хода.
     */
    ChipColour analyzeUnpaintedChipAlignment(Field field, int lastTurnX, int lastTurnY, ChipColour placedColour) {
        if (this.x == lastTurnX && this.y == lastTurnY) {
            return placedColour;
        } else {
            ChipColour nextColour;
            if (placedColour == ChipColour.WHITE) {
                nextColour = ChipColour.BLACK;
            } else {
                nextColour = ChipColour.WHITE;
            }
            boolean isChipAchievable = false;
            if (this.y > 0) {
                isChipAchievable |= checkLeftNeighbor(field, nextColour) > 0;
                if (this.x > 0) {
                    isChipAchievable |= checkLeftHigherNeighbor(field, nextColour) > 0;
                }
            }
            if (this.x > 0) {
                isChipAchievable |= checkHigherNeighbor(field, nextColour) > 0;
                if (this.y <= field.size - 2) {
                    isChipAchievable |= checkRightHigherNeighbor(field, nextColour) > 0;
                }
            }
            if (this.y <= field.size - 2) {
                isChipAchievable |= checkRightNeighbor(field, nextColour) > 0;
                if (this.x <= field.size - 2) {
                    isChipAchievable |= checkRightLowerNeighbor(field, nextColour) > 0;
                }
            }
            if (this.x <= field.size - 2) {
                isChipAchievable |= checkLowerNeighbor(field, nextColour) > 0;
                if (this.y > 0) {
                    isChipAchievable |= checkLeftLowerNeighbor(field, nextColour) > 0;
                }
            }
            return isChipAchievable? ChipColour.ACHIEVABLE:ChipColour.UNACHIEVABLE;
        }
    }

    /**
     * Проверка возможности хода относительно соседней левой фишки.
     * @param field поле, на котором лежит фишка.
     * @param nextColour цвет фишки, которая будет поставлена следующей
     * @return количество фишек слева, которые будут перекрашены в случае постановки данной фишки.
     */
    int checkLeftNeighbor(Field field, ChipColour nextColour) {
        if (field.field[this.x][this.y - 1].colour != nextColour && field.field[this.x][this.y - 1].colour
                != ChipColour.ACHIEVABLE && field.field[this.x][this.y - 1].colour != ChipColour.UNACHIEVABLE) {
            int closedChips = 1;
            for (int i = this.y - 2; i >= 0; i--) {
                closedChips++;
                if (field.field[this.x][i].colour == ChipColour.ACHIEVABLE || field.field[this.x][i].colour ==
                        ChipColour.UNACHIEVABLE) {
                    break;
                }
                if (field.field[this.x][i].colour == nextColour) {
                    closedChips--;
                    return closedChips;
                }
            }
        }
        return 0;
    }

    /**
     * Проверка возможности хода относительно соседней левой верхней фишки.
     * @param field поле, на котором лежит фишка.
     * @param nextColour цвет фишки, которая будет поставлена следующей
     * @return количество фишек слева сверху, которые будут перекрашены в случае постановки данной фишки.
     */
    int checkLeftHigherNeighbor(Field field, ChipColour nextColour) {
        if (field.field[this.x - 1][this.y - 1].colour != nextColour && field.field[this.x - 1][this.y - 1].colour
                != ChipColour.ACHIEVABLE && field.field[this.x - 1][this.y - 1].colour != ChipColour.UNACHIEVABLE) {
            int closedChips = 1;
            for (int i = 2; this.x - i >= 0 && this.y - i >= 0; i++) {
                closedChips++;
                if (field.field[this.x - i][this.y - i].colour == ChipColour.ACHIEVABLE
                        || field.field[this.x - i][this.y - i].colour == ChipColour.UNACHIEVABLE) {
                    break;
                }
                if (field.field[this.x - i][this.y - i].colour == nextColour) {
                    closedChips--;
                    return closedChips;
                }
            }
        }
        return 0;
    }

    /**
     * Проверка возможности хода относительно соседней верхней фишки.
     * @param field поле, на котором лежит фишка.
     * @param nextColour цвет фишки, которая будет поставлена следующей
     * @return количество фишек сверху, которые будут перекрашены в случае постановки данной фишки.
     */
    int checkHigherNeighbor(Field field, ChipColour nextColour) {
        if (field.field[this.x - 1][this.y].colour != nextColour && field.field[this.x - 1][this.y].colour
                != ChipColour.ACHIEVABLE && field.field[this.x - 1][this.y].colour != ChipColour.UNACHIEVABLE) {
            int closedChips = 1;
            for (int i = this.x - 2; i >= 0; i--) {
                closedChips++;
                if (field.field[i][this.y].colour == ChipColour.ACHIEVABLE || field.field[i][this.y].colour
                        == ChipColour.UNACHIEVABLE) {
                    break;
                }
                if (field.field[i][this.y].colour == nextColour) {
                    closedChips--;
                    return closedChips;
                }
            }
        }
        return 0;
    }

    /**
     * Проверка возможности хода относительно соседней правой верхней фишки.
     * @param field поле, на котором лежит фишка.
     * @param nextColour цвет фишки, которая будет поставлена следующей
     * @return количество фишек справа сверху, которые будут перекрашены в случае постановки данной фишки.
     */
    int checkRightHigherNeighbor(Field field, ChipColour nextColour) {
        if (field.field[this.x - 1][this.y + 1].colour != nextColour && field.field[this.x - 1][this.y + 1].colour
                != ChipColour.ACHIEVABLE && field.field[this.x - 1][this.y + 1].colour != ChipColour.UNACHIEVABLE) {
            int closedChips = 1;
            for (int i = 2; this.x - i >= 0 && this.y + i <= field.size - 1; i++) {
                closedChips++;
                if (field.field[this.x - i][this.y + i].colour == ChipColour.ACHIEVABLE ||
                        field.field[this.x - i][this.y + i].colour == ChipColour.UNACHIEVABLE) {
                    break;
                }
                if (field.field[this.x - i][this.y + i].colour == nextColour) {
                    closedChips--;
                    return closedChips;
                }
            }
        }
        return 0;
    }

    /**
     * Проверка возможности хода относительно соседней правой фишки.
     * @param field поле, на котором лежит фишка.
     * @param nextColour цвет фишки, которая будет поставлена следующей
     * @return количество фишек справа, которые будут перекрашены в случае постановки данной фишки.
     */
    int checkRightNeighbor(Field field, ChipColour nextColour) {
        if (field.field[this.x][this.y + 1].colour != nextColour && field.field[this.x][this.y + 1].colour
                != ChipColour.ACHIEVABLE && field.field[this.x][this.y + 1].colour != ChipColour.UNACHIEVABLE) {
            int closedChips = 1;
            for (int i = 2; this.y + i <= field.size - 1; i++) {
                closedChips++;
                if (field.field[this.x][this.y + i].colour == ChipColour.ACHIEVABLE
                        || field.field[this.x][this.y + i].colour == ChipColour.UNACHIEVABLE) {
                    break;
                }
                if (field.field[this.x][this.y + i].colour == nextColour) {
                    closedChips--;
                    return closedChips;
                }
            }
        }
        return 0;
    }

    /**
     * Проверка возможности хода относительно соседней правой нижней фишки.
     * @param field поле, на котором лежит фишка.
     * @param nextColour цвет фишки, которая будет поставлена следующей
     * @return количество фишек справа снизу, которые будут перекрашены в случае постановки данной фишки.
     */
    int checkRightLowerNeighbor(Field field, ChipColour nextColour) {
        if (field.field[this.x + 1][this.y + 1].colour != nextColour && field.field[this.x + 1][this.y + 1].colour
                != ChipColour.ACHIEVABLE && field.field[this.x + 1][this.y + 1].colour != ChipColour.UNACHIEVABLE) {
            int closedChips = 1;
            for (int i = 2; this.x + i <= field.size - 1 && this.y + i <= field.size - 1; i++) {
                closedChips++;
                if (field.field[this.x + i][this.y + i].colour == ChipColour.ACHIEVABLE
                        || field.field[this.x + i][this.y + i].colour == ChipColour.UNACHIEVABLE) {
                    break;
                }
                if (field.field[this.x + i][this.y + i].colour == nextColour) {
                    closedChips--;
                    return closedChips;
                }
            }
        }
        return 0;
    }

    /**
     * Проверка возможности хода относительно соседней нижней фишки.
     * @param field поле, на котором лежит фишка.
     * @param nextColour цвет фишки, которая будет поставлена следующей
     * @return количество фишек снизу, которые будут перекрашены в случае постановки данной фишки.
     */
    int checkLowerNeighbor(Field field, ChipColour nextColour) {
        if (field.field[this.x + 1][this.y].colour != nextColour && field.field[this.x + 1][this.y].colour
                != ChipColour.ACHIEVABLE && field.field[this.x + 1][this.y].colour != ChipColour.UNACHIEVABLE) {
            int closedChips = 1;
            for (int i = 2; this.x + i <= field.size - 1; i++) {
                closedChips++;
                if (field.field[this.x + i][this.y].colour == ChipColour.ACHIEVABLE
                        || field.field[this.x + i][this.y].colour == ChipColour.UNACHIEVABLE) {
                    break;
                }
                if (field.field[this.x + i][this.y].colour == nextColour) {
                    closedChips--;
                    return closedChips;
                }
            }
        }
        return 0;
    }

    /**
     * Проверка возможности хода относительно соседней левой нижней фишки.
     * @param field поле, на котором лежит фишка.
     * @param nextColour цвет фишки, которая будет поставлена следующей
     * @return количество фишек слева снизу, которые будут перекрашены в случае постановки данной фишки.
     */
    int checkLeftLowerNeighbor(Field field, ChipColour nextColour) {
        if (field.field[this.x + 1][this.y - 1].colour != nextColour && field.field[this.x + 1][this.y - 1].colour
                != ChipColour.ACHIEVABLE && field.field[this.x + 1][this.y - 1].colour != ChipColour.UNACHIEVABLE) {
            int closedChips = 1;
            for (int i = 2; this.x + i <= field.size - 1 && this.y - i >= 0; i++) {
                closedChips++;
                if (field.field[this.x + i][this.y - i].colour == ChipColour.ACHIEVABLE
                        || field.field[this.x + i][this.y - i].colour == ChipColour.UNACHIEVABLE) {
                    break;
                }
                if (field.field[this.x + i][this.y - i].colour == nextColour) {
                    closedChips--;
                    return closedChips;
                }
            }
        }
        return 0;
    }

    /**
     * Определение нужды перекрашивания закрашенной фишки после хода
     * @param field поле, на котором лежит фишка.
     * @param lastTurnX строчка, в которую поставили новую фишку.
     * @param lastTurnY столбец, в который поставили новую фишку.
     * @param placedColour цвет поставленной фишки.
     * @return цвет, в который нужно перекрасить фишку после хода.
     */
    ChipColour analyzePaintedChipAlignment(Field field, int lastTurnX, int lastTurnY, ChipColour placedColour) {
        if (this.colour == placedColour) {
            return this.colour;
        }
        if (lastTurnX == this.x) {
            return analyzeHorizontalLocation(field, lastTurnX, lastTurnY, placedColour);
        }
        if (lastTurnY == this.y) {
            return analyzeVerticalLocation(field, lastTurnX, lastTurnY, placedColour);
        }
        if (lastTurnX - this.x == lastTurnY - this.y) {
            return analyzeMainDiagonalLocation(field, lastTurnX, lastTurnY, placedColour);
        }
        if (lastTurnX - this.x == this.y - lastTurnY) {
            return analyzeSideDiagonalLocation(field, lastTurnX, lastTurnY, placedColour);
        }
        return this.colour;
    }

    /**
     * Определение нужды перекрашивания незакрашенной фишки после хода в строчку, в которой лежит данная фишка.
     * @param field поле, на котором лежит фишка.
     * @param lastTurnX строчка, в которую поставили новую фишку.
     * @param lastTurnY столбец, в который поставили новую фишку.
     * @param placedColour цвет поставленной фишки.
     * @return цвет, в который нужно перекрасить фишку после хода.
     */
    ChipColour analyzeHorizontalLocation(Field field, int lastTurnX, int lastTurnY, ChipColour placedColour) {
        if (lastTurnY < this.y) {
            for (int i = lastTurnY + 1; i < this.y; i++) {
                if (field.field[this.x][i].colour == placedColour || field.field[this.x][i].colour
                        == ChipColour.ACHIEVABLE || field.field[this.x][i].colour == ChipColour.UNACHIEVABLE) {
                    return this.colour;
                }
            }
            if (this.y <= field.size - 2) {
                for (int i = this.y + 1; i <= field.size - 1; i++) {
                    if (field.field[this.x][i].colour == ChipColour.ACHIEVABLE || field.field[this.x][i].colour
                            == ChipColour.UNACHIEVABLE) {
                        break;
                    }
                    if (field.field[this.x][i].colour == placedColour) {
                        return placedColour;
                    }
                }
            }
            return this.colour;
        } else {
            for (int i = lastTurnY - 1; i > this.y; i--) {
                if (field.field[this.x][i].colour == placedColour || field.field[this.x][i].colour
                        == ChipColour.ACHIEVABLE || field.field[this.x][i].colour == ChipColour.UNACHIEVABLE) {
                    return this.colour;
                }
            }
            if (this.y > 0) {
                for (int i = this.y - 1; i >= 0; i--) {
                    if (field.field[this.x][i].colour == ChipColour.ACHIEVABLE || field.field[this.x][i].colour
                            == ChipColour.UNACHIEVABLE) {
                        break;
                    }
                    if (field.field[this.x][i].colour == placedColour) {
                        return placedColour;
                    }
                }
            }
            return this.colour;
        }
    }

    /**
     * Определение нужды перекрашивания незакрашенной фишки после хода в столбец, в котором лежит данная фишка.
     * @param field поле, на котором лежит фишка.
     * @param lastTurnX строчка, в которую поставили новую фишку.
     * @param lastTurnY столбец, в который поставили новую фишку.
     * @param placedColour цвет поставленной фишки.
     * @return цвет, в который нужно перекрасить фишку после хода.
     */
    ChipColour analyzeVerticalLocation(Field field, int lastTurnX, int lastTurnY, ChipColour placedColour) {
        if (lastTurnX < this.x) {
            for (int i = lastTurnX + 1; i < this.x; i++) {
                if (field.field[i][this.y].colour == placedColour || field.field[i][this.y].colour
                        == ChipColour.ACHIEVABLE || field.field[i][this.y].colour == ChipColour.UNACHIEVABLE) {
                    return this.colour;
                }
            }
            if (this.x <= field.size - 2) {
                for (int i = this.x + 1; i <= field.size - 1; i++) {
                    if (field.field[i][this.y].colour == ChipColour.ACHIEVABLE || field.field[i][this.y].colour
                            == ChipColour.UNACHIEVABLE) {
                        break;
                    }
                    if (field.field[i][this.y].colour == placedColour) {
                        return placedColour;
                    }
                }
            }
            return this.colour;
        } else {
            for (int i = lastTurnX - 1; i > this.x; i--) {
                if (field.field[i][this.y].colour == placedColour || field.field[i][this.y].colour
                        == ChipColour.ACHIEVABLE || field.field[i][this.y].colour == ChipColour.UNACHIEVABLE) {
                    return this.colour;
                }
            }
            if (this.x > 0) {
                for (int i = this.x - 1; i >= 0; i--) {
                    if (field.field[i][this.y].colour == ChipColour.ACHIEVABLE || field.field[i][this.y].colour
                            == ChipColour.UNACHIEVABLE) {
                        break;
                    }
                    if (field.field[i][this.y].colour == placedColour) {
                        return placedColour;
                    }
                }
            }
            return this.colour;
        }
    }

    /**
     * Определение нужды перекрашивания незакрашенной фишки после хода на главную диагональ, в которой лежит
     * данная фишка.
     * @param field поле, на котором лежит фишка.
     * @param lastTurnX строчка, в которую поставили новую фишку.
     * @param lastTurnY столбец, в который поставили новую фишку.
     * @param placedColour цвет поставленной фишки.
     * @return цвет, в который нужно перекрасить фишку после хода.
     */
    ChipColour analyzeMainDiagonalLocation(Field field, int lastTurnX, int lastTurnY, ChipColour placedColour) {
        if (lastTurnX < this.x) {
            for (int i = 1; lastTurnX + i < this.x; i++) {
                if (field.field[lastTurnX + i][lastTurnY + i].colour == placedColour
                        || field.field[lastTurnX + i][lastTurnY + i].colour == ChipColour.ACHIEVABLE
                        || field.field[lastTurnX + i][lastTurnY + i].colour == ChipColour.UNACHIEVABLE) {
                    return this.colour;
                }
            }
            if (this.x <= field.size - 2 && this.y <= field.size - 2) {
                for (int i = 1; this.x + i <= field.size - 1 && this.y + i <= field.size - 1; i++) {
                    if (field.field[this.x + i][this.y + i].colour == ChipColour.ACHIEVABLE
                            || field.field[this.x + i][this.y + i].colour == ChipColour.UNACHIEVABLE) {
                        break;
                    }
                    if (field.field[this.x + i][this.y + i].colour == placedColour) {
                        return placedColour;
                    }
                }
            }
            return this.colour;
        } else {
            for (int i = 1; lastTurnX - i > this.x; i++) {
                if (field.field[lastTurnX - i][lastTurnY - i].colour == placedColour
                        || field.field[lastTurnX - i][lastTurnY - i].colour == ChipColour.ACHIEVABLE
                        || field.field[lastTurnX - i][lastTurnY - i].colour == ChipColour.UNACHIEVABLE) {
                    return this.colour;
                }
            }
            if (this.x > 0 && this.y > 0) {
                for (int i = 1; this.x - i >= 0 && this.y - i >= 0; i++) {
                    if (field.field[this.x - i][this.y - i].colour == ChipColour.ACHIEVABLE
                            || field.field[this.x - i][this.y - i].colour == ChipColour.UNACHIEVABLE) {
                        break;
                    }
                    if (field.field[this.x - i][this.y - i].colour == placedColour) {
                        return placedColour;
                    }
                }
            }
            return this.colour;
        }
    }

    /**
     * Определение нужды перекрашивания незакрашенной фишки после хода в побочную диагональ, в которой лежит
     * данная фишка.
     * @param field поле, на котором лежит фишка.
     * @param lastTurnX строчка, в которую поставили новую фишку.
     * @param lastTurnY столбец, в который поставили новую фишку.
     * @param placedColour цвет поставленной фишки.
     * @return цвет, в который нужно перекрасить фишку после хода.
     */
    ChipColour analyzeSideDiagonalLocation(Field field, int lastTurnX, int lastTurnY, ChipColour placedColour) {
        if (lastTurnX > this.x) {
            for (int i = 1; lastTurnX - i > this.x; i++) {
                if (field.field[lastTurnX - i][lastTurnY + i].colour == placedColour
                        || field.field[lastTurnX - i][lastTurnY + i].colour == ChipColour.ACHIEVABLE
                        || field.field[lastTurnX - i][lastTurnY + i].colour == ChipColour.UNACHIEVABLE) {
                    return this.colour;
                }
            }
            if (this.x > 0 && this.y <= field.size - 2) {
                for (int i = 1; this.x - i >= 0 && this.y + i <= field.size - 1; i++) {
                    if (field.field[this.x - i][this.y + i].colour == ChipColour.ACHIEVABLE
                            || field.field[this.x - i][this.y + i].colour == ChipColour.UNACHIEVABLE) {
                        break;
                    }
                    if (field.field[this.x - i][this.y + i].colour == placedColour) {
                        return placedColour;
                    }
                }
            }
            return this.colour;
        } else {
            for (int i = 1; lastTurnX + i < this.x; i++) {
                if (field.field[lastTurnX + i][lastTurnY - i].colour == placedColour
                        || field.field[lastTurnX + i][lastTurnY - i].colour == ChipColour.ACHIEVABLE
                        || field.field[lastTurnX + i][lastTurnY - i].colour == ChipColour.UNACHIEVABLE) {
                    return this.colour;
                }
            }
            if (this.x <= field.size - 2 && this.y > 0) {
                for (int i = 1; this.x + i <= field.size - 1 && this.y - i >= 0; i++) {
                    if (field.field[this.x + i][this.y - i].colour == ChipColour.ACHIEVABLE
                            || field.field[this.x + i][this.y - i].colour == ChipColour.UNACHIEVABLE) {
                        break;
                    }
                    if (field.field[this.x + i][this.y - i].colour == placedColour) {
                        return placedColour;
                    }
                }
            }
            return this.colour;
        }
    }
}