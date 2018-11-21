DROP TABLE IF EXISTS address CASCADE;
DROP TABLE IF EXISTS contact CASCADE;
DROP TABLE IF EXISTS employee CASCADE;
DROP TABLE IF EXISTS employee_role CASCADE;
DROP TABLE IF EXISTS role;

CREATE TABLE address (
    id bigint PRIMARY KEY NOT NULL,
    barangay character varying(255) NOT NULL,
    city character varying(255) NOT NULL,
    street_number character varying(255) NOT NULL,
    zipcode integer NOT NULL,
    employee_id bigint NOT NULL
);

CREATE TABLE contact (
    id bigint PRIMARY KEY NOT NULL,
    type character varying(255) NOT NULL,
    value character varying(255) NOT NULL,
    employee_id bigint NOT NULL
);

CREATE TABLE employee (
    id bigint PRIMARY KEY NOT NULL,
    birth_date date NOT NULL,
    date_hired date NOT NULL,
    gwa real NOT NULL,
    is_employed boolean NOT NULL,
    first_name character varying(255),
    last_name character varying(255),
    middle_name character varying(255)
);

CREATE TABLE employee_role (
    employee_id bigint NOT NULL,
    role_id bigint NOT NULL,
    UNIQUE(employee_id, role_id)
);

CREATE TABLE role (
    id bigint PRIMARY KEY NOT NULL,
    code character varying(255) NOT NULL,
    description character varying(255) NOT NULL
);