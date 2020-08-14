package assignment6pack;

/*
 * This is the full time professor class
 * Three types of full time professors exist, which are defined as enums in the parent class.
 * @author	Hunter Blake
 * @since 1.0
 */

public class ZFullTimeProfessor extends ZEmployee {
	
	/*
	 * Constructor, takes no arguments.
	 */
	
	public ZFullTimeProfessor()
	{
		
	}

	private ZFullTimeProfType thisProfType;
	
	/*
	 * Sets the type of full time professor
	 * @param asProfType	The type of full time professor.
	 */
	
	public void ZEmployeeSetProfType( ZFullTimeProfType asProfType )
	{
		thisProfType = asProfType;
	}
	
	/*
	 * Returns the type of full time professor
	 */
	
	public ZFullTimeProfType ourProfType()
	{
		return thisProfType;
	}
	
	/*
	 * Does the same as parent class, but also adds the current salary.
	 * @param currentMonth	The current month they are receiving their payroll
	 */

	
	@Override
	public float ZGetPayroll( int currentMonth )
	{
		return super.ZGetPayroll(currentMonth) + ZGetBaseSalary();
	}
	
	
}
