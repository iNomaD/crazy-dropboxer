package fi.jyu.dropboxer.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fi.jyu.dropboxer.Config;
import fi.jyu.dropboxer.models.AccountInfo;
import fi.jyu.dropboxer.models.Token;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Denis on 12.09.2017.
 */

// client of dropbox
public class DropboxClient {

    private static volatile DropboxClient instance;

    // using Singleton pattern
    public static DropboxClient getInstance() {
        DropboxClient localInstance = instance;
        if (localInstance == null) {
            synchronized (DropboxClient.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DropboxClient();
                }
            }
        }
        return localInstance;
    }

    private DropboxClient() {

    }

    public Token getAccessToken(String codeStr, String redirectUri) throws URISyntaxException, IOException {
        Token token = null;
        String code = "" + codeStr; //code get from previous step
        String appKey = Config.appKey; //appKey get using previous step
        String appSecret = Config.appSecret; //appSecret get using previous step
        StringBuilder tokenUri = new StringBuilder("code=");
        tokenUri.append(URLEncoder.encode(code, "UTF-8"));
        tokenUri.append("&grant_type=");
        tokenUri.append(URLEncoder.encode("authorization_code", "UTF-8"));
        tokenUri.append("&client_id=");
        tokenUri.append(URLEncoder.encode(appKey, "UTF-8"));
        tokenUri.append("&client_secret=");
        tokenUri.append(URLEncoder.encode(appSecret, "UTF-8"));
        tokenUri.append("&redirect_uri=" + redirectUri.toString());
        URL url = new URL(Config.APIUrlToken);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        try {
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-Length", "" + tokenUri.toString().length());
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream());
            outputStreamWriter.write(tokenUri.toString());
            outputStreamWriter.flush();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            Gson gson = new GsonBuilder().create();
            token = gson.fromJson(response.toString(), Token.class);
        }
        finally {
            connection.disconnect();
            return token;
        }
    }

    public AccountInfo getAccountInfo(){
        /*
        try {
            String locale = null;
            Call<AccountInfo> call = dropboxApi.getAccountInfo(locale);
            Response<AccountInfo> response = call.execute();
            return response.body();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }*/
        return null;
    }

}
