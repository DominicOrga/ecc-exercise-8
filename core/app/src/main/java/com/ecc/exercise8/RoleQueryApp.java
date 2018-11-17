package com.ecc.exercise8;

import java.util.List;
import java.util.stream.Collectors;

public class RoleQueryApp {

	RoleService roleService = new RoleService();

	private static final int VIEW_ROLE = 0;
	private static final int ADD_ROLE = 1;
	private static final int UPDATE_ROLE = 2;
	private static final int DELETE_ROLE = 3;
	private static final int RETURN = 4;

	public void execute() {
    	boolean isReturn = false;

    	do {
    		System.out.println("[0] View Roles, [1] Add Role, [2] Update Role, [3] Delete Role, [4] Return");

    		int option = InputUtility.nextIntPersistent("Enter option:");

	    	switch (option) {
	    		case VIEW_ROLE :
	    			viewRole();
	    			break;
                case ADD_ROLE :
                	addRole();
                	break;
    			case 4 :
    				isReturn = true;
	    	}	

    	} while (!isReturn);    	
    }

    public void viewRole() {
		System.out.println(this.roleService.getRoleDetails());    	
    }

    public void addRole() {
    	List<Role> existingRoles = this.roleService.getRoles();
    	String[] existingRoleCodes = existingRoles.stream().map(Role::getCode).toArray(String[]::new);

		String code = InputUtility.nextStringPersistent("Enter Code:", existingRoleCodes);
		String description = InputUtility.nextStringPersistent("Enter Description:");

		Role role = new Role(code, description);
		this.roleService.saveRole(role);
    }
}