package msgraphconnector.actions.custom;

import com.mendix.core.Core;
import com.mendix.core.CoreException;
import com.mendix.core.CoreRuntimeException;
import com.mendix.m2ee.api.IMxRuntimeRequest;
import com.mendix.m2ee.api.IMxRuntimeResponse;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.systemwideinterfaces.core.IDataType;
import com.mendix.systemwideinterfaces.core.IMendixObject;
import com.mendix.systemwideinterfaces.core.IUser;
import msgraphconnector.proxies.Application;
import msgraphconnector.proxies.MSGraphAuthMessage;
import msgraphconnector.proxies.constants.Constants;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import system.proxies.User;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by skruger on 6/26/2017.
 */
public class MSGraphCallback {
    private final String UNAUTHHTML = Constants.getUnauthorizedAccessPage();
    private final String COOKIEHTML = Constants.getCookieErrorPage();
    private final String ERRORHTML = Constants.getErrorPage();
    private final String ADMINCONSENTHTML = Constants.getAdminConsentSuccessPage();
    private final String MSGRAPHDIR = "MSGraph";
    private final String STATEHTML = Constants.getStateErrorPage();
    private final String USERINFOURL = Constants.getMicrosoftGraphBaseAPIUrl()+"/me?$select=userPrincipalName,mail";
    private final String USERRESOLVERMF = Constants.getResolveUserMicroflow();

    public MSGraphCallback() {
    }

    /**
     * This method will start processing the incoming callback request from MS Graph
     *
     */
    protected void processCallbackRequest(IMxRuntimeRequest request, IMxRuntimeResponse response) throws Exception {
        HttpServletRequest servletRequest =  request.getHttpServletRequest();
        Core.getLogger("MSGraph").trace("Received process request event");
        try {
            Core.getLogger("MSGraph").debug("Request URI: "+ servletRequest.getRequestURI());
            doCallbackService(request, response);
        } catch (Exception ex) {
            Core.getLogger("MSGraph").error("Exception occurred while processing request "+ex);
            response.sendError("Exception occurred while processing request");
        }
    }

