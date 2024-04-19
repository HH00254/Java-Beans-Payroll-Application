import java.text.DecimalFormat;

/**
 * This class extends Employee, stores shift, rate of pay and hours. It has accessor methods
 *	for each variable, a calcPay method for calculating shift bonus, and a toString method
 *	for summary information.
 *
 *
 */
public class ProductionWorker extends Employee
{
	private int shift;
	private double rateOfPay;
	private double hours;

	/**
	 * This constructor accepts 5 arguments from main and creates an employee object.
	 *
	 *
     */
	public ProductionWorker(String employeeName, int employeeID, int shift, double rateOfPay, double hours)
	{
		super(employeeName, employeeID);

		this.setShift(shift);
		this.shift = this.getShift();

		this.rateOfPay = rateOfPay;
		this.hours = hours;
	}

	/**
	 * This accessor returns the employees shift code.
	 *
	 *
     */
	public int getShift()
	{
		return this.shift;
	}

	/**
	 * This accessor returns the employees rate of pay.
	 *
	 *
     */
	public double getRateOfPay()
	{
		return this.rateOfPay;
	}

	public double getHours()
	{
		return this.hours;
	}

	/**
	 * This mutator sets the employees shift code.
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
	 * This mutator sets the employees rate of pay.
	 *
	 *
     */
	public void setRateOfPay(double rateOfPay)
	{
		this.rateOfPay = rateOfPay;
	}

	/**
	 * This mutator sets the employees hours worked.
	 *
	 *
     */
	public void setHours(double hours)
	{
		this.hours = hours;
	}


	/**
	 * This method prepares a string object to summarize the employees information. It calls the
	 * employees toString method to fill in the name and ID.
	 *
	 *
     */
	public String toString()
	{
		DecimalFormat constuctor = new DecimalFormat("###,##0.00");

		String template = "Employee ID: %d Name: %s%nProductionWorker - Shift: %d Hours: %.2f ROP: %.2f %n%-19sPay: %s";

		return String.format(template,
							 this.getEmployeeID(),
							 this.getEmployeeName(),
							 this.shift,
							 this.hours,
							 this.rateOfPay,
							 "",
							 constuctor.format(this.calculatePay())
		);
	}

	/**
	 * This method dynamically calculates the current employees
	 *rate of pay, taking into account the 50% shift premium.
	 *
	 *
     */
	public double calculatePay()
	{
		return this.getShift() == 1 ?
			this.getHours() * this.getRateOfPay() : (this.getHours() * this.getRateOfPay() * 1.5)
	    ;
	}
}