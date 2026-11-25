import java.util.*;

public class finalCode_c1a_GRP4 {
    static double hoursWeek1, hoursWeek2, hoursWeek3, hoursWeek4, totalhours, rph;
    static int dependents;
    static double sss, pagibig, philhealth, tax, dependentDed;
    static double regularPay, overtimePay, transportAllowance, grossPay, totalDeductions, netPay;
    static String lastName, firstName, middleName, department, position;

    static ArrayList<String> storedPayrolls = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static double computeDed(double grosspayValue, double hoursWorked, int dependentsCount) {
        double totalDed;

        if (grosspayValue <= 5000) {
            sss = 105;
        } else if (grosspayValue <= 10000) {
            sss = grosspayValue * 0.05;
        } else if (grosspayValue <= 15000) {
            sss = (grosspayValue * 0.08) + 75;
        } else {
            sss = (grosspayValue * 0.12) + 110;
        }

        if (grosspayValue <= 5000) {
            pagibig = 100;
        } else {
            pagibig = grosspayValue * 0.03;
        }

        if (hoursWorked < 10) {
            philhealth = 0;
        } else {
            philhealth = 120;
        }

        if (grosspayValue <= 10000) {
            tax = grosspayValue * 0.03;
        } else if (grosspayValue <= 25000) {
            tax = grosspayValue * 0.08;
        } else if (grosspayValue <= 40000) {
            tax = grosspayValue * 0.11;
        } else {
            tax = grosspayValue * 0.135;
        }

        dependentDed = dependentsCount * 1000;
        totalDed = sss + pagibig + philhealth + tax + dependentDed;

        return totalDed;
    }

    public static double computeGP() {
        lastName = getValidatedString("ENTER LAST NAME: ");
        firstName = getValidatedString("ENTER FIRST NAME: ");
        middleName = getValidatedString("ENTER MIDDLE NAME: ");
        department = getValidatedString("ENTER DEPARTMENT: ");
        position = getValidatedString("ENTER POSITION: ");

        rph = getValidatedDouble("Enter rate per hour: ");
        hoursWeek1 = getValidatedInt("Enter how many hours worked per Week 1: ");
        hoursWeek2 = getValidatedInt("Enter how many hours worked per Week 2: ");
        hoursWeek3 = getValidatedInt("Enter how many hours worked per Week 3: ");
        hoursWeek4 = getValidatedInt("Enter how many hours worked per Week 4: ");
        dependents = getValidatedInt("Enter number of dependents: ");

        totalhours = hoursWeek1 + hoursWeek2 + hoursWeek3 + hoursWeek4;
        
        double weekoneGP = 0;
        double weektwoGP = 0;
        double weekthreeGP = 0;
        double weekfourGP = 0;
        double weekoneRegularPay = 0;
        double weektwoRegularPay = 0;
        double weekthreeRegularPay = 0;
        double weekfourRegularPay = 0;

        double overtimepayWeek1 = 0;
        double overtimepayWeek2 = 0;
        double overtimepayWeek3 = 0;
        double overtimepayWeek4 = 0;

        if (hoursWeek1 > 40) {
            double overtimeWeek1 = hoursWeek1 - 40;
            overtimepayWeek1 = overtimeWeek1 * (rph * 1.25);
            weekoneRegularPay = 40 * rph;
            weekoneGP = weekoneRegularPay + overtimepayWeek1;
        } else {
            weekoneRegularPay = hoursWeek1 * rph;
            overtimepayWeek1 = 0;
        }
         if (hoursWeek2 > 40) {
            double overtimeWeek2 = hoursWeek2 - 40;
            overtimepayWeek2 = overtimeWeek2 * (rph * 1.25);
            weektwoRegularPay = 40 * rph;
            weektwoGP = weektwoRegularPay + overtimepayWeek2;
        } else {
            weektwoRegularPay = hoursWeek2 * rph;
            overtimepayWeek2 = 0;
        }
         if (hoursWeek3 > 40) {
            double overtimeWeek3 = hoursWeek3 - 40;
            overtimepayWeek3 = overtimeWeek3 * (rph * 1.25);
            weekthreeRegularPay = 40 * rph;
            weekthreeGP = weekthreeRegularPay + overtimepayWeek3;
        } else {
            weekthreeRegularPay = hoursWeek3 * rph;
            overtimepayWeek3 = 0;
        }
         if (hoursWeek4 > 40) {
            double overtimeWeek4 = hoursWeek4 - 40;
            overtimepayWeek4 = overtimeWeek4 * (rph * 1.25);
            weekfourRegularPay = 40 * rph;
            weekfourGP = weekfourRegularPay + overtimepayWeek4;
        } else {
            weekfourRegularPay = hoursWeek4 * rph;
            overtimepayWeek4 = 0;
        }

        regularPay = weekoneRegularPay + weektwoRegularPay + weekthreeRegularPay + weekfourRegularPay;
        overtimePay = overtimepayWeek1 + overtimepayWeek2 + overtimepayWeek3 + overtimepayWeek4;

        double weeklyTransportAllowance = 0;
        if (position.equalsIgnoreCase("manager") || position.toLowerCase().contains("manager")) {
            weeklyTransportAllowance = 5000;
        }
        transportAllowance = weeklyTransportAllowance * 4;

        double gross_pay = (regularPay + overtimePay + transportAllowance);
        return gross_pay;
    }

