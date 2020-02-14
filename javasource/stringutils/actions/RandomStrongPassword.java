// This file was generated by Mendix Modeler.
//
// WARNING: Only the following code will be retained when actions are regenerated:
// - the import list
// - the code between BEGIN USER CODE and END USER CODE
// - the code between BEGIN EXTRA CODE and END EXTRA CODE
// Other code you write will be lost the next time you deploy the project.
// Special characters, e.g., é, ö, à, etc. are supported in comments.

package stringutils.actions;

import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.webui.CustomJavaAction;
import stringutils.StringUtils;

/**
 * Returns a random strong password containing a specified number of digits, uppercase and special characters.
 * 
 * Note:Minimumlength should be equal or larger than NrOfCapitalizedCharacters, NrOfDigits and NrOfSpecialCharacters
 */
public class RandomStrongPassword extends CustomJavaAction<java.lang.String>
{
	private java.lang.Long MinLength;
	private java.lang.Long MaxLength;
	private java.lang.Long NrOfCapitalizedCharacters;
	private java.lang.Long NrOfDigits;
	private java.lang.Long NrOfSpecialCharacters;

	public RandomStrongPassword(IContext context, java.lang.Long MinLength, java.lang.Long MaxLength, java.lang.Long NrOfCapitalizedCharacters, java.lang.Long NrOfDigits, java.lang.Long NrOfSpecialCharacters)
	{
		super(context);
		this.MinLength = MinLength;
		this.MaxLength = MaxLength;
		this.NrOfCapitalizedCharacters = NrOfCapitalizedCharacters;
		this.NrOfDigits = NrOfDigits;
		this.NrOfSpecialCharacters = NrOfSpecialCharacters;
	}

	@java.lang.Override
	public java.lang.String executeAction() throws Exception
	{
		// BEGIN USER CODE
		return StringUtils.randomStrongPassword(safeLongToInt(this.MinLength), safeLongToInt(this.MaxLength),safeLongToInt(this.NrOfCapitalizedCharacters), safeLongToInt(NrOfDigits), safeLongToInt(NrOfSpecialCharacters));
		// END USER CODE
	}

	/**
	 * Returns a string representation of this action
	 */
	@java.lang.Override
	public java.lang.String toString()
	{
		return "RandomStrongPassword";
	}

	// BEGIN EXTRA CODE
	public static int safeLongToInt(long l) {
	    if (l < Integer.MIN_VALUE || l > Integer.MAX_VALUE) {
	        throw new IllegalArgumentException
	            (l + " cannot be cast to int without changing its value.");
	    }
	    return (int) l;
	}
	// END EXTRA CODE
}
