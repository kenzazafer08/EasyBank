import Implementation.*;
import dto.*;
import helpers.DBconnection;
import helpers.helper;
import interfaces.ClientI;
import interfaces.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DBconnection dbConnection = DBconnection.getInstance();
        Scanner scanner = new Scanner(System.in);

        Employee employee = new Employee();
        EmployeeI employeeDAO = new EmployeeDAO(dbConnection);

        Client client = new Client();
        ClientI clientDAO = new ClientDAO(dbConnection);

        Account account = new Account();
        AccountI accountDAO = new AccountDAO(dbConnection);
        SavingAccount savingAccount = new SavingAccount();
        CurrentAccount currentAccount = new CurrentAccount();

        Operation operation = new Operation();
        OperationI operationDAO = new OperationDAO(dbConnection);

        Mission mission = new Mission();
        MissionI missionDAO = new MissionDAO(dbConnection);

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
            System.out.println("9. Search for accounts by client");
            System.out.println("10. Add an operation");
            System.out.println("11. Delete an operation");
            System.out.println("12. Search for an operation");
            System.out.println("13. Add mission");
            System.out.println("14. Delete mission");
            System.out.println("15. Display employees list");
            System.out.println("16. Display clients list");
            System.out.println("17. Display accounts list");
            System.out.println("18. Deactivate an account");
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
                    System.out.println("Delete an employee :");
                    System.out.println("Enter the number of the employee :");
                    employee.setNumber(scanner.nextLine());
                    if(!employeeDAO.delete(employee.getNumber())){
                        System.out.println("Something went wrong try again !");
                    }else{
                        System.out.println("Employee deleted successfully");
                    }
                    break;
                case 3:
                    System.out.println("Search for an employee :");
                    System.out.println("Enter the number of the employee :");
                    employee = employeeDAO.searchByMatricul(scanner.nextLine());
                    if(employee == null || employee.getDeleted()){
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
                    System.out.println("Delete a client :");
                    System.out.println("Enter the number of the client :");
                    if(!clientDAO.delete(scanner.nextLine())){
                        System.out.println("Something went wrong try again !");
                    }else{
                        System.out.println("Client deleted successfully");
                    }
                    break;
                case 6:
                    System.out.println("Search for a client :");
                    System.out.println("Enter the code of the client :");
                    client = clientDAO.searchByCode(scanner.nextLine());
                    if(client == null || client.getDeleted()){
                        System.out.println("No client found !");
                    }else{
                        System.out.println("Client " +client.getCode() +":");
                        System.out.println("First Name : " +client.getFirstName());
                        System.out.println("Last Name : " +client.getLastName());
                        System.out.println("Phone number : "+client.getPhone());
                        System.out.println("Address : "+client.getAddress());
                    }
                    break;
                case 7:
                    System.out.println("Create an account :");
                    System.out.println("Enter the client code :");
                    client.setCode(scanner.nextLine());
                    account.setClient(client);
                    System.out.println("Enter the employee number :");
                    employee.setNumber(scanner.nextLine());
                    account.setEmployee(employee);
                    System.out.println("Enter the account solde : ");
                    account.setSold(scanner.nextDouble());
                    System.out.println("What type of account you wanna create :");
                    System.out.println("1. Current account ");
                    System.out.println("2. Saving account ");
                    int c = helper.getInputAsInt(scanner);
                    switch (c){
                        case 1 :
                            currentAccount.setSold(account.getSold());
                            currentAccount.setClient(account.getClient());
                            currentAccount.setEmployee(account.getEmployee());
                            System.out.println("Enter the account overdraft :");
                            currentAccount.setOverdraft(scanner.nextDouble());
                            account = accountDAO.add(currentAccount);
                            if(account == null){
                                System.out.println("Something went wrong !");
                            }else{
                                System.out.println("Your current account created successfully here is you account number : "+account.getNumber());
                            }
                            break;
                        case 2 :
                            System.out.println(account);
                            savingAccount.setSold(account.getSold());
                            savingAccount.setClient(account.getClient());
                            savingAccount.setEmployee(account.getEmployee());
                            System.out.println("Enter the Interest rate :");
                            savingAccount.setInterestRate(scanner.nextDouble());
                            account = accountDAO.add(savingAccount);
                            if(account == null){
                                System.out.println("Something went wrong !");
                            }else{
                                System.out.println("Your current account created successfully here is you account number : "+account.getNumber());
                            }
                            break;
                        default:
                            System.out.println("Invalid choice. Please select a valid option.");
                            break;
                    }
                    break;
                case 8:
                    System.out.println("Delete an account :");
                    System.out.println("Enter the number of the account :");
                    if(!accountDAO.delete(scanner.nextLine())){
                        System.out.println("Something went wrong try again !");
                    }else{
                        System.out.println("Account deleted successfully");
                    }
                    break;

                case 9:
                    System.out.println("Search for an account by client :");
                    System.out.println("Enter the client code :");
                    client.setCode(scanner.nextLine());
                    List<Account> clientAccounts = accountDAO.searchByClient(client);
                    Client cl = clientDAO.searchByCode(client.getCode());
                    System.out.println("Client "+ cl.getFirstName()+ " " + cl.getLastName()+" accounts:");
                    System.out.println();
                    for (Account a : clientAccounts) {
                        System.out.println("Account Number: " + a.getNumber());
                        System.out.println("Account Balance: " + a.getSold());
                        System.out.println("Account manager: " + a.getEmployee().getFirstName() + " " + a.getEmployee().getLastName());
                        if (a instanceof SavingAccount) {
                            SavingAccount savingA = (SavingAccount) a;
                            System.out.println("Account Type: Saving Account");
                            System.out.println("Interest Rate: " + savingA.getInterestRate());
                        } else if (a instanceof CurrentAccount) {
                            CurrentAccount currentA = (CurrentAccount) a;
                            System.out.println("Account Type: Current Account");
                            System.out.println("Overdraft Limit: " + currentA.getOverdraft());
                        }

                        System.out.println();
                    }
                    break;

                case 10:
                    System.out.println("Pass an operation :");
                    System.out.println("Enter the account number");
                    account.setNumber(scanner.nextLine());
                    operation.setAccount(account);
                    System.out.println("Enter the employee number");
                    employee.setNumber(scanner.nextLine());
                    operation.setEmployee(employee);
                    System.out.println("Enter the amount");
                    operation.setAmount(scanner.nextDouble());
                    int op = 0;
                    while(op !=1 && op!=2){
                        System.out.println("What type of operation you wanna create :");
                        System.out.println("1. Payment ");
                        System.out.println("2. Withdraw ");
                        op = helper.getInputAsInt(scanner);
                        if(op == 1) {
                            operation.setType(Operation.Type.payment);
                        }else if(op == 2){
                            operation.setType(Operation.Type.withdrawal);
                        }else{
                            System.out.println("Enter a valid choice !");
                        }
                    }
                    if(operationDAO.add(operation) == null){
                        System.out.println("Something went wrong !");
                    }else{
                        System.out.println("Your operation passed successfully");
                    }
                    break;

                case 11:
                    System.out.println("Delete an operation");
                    System.out.println("Enter the number of the operation");
                    if(operationDAO.delete(scanner.nextLine())){
                        System.out.println("Your operation deleted successfully");
                    }else{
                        System.out.println("Something went wrong !");
                    }
                    break;

                case 12:
                    System.out.println("Search operations by account number ");
                    System.out.println("Enter the account number");
                    List<Operation> operations = operationDAO.searchByNumber(scanner.nextLine());
                    System.out.println("Operations List :");
                    System.out.println();
                    for (Operation o : operations) {
                        System.out.println("Operation number : " + o.getNumber());
                        System.out.println("Operation type : " + o.getType());
                        System.out.println("Operation amount : " + o.getAmount());
                        System.out.println("Date : "+o.getCreationDate());
                        System.out.println("Employee : " + o.getEmployee().getFirstName() + " "+ o.getEmployee().getLastName());
                    }
                    break;

                case 13:
                    System.out.println("Add mission");
                    System.out.println("Mission name :");
                    mission.setName(scanner.nextLine());
                    System.out.println("Mission description :");
                    mission.setDescription(scanner.nextLine());
                    Mission addedMission = missionDAO.add(mission);
                    if (addedMission != null) {
                        System.out.println("Mission added successfully with code: " + addedMission.getCode());
                    } else {
                        System.out.println("Failed to add the mission.");
                    }
                    break;

                case 14:
                    System.out.println("Delete a mission :");
                    System.out.println("Enter the code of the mission :");
                    if(!missionDAO.delete(scanner.nextLine())){
                        System.out.println("Something went wrong try again !");
                    }else{
                        System.out.println("Mission deleted successfully");
                    }
                    break;
                case 15 :
                    System.out.println("Employees List :");
                    System.out.println();
                    List<Employee> employees = employeeDAO.showList();
                    for(Employee e : employees){
                        if(!e.getDeleted()){
                            System.out.println();
                            System.out.println("Employee " +e.getNumber() +":");
                            System.out.println("First Name : " +e.getFirstName());
                            System.out.println("Last Name : " +e.getLastName());
                            System.out.println("Phone number : "+e.getPhone());
                            System.out.println("Email : "+e.getEmail());
                            System.out.println("Address : "+e.getAddress());
                            System.out.println();
                        }
                    }
                    break;
                case 16 :
                    System.out.println("Employees List :");
                    System.out.println();
                    List<Client> clients = clientDAO.showList();
                    for(Client clt : clients){
                        if(!clt.getDeleted()){
                            System.out.println();
                            System.out.println("Client " +clt.getCode() +":");
                            System.out.println("First Name : " +clt.getFirstName());
                            System.out.println("Last Name : " +clt.getLastName());
                            System.out.println("Phone number : "+clt.getPhone());
                            System.out.println("Address : "+clt.getAddress());
                            System.out.println();
                        }
                    }
                    break;
                case 17 :
                    System.out.println("Display accounts list :");
                    System.out.println();
                    List<Account> listAccounts = accountDAO.showList();
                    for (Account as : listAccounts) {
                        System.out.println("Account Number: " + as.getNumber());
                        System.out.println("Account Balance: " + as.getSold());
                        System.out.println("Account manager: " + as.getEmployee().getFirstName() + " " + as.getEmployee().getLastName());
                        System.out.println("Client : " + as.getClient().getCode() + " " + as.getClient().getFirstName() + " "+ as.getClient().getLastName());
                        if (as instanceof SavingAccount) {
                            SavingAccount savingA = (SavingAccount) as;
                            System.out.println("Account Type: Saving Account");
                            System.out.println("Interest Rate: " + savingA.getInterestRate());
                        } else if (as instanceof CurrentAccount) {
                            CurrentAccount currentA = (CurrentAccount) as;
                            System.out.println("Account Type: Current Account");
                            System.out.println("Overdraft Limit: " + currentA.getOverdraft());
                        }
                        System.out.println("Status: "+as.getState());
                        System.out.println();
                    }
                    break;
                case 18 :
                    System.out.println("Deactivate an account");
                    System.out.println("Enter the number of the account");
                    if(accountDAO.updateStatus(scanner.nextLine())==null){
                        System.out.println("Something went wrong !");
                    }else{
                        System.out.println("Account deactivated successfully");
                    }
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