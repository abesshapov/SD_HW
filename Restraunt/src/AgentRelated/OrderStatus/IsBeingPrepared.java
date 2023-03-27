package AgentRelated.OrderStatus;
import AgentRelated.AgentMessages.Message;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

public class IsBeingPrepared extends Behaviour {
    @Override
    public void action() {
        ACLMessage message = myAgent.receive();
        if (message != null) {
            try {
                Message oneMoreMessage = (Message)message.getContentObject();
                System.out.println("Message was received: " + oneMoreMessage.getName());
                myAgent.addBehaviour(new Ready());
            } catch (UnreadableException exception) {
                throw new RuntimeException(exception);
            }
        }
    }

    @Override
    public boolean done() {
        return false;
    }
}