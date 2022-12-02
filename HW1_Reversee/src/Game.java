import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * Клиент игры.
 */
public class Game {
    /**
     * Массив игроков, зарегистрированных в текущей игровой сессии.
     */
    private Player[] currentSessionPlayers;
    /**
     * История игровых полей, меняющихся после выполняемых ходов.
     */
    private Field[] currentGameFields;
    /**
     * Очередность ходящих цветов.
     */
    private ChipColour[] currentGameTurns;

    /**
     * Конструктор клиента игры, запуск игры.
     */
    Game() {
        currentSessionPlayers = new Player[4];
        gameHandler();
    }

    /**
     * Обработчик взаимодействия с меню.
     */
    void gameHandler() {
        int gameModeNumber = getDesiredGameMode();
        while (gameModeNumber != 0) {
            switch (gameModeNumber) {
                case (1):
                    System.out.println("Выбран режим игрок против игрока");
                    twoPlayersGameMode();
                    break;
                case (2):
                    System.out.println("Выбран режим игрок против легкого компьютера");
                    personAgainstJuniorComputerGameMode();
                    break;
                case (3):
                    System.out.println("Выбран режим игрок против продвинутого компьютера");
                    personAgainstSeniorComputerGameMode();
                    break;
                case (4):
                    System.out.println("Выбран режим компьютер против продвинутого компьютера");
                    computerAgainstSeniorComputer();
                    break;
                case (5):
                    leaderboard();
                    break;
                default:
                    System.out.println("Неизвестный номер режима");
            }
            gameModeNumber = getDesiredGameMode();
        }
        System.out.println("Игра закончена");
    }

    /**
     * Регистрация игрока.
     * @return Никнейм зарегистрированного игрока.
     */
    String registerPlayer() {
        Scanner nameScanner = new Scanner(System.in);
        String playerName = nameScanner.nextLine();
        return playerName;
    }

