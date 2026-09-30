Employee Payroll System using Java JDBC

A simple Employee Payroll Management System developed using Java, JDBC, and MySQL. This console-based application allows users to insert, view, update, and delete employee records from a MySQL database.

Features

The application provides the following operations:

Insert 5 Sample Employees

Adds five predefined employee records to the database.

Display Employees Earning More Than Rs. 50,000

Retrieves employees whose salary is greater than ₹50,000.

Increase IT Employee Salary by 10%

Updates the salary of all employees belonging to the IT department by 10%.

Delete Employees Without Department

Deletes employees whose department is either NULL or empty.

Display All Employees

Displays all employee records stored in the database.

Exit

Exits the application.

Technologies Used

Java

JDBC (Java Database Connectivity)

MySQL

MySQL JDBC Driver

Scanner for console input

PreparedStatement for executing SQL queries

Database Configuration

The application connects to a MySQL database using the following configuration:

static final String URL =
        "jdbc:mysql://localhost:3306/employee_db";
static final String USER = "root";
static final String PASSWORD = "YOUR_PASSWORD";


Security Note: Do not commit your actual database password to GitHub or other public repositories. Replace it with your own password or use environment variables.

Database Setup

Before running the Java program, create the database in MySQL:

CREATE DATABASE employee_db;


The employee table is created automatically by the Java program using:

CREATE TABLE IF NOT EXISTS employee (
    emp_id INT PRIMARY KEY,
    emp_name VARCHAR(100),
    designation VARCHAR(100),
    salary DECIMAL(10,2),
    department VARCHAR(100)
);

Employee Table Structure
Column	Data Type	Description
emp_id	INT	Unique employee ID
emp_name	VARCHAR(100)	Employee name
designation	VARCHAR(100)	Employee designation
salary	DECIMAL(10,2)	Employee salary
department	VARCHAR(100)	Employee department
Sample Employees

The program inserts the following sample records:

ID	Name	Designation	Salary	Department
101	Arun	Software Engineer	₹65,000	IT
102	Priya	HR Manager	₹55,000	HR
103	Rahul	Accountant	₹45,000	Finance
104	Divya	Developer	₹70,000	IT
105	Karthik	Assistant	₹35,000	NULL
Project Structure
EmployeePayrollJDBC/
│
├── EmployeePayrollJDBC.java
└── README.md


The MySQL JDBC Connector JAR should also be added to the project's classpath.

How to Run
1. Install MySQL

Make sure MySQL Server is installed and running on your system.

2. Create the Database

Open MySQL and execute:

CREATE DATABASE employee_db;

3. Configure Database Credentials

Update the following variables in EmployeePayrollJDBC.java:

static final String URL =
        "jdbc:mysql://localhost:3306/employee_db";

static final String USER = "root";

static final String PASSWORD = "YOUR_PASSWORD";


Replace YOUR_PASSWORD with your MySQL password.

4. Add MySQL JDBC Driver

Download and add the MySQL Connector/J library to your Java project.

For example, if using a JAR manually:

mysql-connector-j-x.x.x.jar


Add the JAR to your project's classpath.

5. Compile the Program

On Windows:

javac -cp ".;mysql-connector-j-x.x.x.jar" EmployeePayrollJDBC.java


On Linux/macOS:

javac -cp ".:mysql-connector-j-x.x.x.jar" EmployeePayrollJDBC.java

6. Run the Program

On Windows:

java -cp ".;mysql-connector-j-x.x.x.jar" EmployeePayrollJDBC


On Linux/macOS:

java -cp ".:mysql-connector-j-x.x.x.jar" EmployeePayrollJDBC

Application Menu

When the program starts, the following menu is displayed:

===== EMPLOYEE PAYROLL SYSTEM =====
1. Insert 5 Sample Employees
2. Display Employees Earning More Than Rs. 50,000
3. Increase IT Employee Salary by 10%
4. Delete Employees Without Department
5. Display All Employees
6. Exit
Enter your choice:

JDBC Operations Used
CREATE

The program creates the employee table if it does not already exist.

INSERT

Five employee records are inserted using PreparedStatement.

SELECT

Employees earning more than ₹50,000 and all employees can be retrieved from the database.

UPDATE

IT employees receive a 10% salary increase:

UPDATE employee
SET salary = salary * 1.10
WHERE department = 'IT';

DELETE

Employees without a department are deleted:

DELETE FROM employee
WHERE department IS NULL OR department = '';

Important Note

If you select option 1 multiple times, the program will attempt to insert the same employee IDs again. Since emp_id is the primary key, MySQL will generate a duplicate-key error.

To avoid this, either:

Select option 1 only once, or

Modify the INSERT query to handle duplicate employee IDs.

Exception Handling

The program handles:

ClassNotFoundException when the MySQL JDBC driver is not found.

SQLException for database-related errors.

Learning Objectives

This project demonstrates:

Java database connectivity using JDBC

Connecting Java applications with MySQL

Creating database tables using Java

CRUD operations

PreparedStatement

ResultSet

SQL SELECT, INSERT, UPDATE, and DELETE

Exception handling

Console-based menu-driven applications

Author

Employee Payroll JDBC Project

Developed as a Java + JDBC + MySQL database application.
