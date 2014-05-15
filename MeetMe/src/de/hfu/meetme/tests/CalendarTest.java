/**
 * 
 */
package de.hfu.meetme.tests;

import java.util.Calendar;

import org.junit.Test;

/**
 * @author Simeon Sembach
 *
 */
public class CalendarTest
{

	@Test
	public void test()
	{
		Calendar theBirthdayDate = Calendar.getInstance();
		theBirthdayDate.set(2014-13, Calendar.MAY, 13, 0, 0, 0);
		
		Calendar theTodayDate = Calendar.getInstance();
			
		int theDeltaYears  = theTodayDate.get(Calendar.YEAR) - theBirthdayDate.get(Calendar.YEAR);
		int theDeltaMonths = theTodayDate.get(Calendar.MONTH) - theBirthdayDate.get(Calendar.MONTH);
		int theDeltaDays   = theTodayDate.get(Calendar.DAY_OF_MONTH) - theBirthdayDate.get(Calendar.DAY_OF_MONTH);
		
		System.out.println("Delta Years: "+theDeltaYears);
		System.out.println("Delta Months: "+theDeltaMonths);
		System.out.println("Delta Days: "+theDeltaDays);
		
		if (theDeltaYears > 12 || (theDeltaYears == 12 && (theDeltaMonths < 0 || (theDeltaMonths == 0 && theDeltaDays <= 0))))
		{
			System.out.println("Alt genug");	
		}
		
		if (theDeltaYears < 12 || (theDeltaYears == 12 && (theDeltaMonths >= 0 && (theDeltaMonths != 0 || theDeltaDays > 0))))
		{
			System.out.println("Zu jung");
		}
		
		
	}
	
	public void printDate(Calendar aCalendar)
	{
		System.out.println(aCalendar.getTime());
	}
	
}
