// This file was generated by Mendix Modeler.
//
// WARNING: Code you write here will be lost the next time you deploy the project.

package profileservice.proxies;

public class UserProfile
{
	private final com.mendix.systemwideinterfaces.core.IMendixObject userProfileMendixObject;

	private final com.mendix.systemwideinterfaces.core.IContext context;

	/**
	 * Internal name of this entity
	 */
	public static final java.lang.String entityName = "ProfileService.UserProfile";

	/**
	 * Enum describing members of this entity
	 */
	public enum MemberNames
	{
		OpenId("OpenId"),
		DisplayName("DisplayName"),
		EmailAddress("EmailAddress"),
		AvatarUrl("AvatarUrl"),
		AvatarThumbnailUrl("AvatarThumbnailUrl"),
		JobTitle("JobTitle"),
		Department("Department"),
		Location("Location"),
		Country("Country"),
		Bio("Bio"),
		Website("Website"),
		Phone("Phone"),
		LinkedIn("LinkedIn"),
		Twitter("Twitter"),
		Skype("Skype"),
		CompanyId("CompanyId"),
		Company("Company"),
		UserProfile_Company("ProfileService.UserProfile_Company");

		private java.lang.String metaName;

		MemberNames(java.lang.String s)
		{
			metaName = s;
		}

		@java.lang.Override
		public java.lang.String toString()
		{
			return metaName;
		}
	}

	public UserProfile(com.mendix.systemwideinterfaces.core.IContext context)
	{
		this(context, com.mendix.core.Core.instantiate(context, "ProfileService.UserProfile"));
	}

	protected UserProfile(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixObject userProfileMendixObject)
	{
		if (userProfileMendixObject == null)
			throw new java.lang.IllegalArgumentException("The given object cannot be null.");
		if (!com.mendix.core.Core.isSubClassOf("ProfileService.UserProfile", userProfileMendixObject.getType()))
			throw new java.lang.IllegalArgumentException("The given object is not a ProfileService.UserProfile");

		this.userProfileMendixObject = userProfileMendixObject;
		this.context = context;
	}

	/**
	 * @deprecated Use 'UserProfile.load(IContext, IMendixIdentifier)' instead.
	 */
	@java.lang.Deprecated
	public static profileservice.proxies.UserProfile initialize(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixIdentifier mendixIdentifier) throws com.mendix.core.CoreException
	{
		return profileservice.proxies.UserProfile.load(context, mendixIdentifier);
	}

	/**
	 * Initialize a proxy using context (recommended). This context will be used for security checking when the get- and set-methods without context parameters are called.
	 * The get- and set-methods with context parameter should be used when for instance sudo access is necessary (IContext.createSudoClone() can be used to obtain sudo access).
	 */
	public static profileservice.proxies.UserProfile initialize(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixObject mendixObject)
	{
		return new profileservice.proxies.UserProfile(context, mendixObject);
	}

	public static profileservice.proxies.UserProfile load(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixIdentifier mendixIdentifier) throws com.mendix.core.CoreException
	{
		com.mendix.systemwideinterfaces.core.IMendixObject mendixObject = com.mendix.core.Core.retrieveId(context, mendixIdentifier);
		return profileservice.proxies.UserProfile.initialize(context, mendixObject);
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
	 * @return value of OpenId
	 */
	public final java.lang.String getOpenId()
	{
		return getOpenId(getContext());
	}

	/**
	 * @param context
	 * @return value of OpenId
	 */
	public final java.lang.String getOpenId(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.lang.String) getMendixObject().getValue(context, MemberNames.OpenId.toString());
	}

	/**
	 * Set value of OpenId
	 * @param openid
	 */
	public final void setOpenId(java.lang.String openid)
	{
		setOpenId(getContext(), openid);
	}

	/**
	 * Set value of OpenId
	 * @param context
	 * @param openid
	 */
	public final void setOpenId(com.mendix.systemwideinterfaces.core.IContext context, java.lang.String openid)
	{
		getMendixObject().setValue(context, MemberNames.OpenId.toString(), openid);
	}

