package Implementation;

import dto.Operation;
import interfaces.OperationI;

import java.util.List;

public class OperationDAO implements OperationI {
    @Override
    public Operation add(Operation operation) {
        return null;
    }

    @Override
    public List<Operation> searchByNumber() {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
