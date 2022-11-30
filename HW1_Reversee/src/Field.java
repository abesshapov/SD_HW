/**
 * Игровое поле.
 */
public class Field {
    /**
     * Размер игрового поля.
     */
    final int size;
    /**
     * Матрица, состоящая из фишек, лежащих на игровом поле.
     */
    Chip[][] field;

    /**
     * Конструктор поля по умолчанию.
     */
    Field() {
        this.size = 8;
        field = new Chip[this.size][this.size];
        createInitialLayout();
    }

    /**
     * Конструктор поля по заданному полю.
     * @param field матрица, состоящая из фишек.
     */
    Field(Chip[][] field) {
        this.size = 8;
        this.field = new Chip[this.size][this.size];
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                this.field[i][j] = new Chip(i, j, field[i][j].colour);
            }
        }
    }

    /**
     * Изначальное расположение фишек на игровом поле.
     */
    private void createInitialLayout() {
        field[size / 2 - 1][size / 2 - 1] = new Chip(size / 2 - 1, size / 2 - 1, ChipColour.WHITE);
        field[size / 2 - 1][size / 2] = new Chip(size / 2 - 1, size / 2, ChipColour.BLACK);
        field[size / 2][size / 2 - 1] = new Chip(size / 2, size / 2 - 1, ChipColour.BLACK);
        field[size / 2][size / 2] = new Chip(size / 2, size / 2, ChipColour.WHITE);
        field[size / 2 - 2][size / 2] = new Chip(size / 2 - 2, size / 2, ChipColour.ACHIEVABLE);
        field[size / 2 - 1][size / 2 + 1] = new Chip(size / 2 - 1, size / 2 + 1, ChipColour.ACHIEVABLE);
        field[size / 2][size / 2 - 2] = new Chip(size / 2, size / 2 - 2, ChipColour.ACHIEVABLE);
        field[size / 2 + 1][size / 2 - 1] = new Chip(size / 2 + 1, size / 2 - 1, ChipColour.ACHIEVABLE);
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                if (field[i][j] == null) {
                    field[i][j] = new Chip(i, j, ChipColour.UNACHIEVABLE);
                }
            }
        }
    }

    /**
     * Отрисовка игрового поля.
     */
    void drawField() {
        System.out.print(" \t");
        for (int i = 0; i < this.size; i++) {
            System.out.print((i + 1) + "\t");
        }
        System.out.print("\n");
        for (int i = 0; i < this.size; i++) {
            System.out.print((i + 1) + "\t");
            for (int j = 0; j < this.size; j++) {
                System.out.print(field[i][j].getChipColour() + "\t");
            }
            System.out.print("\n");
        }
    }

    /**
     * Определние возможности выполнения следующего хода.
     * @return возможен ли ход.
     */
    boolean isTurnPossible() {
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                if (field[i][j].colour == ChipColour.ACHIEVABLE) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Определение количества белых фишек на поле.
     * @return количество белых фишек на поле.
     */
    int amountOfWhiteChips() {
        int amountOfWhiteChips = 0;
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                amountOfWhiteChips += field[i][j].colour == ChipColour.WHITE? 1:0;
            }
        }
        return amountOfWhiteChips;
    }

    /**
     * Определение количества черных фишек на поле.
     * @return количество черных фишек на поле.
     */
    int amountOfBlackChips() {
        int amountOfBlackChips = 0;
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                amountOfBlackChips += field[i][j].colour == ChipColour.BLACK? 1:0;
            }
        }
        return amountOfBlackChips;
    }

    /**
     * Реинициализация поля в случае невозможности выполнения хода.
     * @param placedColour текущий цвет.
     */
    void reinitializeField(ChipColour placedColour) {
        ChipColour[][] newColours = new ChipColour[this.size][this.size];
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                if (field[i][j].colour == ChipColour.WHITE || field[i][j].colour == ChipColour.BLACK) {
                    newColours[i][j] = field[i][j].colour;
                } else {
                    newColours[i][j] = field[i][j].analyzeUnpaintedChipAlignment(this, -1, -1,
                            placedColour);
                }
            }
        }
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                field[i][j].colour = newColours[i][j];
            }
        }
    }

    /**
     * Реинициализация поля после выполнения хода.
     * @param lastTurnX строчка, в которую была поставлена новая фишка.
     * @param lastTurnY столбец, в который была поставлена новая фишка.
     * @param placedColour цвет поставленной фишки.
     * @return возможно ли поставить фишку в выбранную ячейку поля.
     */
    boolean reinitializeFieldAfterTurn(int lastTurnX, int lastTurnY, ChipColour placedColour) {
        ChipColour[][] newColours = new ChipColour[this.size][this.size];
        if (field[lastTurnX][lastTurnY].colour != ChipColour.ACHIEVABLE) {
            return false;
        }
        field[lastTurnX][lastTurnY].colour = placedColour;
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                if (field[i][j].colour == ChipColour.WHITE || field[i][j].colour == ChipColour.BLACK) {
                    newColours[i][j] = field[i][j].analyzePaintedChipAlignment(this, lastTurnX, lastTurnY,
                            placedColour);
                } else {
                    newColours[i][j] = ChipColour.UNACHIEVABLE;
                }
            }
        }
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                field[i][j].colour = newColours[i][j];
            }
        }
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                newColours[i][j] = field[i][j].analyzeAlignment(this, lastTurnX, lastTurnY, placedColour);
            }
        }
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                field[i][j].colour = newColours[i][j];
            }
        }
        return true;
    }
}