package projmoodleapi;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.security.cert.X509Certificate;

public class MoodleLoginTest {

    public static void main(String[] args) throws MalformedURLException, IOException {

        Scanner s = new Scanner(System.in);
        String domainName = "https://moodle.ifsc.edu.br";
        String serverurl = domainName + "/login/token.php?username=%s&password=%s&service=moodle_mobile_app";

        System.out.println("Digite o usuario:");
        String usuario = s.nextLine();

        System.out.println("Digite a senha:");
        String senha = s.nextLine();

        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[]{
            new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return new java.security.cert.X509Certificate[0];
                }

                public void checkClientTrusted(
                        java.security.cert.X509Certificate[] certs, String authType) {
                }

                public void checkServerTrusted(
                        java.security.cert.X509Certificate[] certs, String authType) {
                }
            }
        };

        // Install the all-trusting trust manager
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            
        } catch (GeneralSecurityException e) {}

        serverurl = String.format(serverurl, usuario, senha);

        HttpsURLConnection con = (HttpsURLConnection) new URL(serverurl).openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        con.setRequestProperty("Content-Language", "en-US");

        con.setDoOutput(true);
        con.setUseCaches(false);
        con.setDoInput(true);

        //Get Response
        InputStream is = con.getInputStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
        String line = rd.readLine();

        System.out.println(line);
        String code = line.substring(2, 7);
        if (code.equals("token")) {
            String token = line.substring(10, line.length() - 2);
            System.out.println("Usuario autenticado com sucesso!");
            System.out.println("Token: " + token);
        } else {
            System.out.println("----->Login falhou");
        }

    }
}
