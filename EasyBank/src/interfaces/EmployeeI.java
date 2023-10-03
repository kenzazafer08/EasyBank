package interfaces;

import dto.Client;
import dto.Employee;

import java.util.List;

public interface EmployeeI {
    Employee add(Employee employee);
    Employee searchByMatricul(String matriculationNumber);
    boolean delete(String id);
    List<Employee> showList();
    Employee update(Employee updatedEmployee);
}
