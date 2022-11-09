import java.util.Scanner;
import java.lang.Math;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        System.out.println("Задание 2");
        Task2();
        System.out.println("------");
        System.out.println("Задание 3");
        Task3();
    }
    public static void Task2() {
        Scanner input = new Scanner(System.in);
        System.out.print("Введите длину массива: ");
        int length = input.nextInt();
        input.close();
        int numbers[] = new int[length];
        int maximum = 0, minimum = 0, sum = 0;
        for (int i=0; i < length;i++) {
            numbers[i] = (int)(Math.random() * 100);
            sum += numbers[i];
            if (i == 0) {
                maximum = minimum = numbers[i];
                continue;
            }
            maximum = Math.max(maximum, numbers[i]);
            minimum = Math.min(minimum, numbers[i]);
        }
        System.out.print("Элементы массива: ");
        for (int number : numbers) {
            System.out.print(number + " ");
        }
        System.out.println();
        System.out.println("Максимум: " + maximum);
        System.out.println("Минимум: " + minimum);
        System.out.println("Среднее: " + (double)sum / length);
    }
    public static void Task3() {
        System.out.println("Простые числа на промежутке от 2 до 100:");
        for (int i = 2; i < 101;i++) {
            boolean isPrime = true;
            for (int j = 2; j <= Math.round(Math.sqrt(i));j++) {
                if (i % j == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                System.out.println(i);
            }
        }
    }
}