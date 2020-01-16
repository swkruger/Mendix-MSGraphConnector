// This file was generated by Mendix Modeler.
//
// WARNING: Code you write here will be lost the next time you deploy the project.

package msgraphconnector.proxies;

public class UserAssignedLicenses
{
	private final com.mendix.systemwideinterfaces.core.IMendixObject userAssignedLicensesMendixObject;

	private final com.mendix.systemwideinterfaces.core.IContext context;

	/**
	 * Internal name of this entity
	 */
	public static final java.lang.String entityName = "MSGraphConnector.UserAssignedLicenses";

	/**
	 * Enum describing members of this entity
	 */
	public enum MemberNames
	{
		SKUId("SKUId"),
		AssignedLicenses_User("MSGraphConnector.AssignedLicenses_User"),
		DisabledPlans_AssignedLicenses("MSGraphConnector.DisabledPlans_AssignedLicenses");

		private java.lang.String metaName;

		MemberNames(java.lang.String s)
		{
			metaName = s;
		}

		@Override
		public java.lang.String toString()
		{
			return metaName;
		}
	}

	public UserAssignedLicenses(com.mendix.systemwideinterfaces.core.IContext context)
	{
		this(context, com.mendix.core.Core.instantiate(context, "MSGraphConnector.UserAssignedLicenses"));
	}

	protected UserAssignedLicenses(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixObject userAssignedLicensesMendixObject)
	{
		if (userAssignedLicensesMendixObject == null)
			throw new java.lang.IllegalArgumentException("The given object cannot be null.");
		if (!com.mendix.core.Core.isSubClassOf("MSGraphConnector.UserAssignedLicenses", userAssignedLicensesMendixObject.getType()))
			throw new java.lang.IllegalArgumentException("The given object is not a MSGraphConnector.UserAssignedLicenses");

		this.userAssignedLicensesMendixObject = userAssignedLicensesMendixObject;
		this.context = context;
	}

	/**
	 * @deprecated Use 'UserAssignedLicenses.load(IContext, IMendixIdentifier)' instead.
	 */
	@Deprecated
	public static msgraphconnector.proxies.UserAssignedLicenses initialize(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixIdentifier mendixIdentifier) throws com.mendix.core.CoreException
	{
		return msgraphconnector.proxies.UserAssignedLicenses.load(context, mendixIdentifier);
	}

	/**
	 * Initialize a proxy using context (recommended). This context will be used for security checking when the get- and set-methods without context parameters are called.
	 * The get- and set-methods with context parameter should be used when for instance sudo access is necessary (IContext.createSudoClone() can be used to obtain sudo access).
	 */
	public static msgraphconnector.proxies.UserAssignedLicenses initialize(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixObject mendixObject)
	{
		return new msgraphconnector.proxies.UserAssignedLicenses(context, mendixObject);
	}

	public static msgraphconnector.proxies.UserAssignedLicenses load(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixIdentifier mendixIdentifier) throws com.mendix.core.CoreException
	{
		com.mendix.systemwideinterfaces.core.IMendixObject mendixObject = com.mendix.core.Core.retrieveId(context, mendixIdentifier);
		return msgraphconnector.proxies.UserAssignedLicenses.initialize(context, mendixObject);
	}

	/**
	 * Commit the changes made on this proxy object.
	 */
	public final void commit() throws com.mendix.core.CoreException
	{
		com.mendix.core.Core.commit(context, getMendixObject());
	}

	/**
	 * Commit the changes made on this proxy object using the specified context.
	 */
	public final void commit(com.mendix.systemwideinterfaces.core.IContext context) throws com.mendix.core.CoreException
	{
		com.mendix.core.Core.commit(context, getMendixObject());
	}

	/**
	 * Delete the object.
	 */
	public final void delete()
	{
		com.mendix.core.Core.delete(context, getMendixObject());
	}

	/**
	 * Delete the object using the specified context.
	 */
	public final void delete(com.mendix.systemwideinterfaces.core.IContext context)
	{
		com.mendix.core.Core.delete(context, getMendixObject());
	}
	/**
	 * @return value of SKUId
	 */
	public final java.lang.String getSKUId()
	{
		return getSKUId(getContext());
	}

