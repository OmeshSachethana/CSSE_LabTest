package main;

import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import common.UtilTransform;
import service.GetEmpService;

public class ExecuteMain {

    /**
     * @param args
     */
    public static void main(String[] args) {
        GetEmpService employeeService = new GetEmpService();
        
        try {
            UtilTransform.requestTransform();
            employeeService.employeesFormXml();
            employeeService.createEmployeeTable();
            employeeService.addEmployees();
            employeeService.getEmployeeByID("EMP10004");
            employeeService.getEmployeeByID("EMP10001"); 
            employeeService.displayEmployee();
        } catch (TransformerConfigurationException e) {
            // Handle configuration exception
            e.printStackTrace();
        } catch (TransformerFactoryConfigurationError e) {
            // Handle configuration error
            e.printStackTrace();
        } catch (TransformerException e) {
            // Handle transformer exception
            e.printStackTrace();
        } catch (Exception e) {
            // Handle other exceptions
            e.printStackTrace();
        }
    }
}
