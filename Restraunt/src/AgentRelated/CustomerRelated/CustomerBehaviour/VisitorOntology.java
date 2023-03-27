package AgentRelated.CustomerRelated.CustomerBehaviour;
import Entities.Visitor;
import jade.content.onto.BasicOntology;
import jade.content.onto.Ontology;
import jade.content.onto.OntologyException;
import jade.content.schema.ConceptSchema;

public class VisitorOntology extends Ontology {
    public static final String ONTOLOGY_NAME = "CustomerOntology";

    private static final VisitorOntology instance = new VisitorOntology();

    public static VisitorOntology getInstance() {
        return instance;
    }

    private VisitorOntology() {
        super(ONTOLOGY_NAME, BasicOntology.getInstance());
        try {
            add(new ConceptSchema(ONTOLOGY_NAME), Visitor.class);
        } catch (OntologyException ontologyException) {
            ontologyException.printStackTrace();
        }
    }
}
