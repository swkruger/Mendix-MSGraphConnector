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
 * Decrypts an AES encrypted string.
 * The keylength should exactly be 16 characters (128 bit).
 */
public class DecryptString extends CustomJavaAction<java.lang.String>
{
	private java.lang.String valueToDecrypt;
	private java.lang.String key;

	public DecryptString(IContext context, java.lang.String valueToDecrypt, java.lang.String key)
	{
		super(context);
		this.valueToDecrypt = valueToDecrypt;
		this.key = key;
	}

	@java.lang.Override
	public java.lang.String executeAction() throws Exception
	{
		// BEGIN USER CODE
		return StringUtils.decryptString(key, valueToDecrypt);
		// END USER CODE
	}

	/**
	 * Returns a string representation of this action
	 */
	@java.lang.Override
	public java.lang.String toString()
	{
		return "DecryptString";
	}

	// BEGIN EXTRA CODE
	// END EXTRA CODE
}
