// package com.ecc.exercise8;

// public class EmployeeQueryApp {
// 	EmployeeService employeeService = new EmployeeService();

// 	private static final int LIST_EMPLOYEES = 0;
// 	private static final int ADD_EMPLOYEE = 1;
// 	private static final int UPDATE_EMPLOYEE = 2;
// 	private static final int REMOVE_EMPLOYEE = 3;
// 	private static final int VIEW_EMPLOYEE = 4;
// 	private static final int RETURN = 5;

// 	private static final int COLUMN_NAME = 0;
// 	private static final int COLUMN_BIRTH_DATE = 1;
// 	private static final int COLUMN_DATE_HIRED = 2;
// 	private static final int COLUMN_GWA = 3;
// 	private static final int COLUMN_EMPLOYMENT_STATUS = 4;
// 	private static final int COLUMN_ADDRESS = 5;

// 	private static final int COLUMN_FIRST_NAME = 0;
// 	private static final int COLUMN_MIDDLE_NAME = 1;
// 	private static final int COLUMN_LAST_NAME = 2;

// 	private static final int COLUMN_STREET_NUMBER = 0;
// 	private static final int COLUMN_BARANGAY = 1;
// 	private static final int COLUMN_CITY = 2;
// 	private static final int COLUMN_ZIPCODE = 3;

// 	public void execute() {
//     	boolean isReturn = false;

//     	do {
//     		System.out.println("[0] List Employees, [1] Add Employee, [2] Update Employee, " + 
//                 "[3] Remove Employee, [4] VIEW_EMPLOYEE, [5] Return");

//     		int option = InputUtility.nextIntPersistent("Enter option:");

// 	    	switch (option) {
// 	    		case LIST_EMPLOYEES:
// 	    			viewEmployee();
// 	    			break;
//                 case ADD_EMPLOYEE :
//                 	addEmployee();
//                 	viewEmployee();
//                 	break;
//                 case UPDATE_EMPLOYEE:
//                 	updateEmployee();
//                 	viewEmployee();
//                 	break;
//                 case REMOVE_EMPLOYEE:
//                 	removeEmployee();
//                 	viewEmployee();
//                 	break;
//     			case RETURN :
//     				isReturn = true;
// 	    	}	
//     	} while (!isReturn);    	
//     }

//     public void viewEmployee() {


// 		System.out.println(this.employeeService.getEmployeeDetails());
//     }

//     public void addEmployee() {
// 		// Optional<Employee> employee = getEmployeeByID();

//   //   	if (!employee.isPresent()) {
//   //           System.out.println("No employee exists to assign a employee.");
//   //   		return;
//   //   	}

//   //   	List<Employee> existingEmployees = this.employeeService.getEmployees();
//   //   	Employee employee;
//   //       boolean isValid = false;

//   //   	do {
//   //   		System.out.println("[0] Landline, [1] Mobile, [2] Email");
// 		// 	Employee.EmployeeType type = 
// 		// 		Employee.EmployeeType.values()[InputUtility.nextIntPersistent("Enter Type:", 0, 2)];
    		
//   //   		String value = InputUtility.nextStringPersistent("Enter Value:");

//   //   		employee = new Employee(type, value, employee.get());

//   //           if (!existingEmployees.contains(employee)) {
//   //               isValid = true;
//   //           }
//   //           else {
//   //               System.out.println("Error: Duplicate Employee.");
//   //           }
//   //   	} while(!isValid);

//   //   	this.employeeService.saveEmployee(employee);
//     }

//     public void updateEmployee() {
// 		// Optional<Employee> employee = getEmployeeByID();

// 		// if (!employee.isPresent()) {
// 		// 	System.out.println("No Employee Exists.");
// 		// 	return;
// 		// }

//   //   	System.out.println("[0] Type, [1] Value");
//   //   	int option = InputUtility.nextIntPersistent("Choose Column to Edit:", 0, 1);

//   //       List<Employee> existingEmployees = this.employeeService.getEmployees();
//   //       boolean isValid = false;

//   //       do {
//   //           switch (option) {
//   //               case COLUMN_TYPE:
//   //                   System.out.println("[0] Landline, [1] Mobile, [2] Email");
//   //                   Employee.EmployeeType type = 
//   //                       Employee.EmployeeType.values()[InputUtility.nextIntPersistent("Enter Type:", 0, 2)];
//   //                   employee.get().setType(type);
//   //                   break;
//   //               case COLUMN_VALUE:
//   //                   String value = InputUtility.nextStringPersistent("Enter Value:");
//   //                   employee.get().setValue(value);
//   //                   break;
//   //           }

//   //           if (!existingEmployees.contains(employee.get())) {
//   //               isValid = true;
//   //           }
//   //           else {
//   //               System.out.println("Error: Duplicate Employee.");
//   //           }
//   //       } while(!isValid);

//   //   	this.employeeService.updateEmployee(employee.get());
//     }

//     public void removeEmployee() {
//     	// Optional<Employee> employee = getEmployeeByID();

//     	// if (!employee.isPresent()) {
//     	// 	System.out.println("No Employee Exists");
//     	// }

//     	// this.employeeService.removeEmployee(employee.get().getId());
//     }

//     private Optional<Employee> getEmployeeByID() {
//     	// EmployeeDAO employeeDAO = new EmployeeDAO();
//     	// List<Employee> employees = employeeDAO.getEmployees();

//     	// if (employees == null || employees.isEmpty()) {
//     	// 	return Optional.empty();
//     	// }

//     	// String idList = employees.stream().map(
//     	// 	employee -> employee.getId() + ": " + employee.getName()).collect(Collectors.joining("\n"));

//     	// System.out.println(idList);

//     	// Optional<Employee> employee;

//     	// do {
//     	// 	long id = InputUtility.nextLongPersistent("Enter Employee ID:");
//     	// 	employee = employeeDAO.getEmployee(id);

//     	// 	if (!employee.isPresent()) {
//     	// 		System.out.println("Employee ID does not exist.");
//     	// 	}	

//     	// } while(!employee.isPresent());

//     	// return employee;
//     }

//     private Optional<Employee> getEmployeeByID() {
//     	// List<Employee> existingEmployees = this.employeeService.getEmployees();

//     	// if (existingEmployees == null || existingEmployees.isEmpty()) {
//     	// 	return Optional.empty();
//     	// }

//     	// Optional<Employee> employee = Optional.empty();    	

//     	// do {
//     	// 	Long id = (long) InputUtility.nextIntPersistent("Enter Employee ID:");

//     	// 	employee = this.employeeService.getEmployee(id);

//     	// 	if (!employee.isPresent()) {
//     	// 		System.out.println("Employee ID does not exist.");
//     	// 	}

//     	// } while (!employee.isPresent());

//     	// return employee;
//     }
// }