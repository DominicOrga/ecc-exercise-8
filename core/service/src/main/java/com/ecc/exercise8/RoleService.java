package com.ecc.exercise8;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RoleService {

	private RoleDAO roleDAO = new RoleDAO();

	public void saveRole(Role role) {
		roleDAO.saveRole(role);
	}

	public Optional<Role> getRole(Long id) {
		return roleDAO.getRole(id);
	}

	public List<Role> getRolesJoinedEmployees() {
		return roleDAO.getRolesJoinedEmployees();
	}

	public Optional<Role> getRoleJoinedEmployees(Long id) {
		return roleDAO.getRoleJoinedEmployees(id);
	}

	public String getRoleDetail(Long id) {
		Optional<Role> role = roleDAO.getRoleJoinedEmployees(id); 

		if (!role.isPresent()) {
			return null;
		}

		return String.format("ID: %d \n" +
						"Code: %s \n" +
						"Description: %s \n" +
						"Employee ID/s: %s",
						role.get().getId(), 
						role.get().getCode(), 
						role.get().getDescription(), 
						role.get().getEmployees().stream().map(e -> e.getId().toString()).collect(Collectors.joining(", ")));
	}

	public String getRoleDetails() {
		return getRolesJoinedEmployees()
			   .stream()
			   .map(role -> getRoleDetail(role.getId()))
			   .collect(Collectors.joining("\n"));
	} 

}