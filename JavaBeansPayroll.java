import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
import java.util.Arrays;
import java.text.DecimalFormat;

/**
 * The main method delcares variables, prompts for and reads employee info, and
 * displays a summary at the end of calculations. It does this by storying
 * employee objects in an ArrayList.
 *
 *
 */
public class JavaBeansPayroll
{
	public static void main(String[] args)
	{
		// Declaring objects
		Employee employees;
		Scanner keyboard = new Scanner(System.in);
		ArrayList<Employee> employeeList = new ArrayList<Employee>();

		 // Scanner object for input  // Scanner object for input
		DecimalFormat dollar = new DecimalFormat("#,##0.00");

		// Local variables
		char yesOrNo = ' ';

		int employeeID = 0,
			shift = 0,
			employeeType = 0;

		String employeeName = "";

		double annualSalary = 0.00,
			   annualBonus = 0.00;

		/*
		ProductionWorker Addresses:
		Address 0 - Hours.
		Address 1 - Rate of pay.

		TeamLeader Addresses:
		Address 4 - Hours.
		Address 5 - Rate of pay.
		Address 6 - bonus.

		ShiftSupervisor Addresses:
		Address 2 - Salary.
		Address 3 - Annual bonus.

		Total Pay of All Employees:
		Address 7 - Total pay
		*/
		double[] employeeDetails = new double[8];

		/*
		Array for tracking # of each type of employee made:
		Address 0 - ProductionWorker
		Address 1 - ShiftSupervisor
		Address 2 - TeamLeader
		Address 3 - Total number of employees.
		*/
		int[] employeeCount = new int[4];


		// Interface Header
		System.out.printf(String.format
			("%-18sJavaBeans Payroll Application%n%n%-18sProgrammed By: Al Hochbaum%n%n",
			 "",
			 "")
		);

		do
		{
			// Prompt and read employee ID
			employeeID = getValidID("\nEmployee ID: ");

			// Prompt and read name
			System.out.print("Employee Name: ");
			employeeName = keyboard.nextLine();

			// Prompt and read shift
			shift = getValidShiftCode("Shift (1 or 2): ");

			employeeType = getValidType("Employee type (1, 2 or 3): ");

			if(employeeType == 1 || employeeType == 3)
			{
				System.out.print("\nHours worked: ");
				employeeDetails[employeeType == 1 ? 0 : 4] = Double.parseDouble(keyboard.nextLine());

				System.out.print("Rate of Pay: ");
				employeeDetails[employeeType == 1 ? 1 : 5] = Double.parseDouble(keyboard.nextLine());

				// Instantiating one of two subclasses of type Employee.
				if(employeeType == 1)
				{
					// Production worker object
					employees =	new ProductionWorker(employeeName,
													 employeeID,
													 shift,
													 employeeDetails[1],
													 employeeDetails[0]
					);
				}
				else
				{
					System.out.print("Monthly Bonus: ");
					employeeDetails[6] = Double.parseDouble(keyboard.nextLine());

					// Team leader object
					employees = new TeamLeader(employeeName,
					                           employeeID,
					                           shift,
					                           employeeDetails[5],
					                           employeeDetails[4],
					                           employeeDetails[6]
					);
				}

			}
			else
			{
				System.out.print("\nAnnual Salary: ");
				employeeDetails[2] = Double.parseDouble(keyboard.nextLine());

				System.out.print("Annual Bonus: ");
				employeeDetails[3] = Double.parseDouble(keyboard.nextLine());

				// Supervisor object
				employees = new ShiftSupervisor(employeeName,
												employeeID,
												shift,
												employeeDetails[2],
												employeeDetails[3]
				);

			}

			// Adding an Employee type object to the ArrayList.
			employeeList.add(employees);

			yesOrNo = addEmployee("\nEnter new employee (Y/N)?: ");

		} while(yesOrNo == 'y');

		// Sorting employeeList
		Collections.sort(employeeList);

		// Printing each element in the ArrayList.
		for(Employee object : employeeList)
		{
			// Adding each employees monthly calculated pay together.
			employeeDetails[7] += object.calculatePay();

			// Check which subclass of Employee is contained in the array list, update counters.
			if(object instanceof ShiftSupervisor)
			{
				employeeCount[1]++;

			}
			else if(object instanceof TeamLeader)
			{
				employeeCount[2]++;

			}
		    else if(object instanceof ProductionWorker)
			{
				employeeCount[0]++;
			}

			// Print summary
			System.out.println(object.toString() + "\n");

			// Increment employee counter
			employeeCount[3]++;
		}

		// Print out summary information
		System.out.println("Total Employees: " + employeeCount[3]);
		System.out.println("\tProduction Worker: " + employeeCount[0]);
		System.out.println("\tShift Supervisor: " + employeeCount[1]);
		System.out.println("\tTeam Leader: " + employeeCount[2]);
		System.out.println("Total pay ALL Employees: " + dollar.format(employeeDetails[7]));

	}

