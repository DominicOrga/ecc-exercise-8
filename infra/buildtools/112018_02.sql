INSERT INTO Employee(id, first_name, middle_name, last_name, birth_date, date_hired, gwa, is_employed)
VALUES(1, 'Wardell', 'Stephen', 'Curry', '1988-3-9', '2009-6-25', 1.75, true);
INSERT INTO Employee(id, first_name, middle_name, last_name, birth_date, date_hired, gwa, is_employed)
VALUES(2, 'Lebron', 'Raymone', 'James', '1984-12-30', '2003-11-3', 1.25, true);
INSERT INTO Employee(id, first_name, middle_name, last_name, birth_date, date_hired, gwa, is_employed)
VALUES(3, 'Kevin', 'Wayne', 'Durant', '1988-9-29', '2007-1-13', 1.5, false);

INSERT INTO Address(id, barangay, city, street_number, zipcode, employee_id)
VALUES(1, 'San Rafael', 'Curayao', '454', 1860, 1);
INSERT INTO Address(id, barangay, city, street_number, zipcode, employee_id)
VALUES(2, 'Manila', 'Metro Manila', '720', 1920, 2);
INSERT INTO Address(id, barangay, city, street_number, zipcode, employee_id)
VALUES(3, 'San Domingo', 'Caloocan', '324', 1920, 3);

INSERT INTO contact(id, type, value, employee_id)
VALUES(1, 'landline', '5702355', 1);
INSERT INTO contact(id, type, value, employee_id)
VALUES(2, 'mobile', '09215168547', 1);
INSERT INTO contact(id, type, value, employee_id)
VALUES(3, 'email', 'stephencurry@gmail.com', 1);
INSERT INTO contact(id, type, value, employee_id)
VALUES(4, 'mobile', '09328696392', 2);
INSERT INTO contact(id, type, value, employee_id)
VALUES(5, 'mobile', '09253653267', 2);

INSERT INTO role(id, code, description)
VALUES(1, 'Dev', 'Responsibilities include: Producing clean, efficient code based on specifications. Testing and deploying programs and systems. Fixing and improving existing software.');
INSERT INTO role(id, code, description)
VALUES(2, 'QA', 'Responsible for ensuring a product or service meets the established standards of quality including reliability, usability and performance.');
INSERT INTO role(id, code, description)
VALUES(3, 'BA', 'Developing technical solutions to business problems, or to advance a companys sales efforts, begins with defining, analyzing and documenting requirements.');

INSERT INTO employee_role(employee_id, role_id)
VALUES(1, 1);
INSERT INTO employee_role(employee_id, role_id)
VALUES(2, 1);
INSERT INTO employee_role(employee_id, role_id)
VALUES(2, 2);
INSERT INTO employee_role(employee_id, role_id)
VALUES(2, 3);