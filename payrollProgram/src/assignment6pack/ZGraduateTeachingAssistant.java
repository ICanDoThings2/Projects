package assignment6pack;

/*
 * Graduate Teaching Assistant, who gets paid based on hours with overtime if they work more than eighty hours.
 * @author Hunter Blake
 * @since 1.0
 */

public class ZGraduateTeachingAssistant extends ZEmployee {
	
	/*
	 * Constructor, takes no arguments.
	 */
	
	public ZGraduateTeachingAssistant()
	{
		
	}
	
	private float overtimePay;
	private int hoursWorked;
	
	/*
	 * Returns the amount of hours worked.
	 */
	
	public float ZGetHoursWorked()
	{
		return hoursWorked;
	}
	
	/*
	 * Sets the amount of hours the TGA has worked
	 * @param asHours	An integer, 0 or higher
	 */
	
	public void ZSetHoursWorked( int asHours )
	{
		if ( asHours < 0 )
		{
			return;
		}
		
		hoursWorked = asHours;
	}
	
	/*
	 * Gets overtime pay, per hour
	 */
	
	public float ZGetOvertimePay()
	{
		return overtimePay;
	}
	
	/*
	 * Sets overtime pay, per hour. 0 or higher
	 * @param asPay	Payment per hour, for every hour more than 80
	 */
	
	public void ZSetOvertimePay( float asPay )
	{
		if ( asPay < 0 )
		{
			return;
		}
		
		overtimePay = asPay;
	}
	
	/*
	 * Does the same as parent class, but adds base salary times hours < 80 worked
	 * and for every extra hour the previous added to the amount of extra hours
	 * times ZGetOvertimePay()
	 * @param currentMonth	The current month they are receiving their payroll
	 */
	
	@Override 
	public float ZGetPayroll( int currentMonth )
	{
		float overtimeBonus = 0 ;
		
		if ( ZGetHoursWorked() > 80 )
		{
			overtimeBonus = ZGetOvertimePay() * ( ZGetHoursWorked() - 80 ) ;
			return super.ZGetPayroll(currentMonth) + ( 80 * ZGetBaseSalary() ) + overtimeBonus;
		}
		
		
		return super.ZGetPayroll(currentMonth) + ( ZGetHoursWorked() * ZGetBaseSalary() );
	}

}
