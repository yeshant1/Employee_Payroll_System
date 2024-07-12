import java.util.*;
import java.util.ArrayList;


import java.util.ArrayList;

abstract class Employee {
    private String name;
    private int id;

    public Employee(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public abstract double calculateSalary();

    @Override
    public String toString() {
        return "Employee[name=" + name + ", id=" + id + ", salary=" + calculateSalary() + "]";
    }
}

class FullTimeEmployee extends Employee {
    private double monthlySalary;

    public FullTimeEmployee(String name, int id, double monthlySalary) {
        super(name, id);
        this.monthlySalary = monthlySalary;
    }

    @Override
    public double calculateSalary() {
        return monthlySalary;
    }
}

class PartTimeEmployee extends Employee {
    private int hoursWorked;
    private double hourlyRate;

    public PartTimeEmployee(String name, int id, int hoursWorked, double hourlyRate) {
        super(name, id);
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }

    @Override
    public double calculateSalary() {
        return hoursWorked * hourlyRate;
    }
}

class PayrollSystem {
    private ArrayList<Employee> employeeList;

    public PayrollSystem() {
        employeeList = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        employeeList.add(employee);
    }

    public boolean removeEmployee(int id) {
        for (Employee employee : employeeList) {
            if (employee.getId() == id) {
                employeeList.remove(employee);
                return true; // Employee found and removed successfully
            }
        }
        return false; // Employee with given ID not found
    }

    public void displayEmployees() {
        for (Employee employee : employeeList) {
            System.out.println(employee);
        }
    }
}


public class Main {
    public static void main(String[] args) {
        PayrollSystem payrollSystem = new PayrollSystem();
        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            System.out.println("\n=== Payroll System Menu ===");
            System.out.println("1. Add Employee");
            System.out.println("2. Remove Employee");
            System.out.println("3. Display Employees");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    scanner.nextLine(); // Consume newline character
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter ID: ");
                    int id = scanner.nextInt();
                    System.out.print("Enter monthly salary (for full-time) or hours worked (for part-time): ");
                    double salaryOrHours = scanner.nextDouble();

                    System.out.print("Is this a Full Time Employee (1) or Part Time Employee (2): ");
                    int employeeType = scanner.nextInt();

                    if (employeeType == 1) {
                        payrollSystem.addEmployee(new FullTimeEmployee(name, id, salaryOrHours));
                        System.out.println("Full Time Employee added successfully.");
                    } else if (employeeType == 2) {
                        System.out.print("Enter hourly rate: ");
                        double hourlyRate = scanner.nextDouble();
                        payrollSystem.addEmployee(new PartTimeEmployee(name, id, (int) salaryOrHours, hourlyRate));
                        System.out.println("Part Time Employee added successfully.");
                    } else {
                        System.out.println("Invalid employee type.");
                    }
                    break;

                case 2:
                    System.out.print("Enter ID of employee to remove: ");
                    int idToRemove = scanner.nextInt();
                    if (payrollSystem.removeEmployee(idToRemove)) {
                        System.out.println("Employee with ID " + idToRemove + " removed successfully.");
                    } else {
                        System.out.println("Employee with ID " + idToRemove + " not found.");
                    }
                    break;

                case 3:
                    System.out.println("Employee Details:");
                    payrollSystem.displayEmployees();
                    break;

                case 4:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }

        } while (choice != 4);

        scanner.close();
    }
}