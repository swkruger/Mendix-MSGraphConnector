package msgraphconnector.actions.custom;

import com.mendix.core.Core;
import com.mendix.core.CoreException;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.systemwideinterfaces.core.ISession;
import communitycommons.XPath;
import system.proxies.Session;

import java.io.IOException;

/**
 * Created by skruger on 6/27/2017.
 */
public class SessionHelper {
    public static ISession getSession(IContext context, String SessionId) throws IOException {
        try {
            XPath<Session> xpath = XPath.create(context,Session.class)
                    .eq(Session.MemberNames.SessionId, SessionId);

            return (ISession) xpath.firstMendixObject();

        } catch (CoreException e) {
            Core.getLogger("MSGraph").error("Something went wrong while retrieving user session. \n"+ e);

        }
        return null;
    }

}
