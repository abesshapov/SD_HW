package Decorators;

import Documents.Document;

/**
 * Декоратор универсальной электронной карты.
 */
public abstract class UECardAbilitiesDecorator extends Document {
    /**
     * Получить возможности документа.
     * @return возможности документа.
     */
    public abstract String getGivenOpportunities();
}
