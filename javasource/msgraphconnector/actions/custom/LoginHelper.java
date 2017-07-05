package msgraphconnector.actions.custom;

import com.mendix.core.Core;
import com.mendix.core.CoreException;
import com.mendix.m2ee.api.IMxRuntimeRequest;
import com.mendix.m2ee.api.IMxRuntimeResponse;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.systemwideinterfaces.core.ISession;
import com.mendix.systemwideinterfaces.core.IUser;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by skruger on 6/27/2017.
 */
public class LoginHelper {
    private static final String XAS_SESSION_ID = "XASSESSIONID";
    private static final String XAS_ID = "XASID";
    private static final String OriginURI = "originURI";
    private static final String OriginURIValue = "index.html";

    private static final String INDEX_PAGE_CONSTANT = "MSGraphConnector.IndexPage";

    public static final int SECONDS_PER_YEAR = 60 * 60 * 24 * 365;

    /**
     * This method can be used to initialize an XAS session when the username is known and verified.
     *
     * @param request
     * @param response
     * @param context
     * @param user
     */
    protected static void createSession(IMxRuntimeRequest request, IMxRuntimeResponse response, IContext context, IUser user) throws IOException {
        String cookie = request.getCookie(XAS_SESSION_ID);
        if (cookie == null || cookie.isEmpty()) {
            cookie = null;
        }

        ISession session = SessionHelper.getSession(context,cookie);
        /*
        ISession session = null;
        for (ISession activeSession : Core.getActiveSessions()) {
            if (activeSession.getId().toString().equals(cookie)) {
                Core.getLogger("MSGraph").debug("Active session found for user");
                session = activeSession;
                break;
            }
        }
        */

        try {
            session = Core.initializeSession(user, session != null ? session.getId().toString() : null);
        } catch (CoreException e) {
            Core.getLogger("MSGraph").error("Failed to initialize new Mendix session " + e);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            throw new RuntimeException("Sign in failure, unable to create new session");
        }

        // no existing session found, perform login using the provided username
        Core.getLogger("MSGraph").debug("Setting Mendix runtime cookies (XASSESSIONID, XASID and originURI)");
        // create cookies and redirect: String key, String value, String path, String domain, int expiry
        response.addCookie(XAS_SESSION_ID, session.getId().toString(), "/", "", -1);
        response.addCookie(XAS_ID, "0." + Core.getXASId(), "/", "", -1);
        response.addCookie(OriginURI, OriginURIValue, "/", "", SECONDS_PER_YEAR);

        String indexconfig = (String)Core.getConfiguration().getConstantValue(INDEX_PAGE_CONSTANT);
        String indexpage = "/index.html";
        if (StringUtils.isNotBlank(indexconfig)) {
            indexpage = StringUtils.trim(indexconfig);
        } else {
            Core.getLogger("MSGraph").warn("Missing constant value "+ INDEX_PAGE_CONSTANT);
        }

        redirect(response.getHttpServletResponse(), indexpage);
    }

    /**
     * Sends a redirect (the redirect method provided by the class is less reliable).
     *
     * @param response
     * @param path
     */
    protected static void redirect(HttpServletResponse response, String path) throws IOException {
        Core.getLogger("MSGraph").debug("Redirecting to location: "+ path);
        response.setStatus(HttpServletResponse.SC_SEE_OTHER);
        response.sendRedirect(path);

        //response.addHeader("location", path);
    }
}