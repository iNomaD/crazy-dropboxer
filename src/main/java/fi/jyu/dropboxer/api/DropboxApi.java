package fi.jyu.dropboxer.api;

import fi.jyu.dropboxer.models.AccountInfo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Denis on 12.09.2017.
 */

// uses retrofit2 library
public interface DropboxApi {

    @GET("/api/get")
    Call<AccountInfo> getAccountInfo(@Query("locale") String locale);
}
