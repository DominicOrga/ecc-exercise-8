// package com.ecc.exercise8;

// public class RoleService {

// 	private RoleDAO roleDAO = new RoleDAO();

// 	public String getRolesAsString() {
// 		StringBuilder sb = new StringBuilder();

// 		List<Role> roles = roleDAO.getRolesJoinedEmployees();

// 		String format = "ID: %d \n" +
// 						"Code: %s \n" +
// 						"Description: %s \n" +
// 						"Employee IDs: %s"; 

// 		for (Role role : roles) {
// 			String employees = role.getEmployees().stream().map(Employee::getId).collect(Collectors.joining());

// 			sb.append(role.getId());
// 		}
// 	}



// }