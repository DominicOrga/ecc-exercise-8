package com.ecc.exercise8;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ContactQueryApp {
	ContactService contactService = new ContactService();

	private static final int VIEW_CONTACT = 0;
	private static final int ADD_CONTACT = 1;
	private static final int UPDATE_CONTACT = 2;
	private static final int REMOVE_CONTACT = 3;
	private static final int RETURN = 4;

	private static final int COLUMN_TYPE = 0;
	private static final int COLUMN_VALUE = 1;

	public void execute() {
    	boolean isReturn = false;

    	do {
    		System.out.println("[0] View Contacts, [1] Add Contact, [2] Update Contact, " + 
                "[3] Remove Contact, [4] Return");

    		int option = InputUtility.nextIntPersistent("Enter option:");

	    	switch (option) {
	    		case VIEW_CONTACT:
	    			viewContact();
	    			break;
                case ADD_CONTACT :
                	addContact();
                	viewContact();
                	break;
                case UPDATE_CONTACT:
                	updateContact();
                	viewContact();
                	break;
                case REMOVE_CONTACT:
                	removeContact();
                	viewContact();
                	break;
    			case RETURN :
    				isReturn = true;
	    	}	
    	} while (!isReturn);    	
    }

    public void viewContact() {
		System.out.println(this.contactService.getContactDetails());
    }

    public void addContact() {
		Optional<Employee> employee = getEmployeeByID();

    	if (!employee.isPresent()) {
            System.out.println("No employee exists to assign a contact.");
    		return;
    	}

    	List<Contact> existingContacts = this.contactService.getContacts();
    	Contact contact;
        boolean isValid = false;

    	do {
    		System.out.println("[0] Landline, [1] Mobile, [2] Email");
			Contact.ContactType type = 
				Contact.ContactType.values()[InputUtility.nextIntPersistent("Enter Type:", 0, 2)];
    		
    		String value = InputUtility.nextStringPersistent("Enter Value:");

    		contact = new Contact(type, value, employee.get());

            if (!existingContacts.contains(contact)) {
                isValid = true;
            }
            else {
                System.out.println("Error: Duplicate Contact.");
            }
    	} while(!isValid);

    	this.contactService.saveContact(contact);
    }

    public void updateContact() {
		Optional<Contact> contact = getContactByID();

		if (!contact.isPresent()) {
			System.out.println("No Contact Exists.");
			return;
		}

    	System.out.println("[0] Type, [1] Value");
    	int option = InputUtility.nextIntPersistent("Choose Column to Edit:", 0, 1);

        List<Contact> existingContacts = this.contactService.getContacts();
        boolean isValid = false;

        do {
            switch (option) {
                case COLUMN_TYPE:
                    System.out.println("[0] Landline, [1] Mobile, [2] Email");
                    Contact.ContactType type = 
                        Contact.ContactType.values()[InputUtility.nextIntPersistent("Enter Type:", 0, 2)];
                    contact.get().setType(type);
                    break;
                case COLUMN_VALUE:
                    String value = InputUtility.nextStringPersistent("Enter Value:");
                    contact.get().setValue(value);
                    break;
            }

            if (!existingContacts.contains(contact.get())) {
                isValid = true;
            }
            else {
                System.out.println("Error: Duplicate Contact.");
            }
        } while(!isValid);

    	this.contactService.updateContact(contact.get());
    }

    public void removeContact() {
    	Optional<Contact> contact = getContactByID();

    	if (!contact.isPresent()) {
    		System.out.println("No Contact Exists");
    	}

    	this.contactService.removeContact(contact.get().getId());
    }

    private Optional<Employee> getEmployeeByID() {
    	EmployeeDAO employeeDAO = new EmployeeDAO();
    	List<Employee> employees = employeeDAO.getEmployees();

    	if (employees == null || employees.isEmpty()) {
    		return Optional.empty();
    	}

    	String idList = employees.stream().map(
    		employee -> employee.getId() + ": " + employee.getName()).collect(Collectors.joining("\n"));

    	System.out.println(idList);

    	Optional<Employee> employee;

    	do {
    		long id = InputUtility.nextLongPersistent("Enter Employee ID:");
    		employee = employeeDAO.getEmployee(id);

    		if (!employee.isPresent()) {
    			System.out.println("Employee ID does not exist.");
    		}	

    	} while(!employee.isPresent());

    	return employee;
    }

    private Optional<Contact> getContactByID() {
    	List<Contact> existingContacts = this.contactService.getContacts();

    	if (existingContacts == null || existingContacts.isEmpty()) {
    		return Optional.empty();
    	}

    	Optional<Contact> contact = Optional.empty();    	

    	do {
    		Long id = (long) InputUtility.nextIntPersistent("Enter Contact ID:");

    		contact = this.contactService.getContact(id);

    		if (!contact.isPresent()) {
    			System.out.println("Contact ID does not exist.");
    		}

    	} while (!contact.isPresent());

    	return contact;
    }
}