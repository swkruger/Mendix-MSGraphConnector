package msgraphconnector.actions.custom;

import com.mendix.core.Core;
import com.mendix.m2ee.api.IMxRuntimeResponse;
import org.apache.commons.io.IOUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by skruger on 6/26/2017.
 */
public class ErrorHandler {
    protected void processErrorHandler(IMxRuntimeResponse response,
                                       String resourceDir, String fileName) throws IOException {

        final String PAGELOCATION = "/"+resourceDir+"/"+fileName;
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(Core.getConfiguration().getResourcesPath()+PAGELOCATION)))
        {
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                stringBuilder.append(sCurrentLine);
            }
            String logoutPage = stringBuilder.toString();
            OutputStream outputStream = response.getOutputStream();
            IOUtils.write(logoutPage, outputStream);
            IOUtils.closeQuietly(outputStream);
            response.setStatus(IMxRuntimeResponse.OK);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        /*
        InputStream is = null;
        try {
            is = new FileInputStream(new File(new File(Core.getConfiguration()
                    .getResourcesPath().getPath(), resourceDir), fileName));
            servletResponse.setContentType("text/html");
            byte[] bytes = new byte[is.available()];
            is.read(bytes);
            servletResponse.setContentLength(bytes.length);

            PrintWriter writer = servletResponse.getWriter();
            writer.print(new String(bytes));
            writer.flush();
            writer.close();
            is.close();
        } catch (Exception e) {
            if (is != null) {
                is.close();
            }
            Core.getLogger("MSGraphCallback").error(
                    "File: " + fileName + " not found in the resources/"
                            + resourceDir + " directory!");
            e.printStackTrace();
        }
        */
    }

}
