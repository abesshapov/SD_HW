package AgentRelated.OrderStatus;
import AgentRelated.AgentMessages.OrderMessage;
import AgentRelated.CustomerRelated.CustomerBehaviour.VisitorOntology;
import Entities.Visitor;
import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.wrapper.ContainerController;

public class Ready extends OneShotBehaviour {
    @Override
    public void action() {
        System.out.println(myAgent.getLocalName() + " send message to " + ((OrderAgent)myAgent).getCustomerName());
        ACLMessage message = new ACLMessage(ACLMessage.INFORM);
        message.addReceiver(new AID(((OrderAgent)myAgent).getCustomerName(), AID.ISLOCALNAME));
        try {
            OrderMessage orderMessage = new OrderMessage(
                    myAgent.getLocalName(),
                    ((OrderAgent)myAgent).getMenu(),
                    ((OrderAgent)myAgent).getId());
            VisitorOntology visitorOntology = VisitorOntology.getInstance();
            message.setContentObject(orderMessage);
            message.setOntology(visitorOntology.getName());
            myAgent.send(message);
            ContainerController containerController = myAgent.getContainerController();
            myAgent.doDelete();
            containerController.kill();
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }
}
