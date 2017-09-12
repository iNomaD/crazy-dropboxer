package fi.jyu.dropboxer.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QuotaInfo {

    @SerializedName("shared")
    @Expose
    private Integer shared;
    @SerializedName("quota")
    @Expose
    private Integer quota;
    @SerializedName("normal")
    @Expose
    private Integer normal;

    public Integer getShared() {
        return shared;
    }

    public void setShared(Integer shared) {
        this.shared = shared;
    }

    public Integer getQuota() {
        return quota;
    }

    public void setQuota(Integer quota) {
        this.quota = quota;
    }

    public Integer getNormal() {
        return normal;
    }

    public void setNormal(Integer normal) {
        this.normal = normal;
    }

}
