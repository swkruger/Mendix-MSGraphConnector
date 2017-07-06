package msgraphconnector.actions.custom;

import com.mendix.core.Core;
import com.mendix.core.CoreException;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.systemwideinterfaces.core.IMendixObject;
import communitycommons.XPath;
import msgraphconnector.proxies.AuthToken;
import org.json.simple.JSONObject;
import system.proxies.User;

import java.util.Date;
import java.util.List;

/**
 *  Created by skruger on 6/28/2017.
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
public class AuthTokenHandler {

    void createAuthTokenRecordFromJson(JSONObject tokenJson, User user) throws CoreException {
        try {
            IContext context = Core.createSystemContext();
            //First delete all existing tokens.
            deleteUserTokens(context,user);

            AuthToken authToken = new AuthToken(context);
            authToken.setToken_Type(tokenJson.get("token_type").toString());
            authToken.setScope(tokenJson.get("scope").toString());
            authToken.setExpires_In(((Long)tokenJson.get("expires_in")).intValue());
            authToken.setExt_Expires_In(((Long)tokenJson.get("ext_expires_in")).intValue());
            authToken.setAccess_Token(tokenJson.get("access_token").toString());
            authToken.setRefresh_Token(tokenJson.get("refresh_token").toString());
            authToken.setCreated_Date(new Date());
            authToken.setExpiry_Date(new Date(System.currentTimeMillis() + ((Long) tokenJson.get("expires_in")).intValue() * 1000));
            authToken.setAuthToken_User(user);
            authToken.commit();
        } catch (Exception e) {
            Core.getLogger("MSGraph").error("An error occured when creating the AuthToken record. /n"+e.getStackTrace());
            e.printStackTrace();
        }
    }

    public void deleteUserTokens(IContext context, User user) {
        try {
            XPath<AuthToken> xpath = XPath.create(context,AuthToken.class)
                    .eq(AuthToken.MemberNames.AuthToken_User, user);

            List<IMendixObject> tokens = xpath.allMendixObjects();
            Core.delete(context,tokens);

        } catch (CoreException e) {
            Core.getLogger("MSGraph").error("Something went wrong while deleting user tokens. \n"+ e);

        }

    }

}
