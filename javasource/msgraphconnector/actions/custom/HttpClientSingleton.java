package msgraphconnector.actions.custom;


import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * Created by skruger on 6/27/2017.
 */
public class HttpClientSingleton {
    private static HttpClientSingleton httpClientSingleton = new HttpClientSingleton();
    private static CloseableHttpClient client;

    private HttpClientSingleton() {
        client = HttpClients.createMinimal();
    }

    /* Static 'instance' method */
    public static HttpClientSingleton getInstance() {
        return httpClientSingleton;
    }

    /* Other methods protected by singleton-ness */
    public static HttpClient getClient() {
        return client;
    }
}
