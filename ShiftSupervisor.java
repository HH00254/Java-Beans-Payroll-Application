import java.text.DecimalFormat;

/**
 * This class extends Employee, it stores a shift, a salary and a bonus. It also calculates the current
 * employees monthly pay with the calcPay method, and creates a String object to return for summary
 * purposes in the toString method.
 *
 *
 */
public class ShiftSupervisor extends Employee
{
	private int shift;
	private double salary;
	private double annualBonus;

	/**
	 * This constructor accepts 5 arguments, creates an employee object, and sets local variables
	 * appropriately.
	 *
	 *
     */
	public ShiftSupervisor(String employeeName, int employeeID, int shift, double salary, double annualBonus)
	{
		super(employeeName, employeeID);

		this.setShift(shift);
		this.shift = this.getShift();

		this.setSalary(salary);
		this.salary = this.getSalary();

		this.setAnnualBonus(annualBonus);
		this.annualBonus = this.getAnnualBonus();
	}

	/**
	 *
	 *
	 *
     */
	public int getShift()
	{
		return this.shift;
	}

	/**
	 *
	 *
	 *
     */
     public double getSalary()
     {
		 return this.salary;
	 }

	/**
	 *
	 *
	 *
     */
     public double getAnnualBonus()
     {
		 return this.annualBonus;
	 }

	/**
	 *
	 *
	 *
	 */
	public void setShift(int shift)
	{
		if(shift == 1 || shift == 2)
		{
			this.shift = shift;
		}
		else
		{
			this.shift = 0;
		}
	}


	/**
	 *
	 *
	 *
	 */
	public void setSalary(double salary)
	{
		this.salary = salary > -1 ? salary : 0.00;
	}

	/**
	 *
	 *
	 *
	 */
	 public void setAnnualBonus(double annualBonus)
	 {
		 this.annualBonus = annualBonus > -1 ? annualBonus : 0.00;
	 }

	 /**
	 * This method creates a String object for summary purposes, it calls Employees' toString for
	 * the name and employee ID, and does the rest from local variables.
	 *
	 *
	 */
	public String toString()
	{
		DecimalFormat constuctor = new DecimalFormat("###,##0.00");

		String template =
			"Employee ID: %d Name: %s%nShiftSupervisor - Shift: %d Annual Salary: %s Annual Bonus: %s%n%-19sPay: %s";

		return String.format(template,
							 this.getEmployeeID(),
							 this.getEmployeeName(),
							 this.shift,
							 constuctor.format(this.getSalary()),
						 	 constuctor.format(this.getAnnualBonus()),
						 	 "",
						 	 constuctor.format(this.calculatePay())
		);
	}

	/**
	 * This method dynamically calculates the current supervisors monthly pay by dividing monthly
	 * numbers by 12.
	 *
	 *
	 */
	public double calculatePay()
	{
		return (this.getSalary() / 12) + (this.getAnnualBonus() / 12);
	}

}