    public static double computeNP(double grosspayValue, double totaldeductionsValue) {
        return grosspayValue - totaldeductionsValue;
    }

    public static String getValidatedString(String askUser) {
        while (true) {
            System.out.print(askUser);   
            String input = sc.nextLine().trim();
            if (!input.isEmpty() && !input.matches("^[0-9]+$")) {
                return input;
            }
            System.out.println("Invalid input. Cannot be empty or purely numbers. Please try again.");
        }
    }

    public static double getValidatedDouble(String askUser) {
        while (true) {
            System.out.print(askUser);
            String input = sc.nextLine().trim();
            try {
                double val = Double.parseDouble(input);
                if (val >= 0) return val;
                System.out.println("Value cannot be negative. Please try again.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    public static int getValidatedInt(String askUser) {
        while (true) {
            System.out.print(askUser);
            String input = sc.nextLine().trim();
            try {
                int val = Integer.parseInt(input);
                if (val >= 0 && val <= 48) return val;
                System.out.println("Value must be between 0 and 48. Please try again.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
    }

    public static void displayPaySlip() {
        System.out.println("\n========================================");
        System.out.println("          EMPLOYEE PAY SLIP             ");
        System.out.println("========================================");
        System.out.println("Employee Name    : " + firstName + " " + middleName + " " + lastName);
        System.out.println("Department       : " + department);
        System.out.println("Position         : " + position);
        System.out.println("Total Hours      : " + totalhours);
        System.out.println("----------------------------------------");
        System.out.println("Earnings");
        System.out.printf("    Regular Pay  : ₱ %.2f\n", regularPay);
        System.out.printf("    Overtime Pay : ₱ %.2f\n", overtimePay);
        System.out.printf("    Transport    : ₱ %.2f\n", transportAllowance);
        System.out.printf("Gross Pay        : ₱ %.2f\n", grossPay);
        System.out.println("Deductions");
        System.out.printf("    SSS          : ₱ %.2f\n", sss);
        System.out.printf("    PagIBIG      : ₱ %.2f\n", pagibig);
        System.out.printf("    PhilHealth   : ₱ %.2f\n", philhealth);
        System.out.printf("    Tax          : ₱ %.2f\n", tax);
        System.out.printf("    Dependents   : ₱ %.2f\n", dependentDed);
        System.out.printf("Total Deductions : ₱ %.2f\n", totalDeductions);
        System.out.printf("Net Pay          : ₱ %.2f\n", netPay);
        System.out.println("========================================");
    }

    public static void storeRecord() {
        while (true) {
            System.out.print("\nDo you want to store this employee payroll information? (Y/N): ");
            String storeInput = sc.nextLine().trim();
            if (storeInput.length() == 1 && (storeInput.equalsIgnoreCase("y") || storeInput.equalsIgnoreCase("n"))) {
                if (storeInput.equalsIgnoreCase("y")) {
                    String record = String.format("Name: %s %s %s %n\tDept: %s %n\tPosition: %s %n\tGross: ₱ %.2f %n\tNet: ₱ %.2f", 
                                                  firstName, middleName, lastName, department, position, grossPay, netPay);
                    storedPayrolls.add(record); 
                    System.out.println("Payroll information successfully stored!");
                }
                break;
            }
            System.out.println("Invalid input. Please enter 'Y' or 'N'.");
        }
    }

    public static void displayRecord() {
        System.out.println("\n========================================");
        System.out.println("      STORED EMPLOYEE PAYROLLS          ");
        System.out.println("========================================");
        if (storedPayrolls.isEmpty()) {
            System.out.println("No payroll records stored yet.");
        } else {
            for (int i = 0; i < storedPayrolls.size(); i++) {
                System.out.println((i + 1) + ". " + storedPayrolls.get(i));
            }
        }
        System.out.println("========================================");
    }

    public static int promptMenu(boolean isSubMenu) {
        while (true) {
            System.out.println("\n----------------------------------------");
            System.out.println("[0] Exit / Terminate program");
            System.out.println("[1] Compute for another employee");
            if (!isSubMenu) {
                System.out.println("[2] Display all stored employee payrolls");
                System.out.print("Enter your choice (0, 1, or 2): ");
            } else {
                System.out.print("Enter your choice (0 or 1): ");
            }

            String menuInput = sc.nextLine().trim();
            try {
                int choice = Integer.parseInt(menuInput);
                if (!isSubMenu && (choice >= 0 && choice <= 2)) {
                    return choice;
                } else if (isSubMenu && (choice >= 0 && choice <= 1)) {
                    return choice;
                }
                System.out.println("Invalid choice. Please enter a valid option.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }
    public static void main(String[] args) {
        do {
            System.out.println("\n----------------------------------------");
            grossPay = computeGP();
            totalDeductions = computeDed(grossPay, totalhours, dependents);
            netPay = computeNP(grossPay, totalDeductions);

            displayPaySlip();
            storeRecord();

            int menuChoice = promptMenu(false);

            if (menuChoice == 2) {
                displayRecord();
                menuChoice = promptMenu(true);
            }

            if (menuChoice == 0) {
                System.out.println("\nProgram terminated. Thank you!");
                break;
            }

        } while (true);
    }
}