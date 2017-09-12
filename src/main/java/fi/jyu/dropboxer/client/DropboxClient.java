package fi.jyu.dropboxer.client;

import fi.jyu.dropboxer.api.DropboxApi;
import fi.jyu.dropboxer.models.AccountInfo;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

/**
 * Created by Denis on 12.09.2017.
 */

// client of dropbox
public class DropboxClient {

    private static String APIUrl = "https://api.dropboxapi.com/";
    private static volatile DropboxClient instance;

    private Retrofit retrofit;
    private DropboxApi dropboxApi = null;

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

    private DropboxClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        dropboxApi = retrofit.create(DropboxApi.class);
    }

    public AccountInfo getAccountInfo(){
        try {
            String locale = null;
            Call<AccountInfo> call = dropboxApi.getAccountInfo(locale);
            Response<AccountInfo> response = call.execute();
            return response.body();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