	/**
	 * @return value of DisplayName
	 */
	public final java.lang.String getDisplayName()
	{
		return getDisplayName(getContext());
	}

	/**
	 * @param context
	 * @return value of DisplayName
	 */
	public final java.lang.String getDisplayName(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.lang.String) getMendixObject().getValue(context, MemberNames.DisplayName.toString());
	}

	/**
	 * Set value of DisplayName
	 * @param displayname
	 */
	public final void setDisplayName(java.lang.String displayname)
	{
		setDisplayName(getContext(), displayname);
	}

	/**
	 * Set value of DisplayName
	 * @param context
	 * @param displayname
	 */
	public final void setDisplayName(com.mendix.systemwideinterfaces.core.IContext context, java.lang.String displayname)
	{
		getMendixObject().setValue(context, MemberNames.DisplayName.toString(), displayname);
	}

	/**
	 * @return value of EmailAddress
	 */
	public final java.lang.String getEmailAddress()
	{
		return getEmailAddress(getContext());
	}

	/**
	 * @param context
	 * @return value of EmailAddress
	 */
	public final java.lang.String getEmailAddress(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.lang.String) getMendixObject().getValue(context, MemberNames.EmailAddress.toString());
	}

	/**
	 * Set value of EmailAddress
	 * @param emailaddress
	 */
	public final void setEmailAddress(java.lang.String emailaddress)
	{
		setEmailAddress(getContext(), emailaddress);
	}

	/**
	 * Set value of EmailAddress
	 * @param context
	 * @param emailaddress
	 */
	public final void setEmailAddress(com.mendix.systemwideinterfaces.core.IContext context, java.lang.String emailaddress)
	{
		getMendixObject().setValue(context, MemberNames.EmailAddress.toString(), emailaddress);
	}

	/**
	 * @return value of AvatarUrl
	 */
	public final java.lang.String getAvatarUrl()
	{
		return getAvatarUrl(getContext());
	}

	/**
	 * @param context
	 * @return value of AvatarUrl
	 */
	public final java.lang.String getAvatarUrl(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.lang.String) getMendixObject().getValue(context, MemberNames.AvatarUrl.toString());
	}

	/**
	 * Set value of AvatarUrl
	 * @param avatarurl
	 */
	public final void setAvatarUrl(java.lang.String avatarurl)
	{
		setAvatarUrl(getContext(), avatarurl);
	}

	/**
	 * Set value of AvatarUrl
	 * @param context
	 * @param avatarurl
	 */
	public final void setAvatarUrl(com.mendix.systemwideinterfaces.core.IContext context, java.lang.String avatarurl)
	{
		getMendixObject().setValue(context, MemberNames.AvatarUrl.toString(), avatarurl);
	}

	/**
	 * @return value of AvatarThumbnailUrl
	 */
	public final java.lang.String getAvatarThumbnailUrl()
	{
		return getAvatarThumbnailUrl(getContext());
	}

	/**
	 * @param context
	 * @return value of AvatarThumbnailUrl
	 */
	public final java.lang.String getAvatarThumbnailUrl(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.lang.String) getMendixObject().getValue(context, MemberNames.AvatarThumbnailUrl.toString());
	}

	/**
	 * Set value of AvatarThumbnailUrl
	 * @param avatarthumbnailurl
	 */
	public final void setAvatarThumbnailUrl(java.lang.String avatarthumbnailurl)
	{
		setAvatarThumbnailUrl(getContext(), avatarthumbnailurl);
	}

	/**
	 * Set value of AvatarThumbnailUrl
	 * @param context
	 * @param avatarthumbnailurl
	 */
	public final void setAvatarThumbnailUrl(com.mendix.systemwideinterfaces.core.IContext context, java.lang.String avatarthumbnailurl)
	{
		getMendixObject().setValue(context, MemberNames.AvatarThumbnailUrl.toString(), avatarthumbnailurl);
	}

	/**
	 * @return value of JobTitle
	 */
	public final java.lang.String getJobTitle()
	{
		return getJobTitle(getContext());
	}

	/**
	 * @param context
	 * @return value of JobTitle
	 */
	public final java.lang.String getJobTitle(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.lang.String) getMendixObject().getValue(context, MemberNames.JobTitle.toString());
	}

	/**
	 * Set value of JobTitle
	 * @param jobtitle
	 */
	public final void setJobTitle(java.lang.String jobtitle)
	{
		setJobTitle(getContext(), jobtitle);
	}

	/**
	 * Set value of JobTitle
	 * @param context
	 * @param jobtitle
	 */
	public final void setJobTitle(com.mendix.systemwideinterfaces.core.IContext context, java.lang.String jobtitle)
	{
		getMendixObject().setValue(context, MemberNames.JobTitle.toString(), jobtitle);
	}

	/**
	 * @return value of Department
	 */
	public final java.lang.String getDepartment()
	{
		return getDepartment(getContext());
	}

	/**
	 * @param context
	 * @return value of Department
	 */
	public final java.lang.String getDepartment(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.lang.String) getMendixObject().getValue(context, MemberNames.Department.toString());
	}

	/**
	 * Set value of Department
	 * @param department
	 */
	public final void setDepartment(java.lang.String department)
	{
		setDepartment(getContext(), department);
	}

	/**
	 * Set value of Department
	 * @param context
	 * @param department
	 */
	public final void setDepartment(com.mendix.systemwideinterfaces.core.IContext context, java.lang.String department)
	{
		getMendixObject().setValue(context, MemberNames.Department.toString(), department);
	}

	/**
	 * @return value of Location
	 */
	public final java.lang.String getLocation()
	{
		return getLocation(getContext());
	}

	/**
	 * @param context
	 * @return value of Location
	 */
	public final java.lang.String getLocation(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.lang.String) getMendixObject().getValue(context, MemberNames.Location.toString());
	}

	/**
	 * Set value of Location
	 * @param location
	 */
	public final void setLocation(java.lang.String location)
	{
		setLocation(getContext(), location);
	}

	/**
	 * Set value of Location
	 * @param context
	 * @param location
	 */
	public final void setLocation(com.mendix.systemwideinterfaces.core.IContext context, java.lang.String location)
	{
		getMendixObject().setValue(context, MemberNames.Location.toString(), location);
	}

	/**
	 * @return value of Country
	 */
	public final java.lang.String getCountry()
	{
		return getCountry(getContext());
	}

	/**
	 * @param context
	 * @return value of Country
	 */
	public final java.lang.String getCountry(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.lang.String) getMendixObject().getValue(context, MemberNames.Country.toString());
	}

	/**
	 * Set value of Country
	 * @param country
	 */
	public final void setCountry(java.lang.String country)
	{
		setCountry(getContext(), country);
	}

	/**
	 * Set value of Country
	 * @param context
	 * @param country
	 */
	public final void setCountry(com.mendix.systemwideinterfaces.core.IContext context, java.lang.String country)
	{
		getMendixObject().setValue(context, MemberNames.Country.toString(), country);
	}

	/**
	 * @return value of Bio
	 */
	public final java.lang.String getBio()
	{
		return getBio(getContext());
	}

	/**
	 * @param context
	 * @return value of Bio
	 */
	public final java.lang.String getBio(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.lang.String) getMendixObject().getValue(context, MemberNames.Bio.toString());
	}

	/**
	 * Set value of Bio
	 * @param bio
	 */
	public final void setBio(java.lang.String bio)
	{
		setBio(getContext(), bio);
	}

	/**
	 * Set value of Bio
	 * @param context
	 * @param bio
	 */
	public final void setBio(com.mendix.systemwideinterfaces.core.IContext context, java.lang.String bio)
	{
		getMendixObject().setValue(context, MemberNames.Bio.toString(), bio);
	}

	/**
	 * @return value of Website
	 */
	public final java.lang.String getWebsite()
	{
		return getWebsite(getContext());
	}

	/**
	 * @param context
	 * @return value of Website
	 */
	public final java.lang.String getWebsite(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.lang.String) getMendixObject().getValue(context, MemberNames.Website.toString());
	}

	/**
	 * Set value of Website
	 * @param website
	 */
	public final void setWebsite(java.lang.String website)
	{
		setWebsite(getContext(), website);
	}

	/**
	 * Set value of Website
	 * @param context
	 * @param website
	 */
	public final void setWebsite(com.mendix.systemwideinterfaces.core.IContext context, java.lang.String website)
	{
		getMendixObject().setValue(context, MemberNames.Website.toString(), website);
	}

	/**
	 * @return value of Phone
	 */
	public final java.lang.String getPhone()
	{
		return getPhone(getContext());
	}

	/**
	 * @param context
	 * @return value of Phone
	 */
	public final java.lang.String getPhone(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.lang.String) getMendixObject().getValue(context, MemberNames.Phone.toString());
	}

	/**
	 * Set value of Phone
	 * @param phone
	 */
	public final void setPhone(java.lang.String phone)
	{
		setPhone(getContext(), phone);
	}

	/**
	 * Set value of Phone
	 * @param context
	 * @param phone
	 */
	public final void setPhone(com.mendix.systemwideinterfaces.core.IContext context, java.lang.String phone)
	{
		getMendixObject().setValue(context, MemberNames.Phone.toString(), phone);
	}

	/**
	 * @return value of LinkedIn
	 */
	public final java.lang.String getLinkedIn()
	{
		return getLinkedIn(getContext());
	}

	/**
	 * @param context
	 * @return value of LinkedIn
	 */
	public final java.lang.String getLinkedIn(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.lang.String) getMendixObject().getValue(context, MemberNames.LinkedIn.toString());
	}

	/**
	 * Set value of LinkedIn
	 * @param linkedin
	 */
	public final void setLinkedIn(java.lang.String linkedin)
	{
		setLinkedIn(getContext(), linkedin);
	}

	/**
	 * Set value of LinkedIn
	 * @param context
	 * @param linkedin
	 */
	public final void setLinkedIn(com.mendix.systemwideinterfaces.core.IContext context, java.lang.String linkedin)
	{
		getMendixObject().setValue(context, MemberNames.LinkedIn.toString(), linkedin);
	}

	/**
	 * @return value of Twitter
	 */
	public final java.lang.String getTwitter()
	{
		return getTwitter(getContext());
	}

	/**
	 * @param context
	 * @return value of Twitter
	 */
	public final java.lang.String getTwitter(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.lang.String) getMendixObject().getValue(context, MemberNames.Twitter.toString());
	}

	/**
	 * Set value of Twitter
	 * @param twitter
	 */
	public final void setTwitter(java.lang.String twitter)
	{
		setTwitter(getContext(), twitter);
	}

	/**
	 * Set value of Twitter
	 * @param context
	 * @param twitter
	 */
	public final void setTwitter(com.mendix.systemwideinterfaces.core.IContext context, java.lang.String twitter)
	{
		getMendixObject().setValue(context, MemberNames.Twitter.toString(), twitter);
	}

	/**
	 * @return value of Skype
	 */
	public final java.lang.String getSkype()
	{
		return getSkype(getContext());
	}

	/**
	 * @param context
	 * @return value of Skype
	 */
	public final java.lang.String getSkype(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.lang.String) getMendixObject().getValue(context, MemberNames.Skype.toString());
	}

	/**
	 * Set value of Skype
	 * @param skype
	 */
	public final void setSkype(java.lang.String skype)
	{
		setSkype(getContext(), skype);
	}

	/**
	 * Set value of Skype
	 * @param context
	 * @param skype
	 */
	public final void setSkype(com.mendix.systemwideinterfaces.core.IContext context, java.lang.String skype)
	{
		getMendixObject().setValue(context, MemberNames.Skype.toString(), skype);
	}

	/**
	 * @return value of CompanyId
	 */
	public final java.lang.String getCompanyId()
	{
		return getCompanyId(getContext());
	}

	/**
	 * @param context
	 * @return value of CompanyId
	 */
	public final java.lang.String getCompanyId(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.lang.String) getMendixObject().getValue(context, MemberNames.CompanyId.toString());
	}

	/**
	 * Set value of CompanyId
	 * @param companyid
	 */
	public final void setCompanyId(java.lang.String companyid)
	{
		setCompanyId(getContext(), companyid);
	}

	/**
	 * Set value of CompanyId
	 * @param context
	 * @param companyid
	 */
	public final void setCompanyId(com.mendix.systemwideinterfaces.core.IContext context, java.lang.String companyid)
	{
		getMendixObject().setValue(context, MemberNames.CompanyId.toString(), companyid);
	}

	/**
	 * @return value of Company
	 */
	public final java.lang.String getCompany()
	{
		return getCompany(getContext());
	}

	/**
	 * @param context
	 * @return value of Company
	 */
	public final java.lang.String getCompany(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.lang.String) getMendixObject().getValue(context, MemberNames.Company.toString());
	}

	/**
	 * Set value of Company
	 * @param company
	 */
	public final void setCompany(java.lang.String company)
	{
		setCompany(getContext(), company);
	}

	/**
	 * Set value of Company
	 * @param context
	 * @param company
	 */
	public final void setCompany(com.mendix.systemwideinterfaces.core.IContext context, java.lang.String company)
	{
		getMendixObject().setValue(context, MemberNames.Company.toString(), company);
	}

	/**
	 * @return value of UserProfile_Company
	 */
	public final profileservice.proxies.Company getUserProfile_Company() throws com.mendix.core.CoreException
	{
		return getUserProfile_Company(getContext());
	}

	/**
	 * @param context
	 * @return value of UserProfile_Company
	 */
	public final profileservice.proxies.Company getUserProfile_Company(com.mendix.systemwideinterfaces.core.IContext context) throws com.mendix.core.CoreException
	{
		profileservice.proxies.Company result = null;
		com.mendix.systemwideinterfaces.core.IMendixIdentifier identifier = getMendixObject().getValue(context, MemberNames.UserProfile_Company.toString());
		if (identifier != null)
			result = profileservice.proxies.Company.load(context, identifier);
		return result;
	}

	/**
	 * Set value of UserProfile_Company
	 * @param userprofile_company
	 */
	public final void setUserProfile_Company(profileservice.proxies.Company userprofile_company)
	{
		setUserProfile_Company(getContext(), userprofile_company);
	}

	/**
	 * Set value of UserProfile_Company
	 * @param context
	 * @param userprofile_company
	 */
	public final void setUserProfile_Company(com.mendix.systemwideinterfaces.core.IContext context, profileservice.proxies.Company userprofile_company)
	{
		if (userprofile_company == null)
			getMendixObject().setValue(context, MemberNames.UserProfile_Company.toString(), null);
		else
			getMendixObject().setValue(context, MemberNames.UserProfile_Company.toString(), userprofile_company.getMendixObject().getId());
	}

	/**
	 * @return the IMendixObject instance of this proxy for use in the Core interface.
	 */
	public final com.mendix.systemwideinterfaces.core.IMendixObject getMendixObject()
	{
		return userProfileMendixObject;
	}

	/**
	 * @return the IContext instance of this proxy, or null if no IContext instance was specified at initialization.
	 */
	public final com.mendix.systemwideinterfaces.core.IContext getContext()
	{
		return context;
	}

	@java.lang.Override
	public boolean equals(Object obj)
	{
		if (obj == this)
			return true;

		if (obj != null && getClass().equals(obj.getClass()))
		{
			final profileservice.proxies.UserProfile that = (profileservice.proxies.UserProfile) obj;
			return getMendixObject().equals(that.getMendixObject());
		}
		return false;
	}

	@java.lang.Override
	public int hashCode()
	{
		return getMendixObject().hashCode();
	}

	/**
	 * @return String name of this class
	 */
	public static java.lang.String getType()
	{
		return "ProfileService.UserProfile";
	}

	/**
	 * @return String GUID from this object, format: ID_0000000000
	 * @deprecated Use getMendixObject().getId().toLong() to get a unique identifier for this object.
	 */
	@java.lang.Deprecated
	public java.lang.String getGUID()
	{
		return "ID_" + getMendixObject().getId().toLong();
	}
}
