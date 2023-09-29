import Implementation.*;
import dto.*;
import helpers.DBconnection;
import helpers.helper;
import interfaces.ClientI;
import interfaces.*;

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

        Account account = new Account();
        AccountI accountDAO = new AccountDAO(dbConnection);

        SavingAccount savingAccount = new SavingAccount();
        AccountI<SavingAccount> savingAccountDAO = new SavingAcountDAO(dbConnection);

        CurrentAccount currentAccount = new CurrentAccount();
        AccountI<CurrentAccount> currentAccountDAO = new CurrentAccountDAO(dbConnection);

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
                    System.out.println("Search for a client :");
                    System.out.println("Enter the code of the client :");
                    client = clientDAO.searchByCode(scanner.nextLine());
                    if(client == null){
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
                            account = accountDAO.add(account);
                            if(account == null){
                                System.out.println("Something went wrong !");
                            }else{
                                currentAccount.setNumber(account.getNumber());
                                System.out.println("Enter the account overdraft :");
                                currentAccount.setOverdraft(scanner.nextDouble());
                                if(currentAccountDAO.add(currentAccount) == null){
                                    System.out.println("Something went wrong !");
                                }else{
                                    System.out.println("Your current account created successfully here is you account number : "+account.getNumber());
                                }
                            }
                            break;
                        case 2 :
                            account = accountDAO.add(account);
                            if(account == null){
                                System.out.println("Something went wrong !");
                            }else{
                                savingAccount.setNumber(account.getNumber());
                                System.out.println("Enter the account interest rate :");
                                savingAccount.setInterestRate(scanner.nextDouble());
                                if(savingAccountDAO.add(savingAccount) == null){
                                    System.out.println("Something went wrong !");
                                }else{
                                    System.out.println("Your saving account created successfully here is you account number : "+account.getNumber());
                                }
                            }
                            break;
                        default:
                            System.out.println("Invalid choice. Please select a valid option.");
                            break;
                    }
                    break;
                case 8:

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