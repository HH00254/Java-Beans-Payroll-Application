import java.util.Collections;

/**
 * This abstract class stores a name and employee ID. It has a
 * compareTo method for sorting, an abstract calcPay method that
 * expects to be overridden, and a toString method for displaying
 * summary information. This class implements Comparable.
 *
 *
 */
public abstract class Employee implements Comparable <Employee>
{
	private String employeeName;
	private int employeeID;

	/**
	 * This constructor accepts 2 arguments from other classes.
	 *
	 *
     */
	public Employee(String employeeName, int employeeID)
	{
		this.employeeName = employeeName;
		this.employeeID = employeeID;
	}

	/**
	 * This accessor returns the integer employee ID.
	 *
	 *
	 */
	public int getEmployeeID()
	{
		return this.employeeID;
	}

	/**
	 * This accessor returns the integer employee Name.
	 *
	 *
	 */
	public String getEmployeeName()
	{
		return this.employeeName;
	}

	/**
	 * This method creates and returns a string object to summarize
	 * employee information.
	 *
	 *
	 */
	public String toString()
	{
		String template = "Employee ID: %d Name: %s%n";

		return String.format(template, this.employeeID, this.employeeName);
	}

	/**
	 * This method is implemented for sorting purposes within
	 * the ArrayList in main.
	 *
	 *
	 */
	public int compareTo(Employee object)
	{
		int returnValue;

		if(this.employeeID > object.getEmployeeID())
		{
			returnValue = 1;
		}
		else if(this.employeeID == object.getEmployeeID())
		{
			returnValue = 0;
		}
		else
		{

			returnValue = -1;
		}
		return returnValue;
	}

	/**
	 * This abstract calcPay method expects to be overridden.
	 *
	 */
	public abstract double calculatePay();
}