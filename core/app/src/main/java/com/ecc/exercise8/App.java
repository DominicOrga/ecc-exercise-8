package com.ecc.exercise8;

public class App 
{
    public static void main( String[] args )
    {
    	EmployeeQueryApp employeeQueryApp = new EmployeeQueryApp();
    	ContactQueryApp contactQueryApp = new ContactQueryApp();
        RoleQueryApp roleQueryApp = new RoleQueryApp();

        employeeQueryApp.execute();
        // contactQueryApp.execute();
        // roleQueryApp.execute();        
    }
}
