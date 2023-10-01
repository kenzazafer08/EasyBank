package interfaces;

import dto.Mission;
import dto.Operation;

import java.util.List;

public interface OperationI {
    Operation add(Operation operation);
    List<Operation> searchByNumber();
    boolean delete(String id);
}
