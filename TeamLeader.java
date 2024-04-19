import java.text.DecimalFormat;

/**
 * This class extends ProductionWorker, it stores a monthly bonus, employee ID, and an employee name.
 * It has a pay calculation method and a toString for summary data.
 *
 *
 */
public class TeamLeader extends ProductionWorker
{
	private double bonus;


	/**
	 * This constructor accepts 6 arguments from the main method. It creates a production worker object and
	 * sets local variables for summary purposes.
	 *
	 *
	 */
	public TeamLeader(String employeeName, int employeeID, int shift, double rateOfPay, double hoursWorked, double monthlyBonus)
	{
		super(employeeName, employeeID, shift, rateOfPay, hoursWorked);

		this.setMonthlyBonus(monthlyBonus);
		this.bonus = this.getMonthlyBonus();
	}


	/**
	 * Returns the bonus.
	 *
	 *
	 */
	 public double getMonthlyBonus()
	 {
		 return this.bonus;
	 }

 	/**
	 * Sets the monthly bonus for this subclass.
	 *
	 *
	 */
	 public void setMonthlyBonus(double monthlyBonus)
	 {
		 this.bonus = monthlyBonus > -1 ? monthlyBonus : 0.00;
	 }

	 /**
	 * This toString method dynamically creates a string object containing all necessary summary information
	 * and returns it to main.
	 *
	 *
	 */
	public String toString()
	{
		DecimalFormat constuctor = new DecimalFormat("###,##0.00");

		String template =
			"Employee ID: %d Name: %s%nProductionWorker - Shift: %d Hours: %.2f ROP: %.2f Monthly Bonus: %s%n%-19sPay: %s";

		return String.format(template,
							 this.getEmployeeID(),
							 this.getEmployeeName(),
							 this.getShift(),
							 this.getHours(),
							 this.getRateOfPay(),
							 constuctor.format(this.bonus),
							 "",
							 constuctor.format(this.calculatePay())
		);
	}

	/**
	 * This method dynamically calculates the current employees' pay. It does so by calling the super class'
	 * calcPay method and adding a monthly bonus.
	 *
	 *
	 */
	public double calculatePay()
	{
		return super.calculatePay() + this.getMonthlyBonus();
	}
}