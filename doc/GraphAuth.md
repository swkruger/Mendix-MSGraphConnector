```
+---------+      +--------------------+      +----------+      +------------------+
| Browser |      | Mendix Application |      | Azure AD |      | Resource Web API |
|         +--T1-->                    |      |          |      |                  |
|         <--R1--+                    |      |          |      |                  |
|         |      |                    |      |          |      |                  |
|         +--T2------------------------------>          |      |                  |
|         <------------------------------R2--+          |      |                  |
|         |      |                    |      |          |      |                  |
|         +--T3-->                    |      |          |      |                  |
|         <--R3--+                    |      |          |      |                  |
|         |      |                    |      |          |      |                  |
|         |      |                    +--T4-->          |      |                  |
|         |      |                    <--R4--+          |      |                  |
|         |      |                    |      |          |      |                  |
|         |      |                    +--T5-------------------->                  |
|         |      |                    <--------------------R5--+                  |
|         |      |                    |      |          |      |                  |
|         |      |                    +--T6-->          |      |                  |
|         |      |                    <--R6--+          |      |                  |
|         |      |                    |      |          |      |                  |
+---------+      +--------------------+      +----------+      +------------------+

```

  1. The browser requests a page from the application. If there is no current 
     session, the application responds with a redirect to the AAD authorisation 
     endpoint.
  2. The browser follows the redirect to the AAD authorisation endpoint. Upon a
     successful authorisation, the AAD authorisation service directs the 
     browser to the login endpoint in the application, passing an .
  3. The application makes a request directly to Azure AD using the code.
  4. Azure AD replies to the application with a token and other authentication information. The application uses this data to create a session for the authenticated user.
  5. The application can now make further requests for resources to the Resource Web API using this token
  6. Resource Web API responds to the request.


Constants
---
  
MSGraphConnector.IndexPage						/index.html
MSGraphConnector.LogoutPage						Logout.html
MSGraphConnector.AdminConsentSuccessPage				AdminConsentSuccess.html
MSGraphConnector.ErrorPage						Error.html
MSGraphConnector.CookieErrorPage					CookieError.html
MSGraphConnector.StateErrorPage					StateError.html
MSGraphConnector.UnauthorizedAccessPage				Unauthorized.html

MSGraphConnector.MicrosoftGraphBaseAPIUrl				https://graph.microsoft.com/v1.0
MSGraphConnector.MicrosoftOnlineBaseAuthUrl			https://login.microsoftonline.com/e1cb0ec1-aa62-4077-ac8a-d399596b900c/oauth2/v2.0
MSGraphConnector.MicrosoftOnlineBaseAuthUrlAdminConsent	https://login.microsoftonline.com/e1cb0ec1-aa62-4077-ac8a-d399596b900c/adminconsent

MSGraphConnector.CookieName						MSGraphOauthCookieMendix
MSGraphConnector.ResolveUserMicroflow				MSGraphConnector.ResolveUserByEmail


Database
---

MSGraphConnector.Application/ApplicationId (AutoNumber)
MSGraphConnector.Application/Name (String)
MSGraphConnector.Application/CliendId (String)
MSGraphConnector.Application/Secret (String)
MSGraphConnector.Application/RedirectUrl (String) <----- This should be based on a constant so it doesn't redirect to the wrong appliction after a DB restore
MSGraphConnector.Application/UserId_MSGraph (String)
MSGraphConnector.Application/ErrorHandling (Enumeration: MSGraphConnector.ApplicationErrorHandling)
	MSGraphConnector.ApplicationErrorHandling.Do_Nothing
	MSGraphConnector.ApplicationErrorHandling.Show_ErrorMessage
	MSGraphConnector.ApplicationErrorHandling.Log_Error
	MSGraphConnector.ApplicationErrorHandling.Log_and_Show_Error_Message

	
Dependencies
---

stringutils.actions.URLEncode (called by Java)


Configuration steps
---

  1. Create default permissions
  2. Register the app on Azure
	a) https://portal.azure.com/#blade/Microsoft_AAD_RegisteredApps/ApplicationsListBlade
	b) New registration
        Name 				= "MS Graph Demo"
        Supported account types 	= "Accounts in this organizational directory only (ShelterBox Trust only - Single tenant)"
        Redirect URI 			= Web; http://localhost:8080/msgraph/callback
        [Register]
  3. Get app registration info (Overview)
      Display name:					MS Graph Demo
      Application (client) ID:			dcce42c1-a6fa-4a92-a5c0-5f89a4ff1637
      Directory (tenant) ID:				e1cb0ec1-aa62-4077-ac8a-d399596b900c
      Object ID:						ecb7f9ad-2af2-4fca-a305-4822fa9e2650
      Supported account types:			My organization only
      Redirect URIs:					1 web, 0 public client
      Application ID URI:				Add an Application ID URI
      Managed application in local directory:	MS Graph Demo
  4. Get endpoints (Overview -> Endpoints)
      OAuth 2.0 authorization endpoint (v2)	https://login.microsoftonline.com/e1cb0ec1-aa62-4077-ac8a-d399596b900c/oauth2/v2.0/authorize
      OAuth 2.0 token endpoint (v2)			https://login.microsoftonline.com/e1cb0ec1-aa62-4077-ac8a-d399596b900c/oauth2/v2.0/token
      OAuth 2.0 authorization endpoint (v1)	https://login.microsoftonline.com/e1cb0ec1-aa62-4077-ac8a-d399596b900c/oauth2/authorize
      OAuth 2.0 token endpoint (v1)			https://login.microsoftonline.com/e1cb0ec1-aa62-4077-ac8a-d399596b900c/oauth2/token
      OpenID Connect metadata document		https://login.microsoftonline.com/e1cb0ec1-aa62-4077-ac8a-d399596b900c/v2.0/.well-known/openid-configuration
      Microsoft Graph API endpoint			https://graph.microsoft.com
      Federation metadata document			https://login.microsoftonline.com/e1cb0ec1-aa62-4077-ac8a-d399596b900c/federationmetadata/2007-06/federationmetadata.xml
      WS-Federation sign-on endpoint		https://login.microsoftonline.com/e1cb0ec1-aa62-4077-ac8a-d399596b900c/wsfed
      SAML-P sign-on endpoint				https://login.microsoftonline.com/e1cb0ec1-aa62-4077-ac8a-d399596b900c/saml2
      SAML-P sign-out endpoint			https://login.microsoftonline.com/e1cb0ec1-aa62-4077-ac8a-d399596b900c/saml2
  5. Generate secrets (Certificates & secrets)
      a) New client secret
        Description = "Demo"
        Expires = "Never"	  
	b) Value: "=lq=EM5C2RqR=y0RBIRBcoh:WOhZSB3b"
  6. Configure application
	Name				= "MS Graph Demo"
	Client Id			= "dcce42c1-a6fa-4a92-a5c0-5f89a4ff1637
	Secret			= "=lq=EM5C2RqR=y0RBIRBcoh:WOhZSB3b"
	Redirect Url		= "http://localhost:8080/msgraph/callback"
	MSGraph User Identifier	= "mail"
	Error Handling		= "Log and Show Error Message"
	Permissions			= email, offline_access, openid, profile
  7. Get admin consent
	
	
	
	
	
	
Delete existing configuration?