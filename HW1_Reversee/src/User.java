import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * Игрок - пользователь.
 */
public class User extends Player {
    /**
     * Конструктор пользователя.
     * @param name Имя пользователя.
     * @param chipColour Используемый цвет фишек.
     */
    User(String name, ChipColour chipColour) {
        super(name, chipColour);
    }

    /**
     * Выполнение пользователем хода.
     * @param field Игровое поле.
     * @param chipColour Цвет используемых фишек.
     * @return Выполненный ход.
     */
    @Override
    Turn makeATurn(Field field, ChipColour chipColour) {
        int x = 0;
        int y = 0;
        while (x < 1 || x > field.size || y < 1 || y > field.size) {
            Scanner scanner = new Scanner(System.in);
            try {
                System.out.print("Введите строчку: ");
                x = scanner.nextInt();
                System.out.print("Введите столбец: ");
                y = scanner.nextInt();
                if (x < 1 || x > field.size || y < 1 || y > field.size) {
                    System.out.println("Некорректные данные, должны быть в пределах размеров игровой доски");
                }
            }
            catch (InputMismatchException e) {
                System.out.println("Некорректный формат входных данных");
            }
        }
        return new Turn(x, y, this.chipColour, 0);
    }
}