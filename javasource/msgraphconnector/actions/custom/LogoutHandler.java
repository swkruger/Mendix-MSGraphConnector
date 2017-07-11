package msgraphconnector.actions.custom;

import com.mendix.core.Core;
import com.mendix.m2ee.api.IMxRuntimeResponse;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.systemwideinterfaces.core.ISession;
import com.mendix.systemwideinterfaces.core.IUser;
import msgraphconnector.proxies.constants.Constants;
import org.apache.commons.io.IOUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by skruger on 6/28/2017.
 */
public class LogoutHandler  {
    private final String MSGRAPHDIR = "MSGraph";
    private final String LOGOUTHTML = Constants.getLogoutPage();



    public LogoutHandler() {
    }

    public void processLogoutRequest(ISession session,IMxRuntimeResponse response) throws Exception
    {
        IContext context = Core.createSystemContext();

        if (session != null){
            IUser user = session.getUser(context);
            new AuthTokenHandler().deleteUserTokens(context, user.getMendixObject());
            Core.logout(session);
        }

        final String LOGOUTPAGELOCATION = "/" + MSGRAPHDIR + "/" +LOGOUTHTML;
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(Core.getConfiguration().getResourcesPath()+LOGOUTPAGELOCATION)))
        {
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                stringBuilder.append(sCurrentLine);
            }
            String logoutPage = stringBuilder.toString();
            OutputStream outputStream = response.getOutputStream();
            IOUtils.write(logoutPage, outputStream);
            IOUtils.closeQuietly(outputStream);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }



}
