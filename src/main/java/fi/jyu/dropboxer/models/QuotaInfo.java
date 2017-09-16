package fi.jyu.dropboxer.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QuotaInfo {

    @SerializedName("shared")
    @Expose
    private Long shared;
    @SerializedName("quota")
    @Expose
    private Long quota;
    @SerializedName("normal")
    @Expose
    private Long normal;

    public Long getShared() {
        return shared;
    }

    public void setShared(Long shared) {
        this.shared = shared;
    }

    public Long getQuota() {
        return quota;
    }

    public void setQuota(Long quota) {
        this.quota = quota;
    }

    public Long getNormal() {
        return normal;
    }

    public void setNormal(Long normal) {
        this.normal = normal;
    }

}
