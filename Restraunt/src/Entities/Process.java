package Entities;
import Entities.OperationRelated.Operation;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * Процесс готовки.
 */
public class Process {
    /**
     * Операции в процессе готовки.
     */
    @JsonProperty("operations")
    private List<Operation> operations;

    /**
     * Получить процесс готовки.
     * @return процесс готовки.
     */
    public List<Operation> getMakingProcess() {
        return operations;
    }

    /**
     * Конструктор по массиву операций.
     * @param operations массив операций.
     */
    public Process(List<Operation> operations) {
        this.operations = operations;
    }
}