    /**
     * This method will process the incoming request
     * First the state of the request is checked against the value in the cookie
     * The the code is exchanged for an Access Token
     * The Access Token is used to retrieve information on behalf of the user with MS Graph
     */
    private void doCallbackService(IMxRuntimeRequest request, IMxRuntimeResponse response) throws ServletException, IOException, CoreException {
        HttpServletResponse servletResponse = response.getHttpServletResponse();
        HttpServletRequest servletRequest = request.getHttpServletRequest();
        if (request.getParameter("error") != null) {
            Core.getLogger("MSGraph").error("An error occured in the MS Graph callback request: "+ request.getParameter("error")+ ", " + request.getParameter("error_description"));
            new ErrorHandler().processErrorHandler(response, MSGRAPHDIR, ERRORHTML);
            //throw new ServletException("Error found: "+ request.getParameter("error")+ ", " +request.getParameter("error_description"));
            return;
        }

        if (request.getParameter("admin_consent") != null) {
            Core.getLogger("MSGraph").info("Administrator has successfully given access for the application permisison scope");
            new ErrorHandler().processErrorHandler(response, MSGRAPHDIR, ADMINCONSENTHTML);
            //throw new ServletException("Error found: "+ request.getParameter("error")+ ", " +request.getParameter("error_description"));
            return;
        }

        /*
		 * Check cookie for UUID compared to state value
		 */
        /*
		 * Check cookie for UUID compared to state value
		 */
        String cookieName = Constants.getCookieName();

        Cookie[] cookies = servletRequest.getCookies();
        boolean cookieStateVerified = false;
        if (cookies != null)
        {
            for(int i=0; i<cookies.length; i++)
            {
                Cookie cookie = cookies[i];
                Core.getLogger("MSGraph").debug("found cookie with name: "+cookie.getName());
                if (cookieName.equals(cookie.getName()))
                {
                    Core.getLogger("MSGraph").debug("cookie value: "+cookie.getValue());
                    Core.getLogger("MSGraph").debug(servletRequest.getRequestURI());
                    String state = servletRequest.getParameter("state");
                    Core.getLogger("MSGraph").debug("State value: "+state);
                    if( cookie.getValue().equals(state)){
                        Core.getLogger("MSGraph").debug("state matches cookie");
                        cookieStateVerified = true;
                    }
                    else{
						/*
						 * state in the cookie is different from what is returned by the provider
						 * create log record
						 * show csf/xss error page
						 */
                        new LogRecordHandler().createLogRecord("Possible CSF/XSS attempt", MSGraphAuthMessage.CSFRAttempt);
                        Core.getLogger("MSGraph").error("False state detected");
                        new ErrorHandler().processErrorHandler(response, MSGRAPHDIR, STATEHTML);
                        return;
                    }
                }
            }
        }
        else
        {
			/*
			 * No cookies found whatsoever
			 * create log record and show cookie error page
			 */
            new LogRecordHandler().createLogRecord("State could not be verified",MSGraphAuthMessage.StateError);
            Core.getLogger("MSGraph").error("Cookie not found");
            new ErrorHandler().processErrorHandler(response, MSGRAPHDIR, COOKIEHTML);
            return;
        }
        if (!cookieStateVerified){
			/*
			 * Cookies found but none contain the correct state
			 * create log record and show cookie error page
			 */
            new LogRecordHandler().createLogRecord("State could not be verified",MSGraphAuthMessage.StateError);
            Core.getLogger("MSGraph").error("Cookie not found");
            new ErrorHandler().processErrorHandler(response, MSGRAPHDIR, COOKIEHTML);
            return;
        }

		/*
		 *  Oauth provide returns a code that can be exchanged for a access token
		 *  get the access token by post to MS Graph
		 *  example returned access token
		 *
		 *  {
         *	    "token_type":"Bearer",
         *	    "scope":"User.Read",
         *	    "expires_in":3599,
         *	    "ext_expires_in":0,
         *	    "access_token":"eyJ0eXAiOiJKV1QiLCJub25jZSI6IkFRQ...",
         *	    "refresh_token":"eyJ0eXAiOiJKV1QiLCJub25j...."
         *  }
		 *
		 */
        String body=  "";
        String code = request.getParameter("code");
        GetAccessTokenMSGraph accessTokenMSGraph = new GetAccessTokenMSGraph(code);
        body = accessTokenMSGraph.getResult();

        /*
        String requestPath = servletRequest.getRequestURI();
        String[] pathParameters = StringUtils.split(requestPath, '/');
        String body=  "";
        String code = request.getParameter("code");
        if (pathParameters.length > 0) {
            if(pathParameters[1].equals("callback")){
                GetAccessTokenMSGraph accessTokenMSGraph = new GetAccessTokenMSGraph(code);
                body = accessTokenMSGraph.getResult();
            }
            else{
                Core.getLogger("MSGraph").error("Unkown request path parameter");
                throw new ServletException("Unkown request path parameter");
            }
        }
        else{
            Core.getLogger("MSGraph").error("Missing request path parameter");
            throw new ServletException("Missing request path parameter");
        }
        */
        JSONObject jsonAuthTokenObject = null;
		/*
		 * Check if body is returned as json if true then
		 * get the access token from json and request info from the OAuth provider
		 * else get access token specifically from the returned body
		 *
		 * Be aware that tokens will expire!
		 */
        boolean isJson = true;
        StringBuilder accessToken = new StringBuilder();
        try {
            jsonAuthTokenObject = (JSONObject) new JSONParser().parse(body);
            accessToken.append(jsonAuthTokenObject.get("token_type"))
                .append(" ")
                .append(jsonAuthTokenObject.get("access_token"));

        } catch (ParseException e) {
            throw new RuntimeException("Unable to parse json " + body);
        }

		/*
		 *  get some info about the user with the access token
		 */
        String userInfoString = "";
        Core.getLogger("MSGraph").trace("Get user info from MSGraph");
        GetHttpRequest geturi = new GetHttpRequest();
        userInfoString = geturi.get(USERINFOURL,accessToken.toString());

        /*
        if (pathParameters.length > 0) {
            GetHttpRequest geturi = new GetHttpRequest();
            if(pathParameters[1].equals("callback")){
                Core.getLogger("MSGraph").trace("Get user info from MSGraph");
                userInfoString = geturi.get(USERINFOURL,accessToken.toString());
            }
            else{
                Core.getLogger("MSGraph").error("Unkown request path parameter");
                throw new ServletException("Unkown request path parameter");
            }
        }
        else{
            Core.getLogger("MSGraph").error("Missing request path parameter");
            throw new ServletException("Missing request path parameter");
        }
        */

   		/*
		 *  return the string with the user's basic info and get the email address from the data
		 *  to retrieve the user.
		 */
        Core.getLogger("MSGraph").debug("Request URI: "+ userInfoString);

        JSONObject jsonUserObject;
        try {
            jsonUserObject = (JSONObject)new JSONParser().parse(userInfoString);
            IContext context = Core.createSystemContext();
            User user = null;

            Application application = new MSGraphApplication().getApplication(context);

            user=resolveUser(context, jsonUserObject.get(application.getUserId_MSGraph()).toString(),application);

            /*
            if(pathParameters[1].equals("callback")){
                user=resolveUser(context, jsonUserObject.get(application.getUserId_MSGraph()).toString(),application);
            }
            */

            if(user != null){
				/*
				 * Create a log record
				 * Create a session for the user
				 */
                Core.getLogger("MSGraph").debug("User found: "+user.getName());
                new LogRecordHandler().createLogRecord(jsonUserObject.toString(),MSGraphAuthMessage.Access);
                IUser iUser = Core.getUser(context, user.getName());
                new AuthTokenHandler().createAuthTokenRecordFromJson(jsonAuthTokenObject, user);
                LoginHelper.createSession(request, response, context, iUser);
            }else{
				/*
				 * Create a log record for the unauthorized access attempt
				 * Show the unauthorized access page
				 */
                Core.getLogger("MSGraph").debug("User not found");
                new LogRecordHandler().createLogRecord(jsonUserObject.toString(),MSGraphAuthMessage.Unauthorised);
                new ErrorHandler().processErrorHandler(response, MSGRAPHDIR, UNAUTHHTML);
                return;

            }
        } catch (ParseException e) {
            Core.getLogger("MSGraph").error("Response for userinfo is not the expected format. /n"+ e.getStackTrace());
        }


    }

    private User resolveUser(IContext context, String mfInput, Application application) {
        try {
            //Microflows microflow = configuration.getOAuthConfig_Microflows();
            //if (microflow == null) {
            //throw new CoreRuntimeException(
            //            "Microflow to resolve user not set in GoogleOauth configuration, please contact the application administrator");
            //}
            Map<String, Object> params = new HashMap<String, Object>();
            Map<String, IDataType> inputParameters = Core.getInputParameters(USERRESOLVERMF);
            for (String name : inputParameters.keySet()) {
                params.put(name, mfInput);
                break;
            }
            Core.getLogger("MSGraph").debug("Calling action "+USERRESOLVERMF+" with params: "+params);
            Object result = Core.execute(context, USERRESOLVERMF, params);
			/*
			 * If user not found then return null
			 * Else return the user object
			 */
            if (result == null) {
                return null;
            }
            else{
                User user = User.initialize(context, (IMendixObject) result);
                Core.getLogger("MSGraph").debug("Resolved user "+user.getName());
                return user;
            }
        } catch (CoreException ex) {
            Core.getLogger("MSGraph").error("Exception occurred while resolving user "+ ex);
            throw new CoreRuntimeException("Failed to resolve user");
        }
    }



}
