package Decorators;

import Documents.Document;

/**
 * Страховой полис физического лица.
 */
public class Insurance extends UECardAbilitiesDecorator {
    /**
     * Переменная, хранящая ссылку.
     */
    Document document;

    /**
     * Конструктор для передачи ссылки на объект.
     * @param document объект, ссылка на который будет храниться.
     */
    public Insurance(Document document) {
        this.document = document;
    }

    /**
     * Получить возможности документа.
     * @return возможности документа.
     */
    @Override
    public String getGivenOpportunities() {
        return document.getGivenOpportunities() + " the right to free treatment in case of injury;";
    }
}
