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

	public Optional<Role> getRole(Long id, boolean isEmployeeInitialized) {
		return roleDAO.getRole(id, isEmployeeInitialized);
	}

	public List<Role> getRoles() {
		return roleDAO.getRoles();
	}

	public List<Role> getRoles(boolean isEmployeesInitialized) {
		return roleDAO.getRoles(isEmployeesInitialized);
	}

	public String getRoleDetail(Long id) {
		Optional<Role> role = roleDAO.getRole(id, true); 

		if (!role.isPresent()) {
			return null;
		}

		return String.format("ID: %d \n" +
						"Code: %s \n" +
						"Description: %s \n" +
						"Employee ID/s: %s\n",
						role.get().getId(), 
						role.get().getCode(), 
						role.get().getDescription(), 
						role.get().getEmployees().stream().map(e -> e.getId().toString()).collect(Collectors.joining(", ")));
	}

	public String getRoleDetails() {
		return getRoles(true)
			   .stream()
			   .map(role -> getRoleDetail(role.getId()))
			   .collect(Collectors.joining("\n"));
	} 

	public void updateRole(Role role) {
		this.roleDAO.updateRole(role);
	}

	public void removeRole(Long id) {
		this.roleDAO.removeRole(id);
	}
}