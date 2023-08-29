package service;

/**
 * This class provides functionality for performing ADD employee, GET employee, and Display all employees actions based on the action types.
 *
 * @param employeeID ID of the employee to remove or select from the list
 * @param actionType DELETE or GET EMPLOYEE action type
 * @return ArrayList<Employee> Array of employee list will be returned
 * @throws SQLException Thrown when a database access error occurs or this method is called on a closed connection
 * @throws ClassNotFoundException Thrown when an application tries to load in a class through its string name using
 * @throws SAXException Encapsulates a general SAX error or warning
 * @throws IOException Exception produced by failed or interrupted I/O operations
 * @throws ParserConfigurationException Indicates a serious configuration error
 * @throws NullPointerException Service is not available
 * @see #displayAllEmployees()
 * @see #removeEmployee(String)
 * @see #getEmployeeByID(String)
 */

import org.xml.sax.SAXException;
import java.sql.Connection;
import java.util.logging.Logger;
import java.sql.DriverManager;
import javax.xml.parsers.ParserConfigurationException;
import java.sql.PreparedStatement;
import common.UtilTransform;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.sql.Statement;
import common.UtilQ;
import java.io.IOException;
import model.Employee;

import java.util.ArrayList;
import java.util.Map;
import common.UtilC;





public class GetEmpService extends UtilC {

    private final ArrayList<Employee> employeeList = new ArrayList<Employee>();

    private static Connection connection;
    private static Statement statement;
    private PreparedStatement preparedStatement;

    public GetEmpService() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(properties.getProperty("url"),
                    properties.getProperty("username"), properties.getProperty("password"));
        } catch (ClassNotFoundException | SQLException e) {
            Logger.getLogger(GetEmpService.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public void employeesFormXml() {
        try {
            int s = UtilTransform.xmlXPaths().size();
            for (int i = 0; i < s; i++) {
                Map<String, String> l = UtilTransform.xmlXPaths().get(i);
                Employee employee = new Employee();
                employee.setIdEmployee(l.get("XpathEmployeeIDKey"));
                employee.setNameFull(l.get("XpathEmployeeNameKey"));
                employee.setAddress(l.get("XpathEmployeeAddressKey"));
                employee.setFacultyName(l.get("XpathFacultyNameKey"));
                employee.setDepartment(l.get("XpathDepartmentKey"));
                employee.setDesignation(l.get("XpathDesignationKey"));
                employeeList.add(employee);
                System.out.println(employee.toString() + "\n");
            }
        } catch (Exception e) {
            Logger.getLogger(GetEmpService.class.getName()).log(Level.SEVERE, "An error occurred while processing XML data.", e);
        }
    }

    public void createEmployeeTable() {
        try {
            statement = connection.createStatement();
            statement.executeUpdate(UtilQ.Q("q2"));
            statement.executeUpdate(UtilQ.Q("q1"));
        } catch (SQLException | ParserConfigurationException | IOException | SAXException e) {
            Logger.getLogger(GetEmpService.class.getName()).log(Level.SEVERE, "An error occurred while creating employee table.", e);
        }
    }

    public void addEmployees() {
        try {
            preparedStatement = connection.prepareStatement(UtilQ.Q("q3"));
            connection.setAutoCommit(false);
            // enhanced for loop
            for (Employee employee : employeeList) {
                preparedStatement.setString(1, employee.getIdEmployee());
                preparedStatement.setString(2, employee.getNameFull());
                preparedStatement.setString(3, employee.getAddress());
                preparedStatement.setString(4, employee.getFacultyName());
                preparedStatement.setString(5, employee.getDepartment());
                preparedStatement.setString(6, employee.getDesignation());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            connection.commit();
        } catch (SQLException | ParserConfigurationException | IOException | SAXException e) {
            Logger.getLogger(GetEmpService.class.getName()).log(Level.SEVERE, "An error occurred while adding employees.", e);
        }
    }

    public void getEmployeeByID(String employeeID) {
        Employee employee = new Employee();
        try {
            preparedStatement = connection.prepareStatement(UtilQ.Q("q4"));
            preparedStatement.setString(1, employeeID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employee.setIdEmployee(resultSet.getString(1));
                employee.setNameFull(resultSet.getString(2));
                employee.setAddress(resultSet.getString(3));
                employee.setFacultyName(resultSet.getString(4));
                employee.setDepartment(resultSet.getString(5));
                employee.setDesignation(resultSet.getString(6));
            }
            ArrayList<Employee> arrayList = new ArrayList<Employee>();
            arrayList.add(employee);
            displayEmployeesDetails(arrayList);
        } catch (SQLException | ParserConfigurationException | IOException | SAXException ex) {
            Logger.getLogger(GetEmpService.class.getName()).log(Level.SEVERE, "An error occurred while fetching employee by ID.", ex);
        }
    }

    public void deleteEmployee(String employeeID) {
        try {
            preparedStatement = connection.prepareStatement(UtilQ.Q("q6"));
            preparedStatement.setString(1, employeeID);
            preparedStatement.executeUpdate();
        } catch (SQLException | ParserConfigurationException | IOException | SAXException e) {
            Logger.getLogger(GetEmpService.class.getName()).log(Level.SEVERE, "An error occurred while deleting employee.", e);
        }
    }

    public void displayEmployee() {
        ArrayList<Employee> arrayList = new ArrayList<Employee>();
        try {
            preparedStatement = connection.prepareStatement(UtilQ.Q("q5"));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setIdEmployee(resultSet.getString(1));
                employee.setNameFull(resultSet.getString(2));
                employee.setAddress(resultSet.getString(3));
                employee.setFacultyName(resultSet.getString(4));
                employee.setDepartment(resultSet.getString(5));
                employee.setDesignation(resultSet.getString(6));
                arrayList.add(employee);
            }
        } catch (SQLException | ParserConfigurationException | IOException | SAXException e) {
            Logger.getLogger(GetEmpService.class.getName()).log(Level.SEVERE, "An error occurred while displaying employees.", e);
        }
        displayEmployeesDetails(arrayList);
    }

    public void displayEmployeesDetails(ArrayList<Employee> arrayList) {
        System.out.println("Employee ID" + "\t\t" + "Full Name" + "\t\t" + "Address" + "\t\t" + "Faculty Name" + "\t\t"
                + "Department" + "\t\t" + "Designation" + "\n");
        System.out.println(
                "================================================================================================================");
        for (int i = 0; i < arrayList.size(); i++) {
            Employee employee = arrayList.get(i);
            System.out.println(employee.getIdEmployee() + "\t" + employee.getNameFull() + "\t\t" + employee.getAddress()
                    + "\t" + employee.getFacultyName() + "\t" + employee.getDepartment() + "\t" + employee.getDesignation()
                    + "\n");
            System.out.println(
                    "----------------------------------------------------------------------------------------------------------------");
        }
    }
}
