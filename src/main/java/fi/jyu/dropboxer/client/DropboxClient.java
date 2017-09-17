package fi.jyu.dropboxer.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fi.jyu.dropboxer.Config;
import fi.jyu.dropboxer.models.*;
import org.apache.commons.io.IOUtils;

import java.io.*;
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
            System.out.println(response.toString());
            token = gson.fromJson(response.toString(), Token.class);
        }
        finally {
            connection.disconnect();
            return token;
        }
    }

    public AccountInfo getAccountInfo(String tokenStr) throws URISyntaxException, IOException {
        AccountInfo accountInfo = null;
        String access_token = ""+tokenStr;
        StringBuilder accountInfoUri=new StringBuilder(Config.APIUrlAccountInfo);
        accountInfoUri.append("?access_token=");
        accountInfoUri.append(URLEncoder.encode(access_token,"UTF-8"));
        URL url=new URL(accountInfoUri.toString());
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        try {
            connection.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            Gson gson = new GsonBuilder().create();
            System.out.println(response.toString());
            accountInfo = gson.fromJson(response.toString(), AccountInfo.class);
        } finally {
            connection.disconnect();
        }
        return accountInfo;
    }

    public String uploadFile(String token, InputStream file, String name) throws URISyntaxException,IOException{
        String access_token = ""+token;
        byte[] data = IOUtils.toByteArray(file);
        StringBuilder uploadInfoUri=new StringBuilder(Config.APIUrlFilesPut+Config.dropboxDir+"/"+name);
        uploadInfoUri.append("?access_token=");
        uploadInfoUri.append(URLEncoder.encode(access_token,"UTF-8"));
        URL url=new URL(uploadInfoUri.toString());
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        System.out.println(uploadInfoUri.toString());
        try {
            connection.setDoOutput(true);
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Type", "mime/type");
            connection.setRequestProperty("Content-Length", String.valueOf(data.length));
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(data);
            outputStream.flush();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            // in the same way (as in the previous example) read response from the BufferReader …
        } finally {
            connection.disconnect();
        }
        return Config.dropboxDir;
    }

    public SharesInfo createShareableUrl(String token,String cPath) throws  URISyntaxException, IOException{
        SharesInfo sharesInfo = null;
        String access_token = ""+token;
        StringBuilder sharesUri=new StringBuilder(Config.APIUrlShares + cPath);
        sharesUri.append("?access_token=");
        sharesUri.append(URLEncoder.encode(access_token,"UTF-8"));
        URL url=new URL(sharesUri.toString());
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        try {
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            Gson gson = new GsonBuilder().create();
            System.out.println(response.toString());
            sharesInfo = gson.fromJson(response.toString(), SharesInfo.class);

        } finally {
            connection.disconnect();
        }
        return  sharesInfo;
    }

    public Metadata getMetadata(String tokenStr, String path) throws URISyntaxException, IOException {
        Metadata metadata = null;
        String access_token = ""+tokenStr;
        StringBuilder metadataUri=new StringBuilder(Config.APIUrlMetadata+path);
        metadataUri.append("?access_token=");
        metadataUri.append(URLEncoder.encode(access_token,"UTF-8"));
        URL url=new URL(metadataUri.toString());
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        try {
            connection.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            Gson gson = new GsonBuilder().create();
            System.out.println(response.toString());
            metadata = gson.fromJson(response.toString(), Metadata.class);
        } finally {
            connection.disconnect();
        }
        return metadata;
    }

    public Media getMedia(String tokenStr, String path) throws URISyntaxException, IOException {
        Media media = null;
        String access_token = ""+tokenStr;
        StringBuilder mediaUri=new StringBuilder(Config.APIUrlMedia+path);
        mediaUri.append("?access_token=");
        mediaUri.append(URLEncoder.encode(access_token,"UTF-8"));
        URL url=new URL(mediaUri.toString());
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        try {
            connection.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            Gson gson = new GsonBuilder().create();
            System.out.println(response.toString());
            media = gson.fromJson(response.toString(), Media.class);
        } finally {
            connection.disconnect();
        }
        return media;
    }
}
