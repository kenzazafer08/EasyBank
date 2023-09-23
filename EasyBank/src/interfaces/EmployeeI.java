package interfaces;

import dto.Client;
import dto.Employee;

import java.util.List;

public interface EmployeeI {
    Employee add(Employee employee);
    List<Employee> searchByMatricul();
    boolean delete(int id);
    List<Employee> showList();
    List<Employee> searchByDateR();
    Employee update();
}
