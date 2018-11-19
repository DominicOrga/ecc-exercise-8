package com.ecc.exercise8;

import java.util.Optional;
import java.time.LocalDate;

public class EmployeeQueryApp {
	EmployeeService employeeService = new EmployeeService();

	private static final int LIST_EMPLOYEES = 0;
	private static final int ADD_EMPLOYEE = 1;
	private static final int UPDATE_EMPLOYEE = 2;
	private static final int REMOVE_EMPLOYEE = 3;
	private static final int VIEW_EMPLOYEE = 4;
	private static final int RETURN = 5;

	private static final int COLUMN_NAME = 0;
	private static final int COLUMN_BIRTH_DATE = 1;
	private static final int COLUMN_DATE_HIRED = 2;
	private static final int COLUMN_GWA = 3;
	private static final int COLUMN_EMPLOYMENT_STATUS = 4;
	private static final int COLUMN_ADDRESS = 5;

	private static final int COLUMN_FIRST_NAME = 0;
	private static final int COLUMN_MIDDLE_NAME = 1;
	private static final int COLUMN_LAST_NAME = 2;

	private static final int COLUMN_STREET_NUMBER = 0;
	private static final int COLUMN_BARANGAY = 1;
	private static final int COLUMN_CITY = 2;
	private static final int COLUMN_ZIPCODE = 3;

	public void execute() {
    	boolean isReturn = false;

    	do {
    		System.out.println("[0] List Employees, [1] Add Employee, [2] Update Employee, " + 
                "[3] Remove Employee, [4] VIEW_EMPLOYEE, [5] Return");

    		int option = InputUtility.nextIntPersistent("Enter option:");

	    	switch (option) {
	    		case LIST_EMPLOYEES:
	    			viewEmployees();
	    			break;
          case ADD_EMPLOYEE :
          	addEmployee();
          	viewEmployees();
          	break;
          case UPDATE_EMPLOYEE:
          	updateEmployee();
          	viewEmployees();
          	break;
          case REMOVE_EMPLOYEE:
          	removeEmployee();
          	viewEmployees();
            break;
    			case RETURN :
    				isReturn = true;
	    	}	
    	} while (!isReturn);    	
    }

    public void viewEmployees() {
		  System.out.println(this.employeeService.getEmployeeDetails());
    }

    public void addEmployee() {
      String firstName = InputUtility.nextStringPersistent("Enter First Name:");
      String middleName = InputUtility.nextStringPersistent("Enter Middle Name:");
      String lastName = InputUtility.nextStringPersistent("Enter Last Name:");

      Name name = new Name(firstName, middleName, lastName);

      LocalDate birthDate = 
        InputUtility.nextDatePersistent("Birth Date", 1900, LocalDate.now().getYear() - 18);

      LocalDate dateHired = 
        InputUtility.nextDatePersistent("Date Hired", birthDate.getYear() + 18, LocalDate.now().getYear());

      float gwa = InputUtility.nextFloatPersistent("Enter GWA:", 1, 5);

      System.out.println("[0] Employed, [1] Unemployed");
      boolean isEmployed = InputUtility.nextIntPersistent("Employment Status:", 0, 1) == 0;

      Employee employee = new Employee(name, birthDate, dateHired, gwa, isEmployed);

      String streetNumber = InputUtility.nextStringPersistent("Street:");
      String barangay = InputUtility.nextStringPersistent("Barangay:");
      String city = InputUtility.nextStringPersistent("City:");
      int zipcode = InputUtility.nextIntPersistent("Zipcode:", 1000, 9999);

      Address address = new Address(streetNumber, barangay, city, zipcode, employee);
      employee.setAddress(address);
      this.employeeService.saveEmployee(employee);
    }

    public void updateEmployee() {
		// Optional<Employee> employee = getEmployeeByID();

		// if (!employee.isPresent()) {
		// 	System.out.println("No Employee Exists.");
		// 	return;
		// }

  //   	System.out.println("[0] Type, [1] Value");
  //   	int option = InputUtility.nextIntPersistent("Choose Column to Edit:", 0, 1);

  //       List<Employee> existingEmployees = this.employeeService.getEmployees();
  //       boolean isValid = false;

  //       do {
  //           switch (option) {
  //               case COLUMN_TYPE:
  //                   System.out.println("[0] Landline, [1] Mobile, [2] Email");
  //                   Employee.EmployeeType type = 
  //                       Employee.EmployeeType.values()[InputUtility.nextIntPersistent("Enter Type:", 0, 2)];
  //                   employee.get().setType(type);
  //                   break;
  //               case COLUMN_VALUE:
  //                   String value = InputUtility.nextStringPersistent("Enter Value:");
  //                   employee.get().setValue(value);
  //                   break;
  //           }

  //           if (!existingEmployees.contains(employee.get())) {
  //               isValid = true;
  //           }
  //           else {
  //               System.out.println("Error: Duplicate Employee.");
  //           }
  //       } while(!isValid);

  //   	this.employeeService.updateEmployee(employee.get());
    }

    public void removeEmployee() {
    	// Optional<Employee> employee = getEmployeeByID();

    	// if (!employee.isPresent()) {
    	// 	System.out.println("No Employee Exists");
    	// }

    	// this.employeeService.removeEmployee(employee.get().getId());
    }

    private Optional<Employee> getEmployeeByID() {
    	// EmployeeDAO employeeDAO = new EmployeeDAO();
    	// List<Employee> employees = employeeDAO.getEmployees();

    	// if (employees == null || employees.isEmpty()) {
    	// 	return Optional.empty();
    	// }

    	// String idList = employees.stream().map(
    	// 	employee -> employee.getId() + ": " + employee.getName()).collect(Collectors.joining("\n"));

    	// System.out.println(idList);

    	// Optional<Employee> employee;

    	// do {
    	// 	long id = InputUtility.nextLongPersistent("Enter Employee ID:");
    	// 	employee = employeeDAO.getEmployee(id);

    	// 	if (!employee.isPresent()) {
    	// 		System.out.println("Employee ID does not exist.");
    	// 	}	

    	// } while(!employee.isPresent());

    	// return employee;
      return Optional.empty();
    }
}