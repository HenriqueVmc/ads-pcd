package projmoodleapi;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.xml.parsers.ParserConfigurationException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xml.sax.SAXException;

public class CarregadorTarefas {

    public static void main(String[] args) throws ProtocolException, IOException, SAXException, ParserConfigurationException {

        // / NEED TO BE CHANGED
        String token = "dd5f2efeeb50751a223490bc1b97a0ed";
        String domainName = "https://moodle.ifsc.edu.br";

        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[]{
            new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return new java.security.cert.X509Certificate[0];
                }

                public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {}
                public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {}
            }
        };

        // Install the all-trusting trust manager
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (GeneralSecurityException e) {  }

        // / REST RETURNED VALUES FORMAT
        String restformat = "json"; // Also possible in Moodle 2.2 and later:
        // 'json'
        // Setting it to 'json' will fail all calls
        // on earlier Moodle version
        restformat = (restformat.equals("json")) ? "&moodlewsrestformat=" + restformat : "";

        // / PARAMETERS - NEED TO BE CHANGED IF YOU CALL A DIFFERENT FUNCTION
        String functionName = "mod_assign_get_assignments";

        // / REST CALL
        // Send request
        String serverurl = domainName + "/webservice/rest/server.php"
                + "?wstoken=" + token + "&wsfunction=" + functionName
                + restformat;
        HttpsURLConnection con = (HttpsURLConnection) new URL(serverurl).openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        con.setRequestProperty("Content-Language", "en-US");
        con.setDoOutput(true);
        con.setUseCaches(false);
        con.setDoInput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.flush();
        wr.close();

        // Get Response
        InputStream is = con.getInputStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
        String line;
        StringBuilder response = new StringBuilder();

        while ((line = rd.readLine()) != null) {
            response.append(line);
        }

        rd.close();

        JSONObject obj = new JSONObject(response.toString());

        JSONArray arr = obj.getJSONArray("courses");
        for (int i = 0; i < arr.length(); i++) {
            System.out.println(arr.getJSONObject(i).getString("fullname"));
            JSONArray arr2 = arr.getJSONObject(i).getJSONArray("assignments");

            for (int j = 0; j < arr2.length(); j++) {
                System.out.println("\t\t---------" + arr2.getJSONObject(j).getString("name"));
                Date d = new Date(arr2.getJSONObject(j).getLong("duedate") * 1000);
                System.out.println(d.toString());
            }
        }
    }
}
