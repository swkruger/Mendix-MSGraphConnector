package msgraphconnector.actions.custom;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by skruger on 6/26/2017.
 */
class ExecuteHttpRequest {
    protected String execute(HttpRequestBase request) throws IOException {
        HttpClient httpClient = HttpClientSingleton.getInstance().getClient();
        HttpResponse response = httpClient.execute(request);

        HttpEntity entity = response.getEntity();
        String body = EntityUtils.toString(entity);

        if (response.getStatusLine().getStatusCode() != 200) {
            //httpClient.getConnectionManager().shutdown();
            throw new RuntimeException("Expected 200 but got " + response.getStatusLine().getStatusCode() + ", with body " + body);
        }
        //httpClient.getConnectionManager().shutdown();
        EntityUtils.consume(entity);
        return body;
    }

}
