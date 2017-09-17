package fi.jyu.dropboxer.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SharesInfo {
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("expires")
    @Expose
    private String expires;
    @SerializedName("visibility")
    @Expose
    private String visibility;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getExpires() {
        return expires;
    }

    public void setExpires(String expires) {
        this.expires = expires;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }
}
