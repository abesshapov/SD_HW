package AgentRelated.CustomerRelated.CustomerBehaviour;
import AgentRelated.AgentMessages.OrderMessage;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;
import java.util.Objects;

public class ClaimFood extends Behaviour {
    @Override
    public void action() {
        ACLMessage message = myAgent.receive();
        if (message != null) {
            if (Objects.equals(message.getOntology(), VisitorOntology.ONTOLOGY_NAME)) {
                try {
                    OrderMessage orderMessage = (OrderMessage)message.getContentObject();
                    System.out.println("Message was received " + orderMessage.getName() +
                            "; id: " + orderMessage.getId() +
                            "; the message: " + orderMessage.getMenu());
                    ContainerController containerController = myAgent.getContainerController();
                    myAgent.doDelete();
                    containerController.kill();
                } catch (UnreadableException exception) {
                    throw new RuntimeException(exception);
                } catch (StaleProxyException exception) {
                    throw new RuntimeException(exception);
                }
            }
        }
    }

    @Override
    public boolean done() {
        return false;
    }
}
