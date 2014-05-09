/**
 * 
 */
package de.hfu.meetme.utilities;

/**
 * @author Simeon Sembach
 *
 */
public final class MMValidationUtil
{

	public static MMValidation isValidNickName(String aNickName)
	{
		if (aNickName == null)
			return new MMValidation("String is null");	
		if (aNickName.equals(""))
			return new MMValidation("String is empty");
		if (aNickName.length() < 3)
			return new MMValidation("String is smaller than 3");
		
		return new MMValidation();
	}
	
}
