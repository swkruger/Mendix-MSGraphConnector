package msgraphconnector.actions.custom;

import com.mendix.core.Core;
import com.mendix.core.CoreException;
import com.mendix.systemwideinterfaces.core.IContext;
import msgraphconnector.proxies.MSGraphAuthLog;
import msgraphconnector.proxies.MSGraphAuthMessage;

/**
 * Created by skruger on 6/26/2017.
 */
public class LogRecordHandler {

    protected void createLogRecord(String msgData, MSGraphAuthMessage msgType) throws CoreException {
        try {
            IContext context = Core.createSystemContext();
            MSGraphAuthLog logMessage = new MSGraphAuthLog(context);
            logMessage.setIncomingDataDetail(msgData);
            logMessage.setMessage(msgType);
            logMessage.commit();
        } catch (Exception e) {
            Core.getLogger("MSGraph").info("An error occured when creating the log record. /n"+e.getStackTrace());
            e.printStackTrace();
        }
    }
}

