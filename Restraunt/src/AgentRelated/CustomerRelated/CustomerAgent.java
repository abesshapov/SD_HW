package AgentRelated.CustomerRelated;
import AgentRelated.CustomerRelated.CustomerBehaviour.ClaimFood;
import AgentRelated.CustomerRelated.CustomerBehaviour.OrderFood;
import Entities.Order;
import jade.core.Agent;
import java.util.HashSet;

public class CustomerAgent extends Agent {
    HashSet<Order> menu;
    protected void setup() {
        Object[] args = getArguments();
        menu = (HashSet<Order>) args[0];
        System.out.println(getAID().getLocalName() + " has been set up.");
        addBehaviour(new OrderFood());
        addBehaviour(new ClaimFood());
    }

    @Override
    protected void takeDown() {
        System.out.println(getAID().getLocalName() + " has been taken down.");
        super.takeDown();
    }
}
