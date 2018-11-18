package com.ecc.exercise8;

public class App 
{
    public static void main( String[] args )
    {
    	ContactQueryApp contactQueryApp = new ContactQueryApp();
        RoleQueryApp roleQueryApp = new RoleQueryApp();

        contactQueryApp.execute();
        // roleQueryApp.execute();        
    }
}
