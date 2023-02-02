package Documents;

/**
 * Документ физического лица.
 */
public abstract class Document {
    /**
     * Возможности, даваемые документом.
     */
    String givenOpportunities = "no opportunities";

    /**
     * Получить возможности документа.
     * @return возможности документа.
     */
    public String getGivenOpportunities() {
        return givenOpportunities;
    }
}
