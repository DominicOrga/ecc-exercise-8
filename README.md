Exist Software Labs, Inc. (PH)  
Exist Code Camp (ECC)  

### Exercise 8: Hibernate Annotation Mapping
1. Create the following entities:
    #### Employee
    * Data: Name (Embedded), Birth Date, Date Hired, GWA, Employment Status

    #### Address
    * Data: Street Number (String), Barangay, City, Zipcode (Integer)
    * One-to-one, composition relationship with Employee
    * Part of Employee Creation

    #### Contact
    * Data: Type (Enum), Value (String)
    * Many-to-one relationship with Employee

    #### Role
    * Data: Code, Description
    * Many-to-Many relationship with Employee

2. DML Commands:
    #### Employee
    * View all Employees ordered by GWA (java sorting), date hired (hql), or last name (hql)
    * Create Employee
    * Delete Employee
    * Update Employee
    * View Single Employee
  
    #### Contact
    * View all Contacts
    * Add Contact to Employee
    * Update Contact
    * Delete Contact

    #### Role
    * View all Roles
    * Add Role
    * Update Role
    * Remove Role
    * Assign Role to Employee
    * Revoke Role from Employee
    
 3. Project Structure:
    - app
      - core
        - model
        - service
        - dao  
    - infra
      - persistence (DB Configuration Files)
      - buildtools (Not a maven repository, but a container for SQL dump files for table creation and data insertion)
    - utilities
