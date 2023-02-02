package Documents;

/**
 * Универсальная электронная карта - документ физического лица.
 */
public class UniversalElectronicCard extends Document {
    /**
     * Конструктор для создания универсальной электронной карты.
     */
    public UniversalElectronicCard() {
        givenOpportunities = "Universal electronic card with the list of supported " +
                "opportunities:";
    }
}
