package msgraphconnector.actions.custom;

import com.mendix.core.Core;
import com.mendix.externalinterface.connector.RequestHandler;
import com.mendix.m2ee.api.IMxRuntimeRequest;
import com.mendix.m2ee.api.IMxRuntimeResponse;
import com.mendix.systemwideinterfaces.core.ISession;
import msgraphconnector.proxies.constants.Constants;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by skruger on 6/23/2017.
 */
public class MSGraphRequestHandler extends RequestHandler {
    /*
	 * Contextpath is not used in this function but is needed for adding the requesthandler.
	 */
    @SuppressWarnings("unused")
    private String contextPath;
    private String signinPath;
    private String callbackPath;
    private String logoutPath;

    public MSGraphRequestHandler(String contextPath, String signinPath, String callbackPath, String logoutPath) {
        this.contextPath = contextPath;
        this.signinPath = signinPath;
        this.callbackPath = callbackPath;
        this.logoutPath = logoutPath;
    }

    /**
     * This method will start processing the incoming request on the handler
     */
    @Override
    protected void processRequest(IMxRuntimeRequest request, IMxRuntimeResponse response, String path) throws Exception {

        HttpServletRequest servletRequest = request.getHttpServletRequest();
        Core.getLogger("MSGraph").trace("Received process request event");
        try {
            Core.getLogger("MSGraph").trace("Request URI: "+ servletRequest.getRequestURI());
            doRequest(request, response,servletRequest.getRequestURI());
            response.setStatus(200);

        } catch (Exception ex) {
            Core.getLogger("MSGraph").error("Exception occurred while processing request "+ex);
            response.sendError("Exception occurred while processing request");
        }
    }


    /**
     * This method will process the request to the signin handler
     * Add a cookie with a generated state parameter
     * Redirect to the Oauth provider based on the URI path parameters
     * @param request
     * @param response
     * @param requestURI
     * @throws ServletException
     * @throws IOException
     */
    private void doRequest(IMxRuntimeRequest request, IMxRuntimeResponse response, String requestURI) throws Exception {
        String[] pathParameters = StringUtils.split(requestURI, '/');
        for(int i =0; i < pathParameters.length; i++){
            Core.getLogger("MSGraph").trace(pathParameters[i]+" "+i);
        }

        if (pathParameters.length > 0) {
            if(pathParameters[1].equals(signinPath)){
                String UUIDstate = createUUID();
                String cookieName = Constants.getCookieName();
                Core.getLogger("MSGraph").trace("Name of the cookie is: "+cookieName);
                Cookie userCookie = new Cookie(cookieName, UUIDstate);
                userCookie.setMaxAge(60*2); //Store cookie for 2 minutes
                userCookie.setPath("/");
                HttpServletResponse servletResponse =  response.getHttpServletResponse();
                servletResponse.addCookie(userCookie);

                new GetAccessCodeMSGraph().getCode(UUIDstate, servletResponse);
            }
            else if(pathParameters[1].equals(callbackPath)){
                new MSGraphCallback().processCallbackRequest(request, response);
            }
            else if(pathParameters[1].equals(logoutPath)){
                ISession session = getSessionFromRequest(request);
                new LogoutHandler().processLogoutRequest(session, response);
            }
            else {
                Core.getLogger("MSGraph").error("Unknown request path parameter");
                throw new ServletException("Unknown request path parameter");
            }

        }
        else{
            Core.getLogger("MSGraph").error("Missing request path parameter");
            throw new ServletException("Missing request path parameter");
        }



    }

    /**
     * This method will generate a java UUID
     * @return
     */
    public String createUUID(){
        return UUID.randomUUID().toString();
    }

}
