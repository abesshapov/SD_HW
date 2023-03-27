package Entities;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class Visitors {
    @JsonProperty("visitors_orders")
    private List<Visitor> visitors;

    public List<Visitor> getCustomers() {
        return visitors;
    }

    public Visitors(List<Visitor> visitors) {
        this.visitors = visitors;
    }
}