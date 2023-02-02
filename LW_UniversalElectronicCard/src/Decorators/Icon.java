package Decorators;

import Documents.Document;

/**
 * Икона физического лица.
 */
public class Icon extends UECardAbilitiesDecorator {
    /**
     * Переменная, хранящая ссылку.
     */
    Document document;

    /**
     * Конструктор для передачи ссылки на объект.
     * @param document объект, ссылка на который будет храниться.
     */
    public Icon(Document document) {
        this.document = document;
    }

    /**
     * Получить возможности документа.
     * @return возможности документа.
     */
    @Override
    public String getGivenOpportunities() {
        return document.getGivenOpportunities() + " protection by the will of God;";
    }
}
