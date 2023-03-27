package AgentRelated.OrderStatus;
import Entities.Order;
import jade.core.Agent;
import java.util.HashSet;
import java.util.Random;

public class OrderAgent extends Agent {

    private String visitorName;
    private int id = 0;
    private HashSet<Order> menu;

    protected void setup() {
        Object[] args = getArguments();
        menu = (HashSet<Order>) args[0];
        Random random = new Random();
        id = random.nextInt(1, 10);
        visitorName = (String) args[1];
        System.out.println("New order has been created: " + this.getLocalName());
        addBehaviour(new GetPrepared());
    }

    public String getCustomerName() {
        return visitorName;
    }

    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }

    public int getId() {
        return id;
    }

    public void setId(int newId) {
        id = newId;
    }

    public HashSet<Order> getMenu() {
        return menu;
    }

    public void setMenu(HashSet<Order> menu) {
        this.menu = menu;
    }

    @Override
    protected void takeDown() {
        System.out.println(getAID().getLocalName() + " has been taken down.");
        super.takeDown();
    }
}
