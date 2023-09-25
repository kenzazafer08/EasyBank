import Implementation.ClientDAO;
import Implementation.EmployeeDAO;
import dto.Client;
import dto.Employee;
import helpers.DBconnection;
import helpers.helper;
import interfaces.ClientI;
import interfaces.EmployeeI;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DBconnection dbConnection = DBconnection.getInstance();
        Scanner scanner = new Scanner(System.in);

        Employee employee = new Employee();
        EmployeeI employeeDAO = new EmployeeDAO(dbConnection);

        Client client = new Client();
        ClientI clientDAO = new ClientDAO(dbConnection);

        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Add an Employee");
            System.out.println("2. Delete an Employee");
            System.out.println("3. Search for an Employee");
            System.out.println("4. Create a client");
            System.out.println("5. Delete a client");
            System.out.println("6. Search for a client");
            System.out.println("7. Create an account");
            System.out.println("8. Delete an account");
            System.out.println("9. Search for an account");
            System.out.println("10. Add mission");
            System.out.println("11. Delete mission");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            int choice = helper.getInputAsInt(scanner);

            switch (choice) {
                case 1:
                System.out.println("Add an employee :");
                System.out.println("Enter the number of the employee :");
                employee.setNumber(scanner.nextLine());
                System.out.println("Enter the first name of the employee :");
                employee.setFirstName(scanner.nextLine());
                System.out.println("Enter the last name of the employee :");
                employee.setLastName(scanner.nextLine());
                System.out.println("Enter the phone number of the employee :");
                employee.setPhone(scanner.nextLine());
                System.out.println("Enter the email of the employee :");
                employee.setEmail(scanner.nextLine());
                System.out.println("Enter the address of the employee :");
                employee.setAddress(scanner.nextLine());
                if(employeeDAO.add(employee) == null){
                    System.out.println("Something went wrong try again !");
                }else{
                    System.out.println("Employee inserted successfully");
                }
                    break;

                case 2:


                    break;
                case 3:
                    System.out.println("Search for an employee :");
                    System.out.println("Enter the number of the employee :");
                    employee = employeeDAO.searchByMatricul(scanner.nextLine());
                    if(employee == null){
                        System.out.println("No employee found !");
                    }else{
                        System.out.println("Employee " +employee.getNumber() +":");
                        System.out.println("First Name : " +employee.getFirstName());
                        System.out.println("Last Name : " +employee.getLastName());
                        System.out.println("Phone number : "+employee.getPhone());
                        System.out.println("Email : "+employee.getEmail());
                        System.out.println("Address : "+employee.getAddress());
                    }
                    break;
                case 4:
                    System.out.println("Create a client : ");
                    System.out.println("Enter the client first name : ");
                    client.setFirstName(scanner.nextLine());
                    System.out.println("Enter the client  last name : ");
                    client.setLastName(scanner.nextLine());
                    System.out.println("Enter the client phone number : ");
                    client.setPhone(scanner.nextLine());
                    System.out.println("Enter the client address : ");
                    client.setAddress(scanner.nextLine());
                    client = clientDAO.add(client);
                    if(client == null){
                        System.out.println("Something went wrong !");
                    }else{
                        System.out.println("Client inserted successfully , client code : "+client.getCode());
                    }
                    break;
                case 5:

                    break;
                case 6:

                    break;
                case 7:
                    // Borrow a book

                    break;
                case 8:
                    // Return a book

                    break;

                case 9:
                    break;

                case 10:
                    break;

                case 11:
                    break;

                case 0:
                    try {
                        dbConnection.closeConnection();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    scanner.close();
                    System.out.println("Goodbye!");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }

    }
}