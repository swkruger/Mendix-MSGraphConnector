package msgraphconnector.actions.custom;

import com.google.common.collect.ImmutableMap;
import com.mendix.core.Core;
import com.mendix.core.CoreException;
import com.mendix.systemwideinterfaces.core.IContext;
import msgraphconnector.proxies.Application;
import msgraphconnector.proxies.Permission;
import msgraphconnector.proxies.constants.Constants;
import org.apache.http.client.ClientProtocolException;

import java.io.IOException;
import java.util.List;
import java.util.StringJoiner;

/**
 * Created by skruger on 6/26/2017.
 */
public class GetAccessTokenMSGraph extends GetAccessToken {
    private final String OAUTHURI = Constants.getMicrosoftOnlineBaseAuthUrl()+"/token";

    public GetAccessTokenMSGraph(String code) {
        super(code);
    }

    protected String getResult() throws ClientProtocolException, IOException, CoreException {
        Core.getLogger("MSGraph").debug("Get access token from MS Graph");

        IContext context = Core.createSystemContext();
        Application application = new MSGraphApplication().getApplication(context);

        List<Permission> permissions = application.getApplication_Permission();
        StringJoiner scope = new StringJoiner(" ");
        for (Permission permission: permissions) {
            scope.add(permission.getValue());
        }

        ImmutableMap<String, String> map = ImmutableMap.<String,String>builder()
                .put("code", code)
                .put("client_id", application.getClientId())
                .put("client_secret", application.getSecret())
                .put("redirect_uri", application.getRedirectUrl())
                .put("scope", scope.toString())
                .put("grant_type", "authorization_code").build();

        return PostHttpRequest.post(OAUTHURI, map);
    }
}
