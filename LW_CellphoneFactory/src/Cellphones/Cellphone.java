package Cellphones;

/**
 * Родительский класс - телефон.
 */
public abstract class Cellphone {
    /**
     * Метод для сборки деталей для телефона.
     */
    public void collectDetails() {
        System.out.println("Details are being collected...");
    }

    /**
     * Метод для совмещения собранных деталей телефона.
     */
    public void combineDetails() {
        System.out.println("Collected details are being combined...");
    }
}
