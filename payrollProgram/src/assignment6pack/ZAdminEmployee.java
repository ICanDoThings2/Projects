package assignment6pack;

/*
 * Admin Employee, paid a flat rate per month
 * @author Hunter Blake
 * @since 1.0
 */

public class ZAdminEmployee extends ZEmployee {
	
	/*
	 * Constructor, takes no arguments.
	 */

	public ZAdminEmployee()
	{
		
	}


	private ZAdminEmployType thisAdminType;
	
	/*
	 * Sets the type of admin employee
	 * @param asEmployType	The admin employee type, Secretary or technician.
	 */
	
	public void ZSetThisAdminType( ZAdminEmployType asEmployType )
	{
		thisAdminType = asEmployType;
	}
	
	/*
	 * Returns the admin employee type.
	 */
	
	public ZAdminEmployType getZAdminEmployType()
	{
		return thisAdminType;
	}
	
	/*
	 * Does same as the parent class, but adds base salary
	 * @param currentMonth	The month they are receiving payroll
	 */
	
	@Override
	public float ZGetPayroll( int currentMonth )
	{
		return super.ZGetPayroll(currentMonth) + ZGetBaseSalary();
	}
	
}