	/**
	 * @param context
	 * @return value of SKUId
	 */
	public final java.lang.String getSKUId(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.lang.String) getMendixObject().getValue(context, MemberNames.SKUId.toString());
	}

	/**
	 * Set value of SKUId
	 * @param skuid
	 */
	public final void setSKUId(java.lang.String skuid)
	{
		setSKUId(getContext(), skuid);
	}

	/**
	 * Set value of SKUId
	 * @param context
	 * @param skuid
	 */
	public final void setSKUId(com.mendix.systemwideinterfaces.core.IContext context, java.lang.String skuid)
	{
		getMendixObject().setValue(context, MemberNames.SKUId.toString(), skuid);
	}

	/**
	 * @return value of AssignedLicenses_User
	 */
	public final msgraphconnector.proxies.User getAssignedLicenses_User() throws com.mendix.core.CoreException
	{
		return getAssignedLicenses_User(getContext());
	}

	/**
	 * @param context
	 * @return value of AssignedLicenses_User
	 */
	public final msgraphconnector.proxies.User getAssignedLicenses_User(com.mendix.systemwideinterfaces.core.IContext context) throws com.mendix.core.CoreException
	{
		msgraphconnector.proxies.User result = null;
		com.mendix.systemwideinterfaces.core.IMendixIdentifier identifier = getMendixObject().getValue(context, MemberNames.AssignedLicenses_User.toString());
		if (identifier != null)
			result = msgraphconnector.proxies.User.load(context, identifier);
		return result;
	}

	/**
	 * Set value of AssignedLicenses_User
	 * @param assignedlicenses_user
	 */
	public final void setAssignedLicenses_User(msgraphconnector.proxies.User assignedlicenses_user)
	{
		setAssignedLicenses_User(getContext(), assignedlicenses_user);
	}

	/**
	 * Set value of AssignedLicenses_User
	 * @param context
	 * @param assignedlicenses_user
	 */
	public final void setAssignedLicenses_User(com.mendix.systemwideinterfaces.core.IContext context, msgraphconnector.proxies.User assignedlicenses_user)
	{
		if (assignedlicenses_user == null)
			getMendixObject().setValue(context, MemberNames.AssignedLicenses_User.toString(), null);
		else
			getMendixObject().setValue(context, MemberNames.AssignedLicenses_User.toString(), assignedlicenses_user.getMendixObject().getId());
	}

	/**
	 * @return value of DisabledPlans_AssignedLicenses
	 */
	public final msgraphconnector.proxies.UserDisabledPlans getDisabledPlans_AssignedLicenses() throws com.mendix.core.CoreException
	{
		return getDisabledPlans_AssignedLicenses(getContext());
	}

	/**
	 * @param context
	 * @return value of DisabledPlans_AssignedLicenses
	 */
	public final msgraphconnector.proxies.UserDisabledPlans getDisabledPlans_AssignedLicenses(com.mendix.systemwideinterfaces.core.IContext context) throws com.mendix.core.CoreException
	{
		msgraphconnector.proxies.UserDisabledPlans result = null;
		com.mendix.systemwideinterfaces.core.IMendixIdentifier identifier = getMendixObject().getValue(context, MemberNames.DisabledPlans_AssignedLicenses.toString());
		if (identifier != null)
			result = msgraphconnector.proxies.UserDisabledPlans.load(context, identifier);
		return result;
	}

	/**
	 * Set value of DisabledPlans_AssignedLicenses
	 * @param disabledplans_assignedlicenses
	 */
	public final void setDisabledPlans_AssignedLicenses(msgraphconnector.proxies.UserDisabledPlans disabledplans_assignedlicenses)
	{
		setDisabledPlans_AssignedLicenses(getContext(), disabledplans_assignedlicenses);
	}

	/**
	 * Set value of DisabledPlans_AssignedLicenses
	 * @param context
	 * @param disabledplans_assignedlicenses
	 */
	public final void setDisabledPlans_AssignedLicenses(com.mendix.systemwideinterfaces.core.IContext context, msgraphconnector.proxies.UserDisabledPlans disabledplans_assignedlicenses)
	{
		if (disabledplans_assignedlicenses == null)
			getMendixObject().setValue(context, MemberNames.DisabledPlans_AssignedLicenses.toString(), null);
		else
			getMendixObject().setValue(context, MemberNames.DisabledPlans_AssignedLicenses.toString(), disabledplans_assignedlicenses.getMendixObject().getId());
	}

	/**
	 * @return the IMendixObject instance of this proxy for use in the Core interface.
	 */
	public final com.mendix.systemwideinterfaces.core.IMendixObject getMendixObject()
	{
		return userAssignedLicensesMendixObject;
	}

	/**
	 * @return the IContext instance of this proxy, or null if no IContext instance was specified at initialization.
	 */
	public final com.mendix.systemwideinterfaces.core.IContext getContext()
	{
		return context;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj == this)
			return true;

		if (obj != null && getClass().equals(obj.getClass()))
		{
			final msgraphconnector.proxies.UserAssignedLicenses that = (msgraphconnector.proxies.UserAssignedLicenses) obj;
			return getMendixObject().equals(that.getMendixObject());
		}
		return false;
	}

	@Override
	public int hashCode()
	{
		return getMendixObject().hashCode();
	}

	/**
	 * @return String name of this class
	 */
	public static java.lang.String getType()
	{
		return "MSGraphConnector.UserAssignedLicenses";
	}

	/**
	 * @return String GUID from this object, format: ID_0000000000
	 * @deprecated Use getMendixObject().getId().toLong() to get a unique identifier for this object.
	 */
	@Deprecated
	public java.lang.String getGUID()
	{
		return "ID_" + getMendixObject().getId().toLong();
	}
}