    /**
     * Ввод пользователем желаемого режима игры.
     * @return Номер желаемого режима игры.
     */
    int getDesiredGameMode() {
        System.out.println("Для начала игры выберите режим: (напишите номер режима)");
        System.out.println("1 - режим игрок против игрока");
        System.out.println("2 - режим игрок против компьютера (легкий)");
        System.out.println("3 - режим игрок против компьютера (продвинутый)");
        System.out.println("4 - режим компьютер против опытного компьютера");
        System.out.println("5 - таблица лучших результатов за сессию");
        int gameModeNumber = -1;
        while (gameModeNumber == -1) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Выбранный режим (0 для выхода из игры): ");
            try {
                gameModeNumber = scanner.nextInt();
                if (gameModeNumber < 0 || gameModeNumber > 5) {
                    System.out.println("Такого режима нет");

                }
            }
            catch (InputMismatchException e) {
                System.out.println("Некорректный формат ввода данных");
                gameModeNumber = -1;
            }
        }
        return gameModeNumber;
    }

    /**
     * Режим игры игрок против игрока.
     */
    void twoPlayersGameMode() {
        if (this.currentSessionPlayers[0] == null) {
            System.out.print("Введите имя для первого игрока: ");
            String firstPlayerName = registerPlayer();
            this.currentSessionPlayers[0] = new User(firstPlayerName, ChipColour.WHITE);
        }
        if (this.currentSessionPlayers[1] == null) {
            System.out.print("Введите имя для второго игрока: ");
            String secondPlayerName = registerPlayer();
            this.currentSessionPlayers[1] = new User(secondPlayerName, ChipColour.WHITE);
        }
        if (Math.random() <= 0.5) {
            this.currentSessionPlayers[0].chipColour = ChipColour.WHITE;
            this.currentSessionPlayers[1].chipColour = ChipColour.BLACK;
        } else {
            this.currentSessionPlayers[0].chipColour = ChipColour.BLACK;
            this.currentSessionPlayers[1].chipColour = ChipColour.WHITE;
        }
        gameProcess(0, 1);
    }

    /**
     * Режим игры игрок против неопытного компьютера.
     */
    void personAgainstJuniorComputerGameMode() {
        if (this.currentSessionPlayers[0] == null) {
            System.out.print("Введите имя для первого игрока: ");
            String firstPlayerName = registerPlayer();
            this.currentSessionPlayers[0] = new User(firstPlayerName, ChipColour.WHITE);
        }
        if (this.currentSessionPlayers[2] == null) {
            this.currentSessionPlayers[2] = new Junior(ChipColour.WHITE);
        }
        if (Math.random() <= 0.5) {
            this.currentSessionPlayers[0].chipColour = ChipColour.WHITE;
            this.currentSessionPlayers[2].chipColour = ChipColour.BLACK;
        } else {
            this.currentSessionPlayers[0].chipColour = ChipColour.BLACK;
            this.currentSessionPlayers[2].chipColour = ChipColour.WHITE;
        }
        gameProcess(0, 2);
    }

    /**
     * Режим игры игрок против опытного компьютера.
     */
    void personAgainstSeniorComputerGameMode() {
        if (this.currentSessionPlayers[0] == null) {
            System.out.print("Введите имя для первого игрока: ");
            String firstPlayerName = registerPlayer();
            this.currentSessionPlayers[0] = new User(firstPlayerName, ChipColour.WHITE);
        }
        if (this.currentSessionPlayers[3] == null) {
            this.currentSessionPlayers[3] = new Senior(ChipColour.WHITE);
        }
        if (Math.random() <= 0.5) {
            this.currentSessionPlayers[0].chipColour = ChipColour.WHITE;
            this.currentSessionPlayers[3].chipColour = ChipColour.BLACK;
        } else {
            this.currentSessionPlayers[0].chipColour = ChipColour.BLACK;
            this.currentSessionPlayers[3].chipColour = ChipColour.WHITE;
        }
        gameProcess(0, 3);
    }

    /**
     * Режим игры компьютер против опытного компьютера.
     */
    void computerAgainstSeniorComputer() {
        if (this.currentSessionPlayers[2] == null) {
            this.currentSessionPlayers[2] = new Junior(ChipColour.WHITE);
        }
        if (this.currentSessionPlayers[3] == null) {
            this.currentSessionPlayers[3] = new Senior(ChipColour.WHITE);
        }
        if (Math.random() <= 0.5) {
            this.currentSessionPlayers[2].chipColour = ChipColour.WHITE;
            this.currentSessionPlayers[3].chipColour = ChipColour.BLACK;
        } else {
            this.currentSessionPlayers[2].chipColour = ChipColour.BLACK;
            this.currentSessionPlayers[3].chipColour = ChipColour.WHITE;
        }
        gameProcess(2, 3);
    }

    /**
     * Определение нужды отмены хода или двух ходов.
     * @param turnsMade Количество ходов, выполненных в течение текущей игры.
     * @return Количество отменяемых ходов.
     */
    int cancelTurn(int turnsMade) {
        if (turnsMade >= 2) {
            System.out.println("Для отмены хода напишите '-', для отмены двух - '--', " +
                    "для продолжения игры - что-либо другое. Для выхода в меню: 'exit'");
            Scanner cancelationScanner = new Scanner(System.in);
            try {
                String cancelation = cancelationScanner.nextLine();
                if (cancelation.equals("-")) {
                    return 1;
                } else if (cancelation.equals("--")) {
                    return 2;
                } else if (cancelation.equals("exit"))
                    return -1;
                return 0;
            }
            catch (InputMismatchException e) {
                System.out.println("Ошибка при считывании");
                return 0;
            }
        }
        return 0;
    }

    /**
     * Обработчик событий в процессе игры.
     * @param firstPlayerNumber Номер первого игрока.
     * @param secondPlayerNumber Номер второго игрока
     */
    void gameProcess(int firstPlayerNumber, int secondPlayerNumber) {
        int turnsMade = 0;
        Field gameField = new Field();
        currentGameFields = new Field[gameField.size * gameField.size];
        currentGameTurns = new ChipColour[gameField.size * gameField.size];
        currentGameFields[turnsMade] = new Field(gameField.field);
        currentGameTurns[turnsMade] = ChipColour.WHITE;
        ChipColour activeColour = currentGameTurns[turnsMade];
        gameField.drawField();
        boolean wasTurnMade = true;
        while (gameField.amountOfBlackChips() + gameField.amountOfWhiteChips() < gameField.size * gameField.size &&
                gameField.amountOfWhiteChips() > 0 && gameField.amountOfBlackChips() > 0) {
            int turnsCancelled = cancelTurn(turnsMade);
            if (turnsCancelled == -1) {
                return;
            }
            turnsMade -= turnsCancelled;
            if (turnsCancelled == 1) {
                currentGameTurns[turnsMade + 1] = null;
                currentGameFields[turnsMade + 1] = null;
            }
            if (turnsCancelled == 2) {
                currentGameTurns[turnsMade + 1] = null;
                currentGameFields[turnsMade + 1] = null;
                currentGameTurns[turnsMade + 2] = null;
                currentGameFields[turnsMade + 2] = null;
            }
            gameField = new Field(currentGameFields[turnsMade].field);
            activeColour = currentGameTurns[turnsMade];
            if (turnsCancelled > 0) {
                gameField.drawField();
            }
            if (this.currentSessionPlayers[firstPlayerNumber].chipColour == activeColour) {
                wasTurnMade = firstPlayerTurn(firstPlayerNumber, gameField, activeColour);
                if (!wasTurnMade && !gameField.isTurnPossible()) {
                    System.out.println("Ни один игрок не может выполнить ход");
                    break;
                }
            } else {
                wasTurnMade = secondPlayerTurn(secondPlayerNumber, gameField, activeColour);
                if (!wasTurnMade && !gameField.isTurnPossible()) {
                    System.out.println("Ни один игрок не может выполнить ход");
                    break;
                }
            }
            gameField.drawField();
            if (activeColour == ChipColour.WHITE) {
                activeColour = ChipColour.BLACK;
            } else {
                activeColour = ChipColour.WHITE;
            }
            if (wasTurnMade) {
                currentGameFields[++turnsMade] = new Field(gameField.field);
                currentGameTurns[turnsMade] = activeColour;
            } else {
                currentGameFields[turnsMade] = new Field(gameField.field);
                currentGameTurns[turnsMade] = activeColour;
            }
        }
        winnerDetermination(gameField, firstPlayerNumber, secondPlayerNumber);
    }

    /**
     * Определение победившего игрока.
     * @param gameField Игровое поле текущей игры.
     * @param firstPlayerNumber Номер первого игрока.
     * @param secondPlayerNumber Номер второго игрока.
     */
    void winnerDetermination(Field gameField, int firstPlayerNumber, int secondPlayerNumber) {
        if (gameField.amountOfBlackChips() > gameField.amountOfWhiteChips()) {
            System.out.println("Победил игрок " + (this.currentSessionPlayers[firstPlayerNumber].chipColour ==
                    ChipColour.BLACK?this.currentSessionPlayers[firstPlayerNumber].name:
                    this.currentSessionPlayers[secondPlayerNumber].name));
        } else if (gameField.amountOfBlackChips() < gameField.amountOfWhiteChips()) {
            System.out.println("Победил игрок " + (this.currentSessionPlayers[firstPlayerNumber].chipColour ==
                    ChipColour.BLACK?this.currentSessionPlayers[secondPlayerNumber].name:
                    this.currentSessionPlayers[firstPlayerNumber].name));
        } else {
            System.out.println("Ничья");
        }
        this.currentSessionPlayers[firstPlayerNumber].bestResult =
                Math.max(this.currentSessionPlayers[firstPlayerNumber].bestResult,
                        this.currentSessionPlayers[firstPlayerNumber].chipColour ==
                                ChipColour.WHITE? gameField.amountOfWhiteChips() :
                                gameField.amountOfBlackChips());
        this.currentSessionPlayers[secondPlayerNumber].bestResult =
                Math.max(this.currentSessionPlayers[secondPlayerNumber].bestResult,
                        this.currentSessionPlayers[secondPlayerNumber].chipColour ==
                                ChipColour.WHITE? gameField.amountOfWhiteChips() :
                                gameField.amountOfBlackChips());
    }

    /**
     * Обработчик хода первого игрока.
     * @param firstPlayerNumber Номер первого игрока.
     * @param gameField Игровое поле текущей игры.
     * @param activeColour Цвет следующей поставленной фишки.
     * @return Возможно ли выполнить ход.
     */
    boolean firstPlayerTurn(int firstPlayerNumber, Field gameField, ChipColour activeColour) {
        if (!gameField.isTurnPossible()) {
            System.out.println("У игрока " + this.currentSessionPlayers[firstPlayerNumber].name + " нет ходов");
            gameField.reinitializeField(activeColour);
            return false;
        } else {
            System.out.println("Ходит игрок: " + this.currentSessionPlayers[firstPlayerNumber].name);
            Turn madeTurn = this.currentSessionPlayers[firstPlayerNumber].makeATurn(gameField,
                    this.currentSessionPlayers[firstPlayerNumber].chipColour);
            boolean isTurnPossible = gameField.reinitializeFieldAfterTurn(madeTurn.x - 1,
                    madeTurn.y - 1, madeTurn.chipColour);
            while (!isTurnPossible) {
                System.out.println("Такой ход невозможен");
                System.out.println("Ходит игрок: " + this.currentSessionPlayers[firstPlayerNumber].name);
                madeTurn = this.currentSessionPlayers[firstPlayerNumber].makeATurn(gameField,
                        this.currentSessionPlayers[firstPlayerNumber].chipColour);
                isTurnPossible = gameField.reinitializeFieldAfterTurn(madeTurn.x - 1, madeTurn.y - 1,
                        madeTurn.chipColour);
            }
            return true;
        }
    }

    /**
     * Обработчик хода второго игрока.
     * @param secondPlayerNumber Номер второго игрока.
     * @param gameField Игровое поле текущей игры.
     * @param activeColour Цвет следующей поставленной фишки.
     * @return Возможно ли выполнить ход.
     */
    boolean secondPlayerTurn(int secondPlayerNumber, Field gameField, ChipColour activeColour) {
        if (!gameField.isTurnPossible()) {
            System.out.println("У игрока " + this.currentSessionPlayers[secondPlayerNumber].name + " нет ходов");
            gameField.reinitializeField(activeColour);
            return false;
        } else {
            System.out.println("Ходит игрок: " + this.currentSessionPlayers[secondPlayerNumber].name);
            Turn madeTurn = this.currentSessionPlayers[secondPlayerNumber].makeATurn(gameField,
                    this.currentSessionPlayers[secondPlayerNumber].chipColour);
            boolean isTurnPossible = gameField.reinitializeFieldAfterTurn(madeTurn.x - 1,
                    madeTurn.y - 1, madeTurn.chipColour);
            while (!isTurnPossible) {
                System.out.println("Такой ход невозможен");
                System.out.println("Ходит игрок: " + this.currentSessionPlayers[secondPlayerNumber].name);
                madeTurn = this.currentSessionPlayers[secondPlayerNumber].makeATurn(gameField,
                        this.currentSessionPlayers[secondPlayerNumber].chipColour);
                isTurnPossible = gameField.reinitializeFieldAfterTurn(madeTurn.x - 1,
                        madeTurn.y - 1, madeTurn.chipColour);
            }
            return true;
        }
    }

    /**
     * Лучшие результаты игроков, зарегистрированных в текущей игровой сессии.
     */
    void leaderboard() {
        for (int i = 0; i < currentSessionPlayers.length; i++) {
            if (currentSessionPlayers[i] != null) {
                System.out.println("Лучший результат игрока " +
                        currentSessionPlayers[i].name + ": " + currentSessionPlayers[i].bestResult);
            }
        }
    }
}