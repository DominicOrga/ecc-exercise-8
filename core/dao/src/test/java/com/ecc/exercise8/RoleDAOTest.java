package com.ecc.exercise8;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.Before;

public class RoleDAOTest {

	private RoleDAO roleDAO;

	private String code;
	private String description;
	private Role role;

	@Before
	public void setupRole() {
		this.code = "Dev";
		this.description = "Dev Things";

		this.role = new Role(this.code, this.description);
	}

	@Before
	public void setupRoleDAO() {
		this.roleDAO = new RoleDAO();
	}

	@Test
	public void whenRoleSavedThenRoleIsPersisted() {
		this.role.setId(null);

		roleDAO.saveRole(this.role);

		assertThat(this.role.getId()).isNotNull();
	}

	@Test
	@Ignore
	public void givenCodeWhenRoleIsSavedAndLoadedThenCodeIsPersisted() {

	}

	@Test
	@Ignore
	public void whenCodeIsNullThenRoleIsNotPersisted() {

	}
}