	 /**
	 * Making sure the ID only containts integers
	 *
	 *
	 */
	 public static int getValidID(String prompt)
	 {
		// New instance of type Scanner.
		Scanner keyboard = new Scanner(System.in);

		 int ID = 0,
		 	 counter = 0;

		 String invalidOutCome = "",
		 		IDEntered = "";

		 do
		{

			System.out.printf("%s%s", invalidOutCome, prompt);
			IDEntered = keyboard.nextLine();

			// Resting
			invalidOutCome = "";
			counter = 0;

			for(int i = 0; invalidOutCome.equals("") && i < IDEntered.length(); i++)
			{
				if(Character.isDigit(IDEntered.charAt(i)))
				{
					counter++;
				}
				else
				{
					// Secondary prompt if user failed to enter the right type of ID.
					invalidOutCome = "\nInvalid input.\n";
				}
			}
		} while(counter != IDEntered.length());

		ID = Integer.parseInt(IDEntered);

		return ID;
	 }

	 /**
	 * Making sure the shift code entered is either 1 or 2.
	 *
	 *
	 */
	 public static int getValidShiftCode(String prompt)
	 {
		 // New instance of type Scanner.
		Scanner keyboard = new Scanner(System.in);

		 int shift = 0;
		 String invalidOutCome = "";

		do
		{
			System.out.printf("%s%s", invalidOutCome, prompt);
			shift = Integer.parseInt(keyboard.nextLine());

			if(shift < 1 || shift > 2)
			{
				invalidOutCome = "\nInvalid input.\n\n";
			}
		} while(shift < 1 || shift > 2);

		return shift;
	}

	 /**
	 * Checking for a valid employee class type.
	 *
	 *
	 */
	public static int getValidType(String prompt)
	{
		// New instance of type Scanner.
		Scanner keyboard = new Scanner(System.in);

		String invalidOutCome = "";
		int classOfWorker;

		do
		{
			System.out.printf("%s%s", invalidOutCome, prompt);
			classOfWorker = Integer.parseInt(keyboard.nextLine());

			if(classOfWorker < 1 || classOfWorker > 3)
			{
				invalidOutCome = "\nInvalid input.\n\n";
			}
		} while(classOfWorker < 1 || classOfWorker > 3);

		return classOfWorker;
	}

	/**
	 *
	 *
	 *
	 */
	public static char addEmployee(String prompt)
	{
		// New instance of type Scanner.
		Scanner keyboard = new Scanner(System.in);

		char validStatement = ' ';
		String yesOrNo = "",
			   invalidOutCome = "";

		do
		{

			System.out.printf("%s%s", invalidOutCome, prompt);
			yesOrNo = keyboard.nextLine();

			if(Character.isLetter(yesOrNo.toLowerCase().charAt(0)) && yesOrNo.length() == 1)
			{
				switch(yesOrNo.toLowerCase().charAt(0))
				{
					case 'y':
						validStatement = 'y';
						break;
					case 'n':
						validStatement = 'n';
						break;
				}
		    }
		    else
		    {
				invalidOutCome = "\nInvalid input.\n\n";
			}

		} while(validStatement == ' ');

		return validStatement;
	}
}