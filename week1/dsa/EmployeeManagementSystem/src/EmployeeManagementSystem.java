class Employee {
    String employeeId;
    String name;
    String position;
    double salary;

    public Employee(String employeeId, String name, String position, double salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.position = position;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee ID: " + employeeId + ", Name: " + name + ", Position: " + position + ", Salary: " + salary;
    }
}

public class EmployeeManagementSystem {
    private Employee[] employees;
    private int size;

    public EmployeeManagementSystem(int capacity) {
        employees = new Employee[capacity];
        size = 0;
    }

    // Add an employee
    public void addEmployee(Employee employee) {
        if (size < employees.length) {
            employees[size++] = employee;
        } else {
            System.out.println("Array is full. Cannot add more employees.");
        }
    }

    // Search for an employee by ID
    public Employee searchEmployeeById(String employeeId) {
        for (int i = 0; i < size; i++) {
            if (employees[i].employeeId.equals(employeeId)) {
                return employees[i];
            }
        }
        return null;
    }

    // Traverse and display all employees
    public void traverseEmployees() {
        for (int i = 0; i < size; i++) {
            System.out.println(employees[i]);
        }
    }

    // Delete an employee by ID
    public boolean deleteEmployeeById(String employeeId) {
        for (int i = 0; i < size; i++) {
            if (employees[i].employeeId.equals(employeeId)) {
                // Shift elements left to fill the gap
                for (int j = i; j < size - 1; j++) {
                    employees[j] = employees[j + 1];
                }
                employees[size - 1] = null; // Clear the last element
                size--;
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        EmployeeManagementSystem ems = new EmployeeManagementSystem(5);

        // Add employees
        ems.addEmployee(new Employee("E001", "Alice", "Manager", 75000));
        ems.addEmployee(new Employee("E002", "Bob", "Developer", 55000));
        ems.addEmployee(new Employee("E003", "Charlie", "Designer", 60000));
        ems.addEmployee(new Employee("E004", "David", "Analyst", 50000));
        ems.addEmployee(new Employee("E005", "Eve", "Tester", 45000));

        // Traverse employees
        System.out.println("All Employees:");
        ems.traverseEmployees();

        // Search for an employee
        System.out.println("\nSearching for employee with ID E003:");
        Employee employee = ems.searchEmployeeById("E003");
        if (employee != null) {
            System.out.println(employee);
        } else {
            System.out.println("Employee not found.");
        }

        // Delete an employee
        System.out.println("\nDeleting employee with ID E002:");
        boolean deleted = ems.deleteEmployeeById("E002");
        if (deleted) {
            System.out.println("Employee deleted.");
        } else {
            System.out.println("Employee not found.");
        }

        // Traverse employees after deletion
        System.out.println("\nAll Employees After Deletion:");
        ems.traverseEmployees();
    }
}
