package com.ecc.exercise8;

public class App 
{

    public static void main( String[] args )
    {
		App app = new App();
		app.execute();    
    }

    private final int QUERY_EMPLOYEE = 0;
    private final int QUERY_CONTACT = 1;
    private final int QUERY_ROLE = 2;
    private final int EXIT = 3;

    public void execute() {
    	EmployeeQueryApp employeeQueryApp = new EmployeeQueryApp();
    	ContactQueryApp contactQueryApp = new ContactQueryApp();
        RoleQueryApp roleQueryApp = new RoleQueryApp();

        

        boolean isExit = false;

        do {
        	System.out.println("Query:");
       		System.out.println("[0] Employee, [1] Contact, [2] Role, [3] Exit");
       		int option = InputUtility.nextIntPersistent("Option:", 0, 3);

       		switch (option) {
       			case QUERY_EMPLOYEE:
       				employeeQueryApp.execute();
       				break;
   				case QUERY_CONTACT:
   					contactQueryApp.execute();
   					break;
   				case QUERY_ROLE:
   					roleQueryApp.execute();
   					break;
   				case EXIT:
   					isExit = true;
       		}

        } while (!isExit);
    }
}
