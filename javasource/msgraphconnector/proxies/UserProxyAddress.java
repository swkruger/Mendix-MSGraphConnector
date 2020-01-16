// This file was generated by Mendix Modeler.
//
// WARNING: Code you write here will be lost the next time you deploy the project.

package msgraphconnector.proxies;

public class UserProxyAddress
{
	private final com.mendix.systemwideinterfaces.core.IMendixObject userProxyAddressMendixObject;

	private final com.mendix.systemwideinterfaces.core.IContext context;

	/**
	 * Internal name of this entity
	 */
	public static final java.lang.String entityName = "MSGraphConnector.UserProxyAddress";

	/**
	 * Enum describing members of this entity
	 */
	public enum MemberNames
	{
		Value("Value"),
		ProxyAddress_ProxyAddresses("MSGraphConnector.ProxyAddress_ProxyAddresses");

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

	public UserProxyAddress(com.mendix.systemwideinterfaces.core.IContext context)
	{
		this(context, com.mendix.core.Core.instantiate(context, "MSGraphConnector.UserProxyAddress"));
	}

	protected UserProxyAddress(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixObject userProxyAddressMendixObject)
	{
		if (userProxyAddressMendixObject == null)
			throw new java.lang.IllegalArgumentException("The given object cannot be null.");
		if (!com.mendix.core.Core.isSubClassOf("MSGraphConnector.UserProxyAddress", userProxyAddressMendixObject.getType()))
			throw new java.lang.IllegalArgumentException("The given object is not a MSGraphConnector.UserProxyAddress");

		this.userProxyAddressMendixObject = userProxyAddressMendixObject;
		this.context = context;
	}

	/**
	 * @deprecated Use 'UserProxyAddress.load(IContext, IMendixIdentifier)' instead.
	 */
	@Deprecated
	public static msgraphconnector.proxies.UserProxyAddress initialize(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixIdentifier mendixIdentifier) throws com.mendix.core.CoreException
	{
		return msgraphconnector.proxies.UserProxyAddress.load(context, mendixIdentifier);
	}

	/**
	 * Initialize a proxy using context (recommended). This context will be used for security checking when the get- and set-methods without context parameters are called.
	 * The get- and set-methods with context parameter should be used when for instance sudo access is necessary (IContext.createSudoClone() can be used to obtain sudo access).
	 */
	public static msgraphconnector.proxies.UserProxyAddress initialize(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixObject mendixObject)
	{
		return new msgraphconnector.proxies.UserProxyAddress(context, mendixObject);
	}

	public static msgraphconnector.proxies.UserProxyAddress load(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixIdentifier mendixIdentifier) throws com.mendix.core.CoreException
	{
		com.mendix.systemwideinterfaces.core.IMendixObject mendixObject = com.mendix.core.Core.retrieveId(context, mendixIdentifier);
		return msgraphconnector.proxies.UserProxyAddress.initialize(context, mendixObject);
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
	 * @return value of Value
	 */
	public final java.lang.String getValue()
	{
		return getValue(getContext());
	}

	/**
	 * @param context
	 * @return value of Value
	 */
	public final java.lang.String getValue(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.lang.String) getMendixObject().getValue(context, MemberNames.Value.toString());
	}

	/**
	 * Set value of Value
	 * @param value
	 */
	public final void setValue(java.lang.String value)
	{
		setValue(getContext(), value);
	}

	/**
	 * Set value of Value
	 * @param context
	 * @param value
	 */
	public final void setValue(com.mendix.systemwideinterfaces.core.IContext context, java.lang.String value)
	{
		getMendixObject().setValue(context, MemberNames.Value.toString(), value);
	}

	/**
	 * @return value of ProxyAddress_ProxyAddresses
	 */
	public final msgraphconnector.proxies.UserProxyAddresses getProxyAddress_ProxyAddresses() throws com.mendix.core.CoreException
	{
		return getProxyAddress_ProxyAddresses(getContext());
	}

	/**
	 * @param context
	 * @return value of ProxyAddress_ProxyAddresses
	 */
	public final msgraphconnector.proxies.UserProxyAddresses getProxyAddress_ProxyAddresses(com.mendix.systemwideinterfaces.core.IContext context) throws com.mendix.core.CoreException
	{
		msgraphconnector.proxies.UserProxyAddresses result = null;
		com.mendix.systemwideinterfaces.core.IMendixIdentifier identifier = getMendixObject().getValue(context, MemberNames.ProxyAddress_ProxyAddresses.toString());
		if (identifier != null)
			result = msgraphconnector.proxies.UserProxyAddresses.load(context, identifier);
		return result;
	}

	/**
	 * Set value of ProxyAddress_ProxyAddresses
	 * @param proxyaddress_proxyaddresses
	 */
	public final void setProxyAddress_ProxyAddresses(msgraphconnector.proxies.UserProxyAddresses proxyaddress_proxyaddresses)
	{
		setProxyAddress_ProxyAddresses(getContext(), proxyaddress_proxyaddresses);
	}

	/**
	 * Set value of ProxyAddress_ProxyAddresses
	 * @param context
	 * @param proxyaddress_proxyaddresses
	 */
	public final void setProxyAddress_ProxyAddresses(com.mendix.systemwideinterfaces.core.IContext context, msgraphconnector.proxies.UserProxyAddresses proxyaddress_proxyaddresses)
	{
		if (proxyaddress_proxyaddresses == null)
			getMendixObject().setValue(context, MemberNames.ProxyAddress_ProxyAddresses.toString(), null);
		else
			getMendixObject().setValue(context, MemberNames.ProxyAddress_ProxyAddresses.toString(), proxyaddress_proxyaddresses.getMendixObject().getId());
	}

	/**
	 * @return the IMendixObject instance of this proxy for use in the Core interface.
	 */
	public final com.mendix.systemwideinterfaces.core.IMendixObject getMendixObject()
	{
		return userProxyAddressMendixObject;
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
			final msgraphconnector.proxies.UserProxyAddress that = (msgraphconnector.proxies.UserProxyAddress) obj;
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
		return "MSGraphConnector.UserProxyAddress";
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
