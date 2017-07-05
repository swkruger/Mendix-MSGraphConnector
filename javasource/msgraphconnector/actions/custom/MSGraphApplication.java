package msgraphconnector.actions.custom;

import com.mendix.core.Core;
import com.mendix.core.CoreException;
import com.mendix.core.CoreRuntimeException;
import com.mendix.systemwideinterfaces.core.IContext;
import communitycommons.XPath;
import msgraphconnector.proxies.Application;

import java.io.IOException;

/**
 * Created by skruger on 6/23/2017.
 */
public class MSGraphApplication {

    protected Application getApplication(IContext context) throws IOException {
        try {
            Application application = XPath.create(context, Application.class).first();
            if (application == null) {
                throw new CoreRuntimeException(
                        "Missing MS Graph Application configuration, please contact the application administrator");
            }
            return application;
        } catch (CoreException e) {
            Core.getLogger("MSGraph").error("Something went wrong while retrieving the MS Graph Application configuration. \n"+ e);

        }
        return null;

    }
}
