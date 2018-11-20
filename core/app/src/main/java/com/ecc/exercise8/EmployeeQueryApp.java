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

        String streetNumber = InputUtility.nextStringPersistent("Street Number:");
        String barangay = InputUtility.nextStringPersistent("Barangay:");
        String city = InputUtility.nextStringPersistent("City:");
        int zipcode = InputUtility.nextIntPersistent("Zipcode:", 1000, 9999);

        Address address = new Address(streetNumber, barangay, city, zipcode, employee);
        employee.setAddress(address);
        this.employeeService.saveEmployee(employee);
    }

    public void updateEmployee() {
  		Optional<Employee> employee = getEmployeeByID();

  		if (!employee.isPresent()) {
  			System.out.println("No Employee Exists.");
  			return;
  		}

    	System.out.println(
            "[0] Name, [1] Birth Date, [2] Date Hired, [3] GWA, [4] Employment Status. [5] Address");
    	int option = InputUtility.nextIntPersistent("Choose Column to Edit:", 0, 5);

        boolean isValid = false;

        switch (option) {
            case COLUMN_NAME:
                updateName(employee.get().getName());
                break;
            
            case COLUMN_BIRTH_DATE:
                LocalDate birthDate = 
                    InputUtility.nextDatePersistent("Birth Date", 1900, employee.get().getDateHired().getYear() - 18);

                employee.get().setBirthDate(birthDate);
                break;

            case COLUMN_DATE_HIRED:
                LocalDate dateHired = 
                    InputUtility.nextDatePersistent("Date Hired", employee.get().getBirthDate().getYear() + 18, 
                        LocalDate.now().getYear());

                employee.get().setDateHired(dateHired);
                break;

            case COLUMN_GWA:
                float gwa = InputUtility.nextFloatPersistent("Enter GWA:", 1, 5);
                employee.get().setGWA(gwa);
                break;

            case COLUMN_EMPLOYMENT_STATUS:
                System.out.println("[0] Employed, [1] Unemployed");
                boolean isEmployed = InputUtility.nextIntPersistent("Employment Status:", 0, 1) == 0;
                employee.get().setEmployed(isEmployed);
                break;

            case COLUMN_ADDRESS:
                updateAddress(employee.get().getAddress());
                break;
        }

    	this.employeeService.updateEmployee(employee.get());
    }

    private void updateName(Name name) {
        System.out.println("[0] First Name, [1] Middle Name, [2] Last Name");
        int option = InputUtility.nextIntPersistent("Choose Column to Edit:", 0, 2);

        switch (option) {
            case COLUMN_FIRST_NAME:
                String firstName = InputUtility.nextStringPersistent("Enter First Name:");
                name.setFirstName(firstName);
                break;
            case COLUMN_MIDDLE_NAME:
                String middleName = InputUtility.nextStringPersistent("Enter Middle Name:");
                name.setMiddleName(middleName);
                break;
            case COLUMN_LAST_NAME:
                String lastName = InputUtility.nextStringPersistent("Enter Last Name:");
                name.setLastName(lastName);
                break;
        }
    }

    private void updateAddress(Address address) {
        System.out.println("[0] Street Number, [1] Barangay, [2] City, [3] Zipcode");
        int option = InputUtility.nextIntPersistent("Choose Column to Edit:", 0, 3);


        switch (option) {
            case COLUMN_STREET_NUMBER:
                String streetNumber = InputUtility.nextStringPersistent("Street Number:");
                address.setStreetNumber(streetNumber);
                break;
            case COLUMN_BARANGAY:
                String barangay = InputUtility.nextStringPersistent("Barangay:");
                address.setBarangay(barangay);
                break;
            case COLUMN_CITY:
                String city = InputUtility.nextStringPersistent("City:");
                address.setCity(city);
                break;
            case COLUMN_ZIPCODE:
                int zipcode = InputUtility.nextIntPersistent("Zipcode:", 1000, 9999);
                address.setZipcode(zipcode);
                break;
        }
    }

    public void removeEmployee() {
    	Optional<Employee> employee = getEmployeeByID();

    	if (!employee.isPresent()) {
    		System.out.println("No Employee Exists");
    	}

    	this.employeeService.removeEmployee(employee.get().getId());
    }

    private Optional<Employee> getEmployeeByID() {
    	Optional<Employee> employee;

    	do {
    		long id = InputUtility.nextLongPersistent("Enter Employee ID:");
    		employee = this.employeeService.getEmployee(id);

    		if (!employee.isPresent()) {
    			System.out.println("Employee ID does not exist.");
    		}	

    	} while(!employee.isPresent());

    	return employee;
    }
}