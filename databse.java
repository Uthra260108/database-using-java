import java.sql.*;
import java.util.Scanner;
 
public class EmployeePayrollJDBC {
 
    static final String URL =
            "jdbc:mysql://localhost:3306/employee_db";
    static final String USER = "root";
    static final String PASSWORD = "Ctmit123@2004";
 
    public static void main(String[] args) {
 
        Scanner sc = new Scanner(System.in);
 
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
 
            // Connect to database
            Connection con = DriverManager.getConnection(
                    URL, USER, PASSWORD);
 
            System.out.println("Connected to MySQL successfully!");
 
            // Create table
            createTable(con);
 
            int choice;
 
            do {
                System.out.println("\n===== EMPLOYEE PAYROLL SYSTEM =====");
                System.out.println("1. Insert 5 Sample Employees");
                System.out.println("2. Display Employees Earning More Than Rs. 50,000");
                System.out.println("3. Increase IT Employee Salary by 10%");
                System.out.println("4. Delete Employees Without Department");
                System.out.println("5. Display All Employees");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");
 
                choice = sc.nextInt();
 
                switch (choice) {
 
                    case 1:
                        insertEmployees(con);
                        break;
 
                    case 2:
                        displayHighSalaryEmployees(con);
                        break;
 
                    case 3:
                        increaseITSalary(con);
                        break;
 
                    case 4:
                        deleteNoDepartmentEmployees(con);
                        break;
 
                    case 5:
                        displayAllEmployees(con);
                        break;
 
                    case 6:
                        System.out.println("Exiting...");
                        break;
 
                    default:
                        System.out.println("Invalid choice!");
                }
 
            } while (choice != 6);
 
            con.close();
            sc.close();
 
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found!");
            e.printStackTrace();
 
        } catch (SQLException e) {
            System.out.println("Database error!");
            e.printStackTrace();
        }
    }
 
    // CREATE TABLE
    static void createTable(Connection con) throws SQLException {
 
        String sql = "CREATE TABLE IF NOT EXISTS employee (" +
                "emp_id INT PRIMARY KEY, " +
                "emp_name VARCHAR(100), " +
                "designation VARCHAR(100), " +
                "salary DECIMAL(10,2), " +
                "department VARCHAR(100))";
 
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.executeUpdate();
            System.out.println("Employee table created successfully!");
        }
    }
 
    // INSERT 5 EMPLOYEES
    static void insertEmployees(Connection con) throws SQLException {
 
        String sql = "INSERT INTO employee " +
                "(emp_id, emp_name, designation, salary, department) " +
                "VALUES (?, ?, ?, ?, ?)";
 
        try (PreparedStatement ps = con.prepareStatement(sql)) {
 
            ps.setInt(1, 101);
            ps.setString(2, "Arun");
            ps.setString(3, "Software Engineer");
            ps.setDouble(4, 65000);
            ps.setString(5, "IT");
            ps.executeUpdate();
 
            ps.setInt(1, 102);
            ps.setString(2, "Priya");
            ps.setString(3, "HR Manager");
            ps.setDouble(4, 55000);
            ps.setString(5, "HR");
            ps.executeUpdate();
 
            ps.setInt(1, 103);
            ps.setString(2, "Rahul");
            ps.setString(3, "Accountant");
            ps.setDouble(4, 45000);
            ps.setString(5, "Finance");
            ps.executeUpdate();
 
            ps.setInt(1, 104);
            ps.setString(2, "Divya");
            ps.setString(3, "Developer");
            ps.setDouble(4, 70000);
            ps.setString(5, "IT");
            ps.executeUpdate();
 
            ps.setInt(1, 105);
            ps.setString(2, "Karthik");
            ps.setString(3, "Assistant");
            ps.setDouble(4, 35000);
            ps.setString(5, null);
            ps.executeUpdate();
 
            System.out.println("5 employees inserted successfully!");
        }
    }
 
    // READ - SALARY > 50000
    static void displayHighSalaryEmployees(Connection con)
            throws SQLException {
 
        String sql = "SELECT * FROM employee WHERE salary > ?";
 
        try (PreparedStatement ps = con.prepareStatement(sql)) {
 
            ps.setDouble(1, 50000);
 
            ResultSet rs = ps.executeQuery();
 
            System.out.println("\nEmployees earning more than Rs. 50,000:");
 
            while (rs.next()) {
 
                System.out.println(
                        rs.getInt("emp_id") + " | " +
                        rs.getString("emp_name") + " | " +
                        rs.getString("designation") + " | " +
                        rs.getDouble("salary") + " | " +
                        rs.getString("department"));
            }
        }
    }
 
    // UPDATE - INCREASE IT SALARY BY 10%
    static void increaseITSalary(Connection con)
            throws SQLException {
 
        String sql = "UPDATE employee " +
                "SET salary = salary * ? " +
                "WHERE department = ?";
 
        try (PreparedStatement ps = con.prepareStatement(sql)) {
 
            ps.setDouble(1, 1.10);
            ps.setString(2, "IT");
 
            int rows = ps.executeUpdate();
 
            System.out.println(
                    rows + " IT employee(s) salary increased by 10%.");
        }
    }
 
    // DELETE - EMPLOYEES WITHOUT DEPARTMENT
    static void deleteNoDepartmentEmployees(Connection con)
            throws SQLException {
 
        String sql = "DELETE FROM employee " +
                "WHERE department IS NULL OR department = ?";
 
        try (PreparedStatement ps = con.prepareStatement(sql)) {
 
            ps.setString(1, "");
 
            int rows = ps.executeUpdate();
 
            System.out.println(
                    rows + " employee(s) without department deleted.");
        }
    }
 
    // DISPLAY ALL EMPLOYEES
    static void displayAllEmployees(Connection con)
            throws SQLException {
 
        String sql = "SELECT * FROM employee";
 
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
 
            System.out.println("\n===== ALL EMPLOYEES =====");
 
            while (rs.next()) {
 
                System.out.println(
                        "ID: " + rs.getInt("emp_id") +
                        ", Name: " + rs.getString("emp_name") +
                        ", Designation: " +
                        rs.getString("designation") +
                        ", Salary: Rs." +
                        rs.getDouble("salary") +
                        ", Department: " +
                        rs.getString("department"));
            }
        }
    }
}
 
