package msgraphconnector.actions.custom;

import com.mendix.core.Core;
import com.mendix.core.CoreException;
import com.mendix.systemwideinterfaces.core.IContext;
import msgraphconnector.proxies.Application;
import msgraphconnector.proxies.Permission;
import msgraphconnector.proxies.constants.Constants;
import stringutils.actions.URLEncode;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.StringJoiner;

/**
 * Created by skruger on 6/23/2017.
 */
class GetAccessCodeMSGraph {
    private final String OAUTHURI = Constants.getMicrosoftOnlineBaseAuthUrl()+"/authorize";

    protected void getCode(String UUIDstate, HttpServletResponse servletResponse) throws IOException,CoreException {
        IContext context = Core.createSystemContext();
        Application application = new MSGraphApplication().getApplication(context);

        List<Permission> permissions = application.getApplication_Permission();
        StringJoiner scope = new StringJoiner(" ");
        for (Permission permission: permissions) {
            scope.add(permission.getValue());
        }

        Core.getLogger("MSGraph").trace("Get token from MS Graph");
        StringBuilder oauthUrl = new StringBuilder()
                .append(OAUTHURI)
                .append("?client_id=")
                .append(application.getClientId()) // the client id from the api console registration
                .append("&response_type=code")
                .append("&redirect_uri=")
                .append(application.getRedirectUrl()) // the servlet that graph redirects to after authorization
                .append("&state="+UUIDstate);
                //.append("&access_type=online") // here we are asking to access to user's data while they are not signed in
                //.append("&approval_prompt=auto"); // this requires them to verify which account to use, if they are already signed in

        if (scope.length() > 0) {
        	String encodedScope = URLEncoder.encode(scope.toString(), "UTF-8");
            oauthUrl.append("&scope=" + encodedScope); // scope is the api permissions we are requesting

        }
        Core.getLogger("MSGraph").trace("Url used for google: \n"+oauthUrl.toString());
        servletResponse.sendRedirect(oauthUrl.toString());
    }


}
