package msgraphconnector.actions.custom;

import com.mendix.core.Core;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;

import java.io.IOException;

/**
 * Created by skruger on 6/26/2017.
 */
public class GetHttpRequest {
    /*
 *  makes a GET request to url and returns body as a string
 */
    protected String get(String url, String authToken) throws ClientProtocolException, IOException {
        Core.getLogger("MSGraph").trace("URI is: "+ url);
        HttpGet get = new HttpGet(url);
        get.setHeader("Authorization",authToken);
        return new ExecuteHttpRequest().execute(get);

    }
}
