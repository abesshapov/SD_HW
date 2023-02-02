import Decorators.CreditCard;
import Decorators.Icon;
import Decorators.Insurance;
import Decorators.Passport;
import Documents.Document;
import Documents.UniversalElectronicCard;

public class Main {
    public static void main(String[] args) {
        Document universalElectronicCard = new UniversalElectronicCard();
        System.out.println(universalElectronicCard.getGivenOpportunities());
        System.out.println("Passport was added:");
        universalElectronicCard = new Passport(universalElectronicCard);
        System.out.println(universalElectronicCard.getGivenOpportunities());
        System.out.println("Insurance was added:");
        universalElectronicCard = new Insurance(universalElectronicCard);
        System.out.println(universalElectronicCard.getGivenOpportunities());
        System.out.println("Credit card was added:");
        universalElectronicCard = new CreditCard(universalElectronicCard);
        System.out.println(universalElectronicCard.getGivenOpportunities());
        System.out.println("Icon was added:");
        universalElectronicCard = new Icon(universalElectronicCard);
        System.out.println(universalElectronicCard.getGivenOpportunities());
    }
}