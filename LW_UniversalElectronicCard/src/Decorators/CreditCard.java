package Decorators;

import Documents.Document;

/**
 * Банковская карта физического лица.
 */
public class CreditCard extends UECardAbilitiesDecorator {
    /**
     * Переменная, хранящая ссылку.
     */
    Document document;

    /**
     * Конструктор для передачи ссылки на объект.
     * @param document объект, ссылка на который будет храниться.
     */
    public CreditCard(Document document) {
        this.document = document;
    }

    /**
     * Получить возможности документа.
     * @return возможности документа.
     */
    @Override
    public String getGivenOpportunities() {
        return document.getGivenOpportunities() + " ability to purchase goods without cash;";
    }
}
