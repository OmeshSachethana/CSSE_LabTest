package model; 

public class Employee {

    // Declaration of variables
    public String idEmployee;
    public String nameFull;
    public String address;
    public String facultyName;
    public String department;
    public String designation;

    // Getter method for idEmployee
    public String getIdEmployee() {
        return idEmployee;
    }

    // Setter method for idEmployee
    public void setIdEmployee(String idEmployee) {
        this.idEmployee = idEmployee;
    }

    // Getter method for nameFull
    public String getNameFull() {
        return nameFull;
    }

    // Setter method for nameFull
    public void setNameFull(String nameFull) {
        this.nameFull = nameFull;
    }

    // Getter method for address
    public String getAddress() {
        return address;
    }

    // Setter method for address
    public void setAddress(String address) {
        this.address = address;
    }

    // Getter method for facultyName
    public String getFacultyName() {
        return facultyName;
    }

    // Setter method for facultyName
    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    // Getter method for department
    public String getDepartment() {
        return department;
    }

    // Setter method for department
    public void setDepartment(String department) {
        this.department = department;
    }

    // Getter method for designation
    public String getDesignation() {
        return designation;
    }

    // Setter method for designation
    public void setDesignation(String designation) {
        this.designation = designation;
    }

    // toString method
    @Override
    public String toString() {
        return "Employee [idEmployee=" + idEmployee + ", nameFull=" + nameFull + ", address=" + address
                + ", facultyName=" + facultyName + ", department=" + department + ", designation=" + designation + "]";
    }
}
