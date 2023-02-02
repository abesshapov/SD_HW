package Decorators;

import Documents.Document;

/**
 * Паспорт физического лица.
 */
public class Passport extends UECardAbilitiesDecorator {
    /**
     * Переменная, хранящая ссылку.
     */
    Document document;

    /**
     * Конструктор для передачи ссылки на объект.
     * @param document объект, ссылка на который будет храниться.
     */
    public Passport(Document document) {
        this.document = document;
    }

    /**
     * Получить возможности документа.
     * @return возможности документа.
     */
    @Override
    public String getGivenOpportunities() {
        return document.getGivenOpportunities() + " ability to travel around the world on your " +
                "own after turning 18;";
    }
}