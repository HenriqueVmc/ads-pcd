/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projmoodleapi;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.xml.parsers.ParserConfigurationException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.xml.sax.SAXException;

/**
 *
 * @author aluno
 */
public class VefificarStatusAtividades {
    
            // / NEED TO BE CHANGED
    private static String token = "dd5f2efeeb50751a223490bc1b97a0ed";
    private static String domainName = "https://moodle.ifsc.edu.br";
        
    public static void main(String[] args) throws ProtocolException, IOException, SAXException, ParserConfigurationException {    

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
                
        // Send request
        String url = domainName + "/webservice/rest/server.php" + "?wstoken=" + token + "&wsfunction=mod_assign_get_assignments";
        String url2 = domainName + "/webservice/rest/server.php" + "?wstoken=" + token + "&wsfunction=mod_assign_get_submission_status&assignid=";
        
        String response = get(url);                
        JSONObject obj = new JSONObject(response);        
        JSONArray arrCouses = obj.getJSONArray("courses");
        
        for (int i = 0; i < arrCouses.length(); i++) {
            System.out.println("\n"+arrCouses.getJSONObject(i).getString("fullname"));
            JSONArray arrTasks = arrCouses.getJSONObject(i).getJSONArray("assignments");

            for (int j = 0; j < arrTasks.length(); j++) {
                System.out.println("\t\t---------" + arrTasks.getJSONObject(j).getString("name"));
                            
                String responseStatus = get(url2 + arrTasks.getJSONObject(j).getInt("id"));                
                
                JSONObject objStatus = new JSONObject(responseStatus);        
                String status = objStatus.getJSONObject("lastattempt").getJSONObject("submission").getString("status");
                
                System.out.println("\t\t---------Status de envio: "+status);                
                
                Date d = new Date(arrTasks.getJSONObject(j).getLong("duedate") * 1000);
                System.out.println("\n"+d.toString());
            }
        }
    }
    
    private static String get(String url){
        
        StringBuilder response = new StringBuilder();
        
        try {
            HttpsURLConnection con = (HttpsURLConnection) new URL(url + "&moodlewsrestformat=json").openConnection();
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
            
            while ((line = rd.readLine()) != null) {
                response.append(line);
            }
              
        } catch (Exception ex) {
            Logger.getLogger(VefificarStatusAtividades.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return response.toString();
    }
}
